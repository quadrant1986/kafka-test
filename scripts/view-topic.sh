#!/bin/bash

TOPIC=''

if [ -z "$1" ]
then
    TOPIC='topic.data'
else
    TOPIC=$1
fi

kafka-console-consumer --bootstrap-server localhost:9092 --topic $TOPIC --from-beginning