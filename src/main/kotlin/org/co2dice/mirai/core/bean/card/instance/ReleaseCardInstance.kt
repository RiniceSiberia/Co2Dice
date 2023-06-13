package org.co2dice.mirai.core.bean.card.instance


import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.instance.release.ReleaseEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.ReleaseEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: 技能，道具等卡片释放时使用的卡片实例，"未召唤前的星态"，处理完后是道具会留在场上，是技能会被"花费掉"(正常是去墓地，但也可能除外)
 **/
class ReleaseCardInstance(
    entry: CardEntry<*>,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance? = null,
    override var effects: EffectInstance<ReleaseEffect> = ReleaseEffectInstance(entry.effectEntry.filterIsInstance<EffectEntry<ReleaseEffect>>()),
    ) : PublicCardInstance<ReleaseEffect>(entry,registry), DependPlayer<PlayerInstance?>,CAO  {

    override val chaos: Int?
        get(){
            return if (entry.card is CAO){
                (entry.card as CAO).chaos
            }else{
                null
            }
        }
    override val order: Int?
        get(){
            return if (entry.card is CAO){
                (entry.card as CAO).order
            }else{
                null
            }
        }


}