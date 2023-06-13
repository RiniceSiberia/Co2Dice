package org.co2dice.mirai.core.ast.symbol.basic.api.stream

import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-20-15:36
 * @Message: 输入一个stream和一个处理stream的函数，返回经过函数处理的stream
 **/
abstract class StreamSymbolApi<O : Any,L : Any,A : Any>: BiOpSymbol<O, Stream<L>,AstTree<A>>() {

}