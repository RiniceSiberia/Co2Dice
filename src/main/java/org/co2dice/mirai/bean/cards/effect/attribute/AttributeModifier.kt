package org.co2dice.mirai.bean.cards.effect.attribute

import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-14-23:34
 * @Message: Have a good time!  :)
 **/
class AttributeModifier(
    val predicate: PredicateEnum,
    //目标,判断一张卡是否属于目标
    val operation:OperationEnum,
    //效果,具体如何执行
    val source:Cards
    //来源,来自哪张卡
    ) {
}