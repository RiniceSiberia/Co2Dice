package org.co2dice.mirai.core.bean.effect.prototype.field

import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.prototype.PassiveEffect

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:26
 * @Message: Have a good time!  :)
 **/
class FieldPassiveEffect(
    override var check: AstTree<Int>,
    //检定值,宣言后会检定是否可以使用技能。
    override var react: AstTree<Int>,
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免.cards应该为技能目标。
    override val launchConditions: AstTree<Boolean>,
    //发动条件
    override val operation: AstTree<String>,
    //操作方法
) : PassiveEffect,FieldEffect {

}