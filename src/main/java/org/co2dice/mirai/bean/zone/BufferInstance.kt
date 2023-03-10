package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.api.DependPlayer
import org.co2dice.mirai.bean.card.instance.CardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-26-0:49
 * @Message: Have a good time!  :)
 **/
class BufferInstance(override var holder: Player?) : CardListContainerAPI(),
    DependPlayer {
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区或者游戏王发动卡片时的区域(未发动成功)
    override val cards: MutableList<CardInstance> = mutableListOf()

}