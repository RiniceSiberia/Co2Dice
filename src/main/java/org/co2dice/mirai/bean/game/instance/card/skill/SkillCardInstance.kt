package org.co2dice.mirai.bean.game.instance.card.skill


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.prototype.card.SkillCard
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.effect.Effect
import org.co2dice.mirai.bean.game.prototype.effect.EffectActive
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
class SkillCardInstance(
    override val entry: CardEntry<SkillCard>,
    //    val inputParam:MutableMap<String,String>,
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    override var holder: Chess?,

) : CardInstance(entry,CardType.SKILL), Possessive {



}