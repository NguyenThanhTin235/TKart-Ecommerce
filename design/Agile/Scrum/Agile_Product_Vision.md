# PRODUCT VISION & EPIC DOCUMENT (Agile Lean BRD)

## Tên dự án: TKart E-commerce Platform
**Tác giả:** Trác Ngọc Đăng Khoa
**Ngày cập nhật:** 08/05/2026
**Phiên bản:** 1.0 (Agile Format)

---

## 1. PRODUCT VISION & GOALS (Tầm nhìn & Mục tiêu)

### 1.1 Product Vision (Tầm nhìn sản phẩm)
Xây dựng **TKart E-commerce Platform** trở thành một sàn thương mại điện tử đa nhà cung cấp (Multi-vendor Marketplace) toàn diện. Nền tảng kết nối trực tiếp Người bán (Seller) và Khách hàng (Customer) thông qua trải nghiệm mua sắm mượt mà, quản lý đơn hàng tự động hóa và các tính năng tương tác thông minh (AI Chatbot, Real-time Chat), đồng thời cung cấp công cụ quản trị và đối soát tài chính minh bạch cho Chủ sàn (Admin).

### 1.2 Mục tiêu & Chỉ số thành công (Success Metrics / OKRs)
Để đánh giá sự thành công của sản phẩm, các chỉ số sau cần được theo dõi:
- **Tăng trưởng người dùng:** Đạt 1000 Khách hàng và 100 Gian hàng đăng ký mới trong quý đầu tiên (Q1).
- **Trải nghiệm mua sắm:** Thời gian tải trang (Page Load Time) luôn duy trì dưới 2 giây.
- **Tỷ lệ chuyển đổi (Conversion Rate):** Tăng tỷ lệ khách hàng hoàn tất thanh toán giỏ hàng đa nhà cung cấp lên mức 65% thông qua Split Order và Voucher Engine.
- **Tự động hóa vận hành:** Giảm 80% thời gian xử lý khiếu nại nhờ quy trình Dispute Resolution và hoàn tiền tự động (Auto Refund API).

### 1.3 Phạm vi hệ thống (Scope)
- **In-scope (Trong phạm vi):** 
  - Hệ thống Web đa nền tảng (Responsive).
  - Phân hệ chức năng cốt lõi: Quản lý tài khoản (RBAC), Sản phẩm & Danh mục, Giỏ hàng đa nhà cung cấp.
  - Phân hệ nâng cao: Đặt hàng gộp & Tách đơn (Split Order), Thanh toán trực tuyến, Tích hợp Vận chuyển (GHTK/Grab), Chatbot AI, Quản lý khuyến mãi, Đối soát dòng tiền.
- **Out-of-scope (Ngoài phạm vi ở giai đoạn này):**
  - Ứng dụng di động Native (Mobile App iOS/Android).
  - Hệ thống kho vận nội bộ (Warehouse Management).
  - Tiếp thị liên kết (Affiliate Marketing) & Quảng cáo trả phí (Paid Ads).

---

## 2. USER PERSONAS (Chân dung người dùng)

### 2.1 Khách hàng (Customer)
- **Đặc điểm:** Người tiêu dùng trực tuyến mong muốn tìm kiếm sản phẩm dễ dàng, so sánh giá cả và nhận tư vấn nhanh chóng.
- **Nỗi đau (Pain-points):** Phải đặt nhiều đơn hàng lẻ tẻ khi mua từ nhiều shop; khó khăn khi theo dõi tình trạng giao hàng; trải nghiệm hoàn trả hàng phức tạp.
- **Kỳ vọng (Goals):** Thanh toán gộp 1 lần cho nhiều shop, tra cứu đơn hàng trực quan (Order Stepper), được hỗ trợ ngay lập tức (Chatbot AI / Chat trực tiếp).

### 2.2 Người bán (Seller)
- **Đặc điểm:** Các cá nhân hoặc doanh nghiệp nhỏ lẻ muốn mở rộng kênh bán hàng trực tuyến.
- **Nỗi đau (Pain-points):** Mất thời gian ghi chép mã vận đơn thủ công; khó đối soát dòng tiền và chiết khấu; thiếu công cụ giữ chân khách hàng.
- **Kỳ vọng (Goals):** Quản lý tồn kho tự động; đẩy đơn vận chuyển qua 1 nút bấm (API GHTK/Grab); bảng điều khiển thống kê tài chính trực quan; dễ dàng quản lý biến thể sản phẩm.

### 2.3 Quản trị viên (Admin)
- **Đặc điểm:** Ban quản lý vận hành sàn thương mại điện tử.
- **Nỗi đau (Pain-points):** Quá tải khi duyệt sản phẩm thủ công; rủi ro thất thoát trong việc đối soát doanh thu giữa các ví; giải quyết tranh chấp (Dispute) khó khăn khi không có bằng chứng rõ ràng.
- **Kỳ vọng (Goals):** Công cụ quản trị tập trung (RBAC); hệ thống đối soát tự động trừ phí nền tảng; dễ dàng tùy chỉnh banner trang chủ và phát hành mã giảm giá toàn sàn.

---

## 3. HIGH-LEVEL ROADMAP (Lộ trình phát hành)

Sản phẩm sẽ được chia thành **5 Giai đoạn phát hành (Releases)** theo nguyên tắc MVP, ưu tiên xây dựng nền tảng cốt lõi trước khi mở rộng các tính năng nâng cao và tích hợp bên thứ ba.

| Giai đoạn | Mục tiêu cốt lõi | Các Epics / Tính năng chính |
|---|---|---|
| **Release 1: MVP (Cốt lõi TMĐT)** | Xây dựng luồng End-to-End cơ bản. Khách tìm sản phẩm, đặt hàng COD. Người bán đăng sản phẩm. Admin kiểm duyệt. | **Epic 1**: Trang chủ, Tìm kiếm/Lọc, Chi tiết SP.<br>**Epic 2**: Đăng nhập/ký Email, Cập nhật Hồ sơ.<br>**Epic 3**: Giỏ hàng, Checkout COD, Theo dõi đơn tĩnh.<br>**Epic 4**: Đăng SP, Quản lý tồn kho.<br>**Epic 6**: Duyệt Seller/Sản phẩm. |
| **Release 2: Multi-vendor Checkout + Khuyến mãi cơ bản** | Hoàn thiện checkout theo hướng “sàn thật”: wishlist, voucher/coin mức cơ bản, và tách đơn theo seller. | **Epic 3**: Wishlist, Voucher Engine, Ví Xu, Split Order. |
| **Release 3: Thanh toán online + Webhook + Seller fulfillment** | Tích hợp thanh toán online và đóng vòng đời giao dịch: success/fail → cập nhật đơn; sau đó nối sang seller fulfillment + vận chuyển thực tế. | **Epic 3**: VNPay (hoặc cổng online) + webhook success/fail.<br>**Epic 4**: Seller xác nhận/từ chối đơn, đẩy đơn vận chuyển, in vận đơn, cập nhật tracking. |
| **Release 4: Real-time & After-sales** | Tăng trải nghiệm (AI/real-time) và hoàn thiện hậu mãi: review → return → dispute. | **Epic 1**: Chatbot AI, Chat 1-1 Real-time (WebSockets).<br>**Epic 5**: Review, Return request, Dispute (Seller + Admin). |
| **Release 5: Admin Ops & Growth** | Hoàn thiện công cụ vận hành sàn, marketing, báo cáo và gia cố bảo mật để nghiệm thu. | **Epic 6**: Homepage config, Coupon/Deal, Quản lý User, Phí sàn, Audit Log.<br>**Epic 4**: Dashboard doanh thu, Transactions, Export Excel.<br>**Epic 2**: OAuth/OTP login/Admin 2FA (nếu chưa làm). |

---

## 4. EPICS BREAKDOWN (Danh sách Epics)

Đây là các nhóm tính năng lớn (Epics) sẽ được phân rã thành các User Story chi tiết trong Product Backlog (`UserStory.md`).

1. **Epic 1: Quản lý Tài khoản & Xác thực (Identity & Access)**
   - Xác thực đa phương thức (OTP, OAuth2, 2FA).
   - Quản lý hồ sơ và sổ địa chỉ.
2. **Epic 2: Trải nghiệm & Khám phá (Discovery & Interaction)**
   - Tìm kiếm và lọc nâng cao, đánh giá sản phẩm.
   - Chatbot AI phân tích ngữ nghĩa, Chat trực tuyến Real-time.
3. **Epic 3: Mua sắm & Thanh toán (Cart & Checkout)**
   - Quản lý giỏ hàng, Wishlist.
   - Cơ chế Split Order (Tách đơn đa nhà cung cấp).
   - Tích hợp cổng thanh toán (VnPay, Momo, SePay), Voucher Engine & Tiêu Xu.
4. **Epic 4: Quản lý Bán hàng & Vận chuyển (Seller Center)**
   - Quản lý sản phẩm, biến thể (Màu sắc/Kích cỡ), quản lý kho.
   - Tự động hóa giao hàng (Push đơn qua API GHTK/Grab, in vận đơn).
   - Thống kê doanh thu, đối soát và xuất file Excel.
5. **Epic 5: Hậu mãi & Khiếu nại (Returns & Disputes)**
   - Quy trình yêu cầu trả hàng, tải minh chứng ảnh/video (Cloudinary).
   - Trọng tài phân xử tranh chấp (Admin) và API Hoàn tiền tự động.
6. **Epic 6: Quản trị Nền tảng (Admin Operations)**
   - Kiểm duyệt Seller và Sản phẩm mới.
   - Quản trị cấu hình Trang chủ (Homepage Builder), mã giảm giá (Coupons/Deals).
   - Cấu hình phí nền tảng, hệ thống Xu và xem Nhật ký kiểm toán (Audit Log).

---

## 5. NON-FUNCTIONAL REQUIREMENTS & CONSTRAINTS (Yêu cầu phi chức năng & Ràng buộc)

### 5.1 Yêu cầu phi chức năng (NFRs)
- **Hiệu suất (Performance):** Thời gian tải trang hiển thị (Front-end) dưới 2s. Xử lý đồng thời khối lượng giao dịch lớn trong giờ cao điểm Flash Sale mà không crash. Quá trình lưu log (Audit Log) phải chạy ngầm (Bất đồng bộ).
- **Bảo mật (Security):** Mật khẩu mã hóa BCrypt, phiên làm việc qua JWT. Toàn bộ API phải được bảo vệ bởi Spring Security theo Role. Thông tin thẻ tín dụng KHÔNG được lưu trên CSDL hệ thống.
- **Tính bảo trì & Mở rộng (Maintainability & Scalability):** Mã nguồn Back-end chia tách theo miền nghiệp vụ rõ ràng (Modular), chuẩn RESTful API. Front-end tổ chức theo Component (React).
- **Tính tiện dụng (Usability):** Giao diện Responsive 100% (Tailwind CSS, MUI), hiển thị tốt trên Desktop, Tablet, Mobile.

### 5.2 Ràng buộc kỹ thuật (Constraints)
- **Stack công nghệ:** Client-Server 3-tier. Front-end: React, TypeScript, Redux Toolkit. Back-end: Java Spring Boot. Database: MySQL.
- **Tích hợp bên thứ ba:** Cloudinary (Lưu trữ ảnh/video), Stripe/VnPay/SePay (Thanh toán), GHTK/Grab (Vận chuyển).
- **Quy tắc Nghiệp vụ cốt lõi (Business Rules Core):** 
  - KHÔNG áp dụng hồi tố kế toán (Cập nhật phí sàn không làm thay đổi các giao dịch cũ).
  - Đơn vị vận chuyển (Mã vận đơn) phải được sinh từ API tự động, Seller tuyệt đối không được nhập tay để tránh gian lận.

---

## 6. DEFINITION OF READY (DoR) & DEFINITION OF DONE (DoD)

### 6.1 Definition of Ready (Tiêu chuẩn Sẵn sàng Code)
Một User Story chỉ được đưa vào Sprint Backlog để team bắt đầu code khi:
- Đã được định nghĩa rõ ràng về nghiệp vụ, thuộc một Epic cụ thể.
- Có đủ các Acceptance Criteria (Given-When-Then) đo lường được.
- Có tài liệu thiết kế giao diện (UI/UX Mockup hoặc Wireframe) đi kèm (nếu cần).
- Các ràng buộc về API, Database hoặc Third-party (Cổng thanh toán, ĐVVC) đã được làm rõ.

### 6.2 Definition of Done (Tiêu chuẩn Hoàn thành)
Một tính năng được coi là hoàn thành (Done) để release cho người dùng khi:
- Code đã vượt qua các Acceptance Criteria quy định trong `UserStory.md`.
- Giao diện (UI) đã được triển khai đúng thiết kế, đảm bảo Responsive trên Mobile/Desktop.
- Code không có cảnh báo lỗi nghiêm trọng (Linter/SonarQube) và đã được merge vào nhánh chính.
- Các tính năng thanh toán, dòng tiền đã được test tích hợp thành công trên môi trường Sandbox/Test của bên thứ ba.
