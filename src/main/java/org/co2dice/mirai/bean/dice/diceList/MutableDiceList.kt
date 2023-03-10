package org.co2dice.mirai.bean.dice.diceList

import com.mojang.datafixers.util.Either
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.dice.DiceResult
import org.co2dice.mirai.bean.dice.single.AttributeFixDice
import org.co2dice.mirai.bean.dice.single.ConstantDice
import org.co2dice.mirai.bean.dice.single.Dice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-10-17:11
 * @Message: Have a good time!  :)
 **/
class MutableDiceList(
    immutable: List<Dice> = mutableListOf(),
    //俺也忘了当初为什么要留这个diceList了，就当他不存在罢
    private val mutable : MutableList<Dice>,
    //可变的部分
    private var fixDice: AttributeFixDice
    //属性修正值，玩家的属性加成加在这里，不包含在期望计算中。需要使用新的方法计算期望值
    = AttributeFixDice {
        Either.right(
            "没有属性修正值"
        )
    }
    //默认方法只在测试时使用
) : DiceList(immutable) {



    override fun roll(): DiceResult {
        val diceList = DiceList(super.getDiceList(), mutable)
        return diceList.roll()
    }

    fun rollContainAttribute(c: ChessmanInstance): Either<DiceResult, String> {
        if (getFixDiceEither(c).left().isPresent) {
            val diceList = DiceList(super.getDiceList(), mutable, getFixDiceEither(c).left().get())
            return Either.left(diceList.roll())
        } else if (getFixDiceEither(c).right().isPresent) {
            return Either.right(getFixDiceEither(c).right().get())
        }
        return Either.right("roll属性时发生未知错误")
    }

    private fun getFixDiceEither(c: ChessmanInstance): Either<List<Dice>, String> {
        return if (fixDice.getListDice(c).left().isPresent) {
            Either.left(fixDice.getListDice(c).left().get())
        } else {
            if (fixDice.getListDice(c).right().isPresent) {
                Either.right(fixDice.getListDice(c).right().get())
            } else Either.right("roll属性时发生未知错误")
        }
    }



    override fun expected(): Map<Int, Double> {
        val diceList = DiceList(super.getDiceList(), mutable)
        return diceList.expected()
    }

    fun expectedContainAttribute(c: ChessmanInstance): Either<Map<Int, Double>, String> {
        if (getFixDiceEither(c).left().isPresent) {
            val diceList = DiceList(super.getDiceList(), mutable, getFixDiceEither(c).left().get())
            return Either.left(diceList.expected())
        } else if (getFixDiceEither(c).right().isPresent) {
            return Either.right(getFixDiceEither(c).right().get())
        }
        return Either.right("roll属性时发生未知错误")
    }

    override fun getDiceList(): List<Dice> {
        val d: MutableList<Dice> = ArrayList(super.getDiceList())
        d.addAll(mutable)
        return d
    }

    fun getDiceListContainAttribute(c: ChessmanInstance): Either<List<Dice>, String> {
        if (getFixDiceEither(c).left().isPresent) {
            val d: MutableList<Dice> = ArrayList(super.getDiceList())
            d.addAll(mutable)
            d.addAll(getFixDiceEither(c).left().get())
            return Either.left(d)
        } else if (getFixDiceEither(c).right().isPresent) {
            return Either.right(getFixDiceEither(c).right().get())
        }
        return Either.right("roll属性时发生未知错误")
    }

    val immutable: List<Dice>
        get() = super.getDiceList()
    val mutableMin: Int
        get() = mutable.stream().mapToInt { obj: Dice -> obj.diceMin }.sum()
    val mutableMax: Int
        get() = mutable.stream().mapToInt { obj: Dice -> obj.diceMax }.sum()
    val immutableMin: Int
        get() = super.getDiceList().stream().mapToInt { obj: Dice -> obj.diceMin }
            .sum()
    val immutableMax: Int
        get() = super.getDiceList().stream().mapToInt { obj: Dice -> obj.diceMax }
            .sum()

    override fun getMin(): Int {
        return mutableMin + immutableMin
    }

    override fun getMax(): Int {
        return mutableMax + immutableMax
    }

    override fun toString(): String {
        //合并可变和不可变
        val diceList = DiceList(mutable, super.getDiceList())
        return super.toString() + "+" + fixDice.toString()
    }

    fun accurateToString() : String{
        return super.toString() + "," + mutable.toString() + "," + fixDice.toString()
    }

    fun addDice(dice: Dice) {
        val r: Boolean = mutable.add(dice)
        integrate()
    }

    fun removeDice(dice: Dice): Boolean {
        val r: Boolean = mutable.remove(dice)
        integrate()
        return r
    }

    fun integrate() {
        //合并常数
        var c = 0
        for (d in mutable) {
            if (d is ConstantDice) {
                c += d.getDiceValue()
                removeDice(d)
            }
        }
        if (c != 0) {
            addDice(ConstantDice(c))
        }
    }

    fun removeDiceAt(index: Int): Dice {
        return mutable.removeAt(index)
    }

    fun changeDice(target: Dice, dice: Dice): Boolean {
        for (d in mutable) {
            if (d == target) {
                mutable[mutable.indexOf(d)] = dice
                integrate()
                return true
            }
        }
        return false
    }

//    fun getDiceListSize(): Int {
//        return mutable.size
//    }

    fun sort( comparator: Comparator<Dice>) {
        mutable.sortWith(comparator)
    }






}