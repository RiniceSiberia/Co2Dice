package org.co2dice.mirai.bean.cards.effect.attribute

import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-17-23:55
 * @Message: 语法,用来进行语句合成
 **/
enum class PredicateEnum{
    NOT,
    //非
    AND,
    //和
    OR,
    //或
    XOR,
    //或非
    EQ,
    //等于
    LE,
    //小
    LEQ,
    //小或等
    GE,
    //大
    GEQ,
    //大或等
    IS,
    //是
    ONE_OF,
    //之一
    MATCH
    //多对多的is
}