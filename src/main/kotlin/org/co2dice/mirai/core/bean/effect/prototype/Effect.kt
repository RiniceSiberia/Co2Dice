package org.co2dice.mirai.core.bean.effect.prototype

import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.cost.Costs
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 * 效果根据所在区域和所在卡的不同而分类，比如从手牌发动的效果和墓地发动的效果就不是一类效果。
 **/
interface Effect {

    val targetFunction : AstTree<EffectTargets>
    //发动时选择目标，与其相关逻辑。传入一个key，使用value对应的取对象方法，即可获取对应的目标。
    //目标会作为Param的一部分传入到effectFunction中

    val cost : AstTree<Costs>
    //传的是传参和场景。使用等于处理cost,返回空代表cost不满足条件

    val operation: AstTree<String>

    val check : AstTree<Int>
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定

    val react: AstTree<Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗
    //效果本身
    val launchConditions : AstTree<Boolean>
    //发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)

    fun preActivationParamCheck(): Map<String, KClass<*>> {
        //获得所有的参数类型,用于检查参数是否合法,效果能否发动
        val param = mutableMapOf<String,KClass<*>>()
        val targetParam = targetFunction.getParamsNoSituation()
        val launchConditionParam = launchConditions.getParamsNoSituation()
        val costParam = cost.getParamsNoSituation()
        val checkParam = check.getParamsNoSituation()
        val reactParam = react.getParamsNoSituation()
        val operationParam = operation.getParamsNoSituation()
        param.putAll(targetParam)
        param.putAll(launchConditionParam)
        param.putAll(costParam)
        param.putAll(checkParam)
        param.putAll(reactParam)
        param.putAll(operationParam)
        //如果有重复的参数名，但是类型不一致，就会抛出异常
        if (param.size != targetParam.size + launchConditionParam.size + costParam.size + checkParam.size + reactParam.size + operationParam.size)
            throw Exception("param name conflict")
        return param
    }
    //使用效果需要的传参，以及其对应的类型
    //输入的传参的格式检查器。用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    //left代表这个参数是选择目标，right代表这个参数是非目标相关,比如说丢弃x张手牌这种自定义参数
    //String代表这个输入的参数意味着什么,距离:effectA( a = 青眼白龙 1 , b = 玩家b, c = 3)，其中c是发动技能时支付的力量值
    // 这种情况下typeCheck里应该存储("a" to Selectors.MONSTER_SELECTOR , "b" to PLAYER_SELECTOR c to Int)




}

fun <E : Effect> E.toEntry() : EffectEntry<E>{
    return EffectEntry(this)
}