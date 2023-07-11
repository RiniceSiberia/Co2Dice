package org.co2dice.mirai.core.bean.api.agent

import org.co2dice.mirai.core.bean.effect.activated_ability.instance.ActivatedAbilityInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-13:29
 * @Message: 存储特殊效果的接口
 **/
interface ActivatedAgent<A : ActivatedAbilityInstance> : Agent {
    val activatedAbilities : List<A>
}