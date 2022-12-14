package org.co2dice.mirai.bean.cards.effect

import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:26
 * @Message: Have a good time!  :)
 **/
abstract class EffectPassive(holder: Cards) : Effect(holder) {
    abstract var check:Function1<EffectPassive,Boolean>
    //检定值,宣言后会检定是否可以使用技能。
    abstract var react:Function1<Cards,Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免.cards应该为技能目标。
    abstract var effect:Function3<Scene, EffectPassive, Cards, Boolean>
    //造成的特效，返回true则技能生效，返回false则技能失效。第一个cards应该为holder，第二个应为target。输出为本技能提供的混乱/秩序值。
    abstract var reactEffect:Function3<Scene, EffectPassive, Cards, Boolean>
    //反抗成功后的特效,card应为target.
}