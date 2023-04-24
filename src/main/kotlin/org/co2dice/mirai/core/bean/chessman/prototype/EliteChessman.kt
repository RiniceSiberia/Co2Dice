package org.co2dice.mirai.core.bean.chessman.prototype

import org.co2dice.mirai.core.bean.attribute.table.AttributeEntryTable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-1:13
 * @Message: Have a good time!  :)
 **/
open class EliteChessman (
    attributeEntryTable : AttributeEntryTable,
    name:String) : Chessman(attributeEntryTable,name) {
        //精英怪棋子，带有一些属性，但不一定是完整的六维属性
        //可以被玩家召唤，但无法作为主将
}