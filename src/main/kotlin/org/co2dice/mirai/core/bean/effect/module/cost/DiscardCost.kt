package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.BiOpNode
import org.co2dice.mirai.core.ast.node.ConstantLeafNode
import org.co2dice.mirai.core.ast.node.SituationLeafNode
import org.co2dice.mirai.core.ast.node.UniOpNode
import org.co2dice.mirai.core.ast.symbol.impl.game.situation.GetDesk
import org.co2dice.mirai.core.ast.symbol.impl.game.situation.GetPlayer
import org.co2dice.mirai.core.ast.symbol.impl.game.zone.GetHand
import org.co2dice.mirai.core.ast.symbol.impl.game.zone.HandCombinationRestrictAmountSymbol
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.IntegerConstant
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.situation.PreActivationSituationSymbol
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.effect.module.cost.CostConstUtils.DISCARD_COST
import org.co2dice.mirai.core.bean.game.zone.instance.DeskInstance
import org.co2dice.mirai.core.bean.game.zone.instance.HandInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-22-23:39
 * {@code @Message:} 丢卡，check会返回丢卡的所有可能性组合
 **/
class DiscardCost(
    val getCard :AstTree = AstTree(
        json = BiOpNode<List<Set<CardInstance>>,HandInstance,Int>(
            symbol = HandCombinationRestrictAmountSymbol,
            left = UniOpNode<HandInstance,DeskInstance>(
                symbol = GetHand,
                child = BiOpNode<DeskInstance, PreActivationSituation, PlayerInstance>(
                    symbol = GetDesk,
                    left = SituationLeafNode<PreActivationSituation>(
                        symbol = PreActivationSituationSymbol,
                    ),
                    right =UniOpNode<PlayerInstance, PreActivationSituation>(
                        symbol = GetPlayer,
                        child = SituationLeafNode<PreActivationSituation>(
                            symbol = PreActivationSituationSymbol,
                        ),
                    ),
                ),
            ),
            right = ConstantLeafNode<Int>(
                symbol = IntegerConstant,
                value = 1,
            )
        ).serialize()
    )
    //测试案例，1张卡的结果混合
) : MultipleSelectionCost<Set<CardInstance>> {
    override val costName: String = DISCARD_COST

    override fun getCosts(situation: PreActivationSituation): List<Set<CardInstance>> {
        val params = Params(mutableMapOf(),situation)
        return getCard.execute<List<Set<CardInstance>>>(params)?.distinct() ?: emptyList()
    }

    override fun practice(obj : Set<CardInstance>, situation: ActivationSituation): Boolean {
        return situation.getHand().discard(obj)
    }
}

object DiscardCostSerializer : KSerializer<DiscardCost>{
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DiscardCost") {
        element("getCard", AstTree.serializer().descriptor)
    }

    override fun deserialize(decoder: Decoder): DiscardCost {
        return DiscardCost(decoder.decodeSerializableValue(AstTree.serializer()))
    }

    override fun serialize(encoder: Encoder, value: DiscardCost) {
        encoder.encodeSerializableValue(AstTree.serializer(),value.getCard)
    }

}