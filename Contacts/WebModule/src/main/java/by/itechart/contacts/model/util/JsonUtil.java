package by.itechart.contacts.model.util;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public static Map<String, Object> getJson(String[]keys, Object[] values){
        Map<String, Object> jsonMap = new HashMap<>();
        for (int i = 0, j = 0; i < keys.length && j < values.length; i++, j++) {
            jsonMap.put(keys[i],values[j]);
        }
        return jsonMap;
    }

}
