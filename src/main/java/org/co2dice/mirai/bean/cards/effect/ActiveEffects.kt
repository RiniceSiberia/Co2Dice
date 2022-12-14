package org.co2dice.mirai.bean.cards.effect

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.battle.Damage
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.dice.ConstantDice
import org.co2dice.mirai.bean.dice.DiceLevel
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.tokens.characterToken.Constitution
import java.util.Comparator
import java.util.stream.Collectors

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:39
 * @Message: Have a good time!  :)
 **/
class ActiveEffects {

    fun blunt(input:MutableList<String>, battle: Battle, skill: EffectActive, target:Cards):{
        //造成伤害，取伤害数值
        var caoPot = (0,0)
        var skillHolder = skill.holder
        var holderCharacter: CharacterCard? = skill.getHolder()
        val costMax = skill.cost.invoke(battle,skill)
            .stream().collect(Collectors.groupingBy { it })
            .values.stream().max( Comparator.comparingInt { it.size } ).get().size

        val diceList:DiceList = DiceLevel.getDiceListByCost(costMax)?: DiceList(ConstantDice(1))
        var damage: Damage = Damage( holderCharacter,
            target,
            diceList,
            Constitution,
            listOf(Damage.DamageType.BLUDGEON)
        )
        var tempDamage:Damage
        return caoPot
    }
}