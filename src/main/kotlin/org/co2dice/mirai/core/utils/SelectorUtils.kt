package org.co2dice.mirai.core.utils

import com.mojang.datafixers.util.Either

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-10-11:31
 * @Message: Have a good time!  :)
 **/
object SelectorUtils {
    fun inputTrans(input : String, splitter : String = "\\s+") : Either<List<Int>, Pair<String, Int>> {
        //检测输入的是坐标还是名字,默认使用空格分割
        val args = input.split(splitter)
        //如果args里面的都是Int，说明是坐标,将其处理成list。
        return if (args.all { it.toIntOrNull() != null }){
            Either.left(args.map { it.toInt() })
        }else{
            //如果不是，说明是名字,那么arg[0]是名称，[1]是id
            Either.right(Pair(args[0],args[1].toIntOrNull() ?: -1))
        }
    }
}