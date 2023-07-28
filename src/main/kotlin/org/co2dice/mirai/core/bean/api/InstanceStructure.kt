package org.co2dice.mirai.core.bean.api

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-23:19
 * {@code @Message:} Have a good time!  :)
 **/

interface InstanceStructure<E : EntryStructure<*>> {
    val entry : E
}