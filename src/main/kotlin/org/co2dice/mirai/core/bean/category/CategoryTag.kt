package org.co2dice.mirai.core.bean.category

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-08-21:30
 * {@code @Message:} 物品/棋子的分类词条
 **/

class CategoryTag(
    val name : String,
    sup : List<CategoryTag> = listOf(),
    //分类词条只支持认爹,不支持认儿子
) {
    init {
        CategoryDepthGraph.addNode(this)
        sup.forEach {
            CategoryDepthGraph.addEdge(this,it)
        }
    }

    fun belong(sup : CategoryTag) : Boolean{
        //检查sup是不是本节点的父级
        //相等自动返回false
        if ( this == sup ){
            return false
        }
        return CategoryDepthGraph.belong(sup,this)
    }

    fun own(sub : CategoryTag) : Boolean{
        //检查sub是不是本节点的子级
        return sub.belong(this)
    }
}