package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.api.agent.Agent
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.emblem.Emblem
import org.co2dice.mirai.core.bean.game.zone.instance.*
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.decorator.api.DecoratorModifier
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-26-20:33
 * {@code @Message:} 情景指针接口
 **/
interface SituationApi {
    val scene: Scene
    val player: PlayerInstance
    val agent: Agent

    fun addDecorator(decorator: DecoratorModifier<*,*,*>) {
        scene.addDecorator(decorator)
    }

    fun getGy() : GyZoneInstance {
        return scene.getDesk(player)!!.gy
    }

    fun getBanish() : BanishZoneInstance {
        return scene.getDesk(player)!!.banish
    }

    fun getMainDeck() : DeckInstance {
        return scene.getDesk(player)!!.deck
    }

    fun getField() : FieldInstance {
        return scene.field
    }

    fun getHand() : HandInstance {
        return scene.getDesk(player)!!.hand
    }

    fun getRegistry() : UniqueIdRegistry {
        return scene.registry
    }


}

inline fun <reified E : Emblem<*>> SituationApi.getAgentEmblem() : E?{
    if (isAgentEmblem<E>()){
        return agentWrapper<E>()
    }
    return null
}

inline fun <reified E : Emblem<*>> SituationApi.isAgentEmblem() : Boolean{
    return agent is E
}

inline fun <reified T : CardInstance> SituationApi.getAgentCardInstance() : T?{
    if (isAgentCardInstance<T>()){
        return agentWrapper<T>()
    }
    return null
}

inline fun <reified T : CardInstance> SituationApi.isAgentCardInstance() : Boolean{
    return agent is T
}

inline fun <reified C : ChessmanInstance> SituationApi.isAgentChessmanInstance() : Boolean{
    return agent is C
}

//妈个鸡，怒从心中起，恶向胆边生，强转！
inline fun <reified T : Any> SituationApi.agentWrapper() : T{
    return agent as T
}

inline fun <reified C : ChessmanInstance> SituationApi.getAgentChessmanInstance() : C?{
    if (isAgentChessmanInstance<C>()){
        return agentWrapper<C>()
    }
    return null
}