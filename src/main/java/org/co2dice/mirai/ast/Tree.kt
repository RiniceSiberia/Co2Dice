package org.co2dice.mirai.ast

import com.mojang.datafixers.util.Either
import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.LeafNode
import org.co2dice.mirai.utils.BinaryTree

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

    fun dfs(find : (AstNode<*>) -> Boolean) : AstNode<*>? {
        return root.dfs(find)
    }

    fun getParam() : List<LeafNode<*>>{
        return root.competeDfs {n -> n is LeafNode<*> }.map { it as LeafNode<*> }
    }


    companion object{

        fun createTree(input : String) : Tree<Any>{
            //将input先去掉空格
            val inputList = input.replace(" ","")
            //先新建一个二叉树
            val stringTree = BinaryTree<String>()
            //根据symbolFactory里注册的节点，遍历输入,如果输入里存的KClass是BranchNode的子类，
            //如A(C(1),C(2)),先调用的symbolRegistry的match方法，再调用leaveRegistry的match方法，
            //如果是symbol，如A就是个symbol，则中间应该用逗号分割，用A\\(([^,]+),([^)]+)\\)的方式匹配
            //如果是leaf,如C就是leaf,则用C\\((\\d+)\\)的方式匹配
            //匹配到了东西后，将其塞入stringTree节点里
            var currentNode = stringTree.root
            while (true){
                //先匹配symbol
                val symbol = SymbolRegistry.match(inputList)
                if (symbol != null){
                    //如果匹配到了symbol
                    //将匹配到的symbol塞入二叉树
                    currentNode.value = symbol.first
                    //将匹配到的symbol的左右两个参数分别塞入二叉树
                    currentNode.left = BinaryTree.Node(symbol.second.first)
                    currentNode.right = BinaryTree.Node(symbol.second.second)
                    //将当前节点指向左节点
                    currentNode = currentNode.left!!
                    //将匹配到的symbol的左右两个参数从输入里删除
                    inputList.replace(symbol.second.first,"")
                    inputList.replace(symbol.second.second,"")
                }else{
                    //如果没有匹配到symbol
                    //匹配leaf
                    val leaf = LeaveRegistry.match(inputList)
                    if (leaf != null){
                        //如果匹配到了leaf
                        //将匹配到的leaf塞入二叉树
                        currentNode.value = leaf.first
                        //将匹配到的leaf的参数塞入二叉树
                        currentNode.left = BinaryTree.Node(leaf.second)
                        //将当前节点指向左节点
                        currentNode = currentNode.left!!
                        //将匹配到的leaf的参数从输入里删除
                        inputList.replace(leaf.second,"")
                    }else{
                        //如果没有匹配到leaf
                        //如果当前节点的左节点为空，说明当前节点是叶子节点，将当前节点指向父节点
                        if (currentNode.left == null){
                            currentNode = currentNode.parent!!
                        }else{
                            //如果当前节点的左节点不为空，说明当前节点是中间节点，将当前节点指向右节点
                            currentNode = currentNode.right!!
                        }
                    }
                }
                //如果输入为空，说明已经匹配完了，跳出循环
                if (inputList.isEmpty()){
                    break
                }


            }
        }
    }







}