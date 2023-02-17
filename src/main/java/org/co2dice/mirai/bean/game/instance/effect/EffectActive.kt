package org.co2dice.mirai.bean.game.instance.effect

import org.co2dice.mirai.bean.game.Damage
import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.card.Situation
import org.co2dice.mirai.bean.dice.*
import org.co2dice.mirai.bean.game.instance.api.EffectAPI
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import org.co2dice.mirai.bean.tokens.Token
import org.co2dice.mirai.bean.tokens.characterToken.Constitution
import org.co2dice.mirai.bean.tokens.characterToken.Dexterity
import java.util.*
import java.util.stream.Collectors

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 **/
abstract class EffectActive(holder: CardsInstance) : Effect(holder) {
    // 输入的传参,用来自定义一些卡的效果。
    // 比如输入damage key，就是定义殴打的伤害。加入heal key，就是定义治疗的回复量。加入coldDown，就是定义技能的冷却时间。加入aim，就是定义技能的命中率，加入range，就是定义技能的射程。
    // 多个位置的技能对应不同的输入,可以技能带来的混乱值和秩序值的变化
    abstract val skillParam:MutableMap<String,Int>

    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定
    var check:Function2<Scene, EffectActive,DiceList> = check@{ _, skill ->
        val h = skill.getRelyCardHolder()
        if (h != null){
            val tokens = h.tokens
            //这里默认值是获取敏捷
            val fuller = tokens.getPointFuller(Dexterity)
            val burnValue:Int = skillParam[Dexterity.id] ?:0
            //额外支付的敏捷值
            if (fuller != null){
                return@check MutableDiceList(
                    listOf(NormalDice(20)),
                    listOf(ConstantDice(burnValue)),
                    AttributeFixDice(listOf(Dexterity)))
                //固定值，增益，以及变化的属性值
            }
        }

        return@check DiceList(ConstantDice(0))
    }
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值

    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗
    var react:Function3<Scene, EffectActive, CardsInstance, DiceList> = react@{ _, _, target ->
        if (target is CharacterCard){
            val tokens = target.tokens
            //这里默认值是获取敏捷
            val fuller = tokens.getPointFuller(Dexterity)
            if (fuller != null){
                return@react DiceList(
                    listOf(ConstantDice(10)),
                    AttributeFixDice(listOf(Dexterity)).getDiceList(target).diceList)
            }
        }
        return@react DiceList(ConstantDice(0))
    }
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值

    override var function: (Scene, CardsInstance, CharacterCard, EffectAPI<Scene, CardsInstance, CharacterCard>) -> Boolean = effect@{
            scene, cards, character,effect ->
        val cost = effect.cost(scene,cards,character,effect)
        var i: MutableList<Token> =
            cost.stream().collect( Collectors.groupingBy { it } )
            .values.stream().max( Comparator.comparingInt { it.size } ).get()
        //找到支付cost中最多的那个token
//        skill.check = object : Function2<Scene, SkillActive, DiceList> {
//            override fun invoke(p1: Scene, p2: SkillActive): DiceList {
//                var dices = skill.check.invoke(scene, skill)
//                dices.diceList.add(ConstantDice(1))
//                return dices
//            }
//        }
//        本函数的检定成功率+1
        if (i.size == 0){
            i = mutableListOf(Constitution)
        }
        //如果没有就扣1体质
        val d = Damage (
            character,
            cards,
            DiceLevel.getDiceListByCost(i.size)?:DiceList(ConstantDice(1)),
            i[0], listOf(Damage.DamageType.BLUDGEON)
        )
        scene.damageList.add(d)

        return@effect true
    }
    //造成的特效。这个模板中，本技能根据使用的cost中,数量最多的cost，决定了其伤害类型（如果没有则选择体质）。
    // 该技能会将一个damage实体塞入场景的伤害列表计算槽。伤害源为持有该技能的卡牌，伤害目标会受到和cost对应等级的伤害。伤害类型为钝器击伤。

    // 传参:玩家的输入值，场景，技能本身，敌人。
    abstract var reactEffect:Function1<Situation,Boolean>
    //反抗成功后的特效。
    // 传参:玩家的输入值，场景，技能本身，敌人。

}