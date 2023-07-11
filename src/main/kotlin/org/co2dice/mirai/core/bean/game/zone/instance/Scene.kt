package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.instance.*
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.game.time.Turns
import org.co2dice.mirai.core.bean.game.zone.api.CardVesselApi
import org.co2dice.mirai.core.bean.game.zone.entry.DeckEntry
import org.co2dice.mirai.core.bean.player.enrty.PlayerEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.decorator.handler.DecoratorHolder
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.UniqueIdRegistry
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
class Scene (
    val registry : UniqueIdRegistry = UniqueIdRegistry(),
    //依赖于场景的注册器
    decks :Map<PlayerEntry, DeckEntry>,
    //玩家与其相关区域的映射
    var hasEnded:Boolean = false,
    //是否已经结束
    var isClosed:Boolean = false,
    //是否已经关闭（暂停）
    mapSize:Int = ConstantUtils.VENUE_SIZE_MAX,
    val turns: Turns = Turns(),
    //回合
    val desks:MutableSet<DeskInstance> = mutableSetOf<DeskInstance>()
        .apply { decks.keys.filter { decks[it]?.checkDeckLegal()?:false }.forEach {
            this.add(DeskInstance(
                registry = registry,
                holder = PlayerInstance(it),
                deck = decks[it]!!.main,
                venueDeck = decks[it]!!.venueCard
            )) } },
    //卡组不合法直接禁止游戏
    val field: FieldInstance = FieldInstance(mapSize).apply {
        //从每个玩家的venueDeck中获取4张地形卡
        desks.forEach {
            val list : MutableList<VenueCardInstance> = mutableListOf()
            for (i in 0 until 3){
                val unPublicVenue = it.value.venueDeck.draw()
                if (unPublicVenue != null){
                    list.add(
                        VenueCardInstance(
                        entry = unPublicVenue.entry ,
                        registry = registry )
                    )
                }
            }
        }
    },
    //角色和地形卡的地图
): DecoratorHolder() {

    init {
        if (desks.size < 2){
            throw Exception("玩家数量不足")
        }
        if (desks.size != decks.size){
            throw Exception("有玩家的卡组不合法")
        }
    }

    private val buffer : BufferStack = BufferStack()


    val history :MutableList<ResolutionSituation> = mutableListOf()
    //历史记录列表

    fun makeDamage(){

    }

    fun start() : Boolean{
        //开启场景，所有角色抽5张卡,如果有人卡组有问题，返回false
        getPlayers().forEach{
            for (i in 0 until  4){
                val deckNotEmpty = desks[it]?.draw() ?: return false
                //如果false
                if (!deckNotEmpty){
                    //如果卡组空了
                }
            }
        }.also { return true }
    }

    fun draw(player : PlayerInstance) : Boolean{
        val deckNotEmpty = getDesk(player)?.draw() ?: return false
        if (!deckNotEmpty){
            //如果卡组空了,对一个玩家的每个棋子的每个属性都削弱一个单位。如果基础的六个属性中,有一个属性没有被

        }

    }

    fun equip(player: PlayerInstance,initiator: ChessmanInstance,item : ItemCardInstance) : Boolean{
        val equipment = field.chessmen[initiator]?.equipments ?: return false
        //如果没有这个棋子，返回false
        return equipment.addCard(item)
    }

    fun getPlayerAllChessmanEquipments(player : PlayerInstance) : Set<Equipments>{
        return field.chessmen.filter {
            it.key is DependPlayer && it.key.holder == player
        }.map { it.value.equipments }.toSet()
    }

    fun moveCard(card : CardInstance,from : CardVesselApi<*>,to : CardListVessel<*>) : Boolean{
        var wasRemoved = false
        if (card is ItemCardInstance){
            //如果是装备卡
            val equipments = field.chessmen.values.stream().map { it.equipments }.toList()
            //将装备栏提取出来
            if (equipments.stream().anyMatch { it.contain(card) }){
                //如果有任何一个装备栏包含这张卡
                equipments.stream().forEach {
                    wasRemoved = it.removed(card) || wasRemoved
                //删掉这张卡
                }
            }
        }
        //如果没有删掉卡，就再执行zones的move方法
        return wasRemoved
            || (card is DependPlayer
            && getDesk(card.holder)?.moveCard(card,from,to)?:false)
    }

    fun releaseCard(player: PlayerInstance,chessmanInstance: ChessmanInstance,card : CardInstance) : Boolean{
        //释放卡必须通过棋子和玩家来发动
        if (card is MainDeckUnPublicCardInstance){
            //主卡组的卡,如果不在手卡直接返回false
            if (getDesk(player) != null && getDesk(player)!!.hand.contain(card)){
                //存在于手牌中，开始判定卡片类型
                val prototype = card.entry.prototype
                if (prototype is SkillCard){
                    //是技能卡:将此卡移动到spell区，并给行动联锁加个action实体
                    getDesk(player)!!.hand.removed(card).also {
                        getDesk(player)!!.buffer.addCard(
                            SpellCardInstance(
                                entry = card.entry,
                                holder = player
                            )
                        )
                        buffer.add()
                    }
                }

            }else{
                return false
            }
        }else if (card is SideDeckUnPublicCardInstance){

        }

        return false
    }

    fun chainCheckOut(situation: ResolutionSituation){
        buffer.checkOut(situation)
    }

    //专门给cost用的
    fun findCardCostVessel(card : CardInstance) : CardVesselApi<out CardInstance<out ActivatedAbility>>? {
        if (card is ItemCardInstance){
            val equipments = field.chessmen.values.stream().map { it.equipments }.toList()
            if (equipments.stream().anyMatch { it.contain(card) }){
                return equipments.stream().filter { it.contain(card) }.findFirst().get()
            }
        }
        if (card is DependPlayer){
            return getDesk(card.holder)?.findCardInstance(card)
        }
        return null
    }


//    fun<E : ActivatedAbility> createSituation(input : Map<String,Any>,
//                                    activatedAgent : ActivatedAgent<E>,
//                                    player: PlayerInstance,
//                                    activatedAbility : ActivatedAbilityEntry<E>) : BaseSituationSymbol {
//
//
//
//        return BaseSituationSymbol(
//            input = input,
//            scene = this,
//            activatedAgent = activatedAgent,
//            player = player,
//            initiator = ,
//            target = ,
//            activatedAbility = activatedAbility,
//
//        )
//    }

    private fun getPlayers() : List<PlayerInstance>{
        return desks.map { it.holder }
    }

    fun getDesk(player : PlayerInstance) : DeskInstance?{
        return desks.firstOrNull { it.holder == player }
    }
}