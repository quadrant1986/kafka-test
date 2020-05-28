package ru.coriolis.json;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;
import ru.coriolis.model.Numbers;

@UtilityClass
public class NumberJsonConverter {

    private final Gson GSON = new Gson();

    public String toJson(Numbers numbers) {
        if (numbers == null)
            return null;

        return GSON.toJson(numbers);
    }

    public Numbers fromJson(String json) {
        if (json == null || json.trim().isEmpty())
            return null;

        return GSON.fromJson(json, Numbers.class);
    }
}
