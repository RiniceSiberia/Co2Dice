package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice
import org.co2dice.mirai.bean.tokens.characterToken.*
import javax.smartcardio.Card

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:38
 * @Message: Have a good time!  :)
 **/
class TokenPool (val holder: Card, val fullers : MutableMap<Token,TokenFuller> = mutableMapOf<Token,TokenFuller>()){

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
                  value : Int = DiceList(
                      NormalDice(6),
                      NormalDice(6),
                      NormalDice(6)).roll().getResult(),
                  limit : Int = value ):Boolean{
        if (fullers[tokenType] == null){
            fullers[tokenType] = TokenFuller(tokenType,value,limit)
            return true
        }
        return false
    }

    fun addToken(token: Token,points:List<Token>):Boolean{
        if (fullers[token] != null){
            //存在属性条，直接加一条
            fullers[token]!!.addToken(token)
            return true
        }else{
            for (fuller in fullers.values){
                //不存在，在现有的条里找能替换的
                if (token.canReplace(fuller.tokenType) && points.contains(fuller.tokenType)){
                    fuller.addToken(token)
                    return true
                }
            }
        }
        return false
    }
/**
  * @author 韩左券
  * @date 2022/12/8 16:46
  * @input
  * @return_value  tokenPond
  * @message roll 人类角色初始点
  * @log /
  */
    fun addRandomHumanFuller():TokenPool{
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