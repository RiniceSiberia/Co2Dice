package org.co2dice.mirai.core.bean.chessman.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.dice.roll
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.DiceSlotStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.DiceRevealTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-13:23
 * {@code @Message:} 骰子状态的棋子
 * 没有生命值，没有属性，没有战斗力，无法被正常攻击击中
 * 静态的能力也只有entry
 **/
@Serializable
class DiceChessmanInstance(
    override val entry: ChessmanEntry,
    override var holder: PlayerInstance,
    val dice : Map<Int,Int> = entry.dice,
    //使用的骰子
    private var point : Int = dice.roll(),
    val staticAbilities: List<DiceSlotStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
    val enterFieldTriggeredAbilities: List<EnterFieldTriggeredAbilityInstance> = entry.triggeredAbilityEntries.toInstance(),
    //进场触发的能力
    val diceRevealTriggeredAbilities : List<DiceRevealTriggeredAbilityInstance> = entry.triggeredAbilityEntries.toInstance(),
    //骰子揭露时触发的能力
) : ChessmanInstance() {


    fun getPoint() = point

    fun refresh(situation: SituationApi){
        point = dice.roll()
    }

    fun diceReveal(input : Map<String,Any>,situation : SituationApi){
        diceRevealTriggeredAbilities.forEach {
            //TODO
        }
    }


}