package org.co2dice.mirai.bean.cards.api

//这是一个继承自function方法的接口
// 一个技能除了可以使用apply方法获取bool值判定是否处理成功,方法本身处理效果外，还可以返回不同情况下这个效果的混乱和秩序值
interface CAOFunc<T,R> :Function1<T,R> {
    fun getOrder(): Int
    fun getChaos(): Int
}