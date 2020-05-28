package ru.coriolis.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

public class Utils {

    private static ObjectMapper objectMapper;

    public static ObjectMapper objectMapper() {
        if (objectMapper == null)
            objectMapper = new ObjectMapper();

        return objectMapper;
    }
}
