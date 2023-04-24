package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.PassiveEffect
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance<A : ActiveEffect, P : PassiveEffect>(
    open val entry: CardEntry,
    registry: UniqueIdRegistry
) : Agent<A,P> {
    val uniqueId: Int = registry.register(this :: class)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardInstance<*, *>) return false

        if (entry != other.entry) return false
        if (uniqueId != other.uniqueId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = entry.hashCode()
        result = 31 * result + uniqueId
        return result
    }


}