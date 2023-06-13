package org.co2dice.mirai.core.bean.dice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-13-12:29
 * @Message: Have a good time!  :)
 **/
open class SampleSpace<T : Any> (
    val list : List<T>
){
//    constructor(vararg elements : T) : this(elements.toList())

    fun union(other : SampleSpace<T>) : SampleSpace<T> {
        //并
        return if (this.isEmpty()){
            other
        }else{
            if (other.isEmpty()){
                this
            }else{
                SampleSpace(this.list + other.list)
            }
        }
    }

    fun intersect(other : SampleSpace<T>) : SampleSpace<T> {
        //交
        return if (this.isEmpty()){
            this
        }else{
            if (other.isEmpty()){
                other
            }else{
                SampleSpace(this.list.filter { other.list.contains(it) })
            }
        }
    }

    fun complements(other : SampleSpace<T>) : SampleSpace<T> {
        //补
        return if (this.isEmpty()){
            this
        }else{
            if (other.isEmpty()){
                other
            }else{
                SampleSpace(this.list.filter { !other.list.contains(it) })
            }
        }
    }

    open fun roll() : T{
        return random()
    }

    private fun random() : T {
        return list.random()
    }

    fun isSubSet(subset : SampleSpace<T>) : Boolean {
        return this.list.containsAll(subset.list)
    }

    fun independent(other : SampleSpace<T>) : Boolean {
        return this.intersect(other).isEmpty()
    }

    fun isEmpty() : Boolean {
        return list.isEmpty()
    }
}