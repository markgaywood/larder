package net.amg.larder.utils;

public class IncorrectJson extends Exception {
    public IncorrectJson() {
    }

    public IncorrectJson(String message) {
        super(message);
    }

    public IncorrectJson(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectJson(Throwable cause) {
        super(cause);
    }

    public IncorrectJson(Throwable throwable, String jsonTryingToDeserialize, Class<?> aClass) {
        super(
                String.format("Could not deserialise json string \"%s\" into an instance of \"%s\" (\"%s\")",
                        jsonTryingToDeserialize, aClass.getName(), throwable.getMessage())
                , throwable);
    }
}
