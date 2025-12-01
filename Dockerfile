# Maven + JDK görüntüsünü kullan
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Maven bağımlılıkları cachelemek için
COPY pom.xml .
RUN mvn dependency:go-offline

# Test kodlarını kopyala
COPY src ./src

# Testleri çalıştır
CMD ["mvn", "test"]
