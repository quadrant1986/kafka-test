package ru.coriolis.serdes;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import ru.coriolis.model.Numbers;

import java.util.Map;

import static ru.coriolis.util.Utils.objectMapper;

public class NumbersSerializer implements Serializer<Numbers> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public byte[] serialize(String topic, Numbers data) {
        if (data == null)
            return null;

        try {
            final JsonNode tree = objectMapper().valueToTree(data);

            return objectMapper().writeValueAsBytes(tree);

        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    @Override
    public void close() { }
}
