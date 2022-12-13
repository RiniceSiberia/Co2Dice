package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Damage
import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.dice.*
import org.co2dice.mirai.bean.tokens.Token
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken
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
abstract class SkillActive(holder: Cards) : Skill(holder) {
    override val skillType = SkillType.ACTIVE

    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定
    var check:Function2<Scene,SkillActive,DiceList> = check@{ _, skill ->
        val h = skill.getHolder()
        if (h != null){
            val tokens = h.tokens
            //这里默认值是获取敏捷
            val fuller = tokens.getPointFuller(Dexterity)
            if (fuller != null){
                return@check MutableDiceList(
                    listOf(NormalDice(20)),
                    listOf(ConstantDice(0)),
                    AttributeFixDice(listOf(Dexterity)))
                //固定值，增益，以及变化的属性值
            }
        }

        return@check DiceList(ConstantDice(0))
    }
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值

    //示例反抗函数,使用敏捷进行反抗,进行一个0修正值,1d20+敏捷的反抗
    var react:Function3<Scene,SkillActive, Cards, DiceList> = react@{ _, _, target ->
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

    var effect:Function3<Scene,SkillActive, Cards, Boolean> = effect@{ scene, skill, cards ->
        val cost = skill.cost.invoke(scene,skill)
        var i: MutableList<Token> =
            cost.stream().collect( Collectors.groupingBy { it } )
            .values.stream().max( Comparator.comparingInt { it.size } ).get()
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
        //获取cost中数量最多的token的数量
        val d = Damage (
            skill.getHolder(),
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
    abstract var reactEffect:Function3<Scene,SkillActive, Cards, Boolean>
    //反抗成功后的特效。
    // 传参:玩家的输入值，场景，技能本身，敌人。

}