package org.co2dice.mirai.core.bean.api

import java.util.*


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-23:20
 * {@code @Message:} Have a good time!  :)
 **/
interface EntryStructure<P : PrototypeStructure> {
    val uuid : UUID
    val prototype : P
}