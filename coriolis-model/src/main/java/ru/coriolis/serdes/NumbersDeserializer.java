package ru.coriolis.serdes;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import ru.coriolis.model.Numbers;

import java.util.Map;

import static ru.coriolis.util.Utils.objectMapper;

public class NumbersDeserializer implements Deserializer<Numbers> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public Numbers deserialize(String topic, byte[] bytes) {
        if (bytes == null)
            return null;

        try {
            JsonNode jsonTree = objectMapper().readTree(bytes);

            // TODO: костыль, разобраться со схемами SourceRecord от Kafka Connect
            if (jsonTree.get("payload") != null) {
                final String payload = jsonTree.get("payload").textValue();

                jsonTree = objectMapper().readTree(payload);
            }

            return objectMapper().treeToValue(jsonTree, Numbers.class);

        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() { }
}
