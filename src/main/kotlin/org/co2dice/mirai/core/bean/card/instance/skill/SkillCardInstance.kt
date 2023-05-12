package org.co2dice.mirai.core.bean.card.instance.skill


import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.api.PublicCardInstance
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.UnPublicEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
class SkillCardInstance(
    entry: CardEntry,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance? = null,
    override var effects: EffectInstance<UnPublicEffect>,
    ) : PublicCardInstance<UnPublicEffect>(entry,registry), DependPlayer<PlayerInstance?>,CAO  {

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