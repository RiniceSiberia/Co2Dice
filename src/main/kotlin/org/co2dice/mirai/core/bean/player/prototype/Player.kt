package org.co2dice.mirai.core.bean.player.prototype

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-08-20:45
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class Player(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    val qq: Long, ) : PrototypeStructure{

}