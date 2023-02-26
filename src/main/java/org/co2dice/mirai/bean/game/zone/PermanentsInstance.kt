package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.api.DependChessman
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chessman

//棋子的永久物栏,没有位置之说
class PermanentsInstance(override var chessman: Chessman?) : CardListContainerAPI(),DependChessman {

    override val cards: MutableList<CardInstance> = mutableListOf()
    //永久物栏的卡片列表,开局双方都是空的
    init {
        cards.forEach{
            if(typeLegal(it)){
                throw IllegalArgumentException("FieldInstance can only contain permanent")
            }
        }
    }

    override fun typeLegal(card: CardInstance): Boolean {
        return card is ItemCardInstance
    }


}