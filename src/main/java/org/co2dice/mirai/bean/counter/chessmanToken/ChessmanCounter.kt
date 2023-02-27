package org.co2dice.mirai.bean.counter.chessmanToken

import org.co2dice.mirai.utils.TokenDepend
import org.co2dice.mirai.bean.counter.Counter
import space.controlnet.lightioc.annotation.Provider

@Provider
open class ChessmanCounter(override val id: String, override val name: String)
    : Counter(id,name, TokenDepend.CHESSMAN) {


        fun registerDefaultToken(){
            //注册默认的token

        }
}