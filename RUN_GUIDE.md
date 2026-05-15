# Hướng dẫn Build và Run dự án TKart-Ecommerce

Tài liệu này tóm tắt các câu lệnh cần thiết để khởi chạy toàn bộ hệ thống.



## 1. Khởi chạy Hạ tầng (Docker)
Yêu cầu: Đã cài đặt Docker Desktop.
Stop-Process -Name "java" -Force
```powershell
# Chạy MongoDB và Redis
docker-compose up -d

# Kiểm tra trạng thái các container
docker ps
```

## 2. Khởi chạy Backend (Spring Boot)
Vị trí: `source/backend`

```powershell
# Di chuyển vào thư mục backend
cd source/backend

# Build dự án (Bỏ qua tests)
mvn clean package -DskipTests

# Chạy ứng dụng với giới hạn RAM (dành cho máy cấu hình thấp)
java -Xmx256m -jar target/ecommerce-0.0.1-SNAPSHOT.jar
```

### Lưu ý sửa lỗi Database (nếu có)
Nếu Backend báo lỗi xung đột Index (IndexKeySpecsConflict), chạy lệnh sau:
```powershell
docker exec tkart-mongodb mongosh tkartdb -u admin -p password --authenticationDatabase admin --eval "db.chat_messages.dropIndex('chatroom_created_idx')"
```

## 3. Khởi chạy Frontend (React + Vite)
Vị trí: `source/frontend`

```powershell
# Di chuyển vào thư mục frontend
cd source/frontend

# Cài đặt dependencies (chỉ cần làm lần đầu)
npm install

# Chạy ở chế độ Development
npm run dev
```

## 4. Thông tin cổng truy cập
- **Frontend**: [http://localhost:3000](http://localhost:3000)
- **Backend API**: [http://localhost:8080](http://localhost:8080)
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **MongoDB**: `localhost:27018`
- **Redis**: `localhost:6379`
