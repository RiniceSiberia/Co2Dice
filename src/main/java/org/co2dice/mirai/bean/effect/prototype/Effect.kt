package org.co2dice.mirai.bean.effect.prototype

import org.co2dice.mirai.bean.api.EffectAPI
import org.co2dice.mirai.bean.api.EffectTarget
import org.co2dice.mirai.bean.card.prototype.Card

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 **/
abstract class Effect(var holder: Card): EffectAPI, EffectTarget {
}