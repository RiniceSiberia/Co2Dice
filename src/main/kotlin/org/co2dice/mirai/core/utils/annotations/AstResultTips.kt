package org.co2dice.mirai.core.utils.annotations

import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-05-0:43
 * @Message: Have a good time!  :)
 **/
@Target( AnnotationTarget.TYPE, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class AstResultTips(val clazzType : String, val tips : String = "")
