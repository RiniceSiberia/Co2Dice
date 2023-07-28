package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
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
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-17:18
 * {@code @Message:} 发动效果的棋子支付属性
 **/
@Serializable
class ThisChessmanCost(
    val table : Map<Attribute, JsonObject>
) : OneToOneSelectionCost<ChessmanInstance> {

    override fun getCostScope(input: Map<String, Any>, situation: PreActivationSituation): ChessmanInstance? {
        val param = Params(input.toMutableMap(),situation)
        val agent = situation.getAgentChessmanInstance<ChessmanInstance>()
        if (agent != null && table.all {
                agent.attributeTable.contain(it.key)
                    && agent.attributeTable.getValue(it.key)!! >= (AstTree(it.value).execute<Int>(param) ?: return@all false)
            } ){
            return agent
        }
        return null
    }

    override fun practice(input: Map<String, Any>, obj: ChessmanInstance, situation: ActivationSituation): Boolean {
        val param = Params(input.toMutableMap(),situation)
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
    override fun toJson(obj: ChessmanInstance): JsonElement {
        return Json.encodeToJsonElement(obj)
    }
}