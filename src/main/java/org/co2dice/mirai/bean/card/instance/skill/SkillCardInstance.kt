package org.co2dice.mirai.bean.card.instance.skill


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.API.DependPlayer
import org.co2dice.mirai.bean.card.entry.CardEntry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
class SkillCardInstance(
    override val entry: CardEntry,
    //    val inputParam:MutableMap<String,String>,
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    override var holder: Player? = null,

    ) : CardInstance(entry), DependPlayer {



}