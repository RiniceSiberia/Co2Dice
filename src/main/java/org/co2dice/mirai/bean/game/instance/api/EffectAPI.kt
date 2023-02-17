package org.co2dice.mirai.bean.game.instance.api

import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.tokens.Token

//这是一个继承自function方法的接口
// 一个技能除了可以使用apply方法获取bool值判定是否处理成功,方法本身处理效果外，还可以返回不同情况下这个效果的混乱和秩序值
interface EffectAPI<S:Scene,C: CardsInstance,R: CharacterCard> {
    fun trigger(scene:S,cards: C,character:R,effect: EffectAPI<S, C, R>):Boolean
    //触发条件，返回true则可以消耗cost使用技能。cards应该为holder，因为通过这张卡可以获得持有者的信息。
    fun cost(scene:S,cards: C,character:R,effect: EffectAPI<S, C, R>):List<Token>
    //三个传参:场景，调用的卡牌，效果调用者
    //技能入场时的token花费，宣言使用技能后无论怎么样都会消耗。返回一个消耗token的数组。
    var function: (S, C, R, EffectAPI<S, C, R>)->Boolean
    //调用处理的效果

    fun getOrder(): Int
    fun getChaos(): Int
}