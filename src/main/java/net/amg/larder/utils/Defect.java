package net.amg.larder.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Defect extends RuntimeException {
    public Defect() {
        super();
    }

    public Defect(Throwable cause) {
        super(cause);
    }

    public Defect(String message) {
        super(message);
    }

    public Defect(String message, Throwable cause) {
        super(message, cause);
    }

    public static Defect ofJsonSerialization(Object object, JsonProcessingException e) {
        return ofSerialization(object, e, "json");
    }

    public static Defect ofXmlSerialization(Object object, JsonProcessingException e) {
        return ofSerialization(object, e, "xml");
    }

    private static Defect ofSerialization(Object object, JsonProcessingException e, final String type) {
        // if we can't convert an object to String then we've made a programming error
        return new Defect(
                String.format(
                        "Failure to convert object \"%s\" to %s string. This is surely a programming error.",
                        object,
                        type),
                e);
    }

    public static Defect ofJsonDeserialization(String jsonString, Class<?> aClass, Throwable e) {
        // if we can't convert a Json string into an object, usually that means there is a problem with the string,
        // but this method is to be used where we believe the string is correct and we've made a programming error
        return new Defect(
                String.format(
                        "Failure to convert json string \"%s\" into an instance of \"%s\". In this particular case, this is thought to be a programming error.",
                        jsonString,
                        aClass.getName()),
                e);
    }
}
