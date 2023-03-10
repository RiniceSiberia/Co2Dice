package org.co2dice.mirai.bean.effect.prototype

import dev.lcy0x1.util.type.Either
import org.co2dice.mirai.bean.effect.utils.Situation
import org.co2dice.mirai.bean.dice.*
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.card.prototype.Card
import org.co2dice.mirai.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.bean.counter.chessmanToken.Dexterity
import org.co2dice.mirai.bean.dice.diceList.DiceList
import org.co2dice.mirai.bean.dice.diceList.MutableDiceList
import org.co2dice.mirai.bean.dice.single.AttributeFixDice
import org.co2dice.mirai.bean.dice.single.ConstantDice
import org.co2dice.mirai.bean.dice.single.NormalDice
import org.co2dice.mirai.bean.effect.EffectTargets
import org.co2dice.mirai.bean.effect.utils.EffectTargetSelectors
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 * 一个主动技能需要以下东西:
 * 输入的传参的格式检查器
 * 需要指定的目标，传参是通过typeCheck的出来的Map
 * 技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
 * 需要消耗的cost(可能要和传参产生互动起来)
 * 技能正常生效需要的条件(比如说攻击怪兽需要怪兽在场上)
 * 检定函数，默认为直接成功
 * 效果具体处理时使用的函数
 *
 **/
open class EffectActive(
    holder: Card,
    val typeCheck : Map<String,Either<EffectTargetSelectors,Any>>,
    //输入的传参的格式检查器，用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    //left代表这个参数是选择目标，right代表这个参数是非目标相关,比如说丢弃x张手牌这种自定义参数
    //String代表这个输入的参数意味着什么,距离:effectA( a = 青眼白龙 1 , b = 玩家b, c = 3)，其中c是发动技能时支付的力量值
    // 这种情况下typeCheck里应该存储("a" to Selectors.MONSTER_SELECTOR , "b" to PLAYER_SELECTOR c to Int)
    val targetFunc : (Map<String,Any>) -> EffectTargets,
    //需要指定的目标，一个通过typeCheck验证过的输入参数，经过targetFunc调用后便会返回一个封装的EffectTargets。
    //如果某个map.entry是选取的目标，那么他应该是effectTargetSelectors
    //入参和返回值都会存入situation
    val launchConditions : (Situation) -> Boolean,
    //技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
//    val cost : (Situation,Map<String,Any>) -> Boolean,
//    //传的是传参和场景，里面要catch throw,找不到对象就是false
//cost还在写
    val realizeConditions : (Situation) -> Boolean,
    //实现需要的条件
    val check : (Situation) -> DiceList= check@{ situation ->
        val h = situation.chessman
        //获取技能源的持有Chess
        if (h != null){
            val table = h.attributeInstanceTable
            //这里默认值是获取敏捷
            val dex = table.getValue(EliteAttribute.DEX)
            val burnValue:Int = skillParam[Dexterity.id] ?:0
            //额外支付的敏捷值
            if (dex != null){
                return@check MutableDiceList(
                    listOf(NormalDice(20)),
                    listOf(ConstantDice(burnValue)),
                    AttributeFixDice(listOf(Dexterity))
                )
                //固定值，增益，以及变化的属性值
            }
        }

        return@check DiceList(0)
    },
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定

    val react:(Situation) -> DiceList = react@{ situation ->
        if (situation.target is ChessmanInstance<*>){
            val tokens = situation.target.tokenPool
            //这里默认值是获取敏捷
            val fuller = tokens.getPointFuller(Dexterity)
            if (fuller != null){
                return@react DiceList(
                    listOf(ConstantDice(10)),
                    AttributeFixDice(listOf(Dexterity))
                        .getDiceList(situation.target).diceList
                )
            }
        }
        return@react DiceList(0)
    },
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗

    override val operation : (Situation) -> Boolean,
    //造成的特效。

) : Effect(holder) {


}