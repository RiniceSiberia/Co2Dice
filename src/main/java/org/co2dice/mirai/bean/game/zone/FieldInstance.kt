package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chessman
import java.util.function.Predicate

//战场没有位置之说
class FieldInstance(var chessman: Chessman?,
                    val cards: MutableList<CardInstance> = mutableListOf()
                    //只能放永久物
                    ) : CardContainerAPI{
    init {
        cards.forEach{
            if(legal(it)){
                throw IllegalArgumentException("FieldInstance can only contain permanent")
            }
        }
    }

    override fun addCard(card: CardInstance): Boolean {
        if(legal(card)){
            return false
        }
        return cards.add(card)
    }

    override fun insertCard(card: CardInstance, index: Int): Boolean {
        if(legal(card)){
            return false
        }
        cards.add(index,card)
        return cards[index] == card
    }

    override fun getAll(): List<CardInstance> {
        TODO("Not yet implemented")
    }

    override fun selectCard(function: Predicate<CardInstance>): List<CardInstance> {
        TODO("Not yet implemented")
    }

    override fun getCard(card: CardInstance): CardInstance? {
        TODO("Not yet implemented")
    }

    override fun checkCard(function: Predicate<CardInstance>): Boolean {
        TODO("Not yet implemented")
    }

    override fun pickCard(function: Predicate<CardInstance>): CardInstance? {
        TODO("Not yet implemented")
    }

    override fun contain(card: CardInstance): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveCardToElseZone(card: CardInstance, container: CardContainerAPI): Boolean {
        TODO("Not yet implemented")
    }

    fun legal(card: CardInstance): Boolean {
        return card !is ItemCardInstance && card !is SkillCardInstance
    }

}