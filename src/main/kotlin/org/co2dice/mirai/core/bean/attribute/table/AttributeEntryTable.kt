package org.co2dice.mirai.core.bean.attribute.table

import org.co2dice.mirai.core.bean.attribute.prototype.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-1:34
 * @Message: 一个记录在卡片上的静态的属性表,可用于卡片记录信息，或者是传递属性cost消耗
 **/
class AttributeEntryTable(private val map : Map<Attribute,Int>) {

    companion object{
        fun createElite(str:Int = 0, con:Int = 0, dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0)
        : AttributeEntryTable {
            val m = hashMapOf<Attribute,Int>().apply {
                if (str > 0) put(Strength,str)
                if (con > 0) put(Constitution,con)
                if (dex > 0) put(Dexterity,dex)
                if (wis > 0) put(Wisdom,wis)
                if (int > 0) put(Intelligence,int)
                if (san > 0) put(Strength,san)
            }
            return AttributeEntryTable(m.toMap())
        }
        //精英怪
        fun createMob(loyalty:Int) : AttributeEntryTable {
            return AttributeEntryTable(mapOf(Loyalty to loyalty))
        }
        //小怪
    }

    fun contain(attribute: Attribute) : Boolean{
        return getValue(attribute) != null
    }

    fun getAttributes() : Set<Attribute>{
        return map.keys
    }

    fun getValue(type: Attribute): Int? {
        return map[type]
    }

    fun toInstance() : AttributeInstanceTable {
        val m = mutableMapOf<Attribute,ValueInstance>().apply {
            map.forEach { (k, v) -> put(k,ValueInstance(v,v)) } }
        return AttributeInstanceTable(m.toMap())
    }

    fun combine(other :AttributeEntryTable) : AttributeEntryTable {
        val m = mutableMapOf<Attribute,Int>().apply {
            map.forEach { (k, v) -> put(k,v) }
            //如果已经存在key,就加上value,否则put
            other.map.forEach { (k, v) -> put(k,this[k]?.plus(v) ?: v) }
        }
        return AttributeEntryTable(m.toMap())
    }
}