package org.co2dice.mirai.plugin

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.ast.node.ConstantLeafNode
import org.co2dice.mirai.core.ast.symbol.impl.leaf.constant.BoolConstant
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.player.prototype.Player

import org.co2dice.mirai.core.bean.game.GameSessionPool
import org.co2dice.mirai.core.decorator.env.card.numeric.CardNumericType
import org.co2dice.mirai.core.decorator.handler.DecoratorRegistry
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentAddValueDecorator
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentMultiplyValueDecorator
import org.co2dice.mirai.core.decorator.implementation.card.numeric.CardNumericPermanentSwitchStatDecorator
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericContext
import org.co2dice.mirai.core.bean.card.prototype.Card
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.chessman.prototype.HumanChessman
import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace
import org.co2dice.mirai.core.bean.dice.instance.ListDices.tripleD6
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.FieldActivatedAbility
import org.co2dice.mirai.core.bean.game.zone.entry.DeckEntry
import org.co2dice.mirai.core.bean.game.zone.instance.Scene
import org.co2dice.mirai.core.bean.player.enrty.PlayerEntry
import org.co2dice.mirai.core.utils.MathUtils
import org.junit.jupiter.api.Test
import space.controlnet.lightioc.api.Container
import java.util.*


class Test {
    @Test
    fun main(args: Array<String>) {
        DecoratorRegistry.register()
        val gameSession = GameSessionPool.getGameSession("test")!!
        val player1 = Player(UUID.randomUUID(),qq = 2979897510L)
        val player2 = Player(UUID.randomUUID(),qq = 2979897511L)
        val player3 = Player(UUID.randomUUID(),qq = 2979897512L)
        val player4 = Player(UUID.randomUUID(),qq = 2979897513L)
        val player1Entry = PlayerEntry(prototype = player1)
        val player2Entry = PlayerEntry(prototype = player2)
        val player3Entry = PlayerEntry(prototype = player3)
        val player4Entry = PlayerEntry(prototype = player4)

        val chessmen : MutableList<ChessmanEntry> = mutableListOf<ChessmanEntry>()
        repeat(8){
            chessmen.add(ChessmanEntry(
                uuid = UUID.randomUUID(),
                prototype = HumanChessman(
                    uuid = UUID.randomUUID(),
                    chaos = 1,
                    order = 1,
                    attributeTable = AttributeTable.createElite(
                        tripleD6.sumOf (DispersedSpace<Int>::roll),
                        tripleD6.sumOf (DispersedSpace<Int>::roll),
                        tripleD6.sumOf (DispersedSpace<Int>::roll),
                        tripleD6.sumOf (DispersedSpace<Int>::roll),
                        tripleD6.sumOf (DispersedSpace<Int>::roll),
                        tripleD6.sumOf (DispersedSpace<Int>::roll)),
                    types = CategoryPack(),
                    name = "Test${it}",
                    activatedAbilities = listOf(),
                    staticAbilities = listOf(),
                    triggeredAbilities = listOf(),
                    ),
                ))
        }
        val main : MutableList<CardEntry> = mutableListOf<CardEntry>()
        repeat(30){
            main.add(CardEntry(
                uuid = UUID.randomUUID(),
                prototype = ItemCard(
                    uuid = UUID.randomUUID(),
                    cardRealName = "testCard${it}",
                    types = CategoryPack(),
                    activatedAbilities = listOf(),
                    staticAbilities = listOf(),
                    triggeredAbilities = listOf(),
                    chaos = 1,
                    order = 1,
                    occupy = mapOf(),
                    ),
                ))
        }
        val venueCard : MutableList<CardEntry> = mutableListOf<CardEntry>()
        repeat(15){
            venueCard.add(CardEntry(
                uuid = UUID.randomUUID(),
                prototype = ItemCard(
                    uuid = UUID.randomUUID(),
                    cardRealName = "testVenueCard${it}",
                    types = CategoryPack(),
                    activatedAbilities = listOf(),
                    staticAbilities = listOf(),
                    triggeredAbilities = listOf(),
                    chaos = 1,
                    order = 1,
                    occupy = mapOf(),
                    ),
                ))
        }
        val deckEntry1 = DeckEntry(player1Entry,chessmen, main, venueCard)
        val deckEntry2 = DeckEntry(player2Entry,chessmen, main, venueCard)
        val deckEntry3 = DeckEntry(player3Entry,chessmen, main, venueCard)
        val deckEntry4 = DeckEntry(player4Entry,chessmen, main, venueCard)
        val scene = Scene(
            decks = setOf(
                deckEntry1,
                deckEntry2,
                deckEntry3,
                deckEntry4,
            ),
        )



        val characterCard = FullAttributeChessman(
            cardName = "测试角色",
            flavorText = "测试角色",
            imgUrl = "/",
            characterHolder = player
        )
        gameSession.addBattle(mutableListOf(characterCard))

        val card = ReleaseCardInstance()
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

    @Test
    fun test3(){
        MathUtils.selectElements(
            listOf(1,2,3,4,5,6,7,8,9),
            { it.size in 4..5 },
            {it != 5 && it != 6}
        )
    }
}