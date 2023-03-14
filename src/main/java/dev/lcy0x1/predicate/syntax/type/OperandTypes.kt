package dev.lcy0x1.predicate.syntax.type

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import dev.lcy0x1.predicate.api.instance.IBiFunction
import dev.lcy0x1.predicate.api.instance.IFunction
import dev.lcy0x1.predicate.instance.*
import dev.lcy0x1.util.ListHelper

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-12-22:42
 * @Message: Have a good time!  :)
 **/
object OperandTypes {
    //这个类记录可操作的数据类型，基本类如bool,number,name,attribute,entity
    val BOOL = OperandType("bool",
        encoder = { bool: Boolean? -> JsonPrimitive(bool) },
        decoder = IDecoder.createIDecoder { t: OperandType<Boolean>?, _: PredicateContext?, e: JsonElement ->
            ValueInstanceConstant(t, e.asBoolean) }
    )
    //布尔类型，编码是调用JsonPrimitive的构造函数，解码是调用ValueInstanceConstant的构造函数

    val NUMBER = OperandType("number",
        encoder = { number: Int? -> JsonPrimitive(number) },
        decoder = IDecoder.createIDecoder { t: OperandType<Int>?, _: PredicateContext?, e: JsonElement ->
            ValueInstanceConstant(t, e.asInt) }
    )

    val STRING = OperandType("string",
        encoder = { string: String? -> JsonPrimitive(string) },
        decoder = IDecoder.createIDecoder { t: OperandType<String>?, _: PredicateContext?, e: JsonElement ->
            ValueInstanceConstant(t, e.asString) }
    )

    val ATTRIBUTE = OperandType("attribute",
        encoder = { e: IAttributeType<*> -> JsonPrimitive(e.name()) },
        decoder = IDecoder.createIDecoder { t: OperandType<IAttributeType<*>>?, ctx: PredicateContext, e: JsonElement ->
            ValueInstanceConstant(t, ctx.env.parseAttributeType(e.asString)) }
    )

//    val ENTITY = OperandType<IEntity>("entity", null, null)

    fun <E> listType(element: OperandType<E>): OperandType<List<E>>? {
        return GenericType1("list", element,
            { list: List<E>? -> ListHelper.collect(JsonArray(), list) { a: JsonArray, e: E -> a.add(element.encode(e)) } },
            IDecoder.createIDecoder { t: OperandType<List<E>>?, _: PredicateContext?, elem: JsonElement ->
                if (!elem.isJsonArray) {
                    throw RuntimeException("$elem is not a list")
                }
                val arr = elem.asJsonArray
                ValueInstanceList(t) //TODO
            }
        )
    }

    fun <O, I> functionType(output: OperandType<O>?, input: OperandType<I>?): OperandType<IFunction<O, I>?> {
        return GenericType2("function", output, input)
    }

    fun <O, A, B> biFunctionType(
        output: OperandType<O>?,
        a: OperandType<A>?,
        b: OperandType<B>?
    ): OperandType<IBiFunction<O, A, B>?> {
        return GenericType3("bifunction", output, a, b)
    }
}