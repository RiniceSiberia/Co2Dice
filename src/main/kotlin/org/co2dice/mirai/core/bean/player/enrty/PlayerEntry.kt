package org.co2dice.mirai.core.bean.player.enrty

import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.player.prototype.Player
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-23-0:30
 * {@code @Message:} 玩家实体
 **/
class PlayerEntry(
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype: Player,
) : EntryStructure<Player>{
}