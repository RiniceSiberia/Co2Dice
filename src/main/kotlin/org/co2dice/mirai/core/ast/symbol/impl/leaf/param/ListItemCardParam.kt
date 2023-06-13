package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.card.instance.ItemCardInstance
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:01
 * @Message:一个stream的param节点，主要用来获取一个区域的所有卡片
 * 但是很蛋疼，泛型擦除之后，这个节点的类型就是List<*>不保留泛型
 * 在考虑要不要从头干
 **/
object ListItemCardParam : ParamLeafSymbol<Stream<ItemCardInstance>>(){

    override fun natualSign(key: String): String {
        return "$key :: Stream<ItemCardInstance>"
    }

    override fun operation(key: String, params:Params): Stream<ItemCardInstance> {
        return params.get(key)
    }

    override val clazz: KClass<Stream<*>> = Stream::class

}