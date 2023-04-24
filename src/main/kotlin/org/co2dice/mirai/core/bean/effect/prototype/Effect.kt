package org.co2dice.mirai.core.bean.effect.prototype

import org.co2dice.mirai.core.ast.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 * 效果根据所在区域和所在卡的不同而分类，比如从手牌发动的效果和墓地发动的效果就不是一类效果。
 **/
interface Effect {
    val operation: AstTree<String>

    val check : AstTree<Int>
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定

    val react: AstTree<Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗
    //效果本身
    val launchConditions : AstTree<Boolean>
    //发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
}