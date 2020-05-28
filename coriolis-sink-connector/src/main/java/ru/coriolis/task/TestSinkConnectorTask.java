package ru.coriolis.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import ru.coriolis.config.TestSinkConnectorConfig;
import ru.coriolis.model.Numbers;
import ru.coriolis.serdes.NumbersDeserializer;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class TestSinkConnectorTask extends SinkTask {

    private TestSinkConnectorConfig connectorConfig;
    private final static NumbersDeserializer numbersDeserializer = new NumbersDeserializer();

    @Override
    public void start(Map<String, String> properties) {
        log.info("Starting {}", this.getClass().getSimpleName());

        this.connectorConfig = new TestSinkConnectorConfig(properties);
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        if (!records.isEmpty()) {
            records.forEach(this::handleRecord);
        }
    }

    @Override
    public void stop() {
        log.info("Stopping {}", this.getClass().getSimpleName());
    }

    @Override
    public String version() {
        return getClass().getPackage().getImplementationVersion();
    }

    private void handleRecord(SinkRecord record) {
        final String key = (String) record.key();

        Numbers numbers = numbersDeserializer.deserialize(null, record.value().toString().getBytes());

        log.info("{} :: [{}] {} {}",
                key,
                numbers.getCreated(),
                numbers.getVal1(),
                numbers.getVal2()
                );
    }
}
