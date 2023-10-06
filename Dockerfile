# Sử dụng hình ảnh cơ sở chứa Java 11
FROM openjdk:11-jre-slim

# Tạo thư mục /app trong hình ảnh và sử dụng nó làm thư mục làm việc
WORKDIR /app

# Thiết lập biến môi trường
ENV JAVA_OPTS=""

# Mở cổng 8080
EXPOSE 8080

# Khởi chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]