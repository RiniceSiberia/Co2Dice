package org.co2dice.mirai.bean.effect

import org.co2dice.mirai.bean.api.EffectTarget

//目标集合,里面要包含目标，以及目标的相关限制条件。
//比如：一张卡的一个技能，目标A为一个角色，目标B为另一个角色，目标C为场上的三张卡
//那么目标集合就是：[A,B,C]，而目标的限制检验方程则要写成：if(A.isChess && B.isChess && C.isCard && C.size == 3)
data class EffectTargets(private val targets:Map<String,Set<EffectTarget>>){
    //选择的目标数组，每个元素代表一个目标,所有List里的目标不能重合
    init {
        //将所有目标合并为set,然后比较原长度，如果不相等则说明有重复，抛出异常
        val set = mutableSetOf<EffectTarget>()
        targets.forEach { set.addAll(it.value) }
        if(set.size != targets.size){
            throw Exception("目标集合中有重复目标")
        }
    }
    fun getTargetByKey(key:String):Set<EffectTarget>?{
        return targets[key]
    }

    fun check():Boolean{
        //如果targets中的set有size=0的返回false
        return targets.values.all { it.isNotEmpty() }
    }
}

