package org.co2dice.mirai.core.bean.api

import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.api.agent.StaticAgent
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.FieldStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.LeavingFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.OnFieldTriggeredAbilityInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-19-21:17
 * @Message: 永久物接口，所有在场上的可被取为对象的实体都是这个接口的实现。拥有一个id来区分不同的永久物。
 * 永久物也具有混乱/秩序接口。
 **/
interface PermanentInstance
    : StaticAgent<FieldStaticAbilityInstance>,
    ActivatedAgent<FieldActivatedAbilityInstance>,
    CAO {
    val uniqueId: Int

    val enterFieldTriggeredAbilities : List<EnterFieldTriggeredAbilityInstance>
    val leavingFieldTriggeredAbilities : List<LeavingFieldTriggeredAbilityInstance>
    val onFieldTriggeredAbilities : List<OnFieldTriggeredAbilityInstance>
}