package org.co2dice.mirai.utils;


/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-12-06-21:06
 * @Message: Have a good time!  :)
 **/
public class ConstantUtils {
    public static final String GPT_KEY = "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";



    public String getCardPath(String resourcesType,
                              String imgUrl,
                              String extension){
        return "/resources/" + resourcesType + "/cards/" + imgUrl + "." + extension;
    }

}
