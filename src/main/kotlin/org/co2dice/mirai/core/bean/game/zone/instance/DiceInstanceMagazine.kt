package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.chessman.instance.DiceChessmanInstance
import org.co2dice.mirai.core.utils.situation.SituationApi


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-06-23:18
 * @Message: 骰子的储存槽，这儿的Magazine是弹匣的意思
 * 因为骰子会被按在这个槽里，所以这个槽也可以叫做骰子槽
 **/
class DiceInstanceMagazine (
    val diceInstanceList : MutableList<DiceChessmanInstance>,
    //可用的骰子列表
    val beUsedDice : MutableList<DiceChessmanInstance> = mutableListOf(),
    //被使用过了的不可用骰子列表,等待刷新
){
    fun useDice(situation: SituationApi,index : Int) : Int{
        val dice = diceInstanceList.removeAt(index)
        beUsedDice.add(dice)
        return dice.getPoint()
    }

    fun refresh(dices : Set<DiceChessmanInstance> = beUsedDice.toSet(), situation: SituationApi) : Int{
        //刷新部分骰子,默认全部重置,返回重置骰子数量
        val diceMutable = dices.toMutableList().filter { it in beUsedDice }
        if (diceMutable.isNotEmpty() && beUsedDice.removeAll(diceMutable) && beUsedDice.addAll(diceMutable)){
            diceMutable.forEach { it.refresh(situation) }
            return diceMutable.size
        }
        return 0
    }

    fun getAllDice() : List<DiceChessmanInstance>{
        return diceInstanceList + beUsedDice
    }



}