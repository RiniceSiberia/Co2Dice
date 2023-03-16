package org.co2dice.mirai.ast

import com.mojang.datafixers.util.Pair
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.BranchNode
import org.co2dice.mirai.ast.node.number.*
import java.lang.reflect.Constructor
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:19
 * @Message: Have a good time!  :)
 **/
enum class SymbolEnum(val constructor : (List<AstNode<*>>) -> BranchNode<*>,private val matchRule:String) {
    PLUS({l -> PlusNode(left = l[0] as AstNode<Int>, right = l[1] as AstNode<Int>) },"+"),
    MINUS({l -> MinusNode() },"-"),
    MULTIPLY({l -> MultiplyNode() },"*"),
    DIVIDE({l -> DivideNode() },"/"),
    NUMBER_EQUALS({l -> NumberEqualsNode() },"=="),
    GREATER({l -> GreaterNode() },">"),
    LESS({l -> LessNode() },"<"),
    ;



    fun getName() : String {
        //返回名字的小写
        return this.name.lowercase(Locale.getDefault())
    }

    companion object{
        fun matchSymbol(input : String) : SymbolEnum? {
            for (l in SymbolEnum.values()) {
                if (Regex(l.matchRule).find(input) != null) {
                    return l
                }
            }
            return null
        }
    }
}