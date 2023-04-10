package org.co2dice.mirai.bean.dice.single

import com.mojang.datafixers.util.Either
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.*
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.impl.game.attribute.GetAttributeValue
import org.co2dice.mirai.ast.symbol.impl.game.dice.*
import org.co2dice.mirai.ast.symbol.impl.leaf.constant.AttributePrototypeConstant
import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.bean.attribute.prototype.MobAttribute
import org.co2dice.mirai.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.dice.diceList.DiceList
import org.co2dice.mirai.ast.symbol.impl.leaf.constant.IntegerConstant
import org.co2dice.mirai.ast.symbol.impl.leaf.param.ChessmanInstanceParam
import org.co2dice.mirai.ast.symbol.impl.leaf.param.IntegerParam

import org.co2dice.mirai.bean.effect.utils.Situation
import java.util.*
import java.util.stream.Collectors

/**
 * @author DUELIST
 * 这个类的目的是，将抽象的属性修正值转换为具体的骰子修正值，比如将玩家的体质转化为体质数值的骰子
 * 可能存在多个属性的修正值，比如玩家的体质/2+意志/2
 */
class AttributeFixDice(
    private val fixFunc: INode<Int>,
    //获取属性修正值的函数
    ) {
    companion object{
        const val ATT_FIX_CHESS_SIGN = "attribute_fix_chess"
    }

    constructor(a: AttributeAPI) : this(
        //指定对象的属性+1d20
    fixFunc = UniOpNode(
        symbol = Open,
        child = UniOpNode(
            symbol = Roll,
            child = ListOpNode<DiceList, AbstractDice>(
                symbol = DiceListSymbol,
                children = listOf(
                    UniOpNode(
                        symbol = NormalDiceSymbol,
                        child = ConstantLeafNode( symbol = IntegerConstant, value = 20 )
                    ),
                    UniOpNode(
                        symbol = ConstantDiceSymbol,
                        child = BiOpNode(
                            symbol = GetAttributeValue,
                            left = ParamLeafNode( symbol = ChessmanInstanceParam, key = ATT_FIX_CHESS_SIGN),
                            right = ConstantLeafNode( symbol = AttributePrototypeConstant, value = a)
                            )
                        )
                    )
                )
            )
        )
    )
//            Either.right("属性修正值为空,为空属性:" + a.nameStr)

        //默认的修正值,传入属性，返回那个属性的数值
        //如果属性不存在返回right
    )

    fun checkAttribute (situation: Situation,table : AttributeInstanceTable) : Either<DiceList, String>{
        val params = Params(mapOf("attributes" to table,"situation" to situation))
        return
    }



    fun canUse(table: AttributeInstanceTable): Boolean {
        return fixFunc(table).left().isPresent
    }

    fun getDiceList(c: ChessmanInstance): Either<DiceList, String> {
        //通过玩家获取属性修正值
        return fixFunc(c.attributeInstanceTable)
    }

    fun getListDice(c: ChessmanInstance): Either<List<AbstractDice>, String> {
        //通过玩家获取属性修正值
        val either = getListDice(c)
        if (either.left().isPresent) {
            return Either.left(either.left().get())
        } else if (either.right().isPresent) {
            return Either.right(either.right().get())
        }
        return Either.right("未知错误")
    }//将set里的属性全部转化为AttributeInstance，然后填充入table中

    //获取这个修正值需要的属性,使用遍历法
    fun getNeeds(): Set<AttributeAPI> {
            //获取这个修正值需要的属性,使用遍历法
            val set: MutableSet<AttributeAPI> = HashSet()
            set.addAll(Arrays.stream(EliteAttribute.values()).toList())
            set.addAll(Arrays.stream(MobAttribute.values()).toList())
            //现在这个set里获取了所有类型的属性，无论是不是怪物的
            set.removeIf { a: AttributeAPI ->
                //将set里的属性全部转化为AttributeInstance，然后填充入table中
                val map = set.stream().filter { i: AttributeAPI -> i != a }
                    //筛出了set中除了本元素外的所有元素
                    //接下来用这些元素建立一个map，这些元素做key，统一生成一个AttributeInstanceTable.ValuesInstance(8, 8)做value
                    .collect(Collectors.toMap({ i: AttributeAPI -> i },
                        { AttributeInstanceTable.ValuesInstance(8, 8) }))

                val table = AttributeInstanceTable(map)
                canUse(table)
            }
            return set
        }

    fun roll(c: ChessmanInstance): Either<Int, String> {
        val either = getDiceList(c)
        if (either.left().isPresent) {
            return Either.left(either.left().get().roll().open())
        } else if (either.right().isPresent) {
            return Either.right(either.right().get())
        }
        return Either.right("检查属性时发生未知错误")
    }

    override fun toString(): String {
        return fixFunc.natualSerialize()
    }
}