#!/bin/bash
# permission error -> chmod +x build_run.sh
# mvn clean
# mvn install
mvn clean package -P local

# docker build --tag shun6889/springtest .
# docker push shun6889/springtest

docker buildx build --platform linux/arm/v7 -t shun6889/springtest:latest --push .
