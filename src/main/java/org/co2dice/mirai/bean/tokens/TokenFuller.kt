package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.game.Battle
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class TokenFuller (val tokenType: Token,
                   value: Int,
                   var limit: Int
){

    //重复添加n个point进points中
    val tokens : MutableList<Token> = mutableListOf<Token>().apply{
        for (i in 0 until value){
            add(tokenType)
            //初始化不触发添加事件
        }
    }


    fun timePointCheck(battle : Battle, characterCard:CharacterCard):Boolean{
        //对每个点都执行一次检查
        tokens.forEach{
            if (it.isTempToken && it.checkPoint(battle,characterCard) && it.timeFlow(battle,characterCard)){
                //如果是临时token并且检查点返回true并且时间流逝返回true则删除这个点
                if (!tokens.remove(it) || !it.removedEvent(this)){
                    //如果删除失败则返回false
                    return false
                }
            }
        }
        return true
    }

    fun getPoints():Int{
        return this.tokens.stream().mapToInt(Token::point).sum()
    }

    //传入一个数字，抹除最后加的新token，并触发其addEvent
    fun addToken(token: Token):Boolean{
        if (this.getPoints() +token.point < this.limit && token.canReplace(this.tokenType)){
            return tokens.add(token) && token.addEvent(this)
        }
        return false
    }
    fun removeToken(v:Int):Boolean{
        var b = true
        for (i in 0 until v){
            if (tokens.size == 0){
                b = false
            }else{
                for (j in 1 .. tokens.size){
                    val token = tokens[tokens.size-j]
                    if(token.point > 0){
                        b = b && tokens.remove(token)
                            //成功删除token
                            && token.removedEvent(this)
                            //成功触发事件
                            && getPoints() > 0
                        break
                    }
                }
            }
        }
        return b
    }

}