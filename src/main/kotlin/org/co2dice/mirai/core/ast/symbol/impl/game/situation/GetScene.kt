package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.game.zone.instance.Scene
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:36
 * @Message: Have a good time!  :)
 **/
object GetScene : UniOpSymbol<Scene, SituationApi>() {
    override fun natualSign(input: INode<out SituationApi>): String {
        return "$input.getScene()"
    }

    override fun operation(input: SituationApi, params:Params): Scene {
        return input.scene
    }
}