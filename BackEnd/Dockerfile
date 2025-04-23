FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/muzeumSys-0.0.1-SNAPSHOT.jar"]
