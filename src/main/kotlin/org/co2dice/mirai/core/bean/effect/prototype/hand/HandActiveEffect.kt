package org.co2dice.mirai.core.bean.effect.prototype.hand

import com.mojang.datafixers.util.Either
import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.ast.node.*
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.GetAttributeValue
import org.co2dice.mirai.core.ast.symbol.impl.game.dice.*
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.AttributePrototypeConstant
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.IntegerConstant
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.FreeChessmanInstanceParam
import org.co2dice.mirai.core.ast.symbol.impl.math.integer.Plus
import org.co2dice.mirai.core.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.utils.EffectTargetSelectors
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-14-21:08
 * @Message: 卡片效果的原始版本，不需要記錄使用者等東西，只需要記錄邏輯即可
 * 本類是手發主動效果
 * 包括:道具卡在手卡时展示本卡发动的效果，技能卡发动时将技能放到
 **/
class HandActiveEffect (
    val typeCheck : Map<String, Either<EffectTargetSelectors, Any>>,
    //输入的传参的格式检查器，用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    //left代表这个参数是选择目标，right代表这个参数是非目标相关,比如说丢弃x张手牌这种自定义参数
    //String代表这个输入的参数意味着什么,距离:effectA( a = 青眼白龙 1 , b = 玩家b, c = 3)，其中c是发动技能时支付的力量值
    // 这种情况下typeCheck里应该存储("a" to Selectors.MONSTER_SELECTOR , "b" to PLAYER_SELECTOR c to Int)

    val targetFunc : (Map<String,Any>) -> EffectTargets,
    //需要指定的目标，一个通过typeCheck验证过的输入参数，经过targetFunc调用后便会返回一个封装的EffectTargets。
    //如果某个map.entry是选取的目标，那么他应该是effectTargetSelectors
    //入参和返回值都会存入situation

    override val launchConditions : AstTree<Boolean>,
    //技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
//    val cost : (Situation,Map<String,Any>) -> Boolean,
//    //传的是传参和场景，里面要catch throw,找不到对象就是false
//cost还在写


    override val check : AstTree<Int> = AstTree(
        //检定直接使用敏捷
        UniOpNode(
            symbol = Open,
            child = UniOpNode(
                symbol = Roll,
                child = ListOpNode(
                    symbol = DiceListSymbol,
                    children = listOf(
                        UniOpNode(
                            symbol = FairDiceSymbol,
                            child = ConstantLeafNode( symbol = IntegerConstant, value = 20 )
                        ),
                        UniOpNode(
                            symbol = DesignatedDiceSymbol,
                            child = BiOpNode(
                                symbol = GetAttributeValue,
                                left = ParamLeafNode( symbol = FreeChessmanInstanceParam, key = "release_skill_player"),
                                right = ConstantLeafNode( symbol = AttributePrototypeConstant, value = EliteAttribute.DEX)
                            )
                        )
                    )
                )
            )
        )
    ),
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    //示例检定函数，使用敏捷进行检定,进行一个0修正值,1d20+敏捷的检定

    override val react: AstTree<Int> = AstTree(
        //检定直接使用敏捷
        BiOpNode(
            symbol = Plus,
            left = ConstantLeafNode(
                symbol = IntegerConstant,
                value = 10
            ),
            right = BiOpNode(
                symbol = GetAttributeValue,
                left = ParamLeafNode( symbol = FreeChessmanInstanceParam, key = "selected_player"),
                right = ConstantLeafNode( symbol = AttributePrototypeConstant, value = EliteAttribute.DEX)
            )
        )
    ),
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    //示例反抗函数,使用敏捷进行反抗,进行一个10+敏捷的反抗

    override val operation: AstTree<String>,
    //造成的特效。
    override val paramTypes: Map<String, KClass<*>>,
    //输入的传参的格式检查器，用来检查输入的参数的Class和typeCheck中的是否一致。如果是输入文字的形式，就是要根据typeCheck反查对应的对象了。
    override val targetFunction: Map<String, EffectTargetSelectors>,
    //需要指定的目标，一个通过typeCheck验证过的输入参数，经过targetFunc调用后便会返回一个封装的EffectTargets。
    override val cost: AstTree<Boolean>,
    //技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
) : Effect,ActiveEffect {

}