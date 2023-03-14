package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-18:06
 * @Message: Have a good time!  :)
 **/
class DivisionNode (
    override var left :  AstNode< Int>?,
    override var right : AstNode<Int>?,
) : PairChildNode<Int, Int, Int>() {

    override val name: String = "division_node"

    override fun operation(param : Map<String,Any>) : Int{
        return left!!.operation(param) / right!!.operation(param)
    }

}