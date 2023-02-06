package org.co2dice.mirai.bean.cards.skill


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.cards.api.CAO
import org.co2dice.mirai.bean.cards.CardsInstance
import org.co2dice.mirai.bean.cards.api.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.cards.effect.EffectActive
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
abstract class SkillCard() : CardsInstance(), CAO, Possessive {

    abstract val inputParam:MutableMap<String,String>
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    abstract override var holder: CharacterCard?
    //持有者可为空
    abstract val burnValue:List<CharacterToken>
    //燃烧时使用的token
    abstract val effects:MutableList<Effect>
    //技能池
    abstract val recycle:Function2<Scene,SkillCard,Int>
    //回收技能时的处理函数
    override fun getChaos(): Int {
        //获取所有技能的混乱值,并取最大值
        return effects.stream().mapToInt { it.getChaos() }.max().orElse(0)
    }

    override fun getOrder(): Int {
        //获取所有技能的秩序值取和
        return effects.stream().mapToInt { it.getOrder() }.sum()
    }


    var burnEffect :Function2<SkillCard,EffectActive,Unit> = {
        skillCard,effect ->
        for (CharacterToken in skillCard.burnValue){
            effect.skillParam[CharacterToken.id] = effect.skillParam[CharacterToken.id]?.plus(1)?:1
        }
    }
    //弃牌加成（没写手牌和丢弃）


}