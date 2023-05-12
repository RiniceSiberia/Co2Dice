package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.utils.situation.api.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:36
 * @Message: Have a good time!  :)
 **/
object GetScene : UniOpSymbol<Scene, SituationApi>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(input: INode<SituationApi>): String {
        return "$input.getScene()"
    }

    override fun operation(input: SituationApi): Scene {
        return input.scene
    }
}