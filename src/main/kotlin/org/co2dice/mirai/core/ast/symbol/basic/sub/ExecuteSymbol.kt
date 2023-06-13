package org.co2dice.mirai.core.ast.symbol.basic.sub

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.ConsumerNode
import org.co2dice.mirai.core.ast.symbol.basic.ConsumerSymbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-08-12:33
 * @Message: Have a good time!  :)
 **/
abstract class ExecuteSymbol<O : Any> : ConsumerSymbol<O,O>(){
    override fun operation(function: AstTree, params: Params): O {
        return execute(function,params)!!
    }
}