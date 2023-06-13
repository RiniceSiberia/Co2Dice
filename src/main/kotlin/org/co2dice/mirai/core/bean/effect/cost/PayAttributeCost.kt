package org.co2dice.mirai.core.bean.effect.cost

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.node.*
import org.co2dice.mirai.core.ast.symbol.impl.ast.ExecuteInt
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.AttributeEntryTableConstitute
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.GetAttributeValue
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.ToAttributeMapEntry
import org.co2dice.mirai.core.ast.symbol.impl.game.attribute.PayAttributeCostSymbol
import org.co2dice.mirai.core.ast.symbol.impl.game.chessman.stream.FilterChessStream
import org.co2dice.mirai.core.ast.symbol.impl.game.situation.GetInitiator
import org.co2dice.mirai.core.ast.symbol.impl.game.situation.stream.GetInitiatorChessInstanceStream
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.AttributePrototypeConstant
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.FreeChessmanInstanceParam
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.situation.ActivationSituationSymbol
import org.co2dice.mirai.core.ast.symbol.impl.leaf.param.situation.BaseSituationSymbol
import org.co2dice.mirai.core.ast.symbol.impl.math.bool.Greater
import org.co2dice.mirai.core.ast.symbol.impl.math.bool.ListAnd
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.attribute.prototype.Attribute
import org.co2dice.mirai.core.bean.attribute.table.AttributeEntryTable
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.annotations.AstResultTips
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.SituationApi
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-04-17:17
 * @Message: Have a good time!  :)
 **/
class PayAttributeCost(val table : Map<Attribute,
    @AstResultTips( "Intelligence")JsonObject>)
    : Cost<ChessmanInstance>(
    check = @AstResultTips( "List<ChessmanInstance>")AstTree(
        json = UniOpFunctionNode<List<ChessmanInstance>, Stream<ChessmanInstance>,  Boolean>(
            symbol = FilterChessStream,
            //筛选出合适的棋子打包传回,做了选择器的工作
            //这儿直接处理为Bool，无需多加一层execute了
            obj = UniOpNode<Stream<ChessmanInstance>, SituationApi>(
                symbol = GetInitiatorChessInstanceStream,
                child = SituationLeafNode<SituationApi>(
                    symbol = BaseSituationSymbol,
                ),
            ),
            //获取到的角色列表
            function = AstTree(
                json = ListOpNode<Boolean,Boolean>(
                    symbol = ListAnd,
                    children = mutableListOf<INode<out Boolean>>().apply {
                        for (entry in table){
                            add(
                                BiOpNode<Boolean,Int,Int>(
                                    symbol = Greater,
                                    left = BiOpNode<Int, ChessmanInstance, Attribute>(
                                        symbol = GetAttributeValue,
                                        left = ParamLeafNode<ChessmanInstance>(
                                            symbol = FreeChessmanInstanceParam,
                                            key = ConstantUtils.IT
                                        ),
                                        //作为IT传参，如果要自定义这个函数一定要加上这个
                                        //因为这玩意是从下往上构建的，所以必须带它，没它不行
                                        //如果可以抽梁换柱就好了
                                        right = ConstantLeafNode<Attribute>(
                                            symbol = AttributePrototypeConstant,
                                            value = entry.key,
                                            ),
                                        //属性
                                        ),
                                    right = ConsumerNode<Int,Int>(
                                        symbol = ExecuteInt,
                                        function = AstTree(entry.value),
                                        ),
                                    //处理逻辑
                                    )
                            )
                        } },
                    ).serialize(),
                ),
            //处理的逻辑，
        ).serialize()
    ),
    execute = @AstResultTips( "List<Boolean>") AstTree(
        json = BiOpNode<Boolean, ChessmanInstance, AttributeEntryTable>(
            symbol = PayAttributeCostSymbol,
            left = UniOpNode<ChessmanInstance, ActivationSituation>(
                symbol = GetInitiator,
                child = SituationLeafNode<ActivationSituation>(
                    symbol = ActivationSituationSymbol,
                ),
            ),
            //获取到的角色列表
            right = ListOpNode<AttributeEntryTable,Pair<Attribute,Int>>(
                symbol = AttributeEntryTableConstitute,
                children = mutableListOf<INode<out Pair<Attribute, Int>>>()
                    .apply {
                        for (entry in table) {
                            add(
                                BiOpNode<Pair<Attribute, Int>, Attribute, Int>(
                                symbol = ToAttributeMapEntry,
                                left = ConstantLeafNode<Attribute>(
                                    symbol = AttributePrototypeConstant,
                                    value = entry.key
                                ),
                                right = ConsumerNode<Int,Int>(
                                    symbol = ExecuteInt,
                                    function = AstTree(entry.value),
                                ),
                            ))
                        }
                    },
            ),
        ).serialize()
    )
){
    //最简单的，必须比这个值大
        object Serializer : KSerializer<PayAttributeCost> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("PayAttributeCost") {
            element("table", MapSerializer(Attribute.serializer(),JsonElement.serializer()).descriptor)
        }

        override fun deserialize(decoder: Decoder): PayAttributeCost {
            return PayAttributeCost(
                decoder.decodeSerializableValue(
                    MapSerializer(Attribute.serializer(),JsonElement.serializer())
                ).map { (key, value) -> key to value.jsonObject }.toMap()
            )
        }

        override fun serialize(encoder: Encoder, value: PayAttributeCost) {
            return encoder.encodeSerializableValue(
                MapSerializer(Attribute.serializer(),JsonElement.serializer()),
                value.table.map { (key, value) -> key to value }.toMap()
            )
        }

    }
}