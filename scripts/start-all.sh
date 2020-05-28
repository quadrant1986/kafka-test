#!/bin/bash

### Build artifacts

echo ' -- build artifacts...'

cd ../
./gradlew clean shadowJar
cd ./scripts

export LOGS_DIR=/opt/kafka/logs

### Creation of topic 'topic.data'
# delete.topic.enable=true should be set

echo ' -- topic.data creation...'

kafka-topics --delete --topic topic.data --zookeeper localhost:2181

kafka-topics --create --topic topic.data --zookeeper localhost:2181 --partitions 1 --replication-factor 1

### Copying connectors

echo ' -- copying connectors...'

cp ../coriolis-source-connector/build/libs/coriolis-source-connector-1.0-all.jar ./libs/coriolis-source-connector.jar
cp ../coriolis-sink-connector/build/libs/coriolis-sink-connector-1.0-all.jar  ./libs/coriolis-sink-connector.jar

### Starting Kafka Connect

WORKERS="0"
for worker in $WORKERS; do
    echo '  -- starting worker '$worker'...'

    export KAFKA_JMX_OPTS="-Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

    connect-distributed config/connect.properties > $LOGS_DIR/connect-$worker.log 2>&1 &
done

