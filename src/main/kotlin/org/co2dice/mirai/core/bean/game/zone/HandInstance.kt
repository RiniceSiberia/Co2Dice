package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.UnPublicCardInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.ReleaseEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.ConstantUtils.HAND_LIMIT
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-29-22:20
 * @Message: 手牌区
 **/
class HandInstance (
    override var holder: PlayerInstance,
    override val cards: MutableList<UnPublicCardInstance> = mutableListOf(),
    var limit : Int = HAND_LIMIT
) : CardsVessel<UnPublicCardInstance,ReleaseEffect>(), DependPlayer<PlayerInstance> {
    fun add(card: UnPublicCardInstance): Boolean {
        //方法的返回值是用来确定是否成功添加卡片的
        if (cards.size >= limit) {
            return false
        }
        return addCard(card)
    }

    override fun constructInstance(card: CardEntry<*>, registry: UniqueIdRegistry): UnPublicCardInstance {
        return UnPublicCardInstance(card,registry,holder)
    }


}