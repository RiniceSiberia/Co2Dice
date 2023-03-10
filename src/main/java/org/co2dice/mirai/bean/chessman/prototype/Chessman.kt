package org.co2dice.mirai.bean.chessman.prototype

import org.co2dice.mirai.bean.attribute.table.AttributeEntryTable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:22
 * @Message: Have a good time!  :)
 **/
abstract class Chessman(
    val attributeEntryTable : AttributeEntryTable,
    var name: String
) {
    //棋子是有属性的，但这个属性不会存储在抽象的棋子里。不同的棋子有不同的属性，比如杂鱼有忠诚
}