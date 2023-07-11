package org.co2dice.mirai.core.bean.effect.module.cost


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-15:55
 * @Message: 卡片费用包装类
 **/
class CardCostPackage  (
    val thisCardCost: ThisCardCost? = null,
    //将这张卡作为cost,只能有一个
    val anyChessmanAttributeCost: AnyChessmanAttributeCost? = null,
    //支付属性作为cost,可以有多个
    val discardCost: DiscardCost? = null,
    //弃牌作为cost,可以有多个
) : CostPackage{

}