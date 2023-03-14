package org.co2dice.mirai.bean.effect.utils

import org.co2dice.mirai.bean.api.EffectTarget

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-09-15:04
 * @Message: 这是一个卡片效果的对象选择器的枚举类，里面的每个实例都可以根据字符串来选择效果的对象
 **/
enum class EffectTargetSelectors(val func : (String, Situation) -> Set<EffectTarget>) {
    SELECT_ALL_SCENE_ITEM(
        //查找全场道具的实例，可以有两种搜索模式:序号(使用一个List<Int>搜索,或者使用一个string(卡名/别名)+int(序号)搜索)
        func = func@{ input, situation ->
            var set : Set<EffectTarget> = setOf()
            val zones = situation.scene.zones.values.toList()
            //把场景提出来
            val t = org.co2dice.mirai.utils.SelectorUtils.inputTrans(input)
            //将输入转换成Either

            zones.flatMap { z -> z.equipmentZone.values }.forEach{ e ->
                t.ifLeft{

                }.ifRight{
                    val equip = e.getEquipmentByNameAndId(it.first,it.second)
                    if (equip != null){
                        set = kotlin.collections.setOf(equip)
                    }
                }


            }
            return@func set
        }
    ),


    ;




    fun getTarget(id : String,situation: Situation) : Set<EffectTarget>{
        return this.func(id,situation)
    }
}