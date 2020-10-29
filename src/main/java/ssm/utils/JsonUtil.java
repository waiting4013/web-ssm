package ssm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.*;

public class JsonUtil extends JSON {
    private static final SerializerFeature[] features = {
            //SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.SkipTransientField
    };
    private JsonUtil() {
    }

    public static String toJson(Object target) {
        return toJson(target,null);
    }

    public static String toJson(Object target, String fmt) {
        if(StringUtils.isEmpty(fmt)){
            return JSON.toJSONStringWithDateFormat(target, "yyyy-MM-dd HH:mm:ss", features);
        }else{
            return JSON.toJSONStringWithDateFormat(target, fmt, features);
        }
    }

    public static String toExculdeJson(Object target, final String... exculdeNames) {
        ValueFilter vf = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                for (String exculdeName : exculdeNames) {
                    if(!exculdeName.equals(name)){
                        return value;
                    }
                }
                return null;
            }
        };
        SerializeWriter writer = new SerializeWriter();
        JSONSerializer out = new JSONSerializer(writer);
        for (SerializerFeature feature : features) {
            out.config(feature, true);
        }
        out.getValueFilters().add(vf);
        out.write(target);
        return out.toString();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }
    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }
    public static <T> List<Object> fromJsonList(String json, Type[] types) {
        return JSON.parseArray(json, types);
    }
    /**
     * json字符串转换为对象
     * @param pojoCalss
     * @return
     */
    public static <T> T jsonStr2Object(String jsonStr, Class<T> pojoCalss){
        return JSONObject.parseObject(jsonStr, pojoCalss);
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<>();
        //最外层解析
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    Object json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * 获取JsonObject
     * @param json
     * @return
     */
    public static JsonObject parseJson(String json){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }

    /**
     * 根据json字符串返回Map对象
     * @param json
     * @return
     */
    public static Map<String,Object> toMap(String json){
        return JsonUtil.toMap(JsonUtil.parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json){
        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JsonArray)
                map.put((String) key, toList((JsonArray) value));
            else if(value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else
                map.put((String) key, value);
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json){
        List<Object> list = new ArrayList<>();
        for (int i=0; i<json.size(); i++){
            Object value = json.get(i);
            if(value instanceof JsonArray){
                list.add(toList((JsonArray) value));
            }
            else if(value instanceof JsonObject){
                list.add(toMap((JsonObject) value));
            }
            else{
                list.add(value);
            }
        }
        return list;
    }
    /**
     * json数组字符串转list
     * @param object
     * @return
     */
    public static List<Map<String, Object>> toList(Object object){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object obj : jsonArray){
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator it = jsonObject.keys();
            while (it.hasNext()){
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }


}
