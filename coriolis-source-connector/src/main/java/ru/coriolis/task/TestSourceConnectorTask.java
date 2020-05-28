package ru.coriolis.task;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import ru.coriolis.constants.ConfigConstants;
import ru.coriolis.json.NumberJsonConverter;
import ru.coriolis.model.Numbers;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSourceConnectorTask extends SourceTask {

    private String topic;

    @Override
    public void start(Map<String, String> properties) {
        this.topic = properties.get(ConfigConstants.TOPIC);
    }

    @Override
    public void stop() { }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        return IntStream.range(0, 5)
                .boxed()
                .map(i -> {
                    Numbers result = new Numbers();
                    result.setVal1(random(1, 5));
                    result.setVal2(random(10, 15));

                    return result;
                })
                .map(this::prepareSourceRecord)
                .collect(Collectors.toList());
    }

    @Override
    public String version() {
        return null;
    }

    private SourceRecord prepareSourceRecord(Numbers numbers) {
        final long currentMillis = Calendar.getInstance().getTimeInMillis();

        Map<String, String> partition = Collections.singletonMap("sender", "randomizer");
        Map<String, String> offset = Collections.singletonMap("timestamp", Long.toString(currentMillis));

        final String json = NumberJsonConverter.toJson(numbers);

        return new SourceRecord(
                partition,
                offset,
                topic,
                Schema.STRING_SCHEMA, numbers.getCreated(),
                Schema.STRING_SCHEMA, json);
    }

    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
