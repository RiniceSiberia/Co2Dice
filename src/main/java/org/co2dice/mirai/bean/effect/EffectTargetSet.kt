package org.co2dice.mirai.bean.effect

import org.co2dice.mirai.bean.API.EffectTarget
import java.util.function.Predicate

//目标集合,里面要包含目标，以及目标的相关限制条件。
//比如：一张卡的一个技能，目标A为一个角色，目标B为另一个角色，目标C为场上的三张卡
//那么目标集合就是：[A,B,C]，而目标的限制检验方程则要写成：if(A.isChess && B.isChess && C.isCard && C.size == 3)
data class EffectTargetSet(private val targets:List<Set<EffectTarget>>,
                           private val checkFunc:List<Predicate<Set<EffectTarget>>>){
    //选择的目标数组，每个元素代表一个目标,所有List里的目标不能重合
    init {
        //将所有目标合并为set,然后比较原长度，如果不相等则说明有重复，抛出异常
        val set = mutableSetOf<EffectTarget>()
        targets.forEach { set.addAll(it) }
        if(set.size != targets.stream().flatMap { it.stream() }.toList().size){
            throw Exception("目标重复")
        }
        if (checkFunc.size != targets.size){
            throw Exception("目标数量与检验函数数量不匹配")
        }
        for (f in checkFunc){
            if(!f.test(targets[checkFunc.indexOf(f)])){
                throw Exception("目标不正确")
            }
        }
    }
    fun getTargetById(i:Int):Set<EffectTarget>{
        return targets[i]
    }
}

