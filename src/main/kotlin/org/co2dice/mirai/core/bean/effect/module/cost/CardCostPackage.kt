package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import org.co2dice.mirai.core.bean.effect.module.cost.CostConstUtils.ANY_CHESSMAN_ATTRIBUTE_COST
import org.co2dice.mirai.core.bean.effect.module.cost.CostConstUtils.DISCARD_COST
import org.co2dice.mirai.core.bean.effect.module.cost.CostConstUtils.THIS_CARD_COST
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-15:55
 * {@code @Message:} 卡片实体的效果的费用包装类
 **/
class CardCostPackage  (
    val thisCardCost: ThisCardCost? = null,
    //将这张卡作为cost
    val anyChessmanAttributeCost: AnyChessmanAttributeCost? = null,
    //支付属性作为cost
    val discardCost: DiscardCost ?= null,
    //弃牌作为cost
) : CostPackage{
    override fun check(situation: PreActivationSituation) : Boolean{
        return (thisCardCost?.getCosts(situation) != null)
            && (anyChessmanAttributeCost?.getCosts(situation) != null)
            && (discardCost?.getCosts(situation) != null)
        //确认里面没有空集
        //最内层的set是单张卡的合集,第二层是单个cost的合集，第三层是总的合集
        //这三层list,总的合集要和单合集混合起来，成为list<set<cardInstance>>,使用循环穷举,从0到size-1,将每个list的所有可能性的合集交叉混合起来
    }

    override fun getSelectScope(situation: PreActivationSituation): JsonObject {
        return JsonObject(mapOf(
            if (thisCardCost != null)
                THIS_CARD_COST to Json.encodeToJsonElement(thisCardCost.getCosts(situation))
            else
                THIS_CARD_COST to JsonNull,
            if (anyChessmanAttributeCost != null)
                ANY_CHESSMAN_ATTRIBUTE_COST to Json.encodeToJsonElement(anyChessmanAttributeCost.getCosts(situation))
            else
                ANY_CHESSMAN_ATTRIBUTE_COST to JsonNull,
            if (discardCost != null)
                DISCARD_COST to Json.encodeToJsonElement(discardCost.getCosts(situation))
            else
                DISCARD_COST to JsonNull


        ))
    }

    override fun practice(situation: ActivationSituation): Boolean {
        return (thisCardCost?.operate(situation) ?: true)
                && (anyChessmanAttributeCost?.operate(situation) ?: true)
                && (discardCost?.operate(situation) ?: true)
    }

}

object CardCostPackageSerializer : KSerializer<CardCostPackage>{
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CardCostPackage"){
        element(THIS_CARD_COST,ThisCardCostSerializer.descriptor)
        element(ANY_CHESSMAN_ATTRIBUTE_COST,AnyChessmanAttributeCostSerializer.descriptor)
        element(DISCARD_COST,DiscardCostSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): CardCostPackage {
        val json = decoder.decodeSerializableValue(JsonObject.serializer())
        return CardCostPackage(
            Json.decodeFromJsonElement(json[THIS_CARD_COST] ?: JsonNull),
            Json.decodeFromJsonElement(json[ANY_CHESSMAN_ATTRIBUTE_COST] ?: JsonNull),
            Json.decodeFromJsonElement(json[DISCARD_COST] ?: JsonNull)
        )
    }

    override fun serialize(encoder: Encoder, value: CardCostPackage) {
        encoder.encodeSerializableValue(JsonObject.serializer(), JsonObject(mapOf(
            THIS_CARD_COST to Json.encodeToJsonElement(value.thisCardCost),
            ANY_CHESSMAN_ATTRIBUTE_COST to Json.encodeToJsonElement(value.anyChessmanAttributeCost),
            DISCARD_COST to Json.encodeToJsonElement(value.discardCost)
        )))
    }

}

