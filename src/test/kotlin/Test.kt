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
        val player1Entry = PlayerEntry(uuid = UUID.randomUUID(),player1)
        val player2Entry = PlayerEntry(uuid = UUID.randomUUID(),player2)
        val player3Entry = PlayerEntry(uuid = UUID.randomUUID(),player3)
        val player4Entry = PlayerEntry(uuid = UUID.randomUUID(),player4)

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
        repeat(40){
            main.add(CardEntry(
                uuid = UUID.randomUUID(),
                prototype = ItemCard(
                    uuid = UUID.randomUUID(),
                    cardRealName = "testCard${it}",
                    types = CategoryPack(),
                    activatedAbilities = listOf(),
                    staticAbilities = listOf(),
                    triggeredAbilities = listOf(),
                    ),
                ))
        }
        val venueCard : MutableList<CardEntry>
        val deckEntry1 = DeckEntry(UUID.randomUUID(),player1Entry)
        val scene = Scene(
            decks = ,

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
        //测试效果:场上发动，丢弃1张手牌,解放此卡,献祭一个骰子,没有额外的启动限制,选择场上的一个棋子,对其造成3点力量伤害,检定力量值+3(默认为0),对抗目标的力量值.
        val effectActivated : FieldActivatedAbilityInstance = FieldActivatedAbilityInstance(
            entry = ActivatedAbilityEntry(
                uuid = UUID.randomUUID(),
                prototype = FieldActivatedAbility(
                    uuid = UUID.randomUUID(),
                    targetFunction = AstTree(
                        json = ,
                    ),
                    launchConditions = AstTree(
                        json = ConstantLeafNode<Boolean>(
                            symbol = BoolConstant,
                            value = true,
                        ).serialize(),
                    ),
                    cost = ,
                    check = ,
                    react = ,
                    operation = ,
                )
            ),
        )
    }
}