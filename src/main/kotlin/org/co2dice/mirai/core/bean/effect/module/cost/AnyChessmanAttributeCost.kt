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
import org.co2dice.mirai.core.utils.MathUtils.listToMap
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-04-17:17
 * {@code @Message:} 单一任意角色支付属性点的cost
 * 如果有类似沙利亚这种加税(加消耗属性点的)的，加入到修饰器里
 **/
@Serializable
class AnyChessmanAttributeCost(val table : Map<Attribute,JsonObject>) : ManyToOneSelectionCost<ChessmanInstance> {


    //最简单的，必须比这个值大
    override fun getCostScope(input : Map<String,Any>, situation: PreActivationSituation): Map<Int, ChessmanInstance> {
        val param = Params(input.toMutableMap(),situation)
        return situation.scene.field.chessmen.keys.stream().filter { chessman ->
            table.all {
                chessman.attributeTable.contain(it.key) && chessman.attributeTable.getValue(it.key)!! >= (AstTree(it.value).execute<Int>(param) ?: return@all false)
            }
        }.toList().listToMap()
    }

    override fun practice(input : Map<String,Any>,obj : ChessmanInstance, situation: ActivationSituation): Boolean {
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