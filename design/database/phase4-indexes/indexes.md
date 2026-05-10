# Phase 4: Thiết kế Indexes (Tối ưu hóa Truy vấn)

Tài liệu này mô tả chi tiết chiến lược tạo Index (Chỉ mục) cho cơ sở dữ liệu MongoDB của dự án TKart. Toàn bộ các index này đã được triển khai thông qua các Annotation của Spring Data MongoDB (`@Indexed`, `@CompoundIndex`) ở Phase 3.

---

## 1. Single Field Indexes (Chỉ mục đơn)

Sử dụng để tìm kiếm nhanh hoặc đảm bảo tính duy nhất của một trường dữ liệu độc lập.

| Entity (Collection) | Trường | Loại Index | Ý nghĩa nghiệp vụ |
| :--- | :--- | :--- | :--- |
| **User** | `email` | Unique | Đảm bảo không trùng lặp tài khoản email lúc đăng ký. Tốc độ tìm kiếm User lúc đăng nhập O(1). |
| **Category** | `slug` | Unique | Giúp Frontend tìm kiếm/hiển thị danh mục theo URL thân thiện SEO. |
| **Category** | `parentId` | Thường | Tối ưu việc lấy toàn bộ danh mục con của một danh mục cha. |
| **Coupon** | `code` | Unique | Ngăn chặn trùng lặp mã giảm giá, tìm kiếm mã hợp lệ khi checkout nhanh chóng. |
| **Product** | `sellerId` | Thường | Filter danh sách sản phẩm của một gian hàng. |
| **Product** | `categoryL3Id` | Thường | Lọc sản phẩm theo danh mục lá. |
| **Order** | `userId` / `sellerId` | Thường | Cho phép lấy lịch sử đơn hàng của người mua hoặc người bán với hiệu suất cao. |
| **Cart** | `userId` | Unique | Mỗi người dùng chỉ có 1 giỏ hàng active. |
| **Article** | `slug` | Unique | Hỗ trợ tìm bài viết/chính sách theo URL. |

---

## 2. Compound Indexes (Chỉ mục phức hợp)

Kết hợp từ 2 trường trở lên, sử dụng để hỗ trợ các câu truy vấn phức tạp kết hợp nhiều điều kiện lọc và sắp xếp.

| Collection | Định nghĩa Index (MongoDB) | Ý nghĩa & Tối ưu truy vấn |
| :--- | :--- | :--- |
| **Review** | `{ "userId": 1, "productId": 1 }` (Unique) | Ngăn chặn việc spam review: Đảm bảo một người mua chỉ được đánh giá một sản phẩm một lần duy nhất. |
| **ChatRoom** | `{ "customerId": 1, "sellerId": 1 }` (Unique) | Chặn việc tạo nhiều phòng chat: Đảm bảo giữa 1 người mua và 1 người bán chỉ tồn tại 1 luồng chat duy nhất. |
| **ChatMessage** | `{ "chatRoomId": 1, "createdAt": -1 }` | Tối ưu hiển thị tin nhắn chat (phân trang). Truy vấn tin nhắn mới nhất trong 1 phòng. |
| **CouponRedemption** | `{ "couponId": 1, "userId": 1 }` | Tối ưu check "Người dùng này đã dùng mã này bao nhiêu lần?" phục vụ giới hạn `perUserLimit`. |

---

## 3. TTL (Time-To-Live) Indexes

Sử dụng để tự động dọn dẹp các dữ liệu rác, hết hạn, giúp giảm dung lượng RAM/Disk cho database.

| Collection | Trường | Cấu hình | Ý nghĩa nghiệp vụ |
| :--- | :--- | :--- | :--- |
| **OtpToken** | `expiresAt` | `expireAfterSeconds = 0` | Tự động xóa dòng dữ liệu mã OTP ngay sau khi đồng hồ hệ thống vượt qua mốc `expiresAt`. Giúp bảng OTP không bao giờ bị phình to bởi các token rác. |

---

## 4. Phân tích Cân bằng (Read vs Write)

1. **Trade-off ghi (Write Penalty)**: Mỗi Index được tạo sẽ làm chậm quá trình `INSERT`, `UPDATE`, `DELETE` đi một chút vì B-Tree của Index phải được cập nhật. Do đó chúng ta chỉ đánh Index ở các trường FK (khóa ngoại) và các trường hay được tìm kiếm (slug, email, createdAt).
2. **ESRB Model (Embedded-Snapshot-Reference-Bucket)**:
   - Thay vì liên tục join `Order` với `Product`, ta đã nhúng (embed) các thông tin sản phẩm vào `OrderItem`. Điều này giúp thao tác Read đơn hàng không cần Lookup, giảm bớt sự phụ thuộc vào Indexes ở các bảng liên quan.

---

> **Note:** Việc tự động tạo các index khi ứng dụng khởi động được quản lý bởi Spring Boot qua thuộc tính `spring.data.mongodb.auto-index-creation=true`. Nếu trên môi trường Production với dữ liệu lớn (hàng triệu bản ghi), ta nên set thành `false` và dùng lệnh createIndex thủ công hoặc chạy script migration để tránh khóa bảng (blocking).
