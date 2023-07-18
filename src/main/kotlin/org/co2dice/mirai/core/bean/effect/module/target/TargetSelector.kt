package org.co2dice.mirai.core.bean.effect.module.target

import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-16:47
 * {@code @Message:} Have a good time!  :)
 **/
sealed interface TargetSelector <T : Any>{

    fun check(situation : PreActivationSituation) : Boolean
    fun get(situation : PreActivationSituation) : List<T>
}
