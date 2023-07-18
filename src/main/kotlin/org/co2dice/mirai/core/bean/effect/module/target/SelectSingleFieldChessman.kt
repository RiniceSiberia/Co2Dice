package org.co2dice.mirai.core.bean.effect.module.target

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.chessman.instance.FieldChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils.IT
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-19:08
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable(with = SelectFieldChessmanSerializer::class)
class SelectSingleFieldChessman(
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

    override fun check(situation: PreActivationSituation): Boolean {
        return get(situation).isNotEmpty()
    }
    override fun get(situation: PreActivationSituation): List<FieldChessmanInstance> {
        //返回一个选择的范围
        return situation.getField().chessmen.keys.stream()
            .filter { filter.execute<Boolean>(Params(mutableMapOf(IT to it),situation)) == true }.filter {
                true
//                it.staticAbilities.any { ability -> ability is CanBeTargetAbility }
            }.toList()
    }

}
object SelectFieldChessmanSerializer : KSerializer<SelectSingleFieldChessman> {
    override val descriptor = buildClassSerialDescriptor("SelectSingleFieldChessman") {
        element("filter", AstTree.serializer().descriptor)
    }
    override fun deserialize(decoder: Decoder): SelectSingleFieldChessman {
        return SelectSingleFieldChessman(
            filter = decoder.decodeSerializableValue(AstTree.serializer())
        )
    }
    override fun serialize(encoder: Encoder, value: SelectSingleFieldChessman) {
        encoder.encodeSerializableValue(AstTree.serializer(),value.filter)
    }
}