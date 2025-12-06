# Используем готовый образ с JDK 17 (или нужной версии)
FROM eclipse-temurin:21-jdk-alpine

# Рабочая директория
WORKDIR /app

# Копируем зависимости и собираем приложение
COPY target/voeemsog-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт приложения
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java","-jar","app.jar"]
