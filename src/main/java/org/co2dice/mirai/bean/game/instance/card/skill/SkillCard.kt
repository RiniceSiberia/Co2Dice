package org.co2dice.mirai.bean.game.instance.card.skill


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.effect.Effect
import org.co2dice.mirai.bean.game.prototype.effect.EffectActive
import org.co2dice.mirai.bean.game.prototype.Card
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
class SkillCard(

    val inputParam:MutableMap<String,String>,
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    override var holder: Chess?,
    //持有者可为空
    val burnValue:List<CharacterToken>,
    //燃烧时使用的token
    val effects:MutableList<Effect<Card>>,
    //技能池
    val recycle:Function2<Scene, SkillCard,Int>,
    //回收技能时的处理函数


    var burnEffect :Function2<SkillCard, EffectActive,Unit> = {
            skillCard,effect ->
        for (CharacterToken in skillCard.burnValue){
            effect.skillParam[CharacterToken.id] = effect.skillParam[CharacterToken.id]?.plus(1)?:1
        }
    }
    //弃牌加成（没写手牌和丢弃）

) : CardInstance<*>(null), Possessive {



}