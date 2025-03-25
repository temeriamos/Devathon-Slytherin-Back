# Usa una imagen con JDK 17 y Maven
FROM maven:latest AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Construye el proyecto (descarga dependencias y genera el .jar)
RUN mvn clean package -DskipTests

# Segunda etapa: solo para ejecutar la app
FROM openjdk:17

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]