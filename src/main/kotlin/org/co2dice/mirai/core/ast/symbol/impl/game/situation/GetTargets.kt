package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.activated.EffectTargets
import org.co2dice.mirai.core.utils.situation.ActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:15
 * @Message: Have a good time!  :)
 **/
object GetTargets : UniOpSymbol<EffectTargets, ActivationSituation>() {

    override fun natualSign(input: INode<out ActivationSituation>): String {
        return "$input.getTargets()"
    }

    override fun operation(input: ActivationSituation, params:Params): EffectTargets {
        return input.target
    }
}