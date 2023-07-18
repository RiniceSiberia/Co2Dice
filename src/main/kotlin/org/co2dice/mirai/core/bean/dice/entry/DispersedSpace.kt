package org.co2dice.mirai.core.bean.dice.entry

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.mapSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import org.apache.commons.statistics.distribution.ContinuousDistribution

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-13-12:29
 * {@code @Message:} 样本空间类，抽象的骰子，随机数生成器
 * 可以在任何场景调用,投掷不会触发额外的事件
 * 用来让作者理解离散函数的定义用的，纯爱好产物
 **/
@Serializable(with = DispersedSpaceSerializer::class)
open class DispersedSpace<T : Any> (
    space : Map<T,Int>,
) : HashMap<T,Int>(space){


    open fun roll() : T{
        return toList().random()
    }

    fun toList() : List<T> {
        val list = mutableListOf<T>()
        this.forEach { (t, u) ->
            for (i in 0 until u){
                list.add(t)
            }
        }
        return list
    }

    fun isSubSet(parentSet : DispersedSpace<T>) : Boolean {
        return parentSet.keys.containsAll(this.keys)
    }

    companion object{

        fun <T : Any>getDispersedSpace(
            distribution : ContinuousDistribution,
            min : Double,
            max : Double,
            mapping : (Double) -> T) : DispersedSpace<T> {
            val space = getDispersedSpace(distribution,min, max)
            val newSpace = mutableMapOf<T,Int>().apply {
                space.forEach { (k, v) ->
                    if (this[mapping(k)] == null) this[mapping(k)] = 0
                    this[mapping(k)] = this[mapping(k)]!! + v
                }
            }
            return DispersedSpace(newSpace)
        }

        fun getDispersedSpace(distribution : ContinuousDistribution,
                              min : Double = 0.0,
                              max : Double,
                              precision : Int = (max - min).toInt()) : DispersedSpace<Double> {
            //默认精度为取整数
            val map : Map<Double,Double> = mutableMapOf<Double, Double>().apply {
                //截取min到max中precision段
                val step = (max - min) / precision
                var current = min
                repeat(precision){
                    this[current] = distribution.density(current)
                    current += step
                }
            }
            //获取其中的最小值，将其除以1
            val minDensity = map.values.minOrNull()
            return if (minDensity == null) {
                DispersedSpace(mapOf())
            }else{
                val divider = 1.0 / minDensity
                DispersedSpace(map.mapValues { (_, v) ->
                    (v * divider).toInt()
                })
            }
        }
    }
}

/**
 * 将普通类型的离散空间转化为Int为Key的离散空间
 **/

fun <D : DispersedSpace<T>,T : Any,R : Any> D.mapping(mapper : (T) -> R) : DispersedSpace<R> {
    val space = this
    return DispersedSpace(
        mutableMapOf<R, Int>().apply {
            space.forEach { (k, v) ->
            if (this[mapper(k)] == null) this[mapper(k)] = 0
            this[mapper(k)] = this[mapper(k)]!! + v
        }
    })
}

fun<D : DispersedSpace<T>,T:Any> D.intersect(other: D): DispersedSpace<T> {
    //交运算,将两个列表中相同的元素取出来,并且将他们的权重相减少
    return DispersedSpace((this.keys + other.keys).associateWith { key ->
        (this[key] ?: 0) - (other[key] ?: 0)
    })
}

fun<D : DispersedSpace<T>,T:Any> D.complements(other: D): DispersedSpace<T> {
    //补运算,将两个列表中不同的元素取出来
    return DispersedSpace(this.filterKeys { key ->
        !other.keys.contains(key)
    } + other.filterKeys {key ->
        !this.keys.contains(key)
    })
}

fun<D : DispersedSpace<T>,T:Any> D.union(other: D): DispersedSpace<T> {
    //并运算,将两个列表中的元素取出来拼接,并且将他们的权重相加
    return DispersedSpace((this.keys + other.keys).associateWith { key ->
        (this[key] ?: 0) + (other[key] ?: 0)
    })
}
/*
*Int专属
 */
fun<D : DispersedSpace<Int>> D.max() : Int{
    return this.keys.maxOrNull()!!
}

fun<D : DispersedSpace<Int>> D.min() : Int{
    return this.keys.minOrNull()!!
}

class DispersedSpaceSerializer<T : Any>(
    val tSerializer : KSerializer<T>
) : KSerializer<DispersedSpace<T>>{
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SampleSpaceInstance"){
        element("space",mapSerialDescriptor(tSerializer.descriptor,Int.serializer().descriptor))
    }

    override fun deserialize(decoder: Decoder): DispersedSpace<T> {
        return decoder.decodeStructure(descriptor){
            var space : Map<T,Int> = mapOf()
            while(true){
                when(val index = decodeElementIndex(descriptor)){
                    0 -> space = decodeSerializableElement(descriptor,index,MapSerializer(tSerializer,Int.serializer()))
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            DispersedSpace(space)
        }
    }

    override fun serialize(encoder: Encoder, value: DispersedSpace<T>) {
        encoder.encodeSerializableValue(MapSerializer(tSerializer,Int.serializer()),value)
    }

}