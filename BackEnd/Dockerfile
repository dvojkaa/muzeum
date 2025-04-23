#FROM eclipse-temurin:21-jdk
#
#WORKDIR /app
#
#COPY .mvn .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#
#COPY . .
#
#RUN ./mvnw clean package -DskipTests
#
#CMD ["java", "-jar", "target/semestralka-0.0.1-SNAPSHOT.jar"]


FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Kopíruj wrapper a pom.xml
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Stáhni závislosti
RUN ./mvnw dependency:go-offline

# Zkopíruj zbytek zdrojového kódu
COPY . .

# Postav spustitelný JAR (s main class v manifestu)
RUN ./mvnw clean package -DskipTests

# ----------------------------------

FROM eclipse-temurin:21-jdk

WORKDIR /app

# Zkopíruj JAR z předchozího buildu
COPY --from=build /app/target/semestralka-0.0.1-SNAPSHOT.jar app.jar

# Spusť aplikaci
ENTRYPOINT ["java", "-jar", "app.jar"]
