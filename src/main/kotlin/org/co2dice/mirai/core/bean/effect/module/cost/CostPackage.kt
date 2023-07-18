package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-24-15:29
 * {@code @Message:} cost的包装类
 **/
@Serializable
sealed interface CostPackage{

    fun check(situation: PreActivationSituation) : Boolean

    fun getSelectScope(situation: PreActivationSituation) : JsonObject

    fun practice(situation: ActivationSituation) : Boolean
}