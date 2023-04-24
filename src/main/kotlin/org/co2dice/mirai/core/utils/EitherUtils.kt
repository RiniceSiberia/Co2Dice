package org.co2dice.mirai.core.utils

import com.mojang.datafixers.util.Either
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-15:17
 * @Message: Have a good time!  :)
 **/
object EitherUtils {

    fun <L,R,T> getResult(either : Either<L, R>,
                          leftFunc : (Optional<L>) -> T,
                          rightFunc : ((Optional<R>) -> T),
                          emptyFunc : () -> T = {
                                throw Exception("Either is empty")
                          }
    ) : T {
        when {
            either.left().isPresent -> {
                leftFunc(either.left())
            }
            either.right().isPresent -> {
                rightFunc(either.right())
            }
        }
        return emptyFunc()
    }
}