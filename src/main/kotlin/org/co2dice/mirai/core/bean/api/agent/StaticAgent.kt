package org.co2dice.mirai.core.bean.api.agent

import org.co2dice.mirai.core.bean.effect.static_ability.instance.StaticAbilityInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-24-13:34
 * @Message: Have a good time!  :)
 **/
interface StaticAgent <A : StaticAbilityInstance> : Agent{
    val staticAbilities : List<A>
}