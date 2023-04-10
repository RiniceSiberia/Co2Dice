package org.co2dice.mirai.ast.symbol.impl.game.attribute

import org.co2dice.mirai.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-22:47
 * @Message: Have a good time!  :)
 **/
object GetAttributeValue : BiOpSymbol<Int, ChessmanInstance, AttributeAPI>() {
    override val natualSign: String = "getAttribute"

    override fun operation(l: ChessmanInstance, r: AttributeAPI): Int {
        return l.attributeInstanceTable.getValue(r)!!
    }

}