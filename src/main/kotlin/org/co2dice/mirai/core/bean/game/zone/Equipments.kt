package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.publicEnums.ItemType
import org.co2dice.mirai.core.bean.api.DependChessman
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

//棋子装备栏
class Equipments(override var chessman: ChessmanInstance?,
                 private val equipments : MutableMap<ItemType,EquipSlots>) : DependChessman {

    fun equip(instance: ItemCardInstance):Boolean{
        val map = instance.occupy
        //对这个map进行检查，先确保所有的key都在equipments中，然后检查value是否超过了限制
        for ((key,value) in map){
            if (key in equipments && value > equipments[key]!!.getCapacity()) {
                return false
            }
            if (!equipments[key]!!.addEquipment(instance,value)){
                equipments.values.forEach{ it.remove(instance) }
                //一个出错就全删，返回false
                return false
            }
        }
        return true
    }

    fun getEquipNum() : Int{
        return getAllEquip().size
    }

    fun getEquipmentByNameAndId(name : String,uniqueId : Int = -1) : ItemCardInstance?{
        //找寻所有name相同的装备
        val list = equipments.values.flatMap { it -> it.getEquipments().filter { it.entry.card.cardRealName == name } }
        if (list.size > 1 && uniqueId > 0){
            //同名且id大于0,查找
        }else if (list.size > 1){
            //无法准确指定
            return null
        }else if(list.size == 1){
            //可以准确指定
            return list.first()
        }
        return null
    }

    fun checkEquipped(instance: ItemCardInstance) : Boolean{
        return equipments.values.any { it.getEquipments().contains(instance) }
    }

    fun getAllEquip() : Set<ItemCardInstance>{
        val set = mutableSetOf<ItemCardInstance>()
        equipments.values.forEach { set.addAll(it.getEquipments()) }
        return set
    }

    data class EquipSlots(val limit : Int,
                          private val equipments :MutableList<ItemCardInstance> = mutableListOf()){
        fun getEquipments() : Set<ItemCardInstance>{
            return equipments.toSet()
        }

        fun getCapacity() : Int{
            return limit - equipments.size
        }

        fun addEquipment(equip : ItemCardInstance,time : Int = 1) : Boolean{
            var b = true
            for (i in 0 until time){
                b = add(equip) && b
                //一个加不进去就会导致后面的也加不进去了
                if (!b){
                    break
                }
            }
            return b
        }
        private fun add(equip : ItemCardInstance) : Boolean{
            if (equipments.size < limit){
                return equipments.add(equip)
            }
            return false
        }

        fun remove(equip : ItemCardInstance) : Boolean{
            return equipments.removeAll { it == equip }
        }
    }
}