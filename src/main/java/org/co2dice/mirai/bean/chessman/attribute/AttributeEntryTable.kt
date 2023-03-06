package org.co2dice.mirai.bean.chessman.attribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-1:34
 * @Message: Have a good time!  :)
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
        fun createMob(loyalty:Int) : AttributeEntryTable{
            return AttributeEntryTable(mapOf(
                MobAttribute.LOYALTY to loyalty
            ))
        }
        //小怪
    }


    fun toInstance() : AttributeInstanceTable{
        return AttributeInstanceTable(map.map { AttributeInstance(it.key,it.value,it.value) }.toSet())
    }
}