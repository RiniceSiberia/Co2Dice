package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.category.CategoryPack
import java.util.*


sealed class Card(
    override val uuid : UUID,
    open val cardRealName : String,
    open val types : CategoryPack,
) : PrototypeStructure{



}