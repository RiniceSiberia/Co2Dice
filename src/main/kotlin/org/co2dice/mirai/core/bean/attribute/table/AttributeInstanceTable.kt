package org.co2dice.mirai.core.bean.attribute.table

import org.co2dice.mirai.core.bean.attribute.prototype.*
import org.co2dice.mirai.core.bean.dice.entry.IntSampleSpace
import org.co2dice.mirai.core.bean.dice.instance.ListDices.tripleD6

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-0:28
 * @Message: 一个动态的属性表
 **/
class AttributeInstanceTable (private val map : Map<Attribute,ValueInstance>){
    //不用set的原因是需要保留AttributeInstance这个完整体




    companion object{
        fun createElite(str:Int = 0, con:Int = 0,
                        dex:Int = 0, wis:Int = 0, int:Int = 0, san:Int = 0,
                        strLimit:Int = str, conLimit:Int = con, dexLimit:Int = dex,
                        wisLimit:Int = wis, intLimit:Int = int, sanLimit:Int = san) : AttributeInstanceTable {
            val s = mutableMapOf<Attribute,ValueInstance>().apply {
                if(str > 0) put(Strength,ValueInstance(str,strLimit))
                if(con > 0) put(Constitution,ValueInstance(con,conLimit))
                if(dex > 0) put(Dexterity,ValueInstance(dex,dexLimit))
                if(wis > 0) put(Wisdom,ValueInstance(wis,wisLimit))
                if(int > 0) put(Intelligence,ValueInstance(int,intLimit))
                if(san > 0) put(Sanity,ValueInstance(san,sanLimit))
            }
            return AttributeInstanceTable(s.toMap())
        }
        //精英怪的构造器，不填或负数属性代表没有这个属性

        fun createElite(str: List<IntSampleSpace> = tripleD6,
                        con: List<IntSampleSpace> = tripleD6,
                        dex: List<IntSampleSpace> = tripleD6,
                        wis: List<IntSampleSpace> = tripleD6,
                        int: List<IntSampleSpace> = tripleD6,
                        san: List<IntSampleSpace> = tripleD6): AttributeInstanceTable {
            return createElite(
                str.stream().mapToInt { it.roll() }.sum(),
                con.stream().mapToInt { it.roll() }.sum(),
                dex.stream().mapToInt { it.roll() }.sum(),
                wis.stream().mapToInt { it.roll() }.sum(),
                int.stream().mapToInt { it.roll() }.sum(),
                san.stream().mapToInt { it.roll() }.sum()
            )
        }
        //现roll的构造器

        fun createMob(loyalty:Int) : AttributeInstanceTable {
            return AttributeInstanceTable(mapOf(Loyalty to ValueInstance(loyalty,loyalty)))
        }
    }

    fun survive() : Boolean{
        return map.all { it.value.value > 0}
    }

    fun contain(attribute: Attribute) : Boolean{
        return findAttribute(attribute) != null
    }

    fun allContain(table: AttributeEntryTable) : Boolean{
        return table.getAttributes().all{ this.contain(it) }
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
        //需要原子化操作
        return if (!allContain(table)){
            false
        } else {
            return table.getAttributes().all{
                this.subtractionValue(table.getValue(it)!!,it)
                true
            }
        }
    }

    fun plusValue(value: Int,attribute: Attribute): AttributeInstanceTable {
        findAttribute(attribute)?.value = value + findAttribute(attribute)!!.value
        return this
    }

    fun subtractionValue(value: Int,attribute: Attribute): AttributeInstanceTable {
        return plusValue(-value,attribute)
    }


    fun setValue(value:Int, attribute: Attribute) : AttributeInstanceTable {
        findAttribute(attribute)?.value = value
        return this
    }

    fun getValue(attribute: Attribute) : Int? {
        return findAttribute(attribute)?.value
    }

    fun getValueLimit(attribute: Attribute) : Int?{
        return findAttribute(attribute)?.limit
    }

    fun setLimit(value:Int, attribute: Attribute) : AttributeInstanceTable {
        findAttribute(attribute)?.limit = value
        return this
    }


    fun setAll(str: Int, con: Int, dex: Int, wis: Int, int: Int, san: Int) : AttributeInstanceTable {
        map.forEach{
            when(it.key){
                Strength -> it.value.value = str
                Constitution -> it.value.value = con
                Dexterity -> it.value.value = dex
                Wisdom -> it.value.value = wis
                Intelligence -> it.value.value = int
                Sanity -> it.value.value = san
            }
        }
        return this
    }


    private fun findAttribute(type: Attribute): ValueInstance? {
        return map[type]
    }
}