FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
WORKDIR /app
#COPY ./target/skbiryani.jar /app       t s changed
COPY ./target/skbiryani-0.0.1-SNAPSHOT.jar /app/skbiryani.jar
EXPOSE 8002
ENTRYPOINT ["java", "-jar", "skbiryani.jar"]
