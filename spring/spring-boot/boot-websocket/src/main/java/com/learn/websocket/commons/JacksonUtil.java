/**
 * @author G-bug 2019/9/26
 */
package com.learn.websocket.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @auth Administrator
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj) {
        try {

            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T json2Bean(String jsonStr, TypeReference type) {
        try {
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
