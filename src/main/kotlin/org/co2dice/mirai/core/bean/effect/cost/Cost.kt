package org.co2dice.mirai.core.bean.effect.cost

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

//这个类是cost的判定条件表，用于判定能否支付cost的逻辑
@Serializable
sealed class Cost<T : Any>(
    //传入的参数值
    val check : AstTree,
    //一个检查函数,用于检查当前这个cost能否正常使用
    //会返回检查后所有可能用于支付cost的对象
    val execute : AstTree,
    //具体使用时的函数
) {
    fun check(situation : PreActivationSituation) : List<T>{
        val param = Params(mutableMapOf(),situation)
        return check.execute<List<T>>(param)?:emptyList()
    }

    fun execute(input : MutableMap<String,Any>,situation : ActivationSituation) : Boolean{
        val param = Params(input,situation)
        return execute.execute<Boolean>(param)?:false
    }
}
