package org.co2dice.mirai.utils;

import org.co2dice.mirai.bean.cards.CardType;

import java.util.Arrays;
import java.util.List;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-12-06-21:06
 * @Message: Have a good time!  :)
 **/
public class ConstantUtils {
    public static final String GPT_KEY = "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";
    public static final List<Float> EXPECTED_VALUE =
            Arrays.asList(2f,
                    3f,
                    4.5f,
                    6f,
                    8f,
                    10f,
                    12.5f,
                    15f,
                    18f,
                    21f,
                    25f,
                    29f,
                    34f,
                    39.5f,
                    45.5f,
                    52f,
                    59.5f,
                    68f,
                    77f,
                    87f,
                    98f);


    public String getCardPath(String resourcesType,CardType type, String name, String extension){
        return "/resources/"+resourcesType+"/cards/" + type.name() +"/"+name+"."+extension;
    }

}
