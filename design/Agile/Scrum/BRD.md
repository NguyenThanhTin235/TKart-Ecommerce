# BRD — Business Requirements Document

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

## 4. As-Is / Current state (Hiện trạng)

### 4.1. Bài toán thị trường
Trong bối cảnh thương mại điện tử phát triển mạnh mẽ, việc xây dựng một nền tảng bán hàng không chỉ dừng lại ở mô hình “một cửa hàng” mà đang chuyển dịch sang **mô hình sàn giao dịch thương mại điện tử đa nhà cung cấp** (multi-vendor marketplace). Mô hình này giúp tập trung nguồn cung, tăng lựa chọn cho người mua và tạo ra hệ sinh thái vận hành (thanh toán, vận chuyển, hậu mãi) ở quy mô lớn.

### 4.2. Hiện trạng (As-Is) — tóm tắt
> Mục đích: mô tả bối cảnh hiện tại và nhu cầu chưa được đáp ứng để làm rõ “vì sao cần xây TKart”.

Hiện tại, khi chuyển từ mô hình bán hàng đơn lẻ sang mô hình “sàn”, các nhóm người dùng thường gặp các nhu cầu/chưa đáp ứng sau:

- **Đối với người tiêu dùng (Customer)**: cần một nền tảng tích hợp nơi họ có thể **tìm kiếm/lọc/sắp xếp** sản phẩm từ **nhiều nhà bán**, quản lý giỏ hàng mượt mà, **thanh toán online an toàn** và có kênh hỗ trợ/tư vấn nhanh (ví dụ **Chatbot AI**).
- **Đối với người bán (Seller)**: thiếu một công cụ tập trung để **đăng bán & quản lý sản phẩm**, theo dõi và xử lý đơn hàng, cập nhật kho, và theo dõi **doanh thu/thống kê** một cách trực quan.
- **Đối với quản trị viên (Admin)**: cần một hệ thống toàn diện để **kiểm duyệt người bán & sản phẩm**, quản lý mã giảm giá/chương trình khuyến mãi, và **tùy biến trang chủ** để phục vụ vận hành và tăng trưởng.

Từ góc nhìn nền tảng, các luồng vận hành cốt lõi (checkout đa seller, tách đơn, đối soát, vận chuyển qua webhook, hoàn tiền tự động, xử lý tranh chấp) cần được **chuẩn hoá** thành quy trình end-to-end với phân quyền & kiểm soát rủi ro rõ ràng.

### 4.3. Nhu cầu theo nhóm người dùng

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

## 5. To-Be / Proposed solution (Định hướng giải pháp)

### 5.1. To-Be (mục tiêu mong muốn)
Xây dựng TKart như một nền tảng marketplace end-to-end cho mô hình đa nhà cung cấp, với mục tiêu:
- **Tự động hóa** quy trình giao dịch (đặt hàng → thanh toán → vận chuyển → hậu mãi).
- **Quản lý tài chính minh bạch** thông qua tích hợp cổng thanh toán và quy tắc đối soát.
- **Nâng cao trải nghiệm người dùng** (mua sắm mượt, theo dõi đơn, hỗ trợ nhanh; có thể mở rộng thêm real-time/AI sau khi lõi giao dịch ổn định).

### 5.2. Phạm vi To-Be (được bàn giao theo Agile/Scrum)
- Yêu cầu được phân rã theo **User Story** và triển khai theo **vertical slice** (mỗi release đều demo được).
- Ưu tiên giảm rủi ro sớm: Auth/RBAC, mô hình Order–Payment, split order multi-vendor; sau đó mới tới payment online/webhook và shipping.


