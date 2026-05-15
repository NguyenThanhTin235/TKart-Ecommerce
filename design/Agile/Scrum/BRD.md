# BRD — Business Requirements Document

> Tài liệu này được tách ra từ `design/Agile/Scrum/O.md` (phiên bản 3.0, cập nhật 08/05/2026) để dễ quản lý.
>
> **Lưu ý:** File nguồn `O.md` **được giữ nguyên**.

---

## 1. Tổng quan

### 1.1. Tên hệ thống
**TKart E-commerce Platform** — Sàn thương mại điện tử **đa nhà cung cấp** (Multi-vendor Marketplace).

### 1.2. Mục đích tài liệu (Purpose)
Tài liệu mô tả bức tranh yêu cầu ở góc nhìn **nghiệp vụ**, bao gồm:
- Mục tiêu và lý do cần xây dựng hệ thống.
- Phạm vi giải pháp (in-scope/out-of-scope).
- Thuật ngữ và khái niệm nền tảng.

Đây là cơ sở để nhóm phát triển phân rã backlog và thiết kế kỹ thuật.

### 1.3. Đối tượng sử dụng
| Vai trò | Mục đích sử dụng |
|---|---|
| Product Owner / Giảng viên | Thẩm định và phê duyệt yêu cầu |
| Đội ngũ phát triển (Dev) | Cơ sở để thiết kế kiến trúc và lập trình |
| QA/Tester | Xây dựng test cases và nghiệm thu |
| BA/SA | Đối chiếu thiết kế với yêu cầu |

---

## 2. Phạm vi (Scope)

### 2.1. Mô tả hệ thống
Hệ thống cho phép nhiều **Seller** đăng ký gian hàng và bán hàng trên cùng một nền tảng. **Customer** có thể mua sản phẩm từ nhiều gian hàng và thanh toán gộp trong một giao dịch.

### 2.2. In-scope
| # | Phân hệ | Mô tả |
|---|---|---|
| 1 | Quản lý Tài khoản & Xác thực | OTP, Password/OTP/OAuth2/2FA, RBAC |
| 2 | Sản phẩm & Danh mục | Danh mục 3 cấp, CRUD sản phẩm, biến thể, kiểm duyệt |
| 3 | Giỏ hàng & Wishlist | Quản lý giỏ hàng, danh sách yêu thích |
| 4 | Đặt hàng & Thanh toán | Checkout, Split Order, Voucher Engine, Xu, COD, VnPay/SePay |
| 5 | Vận chuyển & Tracking | API GHTK/Grab, webhook, in vận đơn PDF |
| 6 | Hoàn trả & Khiếu nại | Return Request, Dispute, Auto Refund API |
| 7 | Chatbot AI & Chat Real-time | NLP intent, WebSocket messaging |
| 8 | Quản trị Nền tảng | Homepage config, Coupon/Deal, kiểm duyệt, đối soát |
| 9 | Báo cáo & Thống kê | Dashboard, xuất Excel, Audit Log |

### 2.3. Out-of-scope
- Mobile App (chỉ web responsive)
- Warehouse Management nội bộ
- Affiliate Marketing
- Paid Ads

---

## 3. Thuật ngữ (Glossary)

| Thuật ngữ | Viết tắt | Giải thích |
|---|---|---|
| Software Requirements Specification | SRS | Tài liệu đặc tả yêu cầu phần mềm |
| Use Case | UC | Trường hợp sử dụng |
| Business Rule | BR | Quy tắc nghiệp vụ |
| Non-Functional Requirement | NFR | Yêu cầu phi chức năng |
| Cash on Delivery | COD | Thanh toán khi nhận hàng |
| JSON Web Token | JWT | Chuẩn token xác thực |
| One-Time Password | OTP | Mật khẩu 1 lần gửi qua Email |
| Role-Based Access Control | RBAC | Phân quyền theo vai trò |
| BCrypt | — | Băm mật khẩu một chiều |
| OAuth 2.0 | OAuth2 | Xác thực ủy quyền qua bên thứ 3 |
| Split Order | — | Tách đơn theo Seller khi checkout |
| Platform Fee | — | Phí nền tảng thu từ Seller |
| Webhook | — | Callback tự động từ hệ thống bên ngoài |
| Cloudinary | — | Lưu trữ/QL hình ảnh, video |
| Voucher Engine | — | Tự động áp mã giảm giá tối ưu |
| Dispute | — | Tranh chấp cần admin xử lý |
| Refund | — | Hoàn tiền tự động |
| Audit Log | — | Nhật ký kiểm toán thay đổi dữ liệu |

---

## 4. Bối cảnh & nhu cầu nghiệp vụ (Current state)

### 4.1. Bài toán thị trường
Xu hướng chuyển dịch từ mô hình “một cửa hàng” sang **sàn đa nhà cung cấp**.

### 4.2. Nhu cầu theo nhóm người dùng

#### Người tiêu dùng (Customer)
- Tìm kiếm/lọc/sắp xếp từ nhiều seller.
- Checkout mượt, hỗ trợ COD và thanh toán online.
- Tương tác qua AI chatbot.
- Quy trình trả hàng/hoàn tiền minh bạch.

#### Người bán (Seller)
- Công cụ đăng bán sản phẩm có biến thể, upload ảnh.
- Xử lý đơn và đẩy vận chuyển tự động.
- Dashboard doanh thu và xuất excel để đối soát.

#### Quản trị viên (Admin)
- Kiểm duyệt seller và sản phẩm.
- Quản lý coupon/deal, cấu hình homepage.
- Giải quyết dispute.

---

## 5. Định hướng giải pháp (Proposed solution)
Xây dựng TKart như một nền tảng marketplace end-to-end, tự động hóa giao dịch và vận hành qua tích hợp thanh toán/vận chuyển.

> Chi tiết yêu cầu chức năng, business rules, NFR, ràng buộc và quy trình tác nghiệp được mô tả trong `design/Agile/Scrum/FRD.md`.
