package org.co2dice.mirai.core.bean.effect.prototype

import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.bean.effect.utils.EffectTargetSelectors
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 共同的主动技能接口，只能在自己的回合使用。
 * 一个主动技能需要以下东西:
 * 输入的传参的格式检查器(params)
// * 需要指定的目标，传参是通过typeCheck的出来的Map
 * 技能的对象查找器
 * 技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
 * 需要消耗的cost(可能要和传参产生互动起来)
 * 技能正常生效需要的条件(比如说攻击怪兽需要怪兽在场上)
 * 检定函数，默认为直接成功
 * 效果具体处理时使用的函数
 *
 **/
interface ActiveEffect : Effect{


    val paramTypes : Map<String, KClass<*>>
    //输入的传参的格式检查器。用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    //left代表这个参数是选择目标，right代表这个参数是非目标相关,比如说丢弃x张手牌这种自定义参数
    //String代表这个输入的参数意味着什么,距离:effectA( a = 青眼白龙 1 , b = 玩家b, c = 3)，其中c是发动技能时支付的力量值
    // 这种情况下typeCheck里应该存储("a" to Selectors.MONSTER_SELECTOR , "b" to PLAYER_SELECTOR c to Int)

    val targetFunction : Map<String,EffectTargetSelectors>
    //发动时选择目标，与其相关逻辑。传入一个key，使用value对应的取对象方法，即可获取对应的目标。
    //目标会作为Param的一部分传入到effectFunction中

    val cost : AstTree<Boolean>
    //传的是传参和场景。使用等于处理cost,返回空代表cost不满足条件



}