package org.co2dice.mirai.core.bean.effect.static_ability.entry

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.effect.static_ability.instance.*
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.*
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-18:43
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class StaticAbilityEntry(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype: StaticAbility,
    var level : Int = 1,
    //做样子的被动等级
) : EntryStructure<StaticAbility>{
    
}

inline fun<reified T : StaticAbilityInstance> List<StaticAbilityEntry>.toInstance() : List<T> {
    return when(T::class) {
        FieldStaticAbilityInstance::class -> {
            this.stream().filter { it.prototype is FieldStaticAbility }.map { FieldStaticAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        GyStaticAbilityInstance::class -> {
            this.stream().filter { it.prototype is GyStaticAbility }.map { GyStaticAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        BanishStaticAbilityInstance::class -> {
            this.stream().filter { it.prototype is BanishStaticAbility }.map { BanishStaticAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        MainDeckStaticAbilityInstance::class -> {
            this.stream().filter { it.prototype is MainDeckStaticAbility }.map { MainDeckStaticAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        DiceSlotStaticAbilityInstance :: class -> {
            this.stream().filter { it.prototype is DiceSlotStaticAbility }.map { DiceSlotStaticAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        else -> {
            throw IndexOutOfBoundsException("unknown static ability type")
        }
    }
}