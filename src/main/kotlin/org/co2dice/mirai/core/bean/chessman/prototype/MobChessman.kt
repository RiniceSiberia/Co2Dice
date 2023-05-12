package org.co2dice.mirai.core.bean.chessman.prototype

import org.co2dice.mirai.core.bean.attribute.table.AttributeEntryTable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-27-0:18
 * @Message: Have a good time!  :)
 **/
class MobChessman(loyalty : Int , types : Set<String>,name:String)
    : Chessman(AttributeEntryTable.createMob( loyalty ), types, name) {
//小怪的唯一属性:忠诚度，忠诚度归零了就会扑街
}