package org.co2dice.mirai.plugin

import org.co2dice.mirai.core.bean.player.prototype.Player

import org.co2dice.mirai.core.bean.game.GameSessionPool
import org.co2dice.mirai.core.decorator.env.card.numeric.CardNumericType
import org.co2dice.mirai.core.decorator.handler.DecoratorRegistry
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentAddValueDecorator
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentMultiplyValueDecorator
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentSwitchStatDecorator
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericContext
import org.co2dice.mirai.core.publicEnums.TokenDepend
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.core.bean.chessman.prototype.FullAttributeChessman
import org.co2dice.mirai.core.bean.card.prototype.Card
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
        val characterCard = FullAttributeChessman(
            cardName = "测试角色",
            flavorText = "测试角色",
            imgUrl = "/",
            characterHolder = player
        )
        gameSession.addBattle(mutableListOf(characterCard))
        val scene = gameSession.getPlayerScene(player)!!
        val card = SkillCardInstance()
        scene.addDecorator(
            CardNumericPermanentAddValueDecorator(
                CardNumericType.CHAOS,
                500
            )
        )
        scene.addDecorator(CardNumericPermanentSwitchStatDecorator())
        scene.addDecorator(
            CardNumericPermanentMultiplyValueDecorator(
                CardNumericType.ORDER,
                2
            )
        )
        val value: Int = scene.getHandler(DecoratorRegistry.GET_CARD_NUMERIC)
            //获取场景的Token，并运用于卡片的获取属性值
            .apply(
                GetCardNumericContext(
                    CardNumericType.CHAOS,
                    card
                )
            ).value()
        println(value)
    }

    @Test
    fun main2(){
        Container.init("org.co2dice.mirai.core.bean.game.prototype")
        val cardA = Card(cardId = UUID.randomUUID(), cardRealName = "testA", cardType = TokenDepend.ITEM)
        val cardB = Card(cardId = UUID.randomUUID(), cardRealName = "testB", cardType = TokenDepend.ITEM)
        Container.register<Card>("cardA").toValue(cardA).inSingletonScope()
        Container.register<Card>("cardB").toValue(cardB).inSingletonScope()
        Container.register<Card>("cardPrototype")
            .toFactory { Card(cardId = UUID.randomUUID(), cardRealName = "test", cardType = TokenDepend.ITEM) }
            .inTransientScope()
        val a = Container.resolve("cardA", Card::class.java)
        val b = Container.resolve("cardPrototype", Card::class.java)
        System.out.println("1");
    }
}