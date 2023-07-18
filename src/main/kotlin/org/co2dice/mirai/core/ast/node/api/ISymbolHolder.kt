package org.co2dice.mirai.core.ast.node.api

import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-04-13:38
 * {@code @Message:} Have a good time!  :)
 **/
interface ISymbolHolder <S : Symbol<*>>{
    var symbol : S
}