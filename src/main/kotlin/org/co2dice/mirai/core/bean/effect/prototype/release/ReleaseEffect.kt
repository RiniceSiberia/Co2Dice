package org.co2dice.mirai.core.bean.effect.prototype.release

import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.utils.EffectTargetSelectors
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-17:50
 * @Message: Have a good time!  :)
 **/
class ReleaseEffect(
    override val paramTypes: Map<String, KClass<*>>,
    override val targetFunction: Map<String, EffectTargetSelectors>,
    override val cost: AstTree<Boolean>,
    override val launchConditions: AstTree<Boolean>,
    override val check: AstTree<Int>,
    override val react: AstTree<Int>,
    override val operation: AstTree<String>,
) : Effect, ActiveEffect {
}