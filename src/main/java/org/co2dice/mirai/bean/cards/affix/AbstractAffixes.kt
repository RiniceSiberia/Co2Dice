package org.co2dice.mirai.bean.cards.affix

/**
  * @author 韩左券
  * @date 2022/12/12 15:55
  * @input
  * @return_value
  * @message 词缀是一个附加在道具，场地或者技能上的效果，他不提供任何的逻辑，只在进行具体的逻辑运算的时候区分道具。
  * 比如坚固，需要在计算的时候判断该道具是否有坚固，而后再处理对应的效果。
  * @log /
  */
abstract class AbstractAffixes(var param:MutableMap<String,String>) {
    abstract val name:String
    abstract val description:String




}