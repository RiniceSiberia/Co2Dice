package org.co2dice.mirai.core.bean.effect.cost

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.CardsVessel
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-11-18:45
 * @Message: Have a good time!  :)
 **/
class ThisCardCost(val from : CardsVessel<*,*>,val zone : CardsVessel<*,*>,
    //操作方式:
    // 解放(从场上)
    // 翻转
    // 丢弃(从手牌)
    // 丢弃(从场上)
    // 丢弃(从卡组)
    // 除外(从墓地)
    // 除外(从卡组)
    // 除外(从场上)
    // 除外(从手卡)
    ) : Cost<CardInstance<*>>(
    check = AstTree(
        json =
    ),
    //检查这个卡在不在指定位置
    execute = AstTree(),
    //把卡除外
) {

}