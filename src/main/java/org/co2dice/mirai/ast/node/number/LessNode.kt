package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.SymbolEnum
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.branch.PairChildNode
import org.co2dice.mirai.ast.node.number.leaf.NumberPlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-20:04
 * @Message: Have a good time!  :)
 **/
class LessNode (
    override var left : AstNode<Int> = NumberPlaceholderNode(),
    override var right : AstNode<Int> = NumberPlaceholderNode(),
) : PairChildNode<Int, Int, Boolean>() {

    override val name: String = SymbolEnum.LESS.getName()

    override fun operation(param : Map<String,Any>) : Boolean{
        return left.operation(param) < right.operation(param)
    }
}