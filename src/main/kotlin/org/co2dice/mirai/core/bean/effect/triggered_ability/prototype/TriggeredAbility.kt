package org.co2dice.mirai.core.bean.effect.triggered_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.effect.module.target.TargetSelectorPackage

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-28-21:29
 * {@code @Message:} 发动的效果
 * 类似炉石的战吼和法术发动
 * 这种效果不能被简单的一个agent封装,因为他可能存在多个不同类型的效果，比如战吼和亡语同时在一张卡上存在
 **/
sealed interface TriggeredAbility : PrototypeStructure{
    val launchConditions : AstTree
    //<Boolean>
    //发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)

    val target : TargetSelectorPackage
    //<EffectTargets>
    //发动时选择目标，与其相关逻辑。传入一个key，使用value对应的取对象方法，即可获取对应的目标。
    //目标会作为Param的一部分传入到effectFunction中
    //触发效果要不要玩意，调整中

    val operation: AstTree
    //<String>

    val check : AstTree
    //<Intelligence>
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定
    //触发式异能也有检定值，但是和启动式异能不同，

    val react: AstTree
    //<Intelligence>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗
    //效果本身

}