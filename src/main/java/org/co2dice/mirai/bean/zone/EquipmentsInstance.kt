package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.utils.ItemType
import org.co2dice.mirai.utils.ItemType.*
import org.co2dice.mirai.bean.API.DependChessman
import org.co2dice.mirai.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance

//棋子装备栏
class EquipmentsInstance(override var chessman: ChessmanInstance?) : DependChessman {

    var leftHand: ItemCardInstance? = null

    var rightHand: ItemCardInstance? = null

    var head: ItemCardInstance? = null

    var body: ItemCardInstance? = null

    var foot: ItemCardInstance? = null

    val miscellaneous :MutableList<ItemCardInstance> = mutableListOf()

    fun equip(card: ItemCardInstance, type: ItemType):Boolean{
        when(type){
            LEFT_HAND -> {
                if(leftHand != null){
                    return false
                }
                leftHand = card
            }
            RIGHT_HAND -> {
                if(rightHand != null){
                    return false
                }
                rightHand = card
            }
            HEAD -> {
                if(head != null){
                    return false
                }
                head = card
            }
            BODY -> {
                if(body != null){
                    return false
                }
                body = card
            }
            FOOT -> {
                if(foot != null){
                    return false
                }
                foot = card
            }
            MISCELLANEOUS -> {
                miscellaneous.add(card)
            }

            TWO_HAND -> {
                if (leftHand != null || rightHand != null) {
                    return false
                }
                leftHand = card
            }
        }
        return true
    }



}