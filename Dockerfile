# Usa una imagen de Java con Maven
FROM maven:17-eclipse-temurin AS build
WORKDIR /app

# Copia el proyecto y construye
COPY . .
RUN mvn clean package -DskipTests

# Crea la imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]