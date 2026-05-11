# Phase 6: Báo cáo Kiểm tra & Đánh giá (Review Report)

Tài liệu này xác nhận rằng thiết kế Cơ sở dữ liệu của dự án TKart đã hoàn toàn tuân thủ các yêu cầu nghiệp vụ (SRS) và tối ưu hóa cho hiệu suất cao trên MongoDB.

---

## 1. Checklist Toàn vẹn Dữ liệu (Data Integrity)

| ID | Tiêu chí kiểm tra | Trạng thái | Ghi chú kỹ thuật |
| :--- | :--- | :--- | :--- |
| **R01** | Email User là duy nhất | ✅ Đạt | `@Indexed(unique = true)` trong `User.java` |
| **R02** | 1 User - 1 Cart/Wishlist/Wallet | ✅ Đạt | Ràng buộc Unique Index trên trường `userId` của các bảng tương ứng. |
| **R03** | 1 User chỉ mở tối đa 1 Seller Profile | ✅ Đạt | `Seller` entity có `@Indexed(unique = true)` cho `userId`. |
| **R04** | Cấu trúc Danh mục 3 cấp (N-cấp) | ✅ Đạt | Triển khai qua `parentId` và đã Seed thử nghiệm thành công 3 cấp. |
| **R05** | Split Order (Tách đơn hàng) | ✅ Đạt | Một `PaymentOrder` chứa danh sách `orderIds`, cho phép thanh toán gộp cho nhiều shop. |
| **R06** | Snapshot Dữ liệu (Bảo toàn giá) | ✅ Đạt | `OrderItem` chứa các trường `price`, `title` riêng biệt, không bị thay đổi khi Product gốc cập nhật. |
| **R07** | Giới hạn Đánh giá (Review) | ✅ Đạt | Compound Unique Index `{userId, productId}` ngăn chặn việc spam review. |
| **R08** | Phòng Chat duy nhất giữa 2 người | ✅ Đạt | Compound Unique Index `{customerId, sellerId}` đảm bảo tính hội thoại tập trung. |
| **R09** | Tự động dọn dẹp OTP hết hạn | ✅ Đạt | TTL Index trên trường `expiresAt` của `OtpToken`. |
| **R10** | Nhật ký kiểm toán (Audit Log) | ✅ Đạt | Thực thể `AuditLog` đã sẵn sàng, không có API cho phép sửa/xóa bản ghi này. |

---

## 2. Đánh giá Hiệu suất Truy vấn (Expected Performance)

Dựa trên hệ thống Index đã thiết lập ở Phase 4, các truy vấn trọng điểm đạt kỳ vọng:

- **Auth Query**: Tìm User theo Email -> **O(1)** nhờ Unique Index.
- **Category Query**: Lấy cây danh mục con -> **O(log N)** nhờ Index trên `parentId`.
- **Product Listing**: Lọc theo Seller/Category + Giá -> Tối ưu nhờ các Single & Compound Indexes.
- **Chat History**: Lấy tin nhắn phân trang theo thời gian giảm dần -> Rất nhanh nhờ `{chatRoomId: 1, createdAt: -1}`.

---

## 3. Đối chiếu với 29 Use Cases (SRS Mapping)

Hệ thống Schema hiện tại bao phủ 100% các Use Case:
- **UC01 - UC10 (Core & Auth)**: Quản lý bởi `User`, `Address`, `OtpToken`.
- **UC11 - UC20 (Catalog & Order)**: Quản lý bởi `Category`, `Product`, `Order`, `PaymentOrder`.
- **UC21 - UC25 (Marketing & Finance)**: Quản lý bởi `Coupon`, `Deal`, `CoinWallet`, `PlatformConfig`.
- **UC26 - UC29 (System & Support)**: Quản lý bởi `AuditLog`, `ChatRoom`, `ReturnRequest`.

---

## 4. Kết luận Final Review

Thiết kế Database đã hoàn thành và đạt tiêu chuẩn để đưa vào vận hành. 
- **Ưu điểm**: Linh hoạt (NoSQL), tránh được các phép Join phức tạp nhờ kỹ thuật Embedding (nhúng dữ liệu), đảm bảo tính toàn vẹn bằng các ràng buộc Index cấp Database.
- **Hướng phát triển**: Khi dữ liệu lớn lên (Scale), có thể triển khai thêm Sharding dựa trên `userId` hoặc `sellerId`.

> **Người thực hiện**: Nguyễn Thành Tin 
> **Ngày hoàn tất**: 10/05/2026
