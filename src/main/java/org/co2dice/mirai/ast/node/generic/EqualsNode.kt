package org.co2dice.mirai.ast.node.generic

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-18:08
 * @Message: Have a good time!  :)
 **/
abstract class EqualsNode<T>(
    override var left :  AstNode<T>,
    override var right : AstNode<T>,
) : PairChildNode<T, T, Boolean>() {


    override fun operation(param : Map<String,Any>) : Boolean{
        return left!!.operation(param) == right!!.operation(param)
    }

    override fun toString(): String {
        return "=="
    }




}