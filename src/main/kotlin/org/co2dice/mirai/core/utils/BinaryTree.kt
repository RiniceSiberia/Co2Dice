package org.co2dice.mirai.core.utils

import com.mojang.datafixers.util.Either

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-03-24-23:33
 * {@code @Message:} 一个单一类型的二叉树
 **/
class BinaryTree<T>(
    var root : BinaryNode<T> = BinaryNode<T>()
) {

    class BinaryNode<T>(
        var left: BinaryNode<T>? = null,
        var right: Either<T,BinaryNode<T>>? = null,
    ){

        fun addLeft(left: BinaryNode<T>) {
            this.left = left
        }

        fun addRight(right: Either<T,BinaryNode<T>>) {
            this.right = right
        }

        fun addRight(right: T) {
            this.right = Either.left(right)
        }

        fun addRight(right: BinaryNode<T>) {
            this.right = Either.right(right)
        }

        //重写print方法
        override fun toString(): String {
            return "Node{left=$left, right=$right}"
        }

    }

}