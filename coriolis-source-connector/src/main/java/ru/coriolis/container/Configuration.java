package ru.coriolis.container;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.coriolis.constants.ConfigConstants;

import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class Configuration {

    private String topic;

    private static Configuration instance = new Configuration();

    private Configuration() { }

    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();

        return instance;
    }

    public Map<String, String> convert2Map() {
        Map<String, String> map = new HashMap<>();

        map.put(ConfigConstants.TOPIC, topic);

        return map;
    }

    public void setBusinessDbHost(String envTopic) {
        if (envTopic != null && System.getenv(envTopic) != null) {
            this.topic = System.getenv(envTopic);

            log.info("Founded DB hostname in env variable {}: {}", envTopic, this.topic);
        }

        else
            this.topic = envTopic == null ? ConfigConstants.TOPIC_DEFAULT : envTopic;
    }
}
