# Build 스테이지
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .

# 실행 권한 부여 및 빌드
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

# Run 스테이지
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# 보안 전용 사용자 생성
RUN useradd -m appuser
USER appuser

# jar 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# JVM 메모리 최적화
ENTRYPOINT ["java", "-server", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]