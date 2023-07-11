package org.co2dice.mirai.core.bean.effect.module.target

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.chessman.instance.FieldChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils.IT
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-19:08
 * @Message: Have a good time!  :)
 **/
class SelectFieldChessman(
    val filter : AstTree
//    = AstTree(
//        json = BiOpNode<Boolean,Int,Int>(
//            symbol = Greater,
//            left = BiOpNode<Int, ChessmanInstance, Attribute>(
//                symbol = GetAttributeValue,
//                left = ParamLeafNode<ChessmanInstance>(
//                    symbol = FreeChessmanInstanceParam,
//                    key = IT,
//                ),
//                right = ConstantLeafNode<Attribute>(
//                    symbol = AttributePrototypeConstant,
//                    value = Strength,
//                ),
//            ),
//            right = ConstantLeafNode<Int>(
//                symbol = IntegerConstant,
//                value = Int.MAX_VALUE,
//            ),
//        ).serialize(),
//    ),
    //示范
) : TargetSelector<FieldChessmanInstance> {
    override fun check(situation: PreActivationSituation): List<FieldChessmanInstance> {
        return situation.getField().chessmen.keys.stream()
            .filter { filter.execute<Boolean>(Params(mutableMapOf(IT to it),situation)) == true }.filter {
                true
//                it.staticAbilities.any { ability -> ability is CanBeTargetAbility }
            }.toList()
    }

}