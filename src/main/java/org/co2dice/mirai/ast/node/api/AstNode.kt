package org.co2dice.mirai.ast.node.api

import com.google.gson.JsonElement
import com.mojang.datafixers.util.Either

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-17:46
 * @Message: Have a good time!  :)
 **/
abstract class AstNode <
    O
    //输出
    >{
    abstract val name : String

    abstract fun operation(param : Map<String,Any>):O
        //这个节点的运算方式，计算这个节点的运算结果

    abstract fun toJSONElement() : JsonElement

    abstract fun isEmpty() : Boolean

}