package org.co2dice.mirai.ast

import com.mojang.datafixers.util.Either
import com.mojang.datafixers.util.Pair
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.LeafNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:43
 * @Message: Have a good time!  :)
 **/
class Tree <T>(
    var root : AstNode<T>
) : Function<T>{
    //根节点返回值一定要是T

    companion object{

        fun createTree(input : String) : Tree<Any>{
            //将input先去掉空格
            val inputList = input.replace(" ","")
            val list : MutableList<Either<LeafNode<*>,SymbolEnum>> = mutableListOf()
            //将inputList里的数字和符号分开,然后分段填充进list变量中,具体对应的元素需要查看SymbolEnum和LeafEnum
            //如果是未知数，变量，或者占位符(规则见leafEnum),就填充到左边,如果是symbol(对应的符号见SymbolEnum),就填充到右边
            while (true){
                LeafEnum.matchSymbol(inputList)?.let {
                    list.add(it.first.constructor(it.second))
                } ?: SymbolEnum.matchSymbol(inputList)?.let {
                    list.add(Either.right(it))
                } ?:throw Exception("输入的字符串不合法")
                if (inputList.isEmpty()){
                    break
                }
            }
            //现在list里装满东西了
            //使用递归开始，从叶子节点的最下方到根节点，从字符串左到字符串右，构建树
            var currentNode : AstNode<Any>

            for (s in list){
                s.ifLeft {

                }.ifRight {
                    it.constructor.apply { mutableListOf() }


                    currentNode = it.createNode()
                }

            }



        }
    }






    fun invoke(param : Map<String,Any>) : T?{
        return try {
            root.operation(param)
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }

    fun haveEmpty() : Boolean{
        return root.vacancy()
    }

    fun dfs(find : () -> Boolean) : AstNode<*>? {
        return root.dfs(find)
    }

}