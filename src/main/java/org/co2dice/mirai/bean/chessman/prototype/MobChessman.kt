package org.co2dice.mirai.bean.chessman.prototype

import org.co2dice.mirai.bean.chessman.attribute.AttributeEntryTable
import org.co2dice.mirai.bean.chessman.attribute.MobAttribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-27-0:18
 * @Message: Have a good time!  :)
 **/
class MobChessman(loyalty : Int, name:String)
    : Chessman(AttributeEntryTable.createMob( loyalty ), name) {
//小怪的唯一属性:忠诚度，忠诚度归零了就会扑街
}