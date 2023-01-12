package org.co2dice.mirai.plugin

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.PlayerCharacterCard
import org.co2dice.mirai.bean.cards.effect.attribute.AttributeType
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.skill.SkillCard
import org.co2dice.mirai.bean.game.GameSession
import org.co2dice.mirai.bean.game.GameSessionPool
import org.co2dice.mirai.bean.game.decorator.handler.DecoratorRegistry
import org.co2dice.mirai.bean.game.decorator.implementation.SimplePermanentAddValueDecorator
import org.co2dice.mirai.bean.game.decorator.implementation.SimplePermanentMultiplyValueDecorator
import org.co2dice.mirai.bean.game.decorator.implementation.SimplePermanentSwitchStatDecorator
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeContext
import org.junit.jupiter.api.Test


class Test {
    @Test
    fun main(args: Array<String>) {
        DecoratorRegistry.register()
        val gameSession = GameSessionPool.getGameSession("test")!!
        val player = Player(qq = 2979897510L)
        GameSessionPool.createGameSession("123456",gameSession)
        val characterCard = PlayerCharacterCard(
            cardName = "测试角色",
            flavorText = "测试角色",
            imgUrl = "/",
            characterHolder = player
        )
        gameSession.addBattle(mutableListOf(characterCard))
        val card = SkillCard()
        duel.addDecorator(SimplePermanentAddValueDecorator(AttributeType.DEF, 500))
        duel.addDecorator(SimplePermanentSwitchStatDecorator())
        duel.addDecorator(SimplePermanentMultiplyValueDecorator(AttributeType.ATK, 2))
        val value: Int = duel.getHandler(DecoratorRegistry.GET_NUMERIC_ATTRIBUTE)
            .apply(GetNumericAttributeContext(AttributeType.ATK, card)).value()
        println(value)
    }
}