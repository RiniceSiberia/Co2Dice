package org.co2dice.mirai.core.bean.api

import java.util.*


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-02-23:20
 * @Message: Have a good time!  :)
 **/
interface EntryStructure<P : PrototypeStructure> {
    val uuid : UUID
    val prototype : P
}