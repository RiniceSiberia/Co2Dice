package org.co2dice.mirai.core.bean.effect.activated_ability.entry

import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.ActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.BanishActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.GyActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.BanishActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.FieldActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.GYActivatedAbility
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-19-20:20
 * {@code @Message:} Have a good time!  :)
 **/
class ActivatedAbilityEntry(
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype : ActivatedAbility,
    val level : Int = 1,
    //备用的技能等级，用作测试,目前没用
): EntryStructure<ActivatedAbility>  {
}
inline fun<reified T : ActivatedAbilityInstance> List<ActivatedAbilityEntry>.toInstance() : List<T> {
    return when(T::class) {
        FieldActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { FieldActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        GYActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { GyActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        BanishActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { BanishActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        else -> {
            throw IndexOutOfBoundsException("unknown activated ability type")
        }
    }
}