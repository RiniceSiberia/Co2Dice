package org.co2dice.mirai.core.bean.category

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-0:01
 * @Message: tag包整合
 **/
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
}