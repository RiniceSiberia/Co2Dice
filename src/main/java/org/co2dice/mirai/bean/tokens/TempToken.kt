package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.character.Character

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-07-12:52
 * @Message: Have a good time!  :)
 **/
interface TempToken {
    var lifeTime:Int
    var checkPoint:Function2<Battle,Character,Boolean>
    //检查是否需要进行时间流逝操作

    var timeFlow :Function2<Battle,Character,Boolean>
    //时间流逝,减少生命时间,返回为是否需要删除
}