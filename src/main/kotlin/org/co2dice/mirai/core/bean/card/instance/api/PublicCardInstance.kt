package org.co2dice.mirai.core.bean.card.instance.api

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.PassiveEffect
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-12:02
 * @Message: Have a good time!  :)
 **/
abstract class PublicCardInstance<A : ActiveEffect, P : PassiveEffect>(
    entry: CardEntry,
    registry : UniqueIdRegistry,
) : CardInstance<A,P>(entry,registry) {
}