# raspberry pi 4에 docker를 올리기 때문에 jdk는 alpine이 아닌 debian으로 설정. platform arm/v7 적용
# https://hub.docker.com/r/adoptopenjdk/openjdk11
# docker build 시 docker buildx build --platform linux/arm/v7 -t [registry] --push .
# docker local 에 표시 할 경우 --output type=docker 추가
FROM adoptopenjdk/openjdk11:armv7l-debian-jdk-11.0.11_9
# FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine

ARG JAR=alltestspring-0.0.1-SNAPSHOT.jar
ARG PORT=8080

ENV TARGET_JAR=/alltest/$JAR
# ENV spring.profiles.active=local

COPY ./target/$JAR /alltest/$JAR

EXPOSE $PORT

ENTRYPOINT java -jar $TARGET_JAR