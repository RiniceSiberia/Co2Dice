package org.co2dice.mirai.bean.chessman.attribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-0:27
 * @Message: Have a good time!  :)
 **/
data class AttributeInstance(val type : AttributeAPI,
                        var value : Int,
                        var limit : Int) {

    fun add(value : Int) :AttributeInstance{
        return setValue(this.value + value)
    }

    fun plus(value : Int) :AttributeInstance{
        return setValue(this.value * value)
    }

    @Override
    fun setValue(value : Int) : AttributeInstance{
        this.value = value
        if (this.value > this.limit) this.value = this.limit
        return this
    }
    @Override
    fun setLimit(value : Int) : AttributeInstance{
        this.limit = value
        if (this.value > this.limit) this.value = this.limit
        return this
    }

    fun same(other : AttributeInstance) : Boolean{
        return (other.type == this.type)
    }
}