# ✅ Use official OpenJDK 21 slim image
FROM openjdk:21

# ✅ Set working directory inside container
WORKDIR /app

# ✅ Copy the JAR into the image
COPY ./target/skbiryani.jar /app

# ✅ Expose the app port
EXPOSE 8002

# ✅ Run the JAR
ENTRYPOINT ["java", "-jar", "skbiryani.jar"]
