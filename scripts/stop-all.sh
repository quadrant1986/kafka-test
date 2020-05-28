#!/bin/bash

echo "stopping all connect workers."
WIDS=$(ps aux | grep java | grep connect | awk '{ print $2 }' | tr "\n" " ")
if [ -z "$WIDS" ]; then
  echo "no connect workers running."
else
  kill ${WIDS}
fi