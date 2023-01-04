package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.game.Battle
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:36
 * @Message: 衍生物类
 * 衍生物是一种类似于属性和指示物的东西，它可以根据类型的不同放置在卡上，用来表示一些状态。
 **/
abstract class Token {
    abstract val id:String
    //id
    abstract val name:String
    //token的名称
    abstract val type:CardType
    //可以放在什么卡上
    var isTempToken = false
    //是否是临时token
    var lifetime:Int = -1
    //临时token的生命周期，isTempToken为true时有效
    abstract var point:Int
    //每个token提供的属性值，用于计算属性，0是无数值，负数会倒扣属性
    // 大于1或者小于1会当做n点属性的加值来计算，比如value=5就等于这是一个+5属性的点，但扣除依然按照一个来计算

    var checkPoint:Function2<Battle<Any?>,CharacterCard,Boolean> = checkPoint@{ _, _ ->
        return@checkPoint isTempToken
    }

    var timeFlow:Function2<Battle<Any?>,CharacterCard,Boolean> = timeFlow@{ _, _ ->
        //触发检查点时自动调用的方法,true视为时间结束，默认方法为检查是否是临时token，如果是临时token则减少生命周期，如果生命周期为0则返回true
        if (isTempToken){
            lifetime--
            if (lifetime <= 0){
                return@timeFlow true
                //生命周期结束
            }
        }
        return@timeFlow false
    }


    fun addEvent(fuller: TokenFuller):Boolean{
        //添加事件
        return true
    }
    fun removedEvent(fuller: TokenFuller):Boolean{
        //删除事件
        return true
    }
    fun canReplace(token: Token):Boolean{
        //检查是否可以替代传参中的token
        return this == token
    }
}