package org.co2dice.mirai.bean.game.objective

/**
  * @author DUELIST
  * @date 2023/1/4 9:23
  * @input
  * @return_value
  * @message 选择目标接口,T可能为特定的卡片,效果的发动,效果本身,区域（手卡,墓地,卡组）。
 * 三种种类不会合并使用,比如同时选择效果的发动和区域,即使是对全场使用的效果,也会有一个区域作为对象。
 * 这个类是用来处理对象选择的,举例：当效果发动时,就需要建立这样的一个实体,来记录效果的发动对象。并临时的存入对局中。
 * 此外,这个类新建时也会需要判定存入值的合法性,比如某效果能不能以某些卡片为对象,如果不能就创建失败。
 * 以及,当一些卡片离开原本的所在位置之后,有的效果是会失效的,这个类也需要在返回的时候判定目标是否还在原来的位置
 * 还需要留两个方法判断效果处理不处理,以及是否合法
  * @log /
  */
interface TargetObjective<T> {
    fun getTarget(): T
    fun legal(): Boolean
    fun process(): Boolean
}