package org.co2dice.mirai.core.bean.attribute.prototype

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-14:54
 * @Message: Have a good time!  :)
 **/
@Serializable(with = AttributeSerializer::class)
open class Attribute(
    val nameStr:String
) {
//    fun getTranslate() : String{
//        return "attribute.$nameStr"
//    }
    //翻译，留档
}
object Strength : Attribute("str")
object Constitution : Attribute("con")
object Dexterity : Attribute("dex")
object Wisdom : Attribute("wis")
object Intelligence : Attribute("int")
object Sanity : Attribute("san")
object Loyalty : Attribute("loyalty")

object AttributeSerializer : KSerializer<Attribute> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Attribute", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Attribute {
        return AttributeRegistry.getAttribute(decoder.decodeString())!!
    }

    override fun serialize(encoder: Encoder, value: Attribute) {
        encoder.encodeString(value.nameStr)
    }
}