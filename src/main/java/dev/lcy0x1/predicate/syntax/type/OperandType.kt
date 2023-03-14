package dev.lcy0x1.predicate.syntax.type

import com.google.gson.JsonElement
import dev.lcy0x1.predicate.datagen.encoder.ConstantToken
import dev.lcy0x1.predicate.datagen.encoder.IValueToken
import dev.lcy0x1.predicate.instance.IValueInstance
import dev.lcy0x1.predicate.instance.PredicateContext
import java.util.function.Function

/**
 *      使用IDEA编写
 * @Author: Lcy0x1
 * @Time:  2022/12/18
 * @Message: 这是操作符号类，T代表了不同的操作类型
 **/
open class OperandType<T>(
        private val name: String,
        private val encoder: Function<T, JsonElement>,
        private val decoder: IDecoder<T>
) {
    fun parse(ctx: PredicateContext, value: Function<PredicateContext?, T>?): IValueInstance<T>? {
        return ctx.buildInstance(this, value)
    }
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        return other is OperandType<*> && name == other.name;
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun constantToken(`val`: T): IValueToken<T> {
        return ConstantToken(this, `val`)
    }

    fun encode(value: T): JsonElement? {
        return encoder.apply(value)
    }
    fun decode(ctx: PredicateContext, elem: JsonElement): IValueInstance<*>? {
        if (elem.isJsonPrimitive) {
            val prim = elem.asJsonPrimitive
            if (prim.isString) {
                val str = prim.asString
                if (str.startsWith("$")) {
                    return ctx.getField(str)
                }
            }
        }
        return decoder.decode(this, ctx, elem)
    }

}