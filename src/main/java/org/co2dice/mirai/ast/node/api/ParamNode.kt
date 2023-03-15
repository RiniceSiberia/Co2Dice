package org.co2dice.mirai.ast.node.api

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-20:38
 * @Message: 这是一个特殊的节点，他没有具体的类型，是作为一个传参而使用的
 **/
abstract class ParamNode<T>(
    private val paramName : String,
) : AstNode<T>() {

    override fun operation(param : Map<String,Any>) : T{
         //获取本节点的参数，然后一个个检查param
         if (param[paramName] != null){
             val v = param[paramName]
             //如果v的class是泛型T，将其转换至T后返回
             return v as T
         }else{
             throw Exception("传参不匹配 : $paramName ")
         }
    }

    override fun toString(): String {
        return paramName
    }

    override fun vacancy(): Boolean {
        return false
    }

    override fun getChild(): List<AstNode<*>> {
        return emptyList()
    }


}