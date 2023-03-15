package org.co2dice.mirai.ast

import com.google.gson.*
import org.co2dice.mirai.ast.node.api.AstNode
import java.lang.reflect.Type

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-18:59
 * @Message: Have a good time!  :)
 **/
object AstSerializer : JsonSerializer<Tree<*>> , JsonDeserializer<Tree<*>> {
    override fun serialize(src: Tree<*>?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {



    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Tree<*> {




    }

}