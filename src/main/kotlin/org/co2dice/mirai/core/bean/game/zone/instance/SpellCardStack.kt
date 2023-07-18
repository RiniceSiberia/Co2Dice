package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.SpellCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-21:01
 * {@code @Message:} 咒语卡的实际存在地
 * 和BufferStack有本质区别,一个是存卡，一个是存行为，这个只是一个缓冲区
 **/
class SpellCardStack(holder : PlayerInstance) : StackZoneInstance<SpellCardInstance>(holder, mutableListOf()) {

    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): SpellCardInstance {
        return SpellCardInstance(card,holder)
    }
}