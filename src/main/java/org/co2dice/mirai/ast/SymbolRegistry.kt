package org.co2dice.mirai.ast

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.integer.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:19
 * @Message: Have a good time!  :)
 **/
object SymbolRegistry {

    private val symbolFactory = NodeFactory()

    init {
        for (s in Symbols.values()) {
            symbolFactory.register(s.symbolName, s.clazz)
        }
    }


    @SuppressWarnings("unchecked", "unsafe")
    fun <O>  getSymbol(symbolName: String): Symbol<O> {
        return symbolFactory.constructorMap[symbolName] as Symbol<O>
    }

    fun <O> parseNode(json:JsonObject):AstNode<O>{
        val symbol = getSymbol<O>(json.get("operation").asString)
        return symbol.createNodeFromJson(json)
    }



    fun match(input : String) : Pair<String,Pair<String,String>>? {
        val symbol:String
        val left:String
        val right:String
        for (s in Symbols.values()) {
            //如果是symbol，如A就是个symbol，则中间应该用逗号分割，用A\\(([^,]+),([^)]+)\\)的方式匹配
            if (Regex(s.symbolName + "\\\\(([^,]+),([^)]+)\\\\)").find(input) != null) {
                //匹配到了最外层的符号
                    input = Regex("(?<=^$s.symbolName\\().*?(?=\\)\$)").find(input)!!.value
                symbol = s.symbolName
                //移除掉最外层的符号
                Regex(s.symbolName + "\\\\(([^,]+),([^)]+)\\\\)").replace(input,"")



                break
            }
        }
        return null
    }



}