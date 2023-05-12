package org.co2dice.mirai.core.bean.game.time

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-30-18:33
 * @Message: 一个大回合，包括所有人的小回合
 **/
class Turns (
    val round: Round = Round(),
    val time : Int = 0,
    ){

    fun passRound(){
        round.passPhase()
    }
}