package org.co2dice.mirai.bean.cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:35
 * @Message: 一个技能输出的混乱和秩序值的容器
 **/
class CAOPot (val chaos:Int,val order:Int) {
    override fun toString(): String {
        return "混乱值:${chaos},秩序值:${order}"
    }
}