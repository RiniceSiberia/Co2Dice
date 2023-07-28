package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.card.instance.MainDeckCardInstance
import org.co2dice.mirai.core.utils.ConstantUtils.IT
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-22-23:39
 * {@code @Message:} 丢卡，check会返回丢卡的所有可能性组合
 **/
@Serializable
class DiscardCost(
    val filterSeg : AstTree,
//    = AstTree(
//        json = BiOpNode<Boolean,Int,Int>(
//            symbol =Greater,
//            left = UniOpNode<Int,CardInstance>(
//                symbol = GetCardChaos,
//                child = ParamLeafNode<MainDeckCardInstance>(
//                    symbol = MainDeckCardParam,
//                    key = IT
//                )
//            ),
//            right = ConstantLeafNode<Int>(
//                symbol = IntegerConstant,
//                value = 5
//            ),
//        ).serialize()
//    ),
    //样板,选择chaos >5的卡丢弃
    val filterSet : AstTree,
//      = AstTree(
//        json = BiOpNode<Boolean,Int,Int>(
//            symbol = Equal,
//            left = UniOpNode<Int,Set<*>>(
//                symbol = SetSizeSymbol,
//                child = ParamLeafNode<Set<MainDeckCardInstance>>(
//                    symbol = SetMainDeckCardParam,
//                    key = IT
//                )
//            ),
//            right = ConstantLeafNode<Int>(
//                symbol = IntegerConstant,
//                value = 2,
//            ),
//        ).serialize(),
//    ),
    //测试案例，丢弃2张卡
) : ManyToManySelectionCost<MainDeckCardInstance> {
    override fun getScope(input: Map<String, Any>, situation: PreActivationSituation)
    : Set<MainDeckCardInstance> {
        return situation.getHand().get().filter {
            filterSeg.execute<Boolean>(Params(input.toMutableMap().also {p -> p[IT] = it },situation)) == true
        }.toSet()
    }

    override fun getCostScope(input: Map<String, Any>, situation: PreActivationSituation)
    : Map<Int, Set<MainDeckCardInstance>> {
        return super.getCostScope(input, situation).filter {
            filterSet.execute<Boolean>(Params(input.toMutableMap().also {p -> p[IT] = it.value },situation)) == true
        }
    }

    override fun toJson(obj: MainDeckCardInstance): JsonElement {
        return Json.encodeToJsonElement(obj)
    }

    override fun practice(
        input: Map<String, Any>,
        obj: Set<MainDeckCardInstance>,
        situation: ActivationSituation
    ): Set<Any> {
        if (situation.scene.moveCards(obj,situation.getHand(),situation.getGy())){
            return obj
        }
        return emptySet()
    }
}