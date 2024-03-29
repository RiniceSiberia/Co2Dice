package org.co2dice.mirai.core.bean.effect.activated_ability.prototype

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.effect.module.cost.CostPackage
import org.co2dice.mirai.core.bean.effect.module.target.TargetSelectorPackage

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-06-21:01
 * {@code @Message:} 启动式效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 * 效果根据所在区域和所在卡的不同而分类，比如从手牌发动的效果和墓地发动的效果就不是一类效果。
 * 发动不会消耗自身
 **/
@Serializable
sealed class ActivatedAbility : PrototypeStructure {
    abstract val launchConditions : AstTree
    //<Boolean>
    //发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)

    abstract val target : TargetSelectorPackage
    //<EffectTargets>
    //发动时选择目标，与其相关逻辑。传入一个key，使用value对应的取对象方法，即可获取对应的目标。
    //目标会作为Param的一部分传入到effectFunction中

    abstract val cost : CostPackage
    //<Cost>
    //传的是传参和场景。使用等于处理cost,返回空代表cost不满足条件

    abstract val operation: AstTree
    //<String>

    abstract val actValue : AstTree
    //<Intelligence>
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定

    abstract val reactValue: AstTree
    //<Intelligence>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗
    //效果本身


    //使用效果需要的传参，以及其对应的类型
    //输入的传参的格式检查器。用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    //left代表这个参数是选择目标，right代表这个参数是非目标相关,比如说丢弃x张手牌这种自定义参数
    //String代表这个输入的参数意味着什么,距离:effectA( a = 青眼白龙 1 , b = 玩家b, c = 3)，其中c是发动技能时支付的力量值
    // 这种情况下typeCheck里应该存储("a" to Selectors.MONSTER_SELECTOR , "b" to PLAYER_SELECTOR c to Intelligence)
}

