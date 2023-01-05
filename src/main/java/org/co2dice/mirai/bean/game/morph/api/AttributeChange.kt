package org.co2dice.mirai.bean.game.morph.api

import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.game.morph.Morph
import org.co2dice.mirai.bean.game.morph.impl.AttributeChangeValueInstance
/**
  * @author DUELIST
  * @date 2023/1/4 9:15
  * @input
  * @return_value
  * @message 一个用来测试，修改卡片的混乱/秩序值的工具
  * @log /
  */
interface AttributeChange : Morph {
    fun getAttributeChange(next : AttributeChange,c : Cards): AttributeChangeValueInstance
}