package org.co2dice.mirai.bean.game

import net.mamoe.mirai.contact.Group

object ScenePool {
    private val scenes = HashMap<Long, Scene>()

    fun createScene(group: Group,scene: Scene) {
        scenes[group.id] = scene
    }

    fun getScene(group:Group) : Scene? {
        return scenes[group.id]
    }
    fun finishScene(group: Group):Scene?{
        return scenes.remove(group.id)
    }

}