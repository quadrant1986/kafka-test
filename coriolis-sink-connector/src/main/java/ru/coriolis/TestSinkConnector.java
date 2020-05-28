package ru.coriolis;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkConnector;
import ru.coriolis.config.TestSinkConnectorConfig;
import ru.coriolis.task.TestSinkConnectorTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestSinkConnector extends SinkConnector {

    private Map<String, String> configProperties;

    @Override
    public void start(Map<String, String> properties) {
        log.info("Starting {}", this.getClass().getSimpleName());

        try {
            configProperties = properties;
            new TestSinkConnectorConfig(properties);

        } catch (ConfigException ex) {
            throw new ConnectException("Couldn't start TestSinkConnector due to exception", ex);
        }
    }

    @Override
    public void stop() {
        log.info("Stopping {}", this.getClass().getSimpleName());
    }

    @Override
    public Class<? extends Task> taskClass() {
        return TestSinkConnectorTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<>();
        Map<String, String> taskProps = new HashMap<>(configProperties);

        for (int i = 0; i < maxTasks; i++)
            taskConfigs.add(taskProps);

        return taskConfigs;
    }

    @Override
    public ConfigDef config() {
        return TestSinkConnectorConfig.CONFIG_DEF;
    }

    @Override
    public String version() {
        return getClass().getPackage().getImplementationVersion();
    }
}
