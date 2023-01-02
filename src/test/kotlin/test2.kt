package org.co2dice.mirai.plugin

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.co2dice.mirai.bean.game.GameSession
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-27-15:00
 * @Message: Have a good time!  :)
 **/
class test2 {
    private val logger = LoggerFactory.getLogger(test2::class.java)
    @Test
    fun test() {
        val gson = Gson()
        var gameSession = GameSession(
            displayName = "1"
        )
        println(gson.toJson(gameSession))

        var parameterizedType = TypeToken.getParameterized(GameSession::class.java)
        val gameSession1 = gson.fromJson<GameSession>(gson.toJson(gameSession), parameterizedType.type)


        logger.info { "test" }
    }

}