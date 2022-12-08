package org.co2dice.mirai.bean.dice

import kotlin.streams.toList

class DiceResult (val diceList:DiceList){
    var resultList:List<Int> = diceList.diceList.stream().mapToInt(Dice::roll).toList()


    fun getResult():Int{
        return resultList.stream().mapToInt(Int::toInt).sum()
    }

    fun changeResult(diceIndex:Int, changeValue:Int){
        resultList = resultList.mapIndexed { index, i -> if(index == diceIndex) changeValue else i }
    }
    fun reRollPointDice(diceIndex: Int,replaceDice:Dice){
        resultList = resultList.mapIndexed { index, i -> if(index == diceIndex) replaceDice.roll() else i }
    }
}