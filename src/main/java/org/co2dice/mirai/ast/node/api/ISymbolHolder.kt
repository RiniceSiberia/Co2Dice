package org.co2dice.mirai.ast.node.api

import org.co2dice.mirai.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-04-13:38
 * @Message: Have a good time!  :)
 **/
interface ISymbolHolder <S : Symbol<*>>{
    val symbol : S
}