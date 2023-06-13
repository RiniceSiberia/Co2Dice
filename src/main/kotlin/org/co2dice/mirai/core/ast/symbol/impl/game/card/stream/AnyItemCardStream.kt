package org.co2dice.mirai.core.ast.symbol.impl.game.card.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.api.stream.StreamSymbolApi
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.card.instance.ItemCardInstance
import org.co2dice.mirai.core.utils.ConstantUtils.IT
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-21-19:18
 * @Message: 抓取场上属性相同的道具实体stream处理
 **/
object AnyItemCardStream : StreamSymbolApi<Boolean, ItemCardInstance,Boolean>() {

    override fun natualSign(left: INode<Stream<ItemCardInstance>>, right: INode<AstTree<Boolean>>): String {
        return "(${left.natualSerialize()}.AnyItemCardStream(${right.natualSerialize()}))"
    }

    override fun operation(l: Stream<ItemCardInstance>, r: AstTree<Boolean>, params:Params): Boolean {
        val newParams = Params(params.map,params.situation)
        return l.anyMatch {
            newParams.add(IT,it)
            r.execute(newParams)?:false.also {
                newParams.remove(IT)
            }
        }
    }
}