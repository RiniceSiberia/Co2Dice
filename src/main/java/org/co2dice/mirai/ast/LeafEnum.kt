package org.co2dice.mirai.ast

import com.mojang.datafixers.util.Pair
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.LeafNode
import org.co2dice.mirai.ast.node.number.leaf.NumberConstantNode
import org.co2dice.mirai.ast.node.number.leaf.NumberParamNode
import org.co2dice.mirai.ast.node.number.leaf.NumberPlaceholderNode
import java.lang.reflect.Constructor
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:26
 * @Message: Have a good time!  :)
 **/
enum class LeafEnum(val constructor : (Any) -> LeafNode<*>, private val matchRule : String) {
    NUMBER_PARAM({o -> NumberParamNode(paramName = o.toString()) },"/^\\w+:INT\$/i"),
    //自定义传参可以是任意字符，下划线和字母的组合
    NUMBER_PLACEHOLDER({NumberPlaceholderNode() },"null_int/i"),
    //NULL代表占位符
    NUMBER_CONSTANT({o -> NumberConstantNode(o as Int)},"^\\+?[0-9]*\$"),
    //常量必须是自然数
//    STRING_PARAM(o -> StringParamNode())),
//    STRING_PLACEHOLDER(o -> StringPlaceholderNode())),
//    STRING_CONSTANT(o -> StringConstantNode())),
    ;

    fun getName() : String {
        //返回名字的小写
        return this.name.lowercase(Locale.getDefault())
    }

    companion object{
        fun matchSymbol(input : String) : Pair<LeafEnum,String>? {
            for (l in values()) {
                if (Regex(l.matchRule).find(input) != null) {
                    val v = Regex(l.matchRule).find(input)!!.value
                    //将查找到的字符串变为变量
                    //删除匹配到的字符串
                    Regex(l.matchRule).replace(input,"")
                    return Pair(l,v)
                }
            }
            return null
        }
    }


}