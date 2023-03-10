package org.co2dice.mirai.bean.chessman.prototype

import org.co2dice.mirai.bean.attribute.table.AttributeEntryTable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
open class FullAttributeChessman(
    str : Int,
    con : Int,
    dex : Int,
    wis : Int,
    int : Int,
    san : Int,
    name: String
) : EliteChessman(AttributeEntryTable.createElite(str = str,con = con,dex = dex,wis = wis,int = int,san = san),name) {
    //拥有完整六维的棋子，不会存在空属性，一般用于玩家的棋子
    //可以作为开始的主将，也可以将对应的卡加入卡组




}