package org.co2dice.mirai.bean.cards.affix

abstract class ItemAffixes(param: MutableMap<String,String>) : AbstractAffixes(param) {
    class Weapon(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "武器"
        override val description: String = "该卡的混乱值根据这张卡能造成的最大伤害而提升。"
    }
    class Armor(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "护甲"
        override val description: String = "该卡的秩序值根据这张卡能降低的伤害而提升。"
    }
    class Magic(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "魔法"
        override val description: String = "该卡为魔法造物。"
    }
    class Valuable(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "工艺"
        override val description: String = "该卡的秩序值提升(${param["level"]?.ifEmpty { "?" }})"
    }
    class Heavy(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "笨重"
        override val description: String = "该卡的混乱值降低(${param["level"]?.ifEmpty { "?" }}),使用该卡的技能时会额外增加消耗。"
    }
    class Blunt(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "钝器"
        override val description: String = "该卡技能造成的永久伤害降低，但会给被攻击者附带高额的临时伤害"
    }
    class Sharp(param: MutableMap<String,String>) : ItemAffixes(param) {
        override val name: String = "锐器"
        override val description: String = "该卡的技能会降低伤害。"
    }
    class

}