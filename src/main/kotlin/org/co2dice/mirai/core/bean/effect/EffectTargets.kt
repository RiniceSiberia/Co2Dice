package org.co2dice.mirai.core.bean.effect

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import kotlin.reflect.KClass


//目标集合,里面要包含目标，以及目标的相关限制条件。
//比如：一张卡的一个技能，目标A为一个角色，目标B为另一个角色，目标C为场上的三张卡
//那么目标集合就是：[A,B,C]，而目标的限制检验方程则要写成：if(A.isChess && B.isChess && C.isCard && C.size == 3)
data class EffectTargets(
    private val targetCards : Map<String,Set<CardInstance>>,
    private val targetChessmen : Map<String,Set<ChessmanInstance>>,
    private val targetPlayers : Map<String,Set<PlayerInstance>>
    ){
    //选择的目标数组，每个元素代表一个目标,所有List里的目标不能重合
    init {
        //将所有目标合并为set,然后比较原长度，如果不相等则说明有重复，抛出异常
        val set = mutableSetOf<Any>()
        //将三个map加入set中，如果有重复元素报错
        set.addAll(targetCards.values)
        set.addAll(targetChessmen.values)
        set.addAll(targetPlayers.values)
        if(set.size != targetCards.size + targetChessmen.size + targetPlayers.size){
            throw Exception("目标集合中有重复目标")
        }
        //如果map之间的key冲突也报错
        if(targetCards.keys.intersect(targetChessmen.keys).isNotEmpty()
            || targetCards.keys.intersect(targetPlayers.keys).isNotEmpty()
            || targetChessmen.keys.intersect(targetPlayers.keys).isNotEmpty()){
            throw Exception("目标集合的标识键重复")
        }
    }

    constructor(any : Map<String,Set<Any>>) : this(
        any.filter { it -> it.value.any { it is CardInstance } }.mapValues { it.value.filterIsInstance<CardInstance>().toSet() },
        any.filter { it -> it.value.any { it is ChessmanInstance } }.mapValues { it.value.filterIsInstance<ChessmanInstance>().toSet() },
        any.filter { it -> it.value.any { it is PlayerInstance } }.mapValues { it.value.filterIsInstance<PlayerInstance>().toSet() }
    ){
        //如果有剩下的就报错
        if(any.any { it -> it.value.any { it !is CardInstance && it !is ChessmanInstance && it !is PlayerInstance } }){
            throw Exception("目标集合中有不支持的目标类型")
        }
    }

    fun getTargetCard(key:String): Set<CardInstance>? {
        return targetCards[key]
    }

    fun getTargetChessman(key:String): Set<ChessmanInstance>? {
        return targetChessmen[key]
    }

    fun getTargetPlayer(key:String): Set<PlayerInstance>? {
        return targetPlayers[key]
    }

    fun getTarget(key:String): Set<Any>? {
        return targetCards[key] ?: targetChessmen[key] ?: targetPlayers[key]
    }

    fun getSize(key : String) : Int{
        return getTarget(key)?.size ?: 0
    }
}

