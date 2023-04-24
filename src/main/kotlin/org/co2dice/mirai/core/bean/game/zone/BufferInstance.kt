package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-26-0:49
 * @Message: Have a good time!  :)
 **/
class BufferInstance(override var holder: PlayerInstance?) : CardsVessel<SkillCardInstance>(),
    DependPlayer<PlayerInstance?> {
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区或者游戏王发动卡片时的区域(未发动成功)
    override val cards: MutableList<SkillCardInstance> = mutableListOf()

}