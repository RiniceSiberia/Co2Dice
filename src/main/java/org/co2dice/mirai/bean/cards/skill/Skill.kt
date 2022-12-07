package org.co2dice.mirai.bean.cards.skill

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: Have a good time!  :)
 **/
abstract class Skill(){
    abstract val skillType:SkillType
    abstract val cost:
    abstract val check:Function<Int>
    //检定值
    abstract val react:Function<Int>
    abstract val trigger:Function<Boolean>
    //触发条件
    abstract val effect:Function<Boolean>
    //造成的特效
}