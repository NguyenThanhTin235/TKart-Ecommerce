# Kế hoạch Phát hành Release 5: Admin Operations & Growth

## 1. Mục tiêu & Phạm vi (Objectives & Scope)

**Release 5 (Admin Operations & Growth)** hoàn thiện tầng quản trị vận hành chiến lược (Admin Ops), cung cấp công cụ tăng trưởng doanh số (Growth/Marketing), nâng cấp hệ thống báo cáo chuyên sâu cho Người bán (Seller Reporting), và củng cố tối đa lớp bảo mật hệ thống (Advanced Security).

### Các mục tiêu chính:
*   **Quản trị Vận hành Sàn (Admin Operations):** Cấu hình động giao diện Trang chủ mà không cần can thiệp mã nguồn, thiết lập tham số tài chính (Phí sàn, tỉ giá Xu), quản lý người dùng và xử lý nghiêm ngặt các gian hàng vi phạm.
*   **Công cụ Marketing Toàn sàn (Coupons & Deals):** Phát hành và quản lý các mã giảm giá, chương trình Flash Sale áp dụng chung cho toàn nền tảng.
*   **Báo cáo & Phân tích Người bán (Seller Analytics & Storefront):** Cung cấp biểu đồ doanh thu trực quan, tra cứu dòng tiền đối soát chi tiết, xuất báo cáo Excel chuẩn hóa, và tùy biến giao diện Cửa hàng (Storefront).
*   **Bảo mật & Kiểm toán Nâng cao (Advanced Security & Audit):** Bảng theo dõi nhật ký hệ thống (Audit Log) bất biến (Read-only), tích hợp đăng nhập không mật khẩu (OTP Login), xác thực mạng xã hội (OAuth2 Google/Facebook) và bảo mật 2 lớp (2FA) cho Admin.

---

## 2. Danh sách User Story & Use Case

| ID User Story | ID Use Case | Tên Chức năng / Mô tả | EPIC liên quan | Độ ưu tiên |
| :--- | :--- | :--- | :--- | :--- |
| `US-21.1` | UC21 | Cấu hình động cấu trúc & giao diện Trang chủ (Homepage Config) | EPIC 6 (Admin Ops) | Should Have |
| `US-22.1` | UC22 | Admin tạo & quản lý Mã giảm giá (Coupons) toàn sàn | EPIC 6 (Admin Ops) | Must Have |
| `US-22.2` | UC22 | Admin cấu hình các chương trình Khuyến mãi (Deals / Flash Sale) | EPIC 6 (Admin Ops) | Must Have |
| `US-24.1` | UC24 | Quản lý danh sách & hồ sơ Khách hàng | EPIC 6 (Admin Ops) | Should Have |
| `US-24.2` | UC24 | Khóa (Ban) tài khoản Khách hàng vi phạm chính sách | EPIC 6 (Admin Ops) | Should Have |
| `US-25.1` | UC25 | Cấu hình tham số Phí nền tảng (Platform Fee) & Tỉ giá Ví Xu | EPIC 6 (Admin Ops) | Must Have |
| `US-26.1` | UC26 | Tra cứu & Bộ lọc Nhật ký Hệ thống (Audit Log Read-only) | EPIC 6 (Admin Ops) | Should Have |
| `US-19.2` | UC19 | Xử lý kỷ luật Người bán (Đình chỉ SUSPEND / Cấm BANNED) | EPIC 6 (Admin Ops) | Must Have |
| `US-14.1` | UC14 | Cấu hình thông tin doanh nghiệp & tài khoản ngân hàng của Seller | EPIC 4 (Seller & Admin) | Should Have |
| `US-14.2` | UC14 | Tải lên & Quản lý Media (Banner, Logo) cho Cửa hàng | EPIC 4 (Seller & Admin) | Should Have |
| `US-14.3` | UC14 | Xem trước giao diện Mặt tiền Cửa hàng (Preview Storefront) | EPIC 4 (Seller & Admin) | Should Have |
| `US-18.1` | UC18 | Bảng điều khiển Doanh thu & Biểu đồ tăng trưởng của Seller | EPIC 4 (Seller & Admin) | Should Have |
| `US-18.2` | UC18 | Tra cứu chi tiết lịch sử dòng tiền đối soát (Transactions) | EPIC 4 (Seller & Admin) | Should Have |
| `US-18.3` | UC18 | Kết xuất báo cáo doanh thu ra file Excel (.xlsx) | EPIC 4 (Seller & Admin) | Should Have |
| `US-27.2` | UC27 | Đăng nhập không dùng mật khẩu (Passwordless OTP Login) | EPIC 2 (Identity & Access) | Should Have |
| `US-27.3` | UC27 | Đăng nhập qua mạng xã hội Google (OAuth2) | EPIC 2 (Identity & Access) | Should Have |
| `US-27.4` | UC27 | Đăng nhập qua mạng xã hội Facebook (OAuth2) | EPIC 2 (Identity & Access) | Should Have |
| `US-27.5` | UC27 | Đăng nhập bảo mật 2 lớp (2FA OTP) dành riêng cho Admin | EPIC 2 (Identity & Access) | Must Have |

---

## 3. Phân rã Công việc Chi tiết (Work Breakdown Structure - WBS)

### 3.1. Phân hệ Cơ sở dữ liệu (Database & Schema)
*   **DB-5.1:** Khởi tạo Lược đồ Collection `homepage_configs` (lưu cấu hình cây JSON gồm danh sách banner, danh mục nổi bật, flash sale).
*   **DB-5.2:** Khởi tạo Lược đồ Collection `system_configs` (lưu các hằng số vận hành như `platformFeePercentage`, `coinEarnRate`, `coinRedeemRate`).
*   **DB-5.3:** Khởi tạo Lược đồ Collection `audit_logs` (lưu thông tin kiểm toán `timestamp`, `actorId`, `actorRole`, `actionType`, `targetEntity`, `ipAddress`).
*   **DB-5.4:** Khởi tạo Lược đồ Collection `seller_storefronts` (lưu cấu hình `bannerUrl`, `logoUrl`, `description`, `bankAccountDetails`).
*   **DB-5.5:** Cập nhật Collection `users` (bổ sung các trường `authProvider` với giá trị `LOCAL`, `GOOGLE`, `FACEBOOK`, trường `twoFactorEnabled`).

### 3.2. Phân hệ Backend (Spring Boot APIs, OAuth2, Excel & Audit)
*   **BE-5.1:** Triển khai `HomepageConfigController` & `ConfigService` (API cho phép Admin lưu cấu trúc JSON Trang chủ và public endpoint cho Frontend render tự động).
*   **BE-5.2:** Triển khai `AdminMarketingController` (CRUD Coupon toàn sàn và thiết lập Flash Sale, tự động đồng bộ với Voucher Engine ở Release 2).
*   **BE-5.3:** Triển khai `SystemConfigController` (API thiết lập Phí sàn và Tỉ giá xu, áp dụng công thức tính toán `CT_AD1` vào logic đối soát đơn hàng).
*   **BE-5.4:** Triển khai `AuditLogAspect` & `AuditLogController` (Sử dụng Spring AOP để tự động bắt các thao tác nhạy cảm, ghi log bất đồng bộ bằng `@Async` vào MongoDB, cung cấp API tra cứu cho Admin).
*   **BE-5.5:** Triển khai `SellerStorefrontController` (API quản lý hồ sơ doanh nghiệp và mặt tiền gian hàng của Seller).
*   **BE-5.6:** Triển khai `SellerAnalyticsController` & `ExcelReportService` (Tổng hợp doanh thu theo ngày/tháng, sử dụng thư viện Apache POI để kết xuất dữ liệu dòng tiền thành file `.xlsx` chuẩn hóa).
*   **BE-5.7:** Nâng cấp `AuthController` & `OAuth2SecurityConfig`:
    *   Tích hợp Spring Security OAuth2 Client kết nối Google/Facebook.
    *   Triển khai endpoint Đăng nhập OTP không mật khẩu.
    *   Bổ sung luồng kiểm tra 2FA: Khi Admin đăng nhập đúng mật khẩu, hệ thống sinh mã OTP gửi qua Email và yêu cầu xác thực bước 2 trước khi cấp JWT.

### 3.3. Phân hệ Frontend (React, TypeScript & Redux Toolkit)
*   **FE-5.1:** Bổ sung Redux Slices: `adminConfigSlice`, `sellerAnalyticsSlice`, `auditSlice`.
*   **FE-5.2:** Xây dựng Giao diện Quản trị Nâng cao (`AdminDashboard`):
    *   Tab `Cấu hình Trang chủ` (Giao diện WYSIWYG / Form nhập liệu URL banner và chọn danh mục hiển thị).
    *   Tab `Coupons & Deals` (Biểu mẫu tạo mã giảm giá toàn sàn và cấu hình Flash Sale).
    *   Tab `Quản lý Khách hàng` (Lưới danh sách user kèm bộ lọc, tích hợp nút "Khóa tài khoản").
    *   Tab `Cài đặt Hệ thống` (Biểu mẫu cấu hình Phí sàn % và Tỉ giá Xu).
    *   Tab `Nhật ký Hệ thống` (Bảng Audit Log chỉ đọc kèm bộ lọc thời gian và module).
*   **FE-5.3:** Xây dựng Giao diện Quản trị Người bán Nâng cao (`SellerDashboard`):
    *   Tab `Hồ sơ & Mặt tiền` (Upload Banner/Logo, nhập thông tin tài khoản ngân hàng, tích hợp nút "Xem trước Cửa hàng").
    *   Tab `Phân tích Doanh thu` (Hiển thị các Thẻ tổng quan, Biểu đồ doanh thu ECharts/Chart.js, Bảng đối soát dòng tiền và nút "Xuất báo cáo Excel").
*   **FE-5.4:** Nâng cấp Giao diện Đăng nhập (`LoginPage`): Thêm nút "Đăng nhập với Google", "Đăng nhập với Facebook", "Đăng nhập bằng OTP". Xây dựng Modal xác thực 2FA dành cho Admin.

---

## 4. Quy tắc Nghiệp vụ & Ràng buộc (Business Rules & NFRs)

*   **BR21-1 (QĐ_AD3):** Dữ liệu cấu hình Trang chủ phải độc lập, Frontend tự động gọi API và render giao diện dựa trên cấu trúc JSON Admin vừa lưu.
*   **BR25-2 (QĐ_AD12):** Phí nền tảng (`Platform Fee`) là biến cấu hình động. Khi đơn hàng hoàn thành, hệ thống tự động trích % phí này từ doanh thu của Seller để chuyển vào doanh thu sàn.
*   **BR26-1 (Bất biến Audit Log):** Bảng dữ liệu Nhật ký là dữ liệu Tuyệt đối Chỉ đọc (Read-only). Hệ thống không cung cấp chức năng Xóa (Delete) hay Sửa (Update) cho bất kỳ ai, kể cả Root Admin.
*   **NFR18-1 (QĐ_SL7):** File Excel kết xuất phải đảm bảo đúng định dạng bảng tính, không bị lỗi font chữ Unicode (tiếng Việt) để Người bán dễ dàng đưa vào phần mềm kế toán.
*   **NFR26-1:** Thao tác ghi log vào CSDL phải chạy ngầm (Asynchronous) để không làm tăng độ trễ của tiến trình chính.

---

## 5. Tiêu chí Nghiệm thu & Kịch bản Demo (Vertical Slice Demo)

Cuối Release 5, hệ thống phải vượt qua kịch bản kiểm chứng thực tế sau:

1.  **Bước 1 (Admin 2FA & Ops):** Admin truy cập trang đăng nhập, nhập Email/Mật khẩu. Hệ thống hiển thị form yêu cầu mã 2FA. Admin nhập mã OTP từ Email, đăng nhập thành công vào `AdminDashboard`.
2.  **Bước 2 (Cấu hình Homepage & Phí sàn):** Admin vào Tab `Cấu hình Trang chủ`, thay đổi Banner chính và chọn danh mục "Thời trang Mùa hè". Tiếp tục vào `Cài đặt Hệ thống`, chỉnh Phí sàn thành 5%. Nhấp "Lưu".
3.  **Bước 3 (Kiểm chứng Client Homepage):** Khách hàng truy cập `HomePage`, ngay lập tức thấy Banner mới và danh mục "Thời trang Mùa hè" xuất hiện mà không cần f5 server.
4.  **Bước 4 (Khách hàng OAuth2 Login):** Khách hàng nhấp "Đăng nhập với Google", xác thực qua form của Google và được đăng nhập thành công vào hệ thống.
5.  **Bước 5 (Seller Analytics & Export):** Người bán đăng nhập `SellerDashboard`, truy cập `Phân tích Doanh thu`. Người bán xem biểu đồ doanh thu tháng, kiểm tra các khoản phí sàn 5% đã bị trừ chính xác. Nhấp "Xuất báo cáo", hệ thống tải về file `DoanhThu_Thang5.xlsx` với định dạng bảng tính hoàn chỉnh.
6.  **Bước 6 (Kiểm toán Audit Log):** Admin vào Tab `Nhật ký Hệ thống`, kiểm tra thấy đầy đủ các dòng log: "Admin A đã thay đổi Phí sàn thành 5%", "Admin A đã cập nhật cấu hình Trang chủ", đảm bảo tính minh bạch tuyệt đối.
