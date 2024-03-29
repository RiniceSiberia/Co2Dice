package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.api.CardVesselApi
import org.co2dice.mirai.core.bean.game.zone.instance.CardListVessel
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.SituationApi
import org.co2dice.mirai.core.utils.situation.getAgentCardInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-11-18:45
 * {@code @Message:} 将卡自己作为cost
 * 我放弃了，我选择最啥b的方法
 * 卡片专属的cost，禁止其他类型的cost使用
 **/
@Serializable
class ThisCardCost(
    val from : Int,
    val to : Int,
    //操作方式: 个位代表去的地方，十位代表原来的地方
    //0场上 1墓地 2除外 3手卡 4卡组

    // 1.解放(从场上)
    // 2.除外(从场上)
    // 3.回手牌(从场上)
    // 4.回卡组(从场上)

    // 12.除外(从墓地)
    // 14.回卡组(从墓地)

    // 24.回卡组(从除外区)

    // 31.丢弃(从手牌)
    // 32.除外(从手牌)

    // 41.丢弃(从卡组)
    // 42.除外(从卡组)

) : OneToOneSelectionCost<CardInstance> {

    init {
        if (from == to){
            throw Exception("from and to can't be the same")
        }
        if (from !in 0..4){
            throw Exception("from must be in 0..4")
        }
        if (to !in 1..4){
            throw Exception("to must be in 1..4")
        }
    }

    override fun toJson(obj: CardInstance): JsonElement {
        return Json.encodeToJsonElement(obj)
    }

    override fun getCostScope(input : Map<String,Any>, situation: PreActivationSituation): CardInstance? {
        val agent = situation.getAgentCardInstance<CardInstance>()
        if (agent != null && getFrom(from,situation,agent).contain(agent)){
            return agent
        }
        return null
    }

    override fun practice(input: Map<String, Any>, obj: CardInstance, situation: ActivationSituation): Boolean {
        val agent = situation.getAgentCardInstance<CardInstance>() ?: return false
        val from = getFrom(from,situation,agent)
        val to = getTo(to,situation)
        return situation.scene.moveCard(agent,from,to)
    }

    companion object{
        fun getFrom(f : Int,situation: SituationApi,agent : CardInstance): CardVesselApi<*> {
            return when (f) {
                0 -> situation.scene.getPlayerAllChessmanEquipments(situation.player)
                    .stream().filter { it.contain(agent) }.findFirst().get()
                1 -> situation.getGy()
                2 -> situation.getBanish()
                3 -> situation.getHand()
                4 -> situation.getMainDeck()
                else -> throw Exception("f must be in 0..4")
            }
        }

        fun getTo(t : Int,situation: ActivationSituation): CardListVessel<*> {
            return when (t) {
                1 -> situation.getGy()
                2 -> situation.getBanish()
                3 -> situation.getHand()
                4 -> situation.getMainDeck()
                else -> throw Exception("t must be in 1..4")
            }
        }
    }
}
