package org.co2dice.mirai.core.bean.effect.utils

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.utils.situation.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-09-15:04
 * @Message: 这是一个卡片效果的对象选择器的枚举类，里面的每个实例都可以根据字符串来选择效果的对象
 **/
object EffectTargetSelectors {

    val SELECT_ALL_SCENE_ITEM = AstTree<Set<EffectTarget>>(
        //查找全场道具的实例，可以有两种搜索模式:序号(使用一个List<Intelligence>搜索,或者使用一个string(卡名/别名)+int(序号)搜索)
        root =
    )


    ;




    fun <T : EffectTarget>getTarget(id : String,situation: Situation) : Set<T>{
        return this.func(id,situation)
    }
}