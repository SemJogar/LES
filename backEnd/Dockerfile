# --- ESTÁGIO 1: Build (Compilação do Java 21 com Maven) ---
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copia o pom.xml e baixa as dependências primeiro (Cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e gera o .jar
COPY src ./src
RUN mvn package -DskipTests

# --- ESTÁGIO 2: Execução (Apenas o motor Java 21 leve) ---
FROM eclipse-temurin:21-jre-alpine AS runner
WORKDIR /app

# Cria a pasta interna de uploads
RUN mkdir -p /app/uploads

# Copia o .jar compilado lá do estágio 'builder'
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
