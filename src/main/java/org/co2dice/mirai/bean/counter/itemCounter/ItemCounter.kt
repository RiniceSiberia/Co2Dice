package org.co2dice.mirai.bean.counter.itemCounter

import org.co2dice.mirai.utils.TokenDepend
import org.co2dice.mirai.bean.counter.Counter

open class ItemCounter(override val id: String, override val name: String)
    : Counter(id,name, TokenDepend.ITEM) {
}