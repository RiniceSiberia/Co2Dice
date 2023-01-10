package org.co2dice.mirai.bean.game

object GameSessionPool {
    private val gameSessionPool = mutableMapOf<String, GameSession>()

    fun getGameSession(groupId: String): GameSession? {
        //获取会话
        return gameSessionPool[groupId]
    }

    fun createGameSession(groupId: String, gameSession: GameSession): Boolean {
        //创建会话
        return gameSessionPool.putIfAbsent(groupId, gameSession) == null
    }



    fun closeGameSession(groupId: String): Boolean {
        //关闭会话
        if (gameSessionPool[groupId] != null) {
            gameSessionPool[groupId]!!.isClosed = true
            return true
        }
        return false
    }

    fun openGameSession(groupId: String):Boolean{
        //开启会话
        if (gameSessionPool[groupId] != null) {
            gameSessionPool[groupId]!!.isClosed = false
            return true
        }
        return false
    }

    fun closeAllGameSession() {
        //关闭所有会话
        gameSessionPool.values.forEach{ it.isClosed = true }
    }

    fun endGameSession(groupId: String):Boolean{
        if (gameSessionPool[groupId] != null) {
            gameSessionPool[groupId]!!.hasEnded = true
            return true
        }
        return false
    }



    fun loadGameSession(){
        //预留的加载会话方法，可加载所有未结束的会话
    }
}