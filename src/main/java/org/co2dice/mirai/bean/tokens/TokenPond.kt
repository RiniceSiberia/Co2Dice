package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice
import org.co2dice.mirai.bean.tokens.characterToken.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:38
 * @Message: Have a good time!  :)
 **/
class TokenPond (val characterCard: CharacterCard, val fullers : MutableMap<Token,TokenFuller> = mutableMapOf<Token,TokenFuller>()){

    fun getPointFuller(tokenType: Token):TokenFuller?{
        return fullers[tokenType]
    }

    fun removeFuller(tokenType: Token):TokenFuller?{
        if (fullers[tokenType] != null){
            return fullers.remove(tokenType)
        }
        return null
    }

    fun replaceFuller(tokenType: Token,tokenFuller: TokenFuller):TokenFuller?{
        if (fullers[tokenType] != null){
            return fullers.replace(tokenType,tokenFuller)
        }
        return null
    }
    fun addFuller(tokenType : Token,
                  value : Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
                  limit : Int = value ):Boolean{
        if (fullers[tokenType] == null){
            fullers[tokenType] = TokenFuller(tokenType,value,limit)
            return true
        }
        return false
    }

    fun addToken(token: Token,points:List<Token>){
        if (fullers[token] != null){
            fullers[token]!!.addToken(token)
        }else{
            for (fuller in fullers.values){
                if (token.canReplace(fuller.tokenType) && points.contains(fuller.tokenType)){
                    fuller.addToken(token)
                    break
                }
            }
        }
    }

    fun addRandomHumanFuller():TokenPond{
        val typeList = mutableListOf<Token>()
        typeList.add(Strength)
        typeList.add(Constitution)
        typeList.add(Dexterity)
        typeList.add(Wisdom)
        typeList.add(Intelligence)
        typeList.add(Sanity)
        for (t in typeList){
            this.addFuller(t)
        }
        return this
    }
}