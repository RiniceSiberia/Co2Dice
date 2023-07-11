package org.co2dice.mirai.core.bean.effect.module.cost


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-17:05
 * @Message: Have a good time!  :)
 **/
class ChessmanCostPackage (
    val anyChessmanAttributeCost: AnyChessmanAttributeCost? = null,
    //支付属性作为cost,可以有多个
    val discardCost: DiscardCost? = null,
    //弃牌作为cost,可以有多个
) : CostPackage {

}