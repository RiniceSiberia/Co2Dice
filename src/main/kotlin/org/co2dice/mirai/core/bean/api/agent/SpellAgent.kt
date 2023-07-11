package org.co2dice.mirai.core.bean.api.agent

import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-16:58
 * @Message: 发动效果的代理
 **/
interface SpellAgent {
    val spellAbilities : List<EnterFieldTriggeredAbilityInstance>
}