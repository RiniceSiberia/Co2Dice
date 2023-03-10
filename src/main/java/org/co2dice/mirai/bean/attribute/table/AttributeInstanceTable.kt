package org.co2dice.mirai.bean.attribute.table


import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.bean.attribute.prototype.MobAttribute
import org.co2dice.mirai.bean.dice.diceList.DiceList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-0:28
 * @Message: 一个动态的属性表
 **/
class AttributeInstanceTable (private val map : Map<AttributeAPI,ValuesInstance>){
    //不用set的原因是需要保留AttributeInstance这个完整体

    data class ValuesInstance(var value : Int,var limit : Int){

    }


    companion object{
        fun createElite(str:Int = 0, con:Int = 0,
                        dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0,
                        strLimit:Int = str, conLimit:Int = con, dexLimit:Int = dex,
                        wisLimit:Int = wis, intLimit:Int = int, sanLimit:Int = san) : AttributeInstanceTable {
            val s = mutableMapOf<AttributeAPI,ValuesInstance>().apply {
                if(str > 0) put(EliteAttribute.STR,ValuesInstance(str,strLimit))
                if(con > 0) put(EliteAttribute.CON,ValuesInstance(con,conLimit))
                if(dex > 0) put(EliteAttribute.DEX,ValuesInstance(dex,dexLimit))
                if(wis > 0) put(EliteAttribute.WIS,ValuesInstance(wis,wisLimit))
                if(int > 0) put(EliteAttribute.INT,ValuesInstance(int,intLimit))
                if(san > 0) put(EliteAttribute.SAN,ValuesInstance(san,sanLimit))
            }
            return AttributeInstanceTable(s.toMap())
        }
        //精英怪的构造器，不填或负数属性代表没有这个属性

        fun createElite(str: DiceList = DiceList(0),
                        con: DiceList = DiceList(0),
                        dex: DiceList = DiceList(0),
                        wis: DiceList = DiceList(0),
                        int: DiceList = DiceList(0),
                        san: DiceList = DiceList(0)): AttributeInstanceTable {
            return createElite(
                str.roll().getResult(),
                con.roll().getResult(),
                dex.roll().getResult(),
                wis.roll().getResult(),
                int.roll().getResult(),
                san.roll().getResult()
            )
        }
        //现roll的构造器

        fun createMob(loyalty:Int) : AttributeInstanceTable {
            return AttributeInstanceTable(mapOf(MobAttribute.LOYALTY to ValuesInstance(loyalty,loyalty)))
        }
    }



    fun contain(attribute: AttributeAPI) : Boolean{
        return findAttribute(attribute) != null
    }

    fun plusValue(value: Int,attribute: AttributeAPI): AttributeInstanceTable {
        findAttribute(attribute)?.value = value + findAttribute(attribute)!!.value
        return this
    }

    fun subtractionValue(value: Int,attribute: AttributeAPI): AttributeInstanceTable {
        return plusValue(-value,attribute)
    }


    fun setValue(value:Int, attribute: AttributeAPI) : AttributeInstanceTable {
        findAttribute(attribute)?.value = value
        return this
    }

    fun getValue(attribute: AttributeAPI) : Int? {
        return findAttribute(attribute)?.value
    }

    fun getValueLimit(attribute: AttributeAPI) : Int?{
        return findAttribute(attribute)?.limit
    }

    fun setLimit(value:Int, attribute: AttributeAPI) : AttributeInstanceTable {
        findAttribute(attribute)?.limit = value
        return this
    }


    fun setAll(str: Int, con: Int, dex: Int, wis: Int, int: Int, san: Int) : AttributeInstanceTable {
        map.forEach{
            when(it.key){
                EliteAttribute.STR -> it.value.value = str
                EliteAttribute.CON -> it.value.value = con
                EliteAttribute.DEX -> it.value.value = dex
                EliteAttribute.WIS -> it.value.value = wis
                EliteAttribute.INT -> it.value.value = int
                EliteAttribute.SAN -> it.value.value = san
            }
        }
        return this
    }


    private fun findAttribute(type: AttributeAPI): ValuesInstance? {
        return map[type]
    }
}