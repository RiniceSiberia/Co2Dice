package org.co2dice.mirai.bean.permanents.attribute

import org.co2dice.mirai.bean.dice.DiceList

//体质
class Constitution() : AbstractAttributePoint() {
    override val id: String = "Constitution"
    override var max: Int = 20

}