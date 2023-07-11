package org.co2dice.mirai.core.utils

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-24-21:56
 * @Message: Have a good time!  :)
 **/
data class Quadruple<out A : Any, out B : Any, out C : Any, out D : Any>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
) {
    override fun toString(): String = "($first, $second, $third, $fourth)"

}

    fun <T : Any> Quadruple<T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth)

