package org.co2dice.mirai.core.utils.annotations

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-05-0:43
 * {@code @Message:} Have a good time!  :)
 **/
@Target( AnnotationTarget.TYPE, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class AstResultTips(val clazzType : String, val tips : String = "")
