package org.co2dice.mirai.core.bean.effect.activated_ability.entry

import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.*
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.*
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-19-20:20
 * @Message: Have a good time!  :)
 **/
class ActivatedAbilityEntry(
    override val uuid: UUID,
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
        ReleaseActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { ReleaseActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        BanishActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { BanishActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        else -> {
            throw IndexOutOfBoundsException("unknown activated ability type")
        }
    }
}