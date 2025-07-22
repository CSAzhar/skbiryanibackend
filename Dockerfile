FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
WORKDIR /app
COPY ./target/skbiryani.jar /app
EXPOSE 8002
ENTRYPOINT ["java", "-jar", "skbiryani.jar"]
