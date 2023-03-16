package org.co2dice.mirai.ast.node.number.leaf

import org.co2dice.mirai.ast.LeafEnum
import org.co2dice.mirai.ast.node.basic.leaf.ParamNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:11
 * @Message: Have a good time!  :)
 **/
class NumberParamNode(override var paramName: String = "x") : ParamNode<Int>() {
    override var name: String = LeafEnum.NUMBER_PARAM.getName()
}