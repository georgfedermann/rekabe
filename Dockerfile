FROM openjdk:11.0.8-jdk AS buildbox

SHELL ["/bin/bash", "-c"]
ENV PROJECT_BUILD_DIR=/opt/build

RUN mkdir -p "${PROJECT_BUILD_DIR}"
WORKDIR $PROJECT_BUILD_DIR

COPY . $PROJECT_BUILD_DIR/

RUN echo "JAVA_HOME=$JAVA_HOME" && \
    java -version && \
    javac -version && \
    ls -Aflh && \
    apt-get update && apt-get install -y tree && \
    ./gradlew clean build && \
    tree .
RUN pwd

FROM openjdk:11.0.8-jre

SHELL ["/bin/bash", "-c"]
ENV PROJECT_DIR=/opt/rekabe
ENV MAIN_CLASS=org.poormanscastle.rechenkaiser.backend.RechenkaiserApplication
EXPOSE 8080

RUN mkdir -p "${PROJECT_DIR}"
WORKDIR $PROJECT_DIR
COPY --from=buildbox /opt/build/build/libs/*.jar /opt/rekabe/rekabe.jar

CMD java -jar /opt/rekabe/rekabe.jar org.poormanscastle.rechenkaiser.backend.RechenkaiserApplication
