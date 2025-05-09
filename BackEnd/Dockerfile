# ===========================
# Build Stage
# ===========================
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Kopíruj vše kromě souborů uvedených v .dockerignore
COPY . .

COPY files/arts /app/files/arts

# Nastav práva k `mvnw`
RUN chmod +x mvnw

# Stáhni závislosti a postav aplikaci
RUN ./mvnw clean package -DskipTests

# ===========================
# Runtime Stage
# ===========================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Kopíruj JAR z build fáze
COPY --from=build /app/target/*.jar app.jar

COPY --from=build /app/files/arts /app/files/arts

# Nastav variabilní port pro nasazení
ENV PORT=8080

# Spusť aplikaci
ENTRYPOINT ["java", "-jar", "app.jar"]