package org.co2dice.mirai.utils;

import org.co2dice.mirai.bean.cards.CardType;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-12-06-21:06
 * @Message: Have a good time!  :)
 **/
public class ConstantUtils {
    public static final String DICE = "dice";
    public static final String SKILL = "skill";
    public static final String CHARACTER = "character";
    public static final String EVENT = "event";


    public String getCardPath(String resourcesType,CardType type, String name, String extension){
        return "/resources/"+resourcesType+"/cards/" + type.name() +"/"+name+"."+extension;
    }

}
