package org.co2dice.mirai.bean.tokens

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class TokenFuller (point: Token,
                   value: Int,
                   var limit: Int
){
    init {
    }
    //重复添加n个point进points中
    val points : MutableList<Token> = mutableListOf<Token>().apply{
        for (i in 0 until value){
            addToken(point)
        }
    }
    val tempZone : MutableList<Token> = mutableListOf<Token>()

    fun timePointCheck(){
        points.forEach{
            if (it is TempToken && it.timeCheck()){
                points.remove(it)
            }
        }
    }

    fun getValue():Int{
        return this.points.size
    }


    //传入一个数字，抹除最后加的新token，并触发其addEvent
    fun addToken(token: Token):Boolean{
        if (this.getValue() < this.limit){
            return points.add(token) && token.addEvent(this)
        }
        return false
    }
    fun removeToken(v:Int):Boolean{
        var b = true
        for (i in 0 until v){
            if (points.size == 0){
                b = false
            }else{
                points.removeAt(points.size-1)
            }
        }
        return b
    }

}