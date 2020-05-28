package ru.coriolis.serde;

import lombok.experimental.UtilityClass;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import ru.coriolis.model.Numbers;
import ru.coriolis.serdes.NumbersDeserializer;
import ru.coriolis.serdes.NumbersSerializer;

@UtilityClass
public class SerdeProvider {

    public Serde<Numbers> getMessageSerde() {
        return Serdes.serdeFrom(new NumbersSerializer(), new NumbersDeserializer());
    }
}
