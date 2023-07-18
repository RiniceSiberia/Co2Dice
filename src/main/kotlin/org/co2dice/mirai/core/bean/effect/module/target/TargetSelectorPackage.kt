package org.co2dice.mirai.core.bean.effect.module.target

import kotlinx.serialization.json.JsonArray
import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-18-22:37
 * @Message: 打包的目标选择器
 **/
interface TargetSelectorPackage {

    fun check(situation: PreActivationSituation): Boolean

    fun getSelectScope(situation: PreActivationSituation): JsonArray

    fun practice(situation: ActivationSituation) : List<List<>>

}