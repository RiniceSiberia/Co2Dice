package org.co2dice.mirai.bean.game.prototype.effect

import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.Situation
import org.co2dice.mirai.bean.game.prototype.card.Card

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:26
 * @Message: Have a good time!  :)
 **/
abstract class EffectPassive<E : CardEntry>(holder: Card) : Effect(holder) {
    abstract var check:Function1<EffectPassive<E>,Boolean>
    //检定值,宣言后会检定是否可以使用技能。
    abstract var react:Function1<CardInstance,Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免.cards应该为技能目标。
    abstract var reactEffect:Function1<Situation,Boolean>
    //反抗成功后的特效,card应为target.
}