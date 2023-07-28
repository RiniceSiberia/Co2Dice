package org.co2dice.mirai.core.utils

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-24-23:23
 * @Message: Have a good time!  :)
 **/
/**
 * 表示一个包含三种可能类型的数据结构。
 *
 * @param <L> 第一个泛型类型
 * @param <R> 第二个泛型类型
 * @param <T> 第三个泛型类型
 */
sealed class TriEither<out L : Any, out M  : Any, out R : Any> {
    /**
     * 表示左侧的数据项。
     */
    data class Left<out L : Any>(val value: L) : TriEither<L, Nothing, Nothing>()

    /**
     * 表示中间的数据项。
     */
    data class Middle<out M : Any>(val value: M) : TriEither<Nothing, M, Nothing>()

    /**
     * 表示右侧的数据项。
     */
    data class Right<out R : Any>(val value: R) : TriEither<Nothing, Nothing, R>()

    fun get() : Any{
        return when(this){
            is Left -> value
            is Middle -> value
            is Right -> value
        }
    }
}

/**
 * 将值包装为Left类型的TriEither。
 */
fun <L : Any> left(value: L): TriEither<L, Nothing, Nothing> = TriEither.Left(value)

/**
 * 将值包装为Middle类型的TriEither。
 */
fun <M : Any> middle(value: M): TriEither<Nothing, M, Nothing> = TriEither.Middle(value)

/**
 * 将值包装为Right类型的TriEither。
 */
fun <R : Any> right(value: R): TriEither<Nothing, Nothing, R> = TriEither.Right(value)

/**
 * 获取TriEither中的值，并根据泛型类型返回对应的结果。
 *
 * @param leftAction 处理Left类型值的函数
 * @param middleAction 处理Middle类型值的函数
 * @param rightAction 处理Right类型值的函数
 * @return 处理后的结果
 */
fun <L : Any, M : Any, R : Any, V> TriEither<L, M, R>.fold(
    leftAction: (L) -> V,
    middleAction: (M) -> V,
    rightAction: (R) -> V
): V = when (this) {
    is TriEither.Left -> leftAction(value)
    is TriEither.Middle -> middleAction(value)
    is TriEither.Right -> rightAction(value)
}

/**
 * 获取TriEither中的值，如果是Left或Middle类型则返回null。
 *
 * @return Right类型的值，如果不是则返回null
 */
fun <T : Any> TriEither<*, *, T>.getRightOrNull(): T? = when (this) {
    is TriEither.Right -> value
    else -> null
}

/**
 * 获取TriEither中的值，如果是Left或Right类型则返回null。
 *
 * @return Middle类型的值，如果不是则返回null
 */
fun <R : Any> TriEither<*, R, *>.getMiddleOrNull(): R? = when (this) {
    is TriEither.Middle -> value
    else -> null
}

/**
 * 获取TriEither中的值，如果是Middle或Right类型则返回null。
 *
 * @return Left类型的值，如果不是则返回null
 */
fun <L : Any> TriEither<L, *, *>.getLeftOrNull(): L? = when (this) {
    is TriEither.Left -> value
    else -> null
}

