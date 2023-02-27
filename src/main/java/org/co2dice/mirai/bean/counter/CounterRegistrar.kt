package org.co2dice.mirai.bean.counter

import org.co2dice.mirai.bean.counter.chessmanToken.ChessmanCounter
import space.controlnet.lightioc.api.Container

object CounterRegistrar {

    fun registerDefaultToken(){
        //注册默认的token
        Container.init("org.co2dice.mirai.bean.counters")
        BasicToken.values().forEach{
            Container.register<Counter>(it.id)
                .toValue(ChessmanCounter(it.id,it.nameStr))
                .inTransientScope()
        }
    }

    fun getChessmanToken(token:BasicToken):ChessmanCounter{
        return Container.resolve(token.id, ChessmanCounter::class.java)
    }

    fun getBasicTokenList():List<ChessmanCounter>{
        return BasicToken.values().map {
            getChessmanToken(it)
        }
    }

    enum class BasicToken(val id:String,val nameStr:String){
        STR("str","力量"),
        CON("con","体质"),
        DEX("dex","敏捷"),
        WIS("wis","感知"),
        INT("int","智力"),
        SAN("san","理智")
    }
}