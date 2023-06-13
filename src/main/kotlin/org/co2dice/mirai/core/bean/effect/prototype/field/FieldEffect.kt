package org.co2dice.mirai.core.bean.effect.prototype.field

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.ast.node.*
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.GetAttributeValue
import org.co2dice.mirai.core.ast.symbol.impl.game.dice.*
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.AttributePrototypeConstant
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.FreeChessmanInstanceParam
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.IntegerConstant
import org.co2dice.mirai.core.ast.symbol.impl.math.integer.Plus
import org.co2dice.mirai.core.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.cost.Cost
import org.co2dice.mirai.core.bean.effect.prototype.Effect

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 * 一个主动技能需要以下东西:
 * 输入的传参的格式检查器
 * 需要指定的目标，传参是通过typeCheck的出来的Map
 * 技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)
 * 需要消耗的cost(可能要和传参产生互动起来)
 * 技能正常生效需要的条件(比如说攻击怪兽需要怪兽在场上)
 * 检定函数，默认为直接成功
 * 效果具体处理时使用的函数
 *
 **/
class FieldEffect(

    override val targetFunction : AstTree<EffectTargets>,
    //需要指定的目标，一个通过typeCheck验证过的输入参数，经过targetFunc调用后便会返回一个封装的EffectTargets。
    //如果某个map.entry是选取的目标，那么他应该是effectTargetSelectors
    //入参和返回值都会存入situation

    override val launchConditions : AstTree<Boolean>,
    //技能发动所需的条件(和cost的检查函数有互动，也和本技能依赖的主体的卡片有互动)

    override val cost: AstTree<Cost>,
    //传的是传参和场景。使用等于处理cost,返回空代表cost不满足条件

    override val check : AstTree<Int> = AstTree(
        //检定直接使用敏捷
        UniOpNode(
            symbol = Open,
            child = UniOpNode(
                symbol = DiceRollSymbol,
                child = ListOpNode(
                    symbol = DiceSumSymbol,
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
    //具体实现的时候操作的方法

    //造成的特效。

) : Effect {




    fun inputLegal(map : Map<String,Any>) : Boolean{
        //将target进行stream化，如果key都对得上且对应key的value，每个map的类型是value的子类或者本类，返回true
        //允许param中存在target中不存在的key
//        return this.paramTypes().entries.stream().allMatch {
//            map.containsKey(it.key) && it.value.isInstance(map[it.key])
//        }
        return true
    }

}