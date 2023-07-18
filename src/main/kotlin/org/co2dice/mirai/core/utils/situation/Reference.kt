package org.co2dice.mirai.core.utils.situation


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-28-23:25
 * {@code @Message:} Have a good time!  :)
 **/
class Reference<T>(
    private var pointer : T?
) {
    fun get() : T? = pointer

    fun invalid() {
        pointer = null
    }
}