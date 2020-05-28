package ru.coriolis.config;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class TestSinkConnectorConfig extends AbstractConfig {

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            ;
            //.define(PROCESSING_MAX_ATTEMPTS, ConfigDef.Type.INT, PROCESSING_MAX_ATTEMPTS_DEFAULT, ConfigDef.Importance.MEDIUM, PROCESSING_MAX_ATTEMPTS_DOC)

    public TestSinkConnectorConfig(Map<String, ?> properties) {
        super(CONFIG_DEF, properties);
    }
}
