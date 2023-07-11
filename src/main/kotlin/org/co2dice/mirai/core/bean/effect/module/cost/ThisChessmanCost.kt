package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
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
import org.co2dice.mirai.core.utils.situation.getAgentChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-17:18
 * @Message: 发动效果的棋子支付属性
 **/
@Serializable(with = ThisChessmanCostSerializer::class)
class ThisChessmanCost(
    val table : Map<Attribute, JsonObject>
) : OnlySelectionCost<ChessmanInstance> {
    override fun check(situation: PreActivationSituation): ChessmanInstance? {
        val param = Params(mutableMapOf(),situation)
        val agent = situation.getAgentChessmanInstance<ChessmanInstance>()
        if (agent != null && table.all {
                agent.attributeTable.contain(it.key)
                    && agent.attributeTable.getValue(it.key)!! >= (AstTree(it.value).execute<Int>(param) ?: return@all false)
            } ){
            return agent
        }
        return null
    }

    override fun execute(
        obj: ChessmanInstance,
        situation: ActivationSituation
    ): Boolean {
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
object ThisChessmanCostSerializer : KSerializer<ThisChessmanCost> {
    override val descriptor = buildClassSerialDescriptor("ThisChessmanCost"){
        element("table", MapSerializer(Attribute.serializer(), JsonElement.serializer()).descriptor)
    }

    override fun deserialize(decoder: Decoder): ThisChessmanCost {
        return ThisChessmanCost(
            decoder.decodeSerializableValue(
                MapSerializer(Attribute.serializer(),JsonElement.serializer())
            ).map { (key, value) -> key to value.jsonObject }.toMap()
        )
    }

    override fun serialize(encoder: Encoder, value: ThisChessmanCost) {
        return encoder.encodeSerializableValue(
            MapSerializer(Attribute.serializer(),JsonElement.serializer()),
            value.table.map { (key, value) -> key to value }.toMap()
        )
    }
}