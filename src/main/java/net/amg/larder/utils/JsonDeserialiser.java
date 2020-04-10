package net.amg.larder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonDeserialiser {
    public static <T> T from(String jsonString, Class<T> aClass) throws IncorrectJson {
        try {
            return new ObjectMapper().readValue(jsonString, aClass);
        } catch (IOException | NullPointerException e) {
            throw new IncorrectJson(e, jsonString, aClass);
        }
    }
}
