package org.co2dice.mirai.core.bean.emblem

import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.FieldStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.LeavingFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.OnFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-20-13:43
 * {@code @Message:} Emblem(徽记)来自万智牌的徽记，他可以放在人物，物品，场地上。
 * 徽记是永存的，除非依附的卡离场或者依附的人物无法继续行动，它不会被清除。
 * 徽记可以释放效果。徽记释放的效果必须通过被依附的物体实现。
 * 徽记本身就是instance,不存在原型
 **/
class Emblem<T : PermanentInstance>(
    registry : UniqueIdRegistry,
    override val chaos: Int?,
    override val order: Int?,
    override val activatedAbilities: List<FieldActivatedAbilityInstance> = listOf(),
    override val staticAbilities: List<FieldStaticAbilityInstance> = listOf(),
    override val enterFieldTriggeredAbilities: List<EnterFieldTriggeredAbilityInstance> = listOf(),
    override val leavingFieldTriggeredAbilities: List<LeavingFieldTriggeredAbilityInstance> = listOf(),
    override val onFieldTriggeredAbilities: List<OnFieldTriggeredAbilityInstance> = listOf(),
) : ActivatedAgent<FieldActivatedAbilityInstance>,PermanentInstance{

    override val uniqueId: Int = registry.register(this :: class)


    fun invoke(effectId : String, situation: SituationApi, initiator : T){

    }

}