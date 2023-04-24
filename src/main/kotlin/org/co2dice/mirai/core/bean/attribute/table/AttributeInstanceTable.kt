package org.co2dice.mirai.core.bean.attribute.table


import org.co2dice.mirai.core.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.core.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.core.bean.attribute.prototype.MobAttribute
import org.co2dice.mirai.core.bean.dice.diceList.DiceList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-0:28
 * @Message: 一个动态的属性表
 **/
class AttributeInstanceTable (private val map : Map<AttributeAPI,ValueInstance>){
    //不用set的原因是需要保留AttributeInstance这个完整体




    companion object{
        fun createElite(str:Int = 0, con:Int = 0,
                        dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0,
                        strLimit:Int = str, conLimit:Int = con, dexLimit:Int = dex,
                        wisLimit:Int = wis, intLimit:Int = int, sanLimit:Int = san) : AttributeInstanceTable {
            val s = mutableMapOf<AttributeAPI,ValueInstance>().apply {
                if(str > 0) put(EliteAttribute.STR,ValueInstance(str,strLimit))
                if(con > 0) put(EliteAttribute.CON,ValueInstance(con,conLimit))
                if(dex > 0) put(EliteAttribute.DEX,ValueInstance(dex,dexLimit))
                if(wis > 0) put(EliteAttribute.WIS,ValueInstance(wis,wisLimit))
                if(int > 0) put(EliteAttribute.INT,ValueInstance(int,intLimit))
                if(san > 0) put(EliteAttribute.SAN,ValueInstance(san,sanLimit))
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
                str.roll().open(),
                con.roll().open(),
                dex.roll().open(),
                wis.roll().open(),
                int.roll().open(),
                san.roll().open()
            )
        }
        //现roll的构造器

        fun createMob(loyalty:Int) : AttributeInstanceTable {
            return AttributeInstanceTable(mapOf(MobAttribute.LOYALTY to ValueInstance(loyalty,loyalty)))
        }
    }



    fun contain(attribute: AttributeAPI) : Boolean{
        return findAttribute(attribute) != null
    }

    fun canPayToCost(table: AttributeEntryTable) : Boolean{
        table.getAttributes().forEach{
            if (this.contain(it)){
                //如果本实体包括这个元素就对比，如果table里的值大于本方法就false
                if (table.getValue(it)!! > this.getValue(it)!!){
                    return false
                }
            }else{
                return false
            }
        }

        return true
    }

    fun payCost(table: AttributeEntryTable) : Boolean {
        return try {
            table.getAttributes().forEach{
                this.subtractionValue(table.getValue(it)!!,it)
            }
            true
        }catch (e:Exception){
            e.printStackTrace()
            false
        }
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


    private fun findAttribute(type: AttributeAPI): ValueInstance? {
        return map[type]
    }
}