package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.character.Character

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
            addToken(tokenType)
        }
    }
    val detainTokens : MutableList<Token> = mutableListOf<Token>()
    //扣押区
    val interestTokens : MutableList<Token> = mutableListOf<Token>()
    //增益区


    fun timePointCheck(battle : Battle,character:Character):Boolean{
        tokens.forEach{
            if (it is TempToken && it.checkPoint(battle,character) && it.timeFlow(battle,character)){
                tokens.remove(it)
            }
        }
    }

    fun getValue():Int{
        return this.tokens.size + this.interestTokens.size - this.detainTokens.size
    }

    fun addTempValue(token:Token,value:Int, lifeTime: Int){
        for (i in 0 until value){
            var j = 1
            token as TempToken
            token.lifeTime = lifeTime
            token.checkPoint = checkPoint@{ _, _ ->
                return@checkPoint true
            }
            token.timeFlow = timeFlow@{ _, _ ->
                token.lifeTime--
                if (token.lifeTime == 0){
                    return@timeFlow true
                }
                return@timeFlow false
            }
            //将token转移为临时变量
            detainTokens.add(token)
        }
    }

//    fun timeCheck():Boolean{
//        if (checkPoint()){
//            return timeFlow()
//        }
//        return false
//    }
//
//    private fun timeFlow():Boolean{
//        lifeTime--
//        return lifeTime <= 0
//    }

    //传入一个数字，抹除最后加的新token，并触发其addEvent
    fun addToken(token: Token):Boolean{
        if (this.getValue() < this.limit && token.canReplace(this.tokenType)){
            if (token is TempToken){
                if (token)


            }else{
                return tokens.add(token) && token.addEvent(this)
            }
        }
        return false
    }
    fun removeToken(v:Int):Boolean{
        var b = true
        for (i in 0 until v){
            if (tokens.size == 0){
                b = false
            }else{
                tokens.removeAt(tokens.size-1)
            }
        }
        return b
    }

}