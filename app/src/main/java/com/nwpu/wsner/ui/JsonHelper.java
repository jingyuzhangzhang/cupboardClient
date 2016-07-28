package com.nwpu.wsner.ui;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JsonHelper {
    static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转成JsonString
     *
     * @param obj
     * @return
     */
    public static String obj2jsonStr(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JsonString解析获得对象
     *
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T jsonStr2Obj(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JsonString解析获得JSON对象({"position":...}格式)
     * @param content
     * @param objName
     * @param valueType
     * @param <T>
     * @return
     */

    public static <T> T jsonStr2Obj(String content, String objName, Class<T> valueType){
        try {
            JSONObject object=new JSONObject(content);
            String result=object.getString(objName);
            try {
                return objectMapper.readValue(result, valueType);
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
