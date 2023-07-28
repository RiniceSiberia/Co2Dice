package org.co2dice.mirai.core.utils

import com.mojang.datafixers.util.Either
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-21-20:05
 * @Message: Have a good time!  :)
 **/
object ElseUtils {
}

inline fun<reified V,reified T> Set<V>.filterAsInstance() : Set<T>{
    return this.filter{it is T}.map{it as T}.toSet()
}

inline fun <reified V,reified T> List<V>.filterAsInstance() : List<T>{
    return this.filter{it is T}.map{it as T}.toList()
}

inline fun <reified V,reified T> Stream<V>.filterAsInstance() : Stream<T>{
    return this.filter{it is T}.map{it as T}
}

fun <L,R> Either<L,R>.isLeft() : Boolean{
    return this.left().isPresent
}

fun <L,R> Either<L,R>.isRight() : Boolean{
    return this.right().isPresent
}