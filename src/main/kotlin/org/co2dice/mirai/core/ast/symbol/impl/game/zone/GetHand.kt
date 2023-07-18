package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.game.zone.instance.DeskInstance
import org.co2dice.mirai.core.bean.game.zone.instance.HandInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-17-20:49
 * @Message: Have a good time!  :)
 **/
object GetHand : UniOpSymbol<HandInstance,DeskInstance>() {
    override fun natualSign(input: INode<out DeskInstance>): String {
        return "${input.natualSerialize()}.getHand()"
    }

    override fun operation(input: DeskInstance, params: Params): HandInstance {
        return input.hand
    }
}