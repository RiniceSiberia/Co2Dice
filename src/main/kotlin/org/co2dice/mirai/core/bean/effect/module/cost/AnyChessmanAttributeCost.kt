package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.attribute.prototype.Attribute
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-04-17:17
 * @Message: 单一任意角色支付属性点的cost
 * 如果有类似沙利亚这种加税(加消耗属性点的)的，加入到修饰器里
 **/
@Serializable(with = AnyChessmanAttributeCostSerializer::class)
class AnyChessmanAttributeCost(val table : Map<Attribute,JsonObject>) : MultipleSelectionCost<ChessmanInstance> {
    //最简单的，必须比这个值大
    override fun check(situation: PreActivationSituation): List<ChessmanInstance> {
        val param = Params(mutableMapOf(),situation)
        return situation.scene.field.chessmen.keys.stream().filter { chessman ->
            table.all {
                chessman.attributeTable.contain(it.key) && chessman.attributeTable.getValue(it.key)!! >= (AstTree(it.value).execute<Int>(param) ?: return@all false)
            }
        }.toList()
    }

    override fun execute(obj : ChessmanInstance, situation: ActivationSituation): Boolean {
        val param = Params(mutableMapOf(),situation)
        return obj.attributeTable.payCost(
            table = AttributeTable(
                mutableMapOf<Attribute, Int>().apply {
                    for (entry in table){
                        this[entry.key] = AstTree(entry.value).execute<Int>(param)?: return false
                    }
                }
            )
        )
    }


}
object AnyChessmanAttributeCostSerializer : KSerializer<AnyChessmanAttributeCost> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("AnyChessmanAttributeCost") {
        element("table", MapSerializer(Attribute.serializer(),JsonElement.serializer()).descriptor)
    }

    override fun deserialize(decoder: Decoder): AnyChessmanAttributeCost {
        return AnyChessmanAttributeCost(
            decoder.decodeSerializableValue(
                MapSerializer(Attribute.serializer(),JsonElement.serializer())
            ).map { (key, value) -> key to value.jsonObject }.toMap()
        )
    }

    override fun serialize(encoder: Encoder, value: AnyChessmanAttributeCost) {
        return encoder.encodeSerializableValue(
            MapSerializer(Attribute.serializer(),JsonElement.serializer()),
            value.table.map { (key, value) -> key to value }.toMap()
        )
    }

}