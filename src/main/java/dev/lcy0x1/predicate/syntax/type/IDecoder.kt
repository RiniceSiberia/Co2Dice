package dev.lcy0x1.predicate.syntax.type

import com.google.gson.JsonElement
import dev.lcy0x1.predicate.instance.IValueInstance
import dev.lcy0x1.predicate.instance.PredicateContext

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-14:22
 * @Message: Have a good time!  :)
 **/
interface IDecoder<T> {
    fun decode(type: OperandType<T>, ctx: PredicateContext, elem: JsonElement): IValueInstance<T>?
    companion object{
        fun <T> createIDecoder(
            func : (type: OperandType<T>,
            ctx: PredicateContext,
            elem: JsonElement) -> IValueInstance<T>?) : IDecoder<T> = object : IDecoder<T> {
            override fun decode(type: OperandType<T>, ctx: PredicateContext, elem: JsonElement): IValueInstance<T>? {
                return func(type, ctx, elem)
            }
        }
    }
}