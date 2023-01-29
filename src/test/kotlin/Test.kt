package org.co2dice.mirai.plugin

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.character.PlayerCharacterCard
import org.co2dice.mirai.bean.cards.skill.SkillCard
import org.co2dice.mirai.bean.game.GameSessionPool
import org.co2dice.mirai.bean.game.decorator.env.AttributeNumericType
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
        val scene = gameSession.getPlayerScene(player)!!
        val card = SkillCard()
        scene.addDecorator(SimplePermanentAddValueDecorator(AttributeNumericType.CHAOS, 500))
        scene.addDecorator(SimplePermanentSwitchStatDecorator())
        scene.addDecorator(SimplePermanentMultiplyValueDecorator(AttributeNumericType.ORDER, 2))
        val value: Int = scene.getHandler(DecoratorRegistry.GET_NUMERIC_ATTRIBUTE)
            //获取场景的Token，并运用于卡片的获取属性值
            .apply(GetNumericAttributeContext(AttributeNumericType.CHAOS, card)).value()
        println(value)
    }
}