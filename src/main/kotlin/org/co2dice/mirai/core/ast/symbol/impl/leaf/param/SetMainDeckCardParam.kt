package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.card.instance.MainDeckCardInstance
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-21-17:49
 * @Message: 主卡组卡片集合的传参
 **/
object SetMainDeckCardParam : ParamLeafSymbol<Set<MainDeckCardInstance>>(){
    override val clazz: KClass<*> = Set::class

    override fun operation(key: String, params: Params): Set<MainDeckCardInstance> {
        return params.get(key)
    }

    override fun natualSign(key: String): String {
        return "$key :: Set<MainDeckUnPublicCard>"
    }
}