package org.co2dice.mirai.core.bean.card.instance


import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.api.agent.StaticAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.FieldStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.LeavingFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.OnFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 * 场地类
 * 注:场地不是卡片，虽然也拥有场地卡类，但没有
 */
class VenueCardInstance (
    entry: CardEntry,
    registry : UniqueIdRegistry,
    val volume :Int = 8,
    override val activatedAbilities: List<FieldActivatedAbilityInstance> = entry.activatedAbilityEntries.toInstance(),
    override val staticAbilities: List<FieldStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
    override val enterFieldTriggeredAbilities: List<EnterFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val leavingFieldTriggeredAbilities: List<LeavingFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val onFieldTriggeredAbilities: List<OnFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    //场地可容纳棋子数
    //举例说明，宽敞的大平原可容纳的只有2个棋子，而狭窄的山谷可容纳的棋子只有1~2个
    //如果场地容纳棋子数为0，那么该场地就是一个障碍物，棋子不能进入。如果强制进入，那么这个棋子就会直接死亡。

) : CardInstance(entry),PermanentInstance,ActivatedAgent<FieldActivatedAbilityInstance>,StaticAgent<FieldStaticAbilityInstance> {

    override val uniqueId: Int = registry.register(this::class)
    override val chaos: Int?
        get() = if (entry.prototype is CAO) entry.prototype.chaos else null
    override val order: Int?
        get() = if (entry.prototype is CAO) entry.prototype.order else null

    fun canPassArrow (): Boolean{
        return true
        //先写true，以后如果出现被动效果是无法通过箭头的，再改
    }

}