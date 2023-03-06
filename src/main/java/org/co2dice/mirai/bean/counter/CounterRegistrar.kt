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


}