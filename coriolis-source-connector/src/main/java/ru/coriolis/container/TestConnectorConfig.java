package ru.coriolis.container;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

import static ru.coriolis.constants.ConfigConstants.TOPIC;
import static ru.coriolis.constants.ConfigConstants.TOPIC_DESC;

public class TestConnectorConfig extends AbstractConfig {

    public TestConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public TestConnectorConfig(Map<String, String> parsedConfig) {
        this(config(), parsedConfig);
    }

    public static ConfigDef config() {
        return new ConfigDef()
                .define(TOPIC, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TOPIC_DESC)
                ;
    }
}
