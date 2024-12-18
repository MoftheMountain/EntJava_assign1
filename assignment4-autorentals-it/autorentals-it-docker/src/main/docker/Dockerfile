#https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html
# Perform the extraction in a separate builder container
FROM eclipse-temurin:17-jre AS builder
WORKDIR /builder
# Identify bootexec with application
ARG JAR_FILE=target/*-bootexec.jar
# Copy bootexec to working directory in builder and generalize name to application.jar
COPY ${JAR_FILE} application.jar
# Extract layers into /builder/extracted
RUN java -Djarmode=tools -jar application.jar extract --layers --launcher --destination extracted

# This is the runtime container
FROM eclipse-temurin:17-jre
WORKDIR /application
# Copy layers into the runtime container - each COPY is a Docker layer
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./
ENV PORT=8080
#https://github.com/spring-projects/spring-boot/issues/37667
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
CMD ["--server.port=${PORT}"]