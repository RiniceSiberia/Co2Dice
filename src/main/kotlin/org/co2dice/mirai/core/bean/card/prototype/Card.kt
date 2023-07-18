package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*


sealed class Card(
    override val uuid : UUID,
    val cardRealName : String,
    val types : CategoryPack,
    val activatedAbilities : List<ActivatedAbility>,
    val staticAbilities : List<StaticAbility>,
    val triggeredAbilities :List<TriggeredAbility>,
    //我为什么要在card里加上这三个效果:
    //1.卡片的一致性
    //可能会有人说，事件卡或者技能卡是发动的，而非永续的permanent，所以不应该有static ability
    //但实际上，只要是卡片，就会进入墓地。有的事件卡，可能场上或者发动时是没有效果的，但当他进入墓地后，本身就会成为一个光环，想去除他，就必须抛坟
    //因此，这三个效果都有在所有子类中存在的意义
) : PrototypeStructure{



}