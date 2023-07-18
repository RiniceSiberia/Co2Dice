package org.co2dice.mirai.core.ast.tree

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import kotlin.jvm.Throws

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-26-20:26
 * {@code @Message:} Have a good time!  :)
 **/
class PreActionTree<O : Any> (private var root : INode<O>) {

    @Throws(NullPointerException::class)
    constructor(json : JsonObject) : this(
        root = SymbolRegistry.deserialize(json)
    )

    fun execute(input : MutableMap<String, Any>,situation : PreActivationSituation) : O?{
        try {
            return root.evaluate(Params(input,situation))
        }catch (e : Exception){
            e.printStackTrace()
        }
        return null
    }
}