package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.SymbolEnum
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.generic.EqualsNode
import org.co2dice.mirai.ast.node.number.leaf.NumberPlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-16:23
 * @Message: Have a good time!  :)
 **/
class NumberEqualsNode(
    left : AstNode<Int> = NumberPlaceholderNode(),
    right : AstNode<Int> = NumberPlaceholderNode(),
) : EqualsNode<Int>(left,right) {
    override val name: String = SymbolEnum.NUMBER_EQUALS.getName()
}