package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:01
 * @Message:一个list的param节点，主要用来获取一个区域的所有卡片
 **/
object ListItemCardParam : ParamLeafSymbol<List<ItemCardInstance>>(){

    override fun natualSign(key: String): String {
        return "$key :: List<ItemCardInstance>"
    }

    override fun operation(key: String, params: Params): List<ItemCardInstance> {
        return params.get(key)
    }

    override val clazz: KClass<List<*>> = List::class

}