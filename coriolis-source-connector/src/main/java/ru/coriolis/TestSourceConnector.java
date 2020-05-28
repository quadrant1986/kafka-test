package ru.coriolis;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import ru.coriolis.constants.ConfigConstants;
import ru.coriolis.container.Configuration;
import ru.coriolis.container.TestConnectorConfig;
import ru.coriolis.task.TestSourceConnectorTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestSourceConnector extends SourceConnector {

    @Override
    public void start(Map<String, String> properties) {
        log.info("Starting {}", this.getClass().getSimpleName());

        Configuration.getInstance().setTopic(properties.get(ConfigConstants.TOPIC));
    }

    @Override
    public void stop() {
        log.info("Starting {}", this.getClass().getSimpleName());
    }

    @Override
    public Class<? extends Task> taskClass() {
        return TestSourceConnectorTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<>();

        final Map<String, String> taskProps = Configuration.getInstance().convert2Map();

        for (int i = 0; i < maxTasks; i++)
            taskConfigs.add(taskProps);

        return taskConfigs;
    }

    @Override
    public ConfigDef config() {
        return TestConnectorConfig.config();
    }

    @Override
    public String version() {
        return this.getClass().getPackage().getImplementationVersion();
    }
}
