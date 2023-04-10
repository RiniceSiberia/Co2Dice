package org.co2dice.mirai.bean.dice

import org.co2dice.mirai.bean.dice.diceList.DiceList
import org.co2dice.mirai.bean.dice.single.AbstractDice
import kotlin.streams.toList

class DiceResult (val diceList: DiceList){
    var resultList:List<Int> = diceList.getDiceList().stream().mapToInt(AbstractDice::roll).toList()


    fun open():Int{
        return resultList.stream().mapToInt(Int::toInt).sum()
    }

    fun changeResult(diceIndex:Int, changeValue:Int){
        resultList = resultList.mapIndexed { index, i -> if(index == diceIndex) changeValue else i }
    }
    fun reRollPointDice(diceIndex: Int, replaceAbstractDice: AbstractDice){
        resultList = resultList.mapIndexed { index, i -> if(index == diceIndex) replaceAbstractDice.roll() else i }
    }
}