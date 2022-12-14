package org.co2dice.mirai.bean.cards.skill


import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.api.CAO
import org.co2dice.mirai.bean.cards.Cards
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
abstract class SkillCard() : Cards(), CAO, Possessive {

    abstract val inputParam:MutableMap<String,String>
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    abstract override var holder: CharacterCard?
    //持有者可为空
    abstract val burnValue:List<CharacterToken>
    //燃烧时使用的token
    abstract val effects:MutableSet<Effect>
    //技能池
    abstract val recycle:Function3<Scene,SkillCard,CharacterCard,Int>
    //回收技能时的处理函数
    override var chaos: Int=0
        get() {
            chaos = effects.stream().mapToInt { it.chaos }.sum()
            return field
        }
        set(value) {
            //无法set,chaos是自动计算的
        }


    var burnEffect :Function2<SkillCard,EffectActive,Unit> = {
        skillCard,effect ->
        for (CharacterToken in skillCard.burnValue){
            effect.skillParam[CharacterToken.id] = effect.skillParam[CharacterToken.id]?.plus(1)?:1
        }
    }
    //弃牌加成（没写手牌和丢弃）


}