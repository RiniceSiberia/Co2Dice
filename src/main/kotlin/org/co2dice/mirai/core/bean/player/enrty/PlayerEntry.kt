package org.co2dice.mirai.core.bean.player.enrty

import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.player.prototype.Player
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-23-0:30
 * @Message: 玩家实体
 **/
class PlayerEntry(
    override val uuid: UUID, override val prototype: Player,
) : EntryStructure<Player>{
}