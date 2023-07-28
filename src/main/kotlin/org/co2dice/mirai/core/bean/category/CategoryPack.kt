package org.co2dice.mirai.core.bean.category

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-0:01
 * {@code @Message:} tag包整合
 * 备注:因为这个类的存在，所有卡片类的东西请等到category注册器注册完毕后再使用
 **/
@Serializable(with = CategoryPackSerializer::class)
class CategoryPack (
    private val categories : MutableSet<CategoryTag> = mutableSetOf(),
){
    fun addCategory(tag: CategoryTag) : Boolean{
        //如果新增的tag和已有的tag有重叠，返回false,如果新增的tag在categories中有子级则不添加返回false,如果新增的tag在categories中有父级则删除父级并添加tag，其他直接添加tag
        if ( categories.contains(tag) || categories.any { it.belong(tag) } ){
            return false
        }
        categories.filter { tag.belong(it) }.forEach { categories.remove(it) }
        return categories.add(tag)
    }

    fun contain(tag : CategoryTag) : Boolean{
        return categories.any { it == tag || it.belong(tag) }
    }

    fun getCategories() : Set<String>{
        return categories.map { it.name }.toSet()
    }
}

object CategoryPackSerializer : KSerializer<CategoryPack>{
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CategoryPack"){
        element<String>("category")
    }

    override fun deserialize(decoder: Decoder): CategoryPack {
        return CategoryPack(decoder.decodeString().split(",").mapNotNull {
            CategoryDepthGraph.selectNode(it)
        }.toMutableSet())
    }

    override fun serialize(encoder: Encoder, value: CategoryPack) {
        encoder.encodeString(value.getCategories().joinToString(","))
    }

}