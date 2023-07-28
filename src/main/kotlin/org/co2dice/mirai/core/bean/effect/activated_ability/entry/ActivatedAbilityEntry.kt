package org.co2dice.mirai.core.bean.effect.activated_ability.entry

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.ActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.BanishActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.GyActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.BanishActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.FieldActivatedAbility
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.GYActivatedAbility
import org.co2dice.mirai.core.utils.ConstantUtils.SKILL_LEVEL_SIGN
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.ResolutionSituation
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-19-20:20
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class ActivatedAbilityEntry(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype : ActivatedAbility,
    var level : Int = 1,
    //备用的技能等级，用作测试,目前没用
): EntryStructure<ActivatedAbility>  {

    fun launchConditions(situation: PreActivationSituation) : Boolean {
        return prototype.launchConditions.execute<Boolean>(
            Params(map = mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
        ) == true
    }

    /**
     * 用于检查目标是否符合要求
     * @param situation 激活前的情况
     * @return 是否符合要求
     */

    fun targetCheck(situation: PreActivationSituation) : Boolean{
        return prototype.target.check(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 用于获取目标选择范围
     * @param situation 激活前的情况
     * @return 目标选择范围
     */

    fun targetGetSelectScope(situation: PreActivationSituation) : JsonElement {
        return prototype.target.getSelectScopeJson(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 用于获取选中的效果对象
     * @param situation 处理中的情况
     * @return 目标选择范围
     */

    fun targetPractice(situation: ActivationSituation) : Map<String,Any>? {
        return prototype.target.practice(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 用于检查cost是否符合要求
     * @param situation 激活前的情况
     * @return 是否符合要求
     */

    fun costCheck(situation: PreActivationSituation) : Boolean{
        return prototype.cost.check(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 用于获取cost选择范围
     * @param situation 激活前的情况
     * @return cost选择范围
     */

    fun costGetSelectScope(situation: PreActivationSituation) : JsonElement {
        return prototype.cost.getSelectScopeJson(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 处理cost
     * @param situation 处理中的情况
     * @return 被支付的cost
     */

    fun costPractice(situation: ActivationSituation) : Map<String,Any> {
        return prototype.cost.practice(mutableMapOf(SKILL_LEVEL_SIGN to level),situation)
    }

    /**
     * 用于检查operation是否符合要求
     * @param situation 激活前的情况
     * @return 是否符合要求
     */

    fun operation(input : Map<String,Any>,situation: ResolutionSituation) : Boolean {
        return prototype.operation.execute<Boolean>(
            Params(map = input.toMutableMap().apply { this[SKILL_LEVEL_SIGN] = level },situation)
        ) == true
    }

    fun actValue(input : Map<String,Any>,situation: ResolutionSituation) : Int {
        return prototype.actValue.execute<Int>(
            Params(map = input.toMutableMap().apply { this[SKILL_LEVEL_SIGN] = level },situation)
        ) ?: 0
    }

    fun reactValue(input : Map<String,Any>,situation: ResolutionSituation) : Int {
        return prototype.reactValue.execute<Int>(
            Params(map = input.toMutableMap().apply { this[SKILL_LEVEL_SIGN] = level },situation)
        ) ?: 0
    }



}
inline fun<reified T : ActivatedAbilityInstance> List<ActivatedAbilityEntry>.toInstance() : List<T> {
    return when(T::class) {
        FieldActivatedAbility::class -> {
            this.stream().filter { it.prototype is FieldActivatedAbility }.map { FieldActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        GYActivatedAbility::class -> {
            this.stream().filter { it.prototype is GYActivatedAbility }.map { GyActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        BanishActivatedAbility::class -> {
            this.stream().filter { it.prototype is BanishActivatedAbility }.map { BanishActivatedAbilityInstance(it) }.toList().filterIsInstance<T>()
        }
        else -> {
            throw IndexOutOfBoundsException("unknown activated ability type")
        }
    }
}