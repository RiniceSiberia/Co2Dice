package org.co2dice.mirai.core.publicEnums

import kotlinx.serialization.Serializable

@Serializable
enum class ItemType(val nameStr : String) {
    LEFT_HAND("左手"),
    RIGHT_HAND("右手"),
    HEAD("头部"),
    BODY("身体"),
    FOOT("脚"),
}