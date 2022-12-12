package org.co2dice.mirai.bean.cards

import org.co2dice.mirai.bean.cards.character.CharacterCard


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-11-18:11
 * @Message: Have a good time!  :)
 **/
interface Possessive {
    var holder: CharacterCard?
    //持有者可为空
}