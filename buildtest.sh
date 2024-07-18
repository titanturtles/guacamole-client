#!/bin/bash

# 0) remove previous versions
rm ./guacamole/target/guacamole-1.5.5*.war

# 1) build
# timestamp=$(date +"%Y%m%d%H%M%S")
# mvn versions:set -DnewVersion=1.5.5.$timestamp
mvn versions:set -DnewVersion=1.5.5
mvn -Drat.ignoreErrors=true -Drat.skip=true package

