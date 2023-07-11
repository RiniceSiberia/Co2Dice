package org.co2dice.mirai.core.utils

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.exp
import kotlin.math.ln

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-24-18:22
 * @Message: Have a good time!  :)
 **/
object MathUtils {
    fun factorial(n : Int) : Long{
        var result = 1L
        for (i in 2..n) result *= i
        return result
    }

    fun combination(n: Int, k: Int): Long {
        //从n个元素中取k个元素的组合数
        return factorial(n) / (factorial(k) * factorial(n - k))
    }

    fun BigDecimal.pow(exponent: BigDecimal,precision : Int = 4,roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
        // 创建一个MathContext对象，默认指定4位有效数字和四舍五入模式
        val mc = MathContext(precision, roundingMode)
        // 计算base的自然对数
        val lnBase = BigDecimal(ln(this.toDouble()), mc)
        // 计算exponent乘以lnBase
        val product = exponent.multiply(lnBase, mc)
        // 计算e的product次方
        // 返回结果
        return BigDecimal(exp(product.toDouble()), mc)
    }

    /*
    * 仅为求概率而定制
    * 当介于0和pY之间时会自动停止计算
     */
//    fun definiteIntegral(
//        pmf : (BigDecimal) -> BigDecimal,
//        up : BigDecimal,
//        down : BigDecimal,
//        precisionX: Int = 4,
//        timeBreak : Int = 16777216
//        //计算次数熔断开关,16777216
//    ) : BigDecimal{
//        val dX = BigDecimal.ONE / BigDecimal.TEN.pow(precisionX)
//        var dY : BigDecimal
//        val times = ((up-down)/dX).toInt()
//        if (times >= timeBreak) throw Exception("Too many times to calculate")
//        var index : BigDecimal = down
//        var result : BigDecimal = BigDecimal.ZERO
//        while (index < up ){
//            dY = pmf(index)
//            result += dY*dX
//            index += dX
//        }
//        return result
//    }

    fun <X,Y,Z> ((X, Y) -> Z).partial(y: Y): (X) -> Z {
        return { x -> this(x, y) }
    }




    // apply simpson rule to approximately compute the integration.
    fun simpsonRule(upper: BigDecimal,
                    lower: BigDecimal,
                    n: Int,
                    df: (BigDecimal) -> BigDecimal): BigDecimal {
        //上限，下限，需要分割成的段，函数
        //默认精度200
        var result = BigDecimal.ZERO
        val unit = (upper - lower) / n.toBigDecimal()
        val factor1 = unit / BigDecimal(3)
        val x = arrayOf<BigDecimal>()
        for (i in x.indices) {
            x[i] = lower + unit * i.toBigDecimal()
        }
        for (i in x.indices) {
            result += if (i == 0 || i == x.size - 1) {
                df(x[i])
            } else if (i % 2 == 0) { // if 'i' is even num.
                BigDecimal(2) * df(x[i])
            } else { // if i is odd num.
                BigDecimal(4) * df(x[i])
            }
        }
        result *= factor1
        return result
    }
}