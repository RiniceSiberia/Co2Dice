package org.co2dice.mirai.core.bean.attribute.table

import org.co2dice.mirai.core.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.core.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.core.bean.attribute.prototype.MobAttribute

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

    fun contain(attribute: AttributeAPI) : Boolean{
        return getValue(attribute) != null
    }

    fun getAttributes() : Set<AttributeAPI>{
        return map.keys
    }

    fun getValue(type: AttributeAPI): Int? {
        return map[type]
    }

    fun toInstance() : AttributeInstanceTable {
        val m = mutableMapOf<AttributeAPI,ValueInstance>().apply {
            map.forEach { (k, v) -> put(k,ValueInstance(v,v)) } }
        return AttributeInstanceTable(m.toMap())
    }

    fun combine(other :AttributeEntryTable) : AttributeEntryTable {
        val m = mutableMapOf<AttributeAPI,Int>().apply {
            map.forEach { (k, v) -> put(k,v) }
            //如果已经存在key,就加上value,否则put
            other.map.forEach { (k, v) -> put(k,this[k]?.plus(v) ?: v) }
        }
        return AttributeEntryTable(m.toMap())
    }
}