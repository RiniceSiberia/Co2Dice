package org.co2dice.mirai.bean.attribute.table

import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.bean.attribute.prototype.MobAttribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-1:34
 * @Message: 一个记录在卡片上的静态的属性表,可用于卡片记录信息，或者是传递属性cost消耗
 **/
class AttributeEntryTable(private val map : Map<AttributeAPI,Int>) {

    companion object{
        fun createElite(str:Int = 0, con:Int = 0, dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0)
        : AttributeEntryTable {
            val m = hashMapOf<EliteAttribute,Int>().apply {
                if (str > 0) put(EliteAttribute.STR,str)
                if (con > 0) put(EliteAttribute.CON,con)
                if (dex > 0) put(EliteAttribute.DEX,dex)
                if (wis > 0) put(EliteAttribute.WIS,wis)
                if (int > 0) put(EliteAttribute.INT,int)
                if (san > 0) put(EliteAttribute.SAN,san)
            }
            return AttributeEntryTable(m.toMap())
        }
        //精英怪
        fun createMob(loyalty:Int) : AttributeEntryTable {
            return AttributeEntryTable(mapOf(MobAttribute.LOYALTY to loyalty))
        }
        //小怪
    }


    fun toInstance() : AttributeInstanceTable {
        val m = mutableMapOf<AttributeAPI,AttributeInstanceTable.ValuesInstance>().apply {
            map.forEach { (k, v) -> put(k,AttributeInstanceTable.ValuesInstance(v,v)) } }
        return AttributeInstanceTable(m.toMap())
    }
}