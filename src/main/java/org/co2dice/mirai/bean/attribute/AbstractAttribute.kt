package org.co2dice.mirai.bean.attribute

import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice

abstract class AbstractAttribute {
    var id:String = this.javaClass.name
    var value = 0

    constructor(id: String, value: Int) {
        this.id = id
        this.value = value
    }
    constructor(id: String, list:DiceList){
        this.id = id
        this.value = list.roll()
    }
}