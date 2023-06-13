package org.co2dice.mirai.core.ast.tree

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.ParamLeafNode
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.utils.situation.*
import kotlin.jvm.Throws

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-14-12:29
 * @Message: Have a good time!  :)
 **/
@Serializable(with = AstTree.AstTreeSerializer::class)
class AstTree (json : JsonObject){

    val root : INode<*> = SymbolRegistry.deserialize<Any>(json)

    constructor(str : String) : this(Json.decodeFromString<JsonObject>(str))

    @Suppress("UNCHECKED_CAST")
    fun <O : Any>execute(params :Params) : O?{
        try {
            return root.evaluate(params) as O
        }catch (e : Exception){
            e.printStackTrace()
        }
        return null
    }

    //检查能不能跑
    fun check(params:Params) : Boolean{
        return try {
            root.check(params)
            true
        }catch (e : Exception){
            false
        }
    }

    private fun dfs(func : (INode<*>) -> Boolean) : List<INode<*>>{
        return root.competeDfs(func)
    }

    @Deprecated("这玩意毫无卵用,就算查出了所需传参也查不出具体错在哪",ReplaceWith("dfs"))
    fun getParams() : List<ParamLeafNode<*>>{
        return root.getParams()
    }

    fun getParamsNoSituation() : List<ParamLeafNode<*>>{
        return getParams().filter {
            it.symbol.clazz != PreActivationSituation::class
                || it.symbol.clazz != ActivationSituation::class
                || it.symbol.clazz != ResolutionSituation::class
                || it.symbol.clazz != ResultSituation::class
                || it.symbol.clazz != SituationApi::class
        }
    }

    fun natualSerialize() : String {
        return root.natualSerialize()
    }

    fun serialize() : JsonObject{
        return root.serialize()
    }

    object AstTreeSerializer: KSerializer<AstTree> {
        // here we use tSerializer.descriptor because it represents T
        override val descriptor = buildClassSerialDescriptor("AstTree"){
            element<String>("root")
        }

        override fun deserialize(decoder: Decoder): AstTree {
            decoder.decodeStructure(descriptor){
                return@decodeStructure AstTree(decodeStringElement(descriptor, 0))
            }
            throw Exception("AstTree deserialize failed")
        }

        override fun serialize(encoder: Encoder, value: AstTree) {
            encoder.encodeStructure(descriptor){
                encodeStringElement(descriptor, 0, value.root.serialize().toString())
            }
        }
    }


}
