package org.co2dice.mirai.plugin

import org.co2dice.mirai.bean.Player

import org.co2dice.mirai.bean.game.GameSessionPool
import org.co2dice.mirai.bean.game.decorator.env.AttributeNumericType
import org.co2dice.mirai.bean.game.decorator.handler.DecoratorRegistry
import org.co2dice.mirai.bean.game.decorator.implementation.numeric.SimpleNumericPermanentAddValueDecorator
import org.co2dice.mirai.bean.game.decorator.implementation.numeric.SimpleNumericPermanentMultiplyValueDecorator
import org.co2dice.mirai.bean.game.decorator.implementation.numeric.SimpleNumericPermanentSwitchStatDecorator
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeContext
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCardInstance
import org.co2dice.mirai.bean.game.prototype.character.PlayerChessman
import org.co2dice.mirai.bean.game.prototype.card.Card
import org.junit.jupiter.api.Test
import space.controlnet.lightioc.api.Container
import java.util.*


class Test {
    @Test
    fun main(args: Array<String>) {
        DecoratorRegistry.register()
        val gameSession = GameSessionPool.getGameSession("test")!!
        val player = Player(qq = 2979897510L)
        GameSessionPool.createGameSession("123456",gameSession)
        val characterCard = PlayerChessman(
            cardName = "测试角色",
            flavorText = "测试角色",
            imgUrl = "/",
            characterHolder = player
        )
        gameSession.addBattle(mutableListOf(characterCard))
        val scene = gameSession.getPlayerScene(player)!!
        val card = SkillCardInstance()
        scene.addDecorator(
            SimpleNumericPermanentAddValueDecorator(
                AttributeNumericType.CHAOS,
                500
            )
        )
        scene.addDecorator(SimpleNumericPermanentSwitchStatDecorator())
        scene.addDecorator(
            SimpleNumericPermanentMultiplyValueDecorator(
                AttributeNumericType.ORDER,
                2
            )
        )
        val value: Int = scene.getHandler(DecoratorRegistry.GET_NUMERIC_ATTRIBUTE)
            //获取场景的Token，并运用于卡片的获取属性值
            .apply(GetNumericAttributeContext(AttributeNumericType.CHAOS, card)).value()
        println(value)
    }

    @Test
    fun main2(){
        Container.init("org.co2dice.mirai.bean.game.prototype")
        val cardA = Card(cardId = UUID.randomUUID(), cardRealName = "testA", cardType = CardType.ITEM)
        val cardB = Card(cardId = UUID.randomUUID(), cardRealName = "testB", cardType = CardType.ITEM)
        Container.register<Card>("cardA").toValue(cardA).inSingletonScope()
        Container.register<Card>("cardB").toValue(cardB).inSingletonScope()
        Container.register<Card>("cardPrototype")
            .toFactory { Card(cardId = UUID.randomUUID(), cardRealName = "test", cardType = CardType.ITEM) }
            .inTransientScope()
        val a = Container.resolve("cardA", Card::class.java)
        val b = Container.resolve("cardPrototype", Card::class.java)
        System.out.println("1");
    }
}