package org.co2dice.mirai.core.bean.effect.triggered_ability.entry

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.*
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.*
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-28-22:48
 * {@code @Message:} 触发式效果的实体
 **/
@Serializable
class TriggeredAbilityEntry(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype: TriggeredAbility,
    var level : Int = 1,
    //做样子的技能等级
) : EntryStructure<TriggeredAbility>{

}

inline fun<reified T : TriggeredAbilityInstance> List<TriggeredAbilityEntry>.toInstance() : List<T> {
    return when(T::class) {
        EnterFieldTriggeredAbilityInstance::class -> {
            this.stream().filter { it.prototype is EnterFieldTriggeredAbility }
                .map { EnterFieldTriggeredAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        LeavingFieldTriggeredAbilityInstance::class -> {
            this.stream().filter { it.prototype is LeavingFieldTriggeredAbility }
                .map { LeavingFieldTriggeredAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        OnFieldTriggeredAbilityInstance::class -> {
            this.stream().filter { it.prototype is OnFieldTriggeredAbility }
                .map { OnFieldTriggeredAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        DiceRevealTriggeredAbilityInstance::class -> {
            this.stream().filter { it.prototype is DiceRevealTriggeredAbility }
                .map { DiceRevealTriggeredAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        else -> {
            throw IndexOutOfBoundsException("unknown triggered ability type")
        }
    }
}