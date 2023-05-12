package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.decorator.handler.DecoratorHolder
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance<E : Effect>(
    open val entry: CardEntry,
    registry: UniqueIdRegistry
) : Agent<E>, DecoratorHolder() {
    val uniqueId: Int = registry.register(this :: class)
}