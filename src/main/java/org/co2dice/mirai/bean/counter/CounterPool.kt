package org.co2dice.mirai.bean.counter

import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:38
 * @Message: Have a good time!  :)
 **/
class CounterPool(val fullers : MutableMap<Counter,CounterFuller> = mutableMapOf<Counter,CounterFuller>()){

    constructor(str:Int,con:Int,dex:Int,wis:Int,int:Int,san:Int) : this() {
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.STR),str)
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.CON),con)
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.DEX),dex)
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.WIS),wis)
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.INT),int)
        addFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.SAN),san)
    }
    fun getPointFuller(counterType: Counter):CounterFuller?{
        return fullers[counterType]
    }

    fun getBasicFuller():MutableList<CounterFuller>{
        val fullers : MutableList<CounterFuller> = mutableListOf()
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.STR))?.let { fullers.add(it) }
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.CON))?.let { fullers.add(it) }
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.DEX))?.let { fullers.add(it) }
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.WIS))?.let { fullers.add(it) }
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.INT))?.let { fullers.add(it) }
        getPointFuller(CounterRegistrar.getChessmanToken(CounterRegistrar.BasicToken.SAN))?.let { fullers.add(it) }
        return fullers
    }

    fun addFuller(counterType : Counter,
                  value : Int = DiceList(
                      NormalDice(6),
                      NormalDice(6),
                      NormalDice(6)).roll().getResult(),
                  limit : Int = value ):Boolean{
        if (fullers[counterType] == null && limit > 0){
            fullers[counterType] = CounterFuller(counterType,value,limit)
            return true
        }else{
            return false
        }
    }


}