package org.co2dice.mirai.core.utils

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-23:08
 * @Message: Have a good time!  :)
 **/
object ConstantUtils {
    fun getCardPath(
        resourcesType: String,
        imgUrl: String,
        extension: String
    ): String {
        return "/resources/$resourcesType/cards/$imgUrl.$extension"
    }
    const val GPT_KEY = "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"

    const val SITUATION_AST_SIGN = "situation"

    const val INPUT_AST_SIGN = "input"

    const val CHECK_VALUE_SIGN = "check_value"

    const val REACT_VALUE_SIGN = "react_value"

    const val EFFECT_TARGETS_SIGN = "effect_targets"

    const val VENUE_SIZE_MAX = 16

    const val VENUE_REGULARITY_DEFAULT_RATE : Double= 0.7
    //地图规则度，值越小说明越规则

    const val TINY_FUSE = 1000
    //循环熔断开关，用于防止死循环

    const val SKILL_LEVEL_SIGN = "skill_level"

    const val INNER_AST_SIGN = "inner_ast"

    const val HAND_LIMIT = 7
    //手牌上限

    const val CARD_LIMIT_MAX = 40

    const val CARD_LIMIT_MIN = 20

    const val IT = "it"

}