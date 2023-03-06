package org.co2dice.mirai.bean.chessman.attribute

import org.co2dice.mirai.bean.dice.DiceList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-0:28
 * @Message: Have a good time!  :)
 **/
class AttributeInstanceTable (private val set : Set<AttributeInstance>){
    //不用set的原因是需要保留AttributeInstance这个完整体


    companion object{
        fun createElite(str:Int = 0, con:Int = 0,
                        dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0,
                        strLimit:Int = str, conLimit:Int = con, dexLimit:Int = dex,
                        wisLimit:Int = wis, intLimit:Int = int, sanLimit:Int = san) : AttributeInstanceTable{
            val s = mutableSetOf<AttributeInstance>().apply {
                if(str > 0) add(AttributeInstance(EliteAttribute.STR,str,strLimit))
                if(con > 0) add(AttributeInstance(EliteAttribute.CON,con,conLimit))
                if(dex > 0) add(AttributeInstance(EliteAttribute.DEX,dex,dexLimit))
                if(wis > 0) add(AttributeInstance(EliteAttribute.WIS,wis,wisLimit))
                if(int > 0) add(AttributeInstance(EliteAttribute.INT,int,intLimit))
                if(san > 0) add(AttributeInstance(EliteAttribute.SAN,san,sanLimit))
            }
            return AttributeInstanceTable(s.toSet())
        }
        //精英怪的构造器，不填或负数属性代表没有这个属性

        fun createElite(str:DiceList = DiceList(0),
                        con:DiceList = DiceList(0),
                        dex:DiceList = DiceList(0),
                        wis:DiceList = DiceList(0),
                        int:DiceList = DiceList(0),
                        san:DiceList = DiceList(0))
        : AttributeInstanceTable{
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

        fun createMob(loyalty:Int) : AttributeInstanceTable{
            return AttributeInstanceTable(setOf(AttributeInstance(MobAttribute.LOYALTY,loyalty,loyalty)))
        }
    }



    fun contain(attribute:AttributeAPI) : Boolean{
        return findAttribute(attribute) != null
    }

    fun plusValue(value: Int,attribute: AttributeAPI):AttributeInstanceTable{
        findAttribute(attribute)?.setValue(value + findAttribute(attribute)!!.value)
        return this
    }

    fun subtractionValue(value: Int,attribute: AttributeAPI):AttributeInstanceTable{
        return plusValue(-value,attribute)
    }


    fun setValue(value:Int, attribute:AttributeAPI) : AttributeInstanceTable{
        findAttribute(attribute)?.setValue(value)
        return this
    }

    fun getValue(attribute: AttributeAPI) : Int? {
        return findAttribute(attribute)?.value
    }

    fun getValueLimit(attribute: AttributeAPI) : Int?{
        return findAttribute(attribute)?.limit
    }

    fun setLimit(value:Int, attribute:AttributeAPI) : AttributeInstanceTable {
        findAttribute(attribute)?.setLimit(value)
        return this
    }


    fun setAll(str: Int, con: Int, dex: Int, wis: Int, int: Int, san: Int) : AttributeInstanceTable{
        set.forEach{
            when(it.type){
                EliteAttribute.STR -> it.setValue(str)
                EliteAttribute.CON -> it.setValue(con)
                EliteAttribute.DEX -> it.setValue(dex)
                EliteAttribute.WIS -> it.setValue(wis)
                EliteAttribute.INT -> it.setValue(int)
                EliteAttribute.SAN -> it.setValue(san)
            }
        }
        return this
    }


    private fun findAttribute(type: AttributeAPI): AttributeInstance? {
        return set.find { it.type == type }
    }
}