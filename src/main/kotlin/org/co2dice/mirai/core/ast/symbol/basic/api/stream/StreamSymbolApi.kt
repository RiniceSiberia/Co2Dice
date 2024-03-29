package org.co2dice.mirai.core.ast.symbol.basic.api.stream

import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-20-15:36
 * {@code @Message:} 输入一个stream和一个处理stream的函数，返回经过函数处理的stream
 * O : 输出
 * L : 流类型
 * A : Ast输出类型
 **/
abstract class StreamSymbolApi<O : Any,L : Any>: BiOpSymbol<O, Stream<L>,AstTree>() {

}