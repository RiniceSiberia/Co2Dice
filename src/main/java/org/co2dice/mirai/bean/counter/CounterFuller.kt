package org.co2dice.mirai.bean.counter

import org.co2dice.mirai.bean.game.Battle
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class CounterFuller (val counterType: Counter,
                     value: Int = 1
){

    //重复添加n个point进points中
    private val counters : MutableList<Counter> = mutableListOf<Counter>().apply{
        for (i in 0 until value){
            add(counterType)
            //初始化不触发添加事件
        }
    }


    fun overTime(battle : Battle, chessman: ChessmanInstance):Boolean{
        //对每个点都执行一次检查
        counters.forEach{
            if (it.isTempToken && it.checkPoint(battle,chessman) && it.timeFlow(battle,chessman)){
                //如果是临时token并且检查点返回true并且时间流逝返回true则删除这个点
                if (!counters.remove(it) || !it.removedEvent(this)){
                    //如果删除失败则返回false
                    return false
                }
            }
        }
        return true
    }

    fun getCount():Int{
        return this.counters.size
    }

    //传入一个数字，抹除最后加的新token，并触发其addEvent
    fun addCounter(counter: Counter):Boolean{
        if (!counter.isTempToken){
            return if (counter.canReplace(this.counterType)){
                counters.add(counter) && counter.addEvent(this)
            }else{
                false
            }
        }
            return counters.add(counter) && counter.addEvent(this)
    }
    //加token,如果是临时token则不检查是否超过上限,如果是永久token则检查是否超过上限,如果加成功则返回true
    fun removeToken(v:Int):Boolean{
        //传入一个数字，抹除最后加的新token，并触发其addEvent
        var b = true
        for (i in 0 until v){
            //如果tokens为空则返回false
            if (counters.size == 0){
                b = false
            }else{
                for (j in 1 .. counters.size){
                    val token = counters[counters.size-j]
                    b = b && counters.remove(token)
                        //成功删除token
                        && token.removedEvent(this)
                        //成功触发事件
                        && getCount() > 0
                    break
                }
            }
        }
        return b
    }

}