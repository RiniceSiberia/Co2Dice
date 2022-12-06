package org.co2dice.mirai.utils;

import java.util.ArrayList;
import java.util.List;

public class SerializeUtils {
    public static List<String> serializeToStr(String str){
        //将字符串序列化
        String regex = "\\,";
        List<String> list = new ArrayList<>();
            String[] ids = str.split(regex);
            for (String id : ids) {
                if (id != null && !id.equals("")) {
                    list.add(id);
                }
            }

        return list;
    }

    public static <T> String deserialize(List<T> list){
        //反序列化RiverIds
        StringBuilder sb = new StringBuilder();
        for (T a : list) {
            sb.append(a.toString()).append(",");
        }
        return sb.toString();
    }
}
