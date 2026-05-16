# Kế hoạch Phát hành Release 3: Online Payment & Seller Fulfillment

## 1. Mục tiêu & Phạm vi (Objectives & Scope)

**Release 3 (Online Payment & Seller Fulfillment)** đóng vai trò hoàn thiện vòng đời giao dịch tài chính và vận hành logistics thực tế của nền tảng TKart. Phiên bản này đưa hệ thống vượt ra khỏi phạm vi thanh toán COD truyền thống để tích hợp thanh toán trực tuyến qua cổng điện tử, đồng thời cung cấp cho Người bán (Seller) bộ công cụ hoàn chỉnh để xử lý đơn hàng và tự động hóa khâu vận chuyển.

### Các mục tiêu chính:
*   **Thanh toán Trực tuyến (VNPay Integration):** Tích hợp cổng thanh toán VNPay, xử lý luồng chuyển hướng (redirect) và đối soát tự động qua Webhook/IPN (Instant Payment Notification).
*   **Quản lý Đơn hàng Người bán (Seller Order Fulfillment):** Cho phép Seller xác nhận, từ chối hoặc hủy đơn hàng của khách hàng.
*   **Tích hợp Vận chuyển Tự động (Shipping API & Webhook):** Tự động kết nối với API của Đơn vị vận chuyển (GHTK/Grab) để sinh Mã vận đơn (Tracking ID) và cập nhật trạng thái giao hàng real-time qua Webhook.

---

## 2. Danh sách User Story & Use Case

| ID User Story | ID Use Case | Tên Chức năng / Mô tả | EPIC liên quan | Độ ưu tiên |
| :--- | :--- | :--- | :--- | :--- |
| `US-05.9` | UC05 | Chuyển hướng thanh toán sang cổng VNPay | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.12` | UC05 | Xử lý Webhook/IPN xác nhận thanh toán thành công | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.13` | UC05 | Xử lý kịch bản thanh toán thất bại / người dùng hủy giao dịch | EPIC 3 (Cart & Checkout) | Must Have |
| `US-16.1` | UC16 | Người bán xác nhận đơn hàng mới (CONFIRMED) | EPIC 4 (Seller & Admin) | Must Have |
| `US-16.2` | UC16 | Người bán từ chối / hủy đơn hàng (CANCELLED) | EPIC 4 (Seller & Admin) | Must Have |
| `US-16.3` | UC16 | Đẩy đơn qua API Vận chuyển (GHTK/Grab) & Lấy Tracking ID | EPIC 4 (Seller & Admin) | Must Have |
| `US-16.4` | UC16 | Xuất & In phiếu giao hàng (Shipping Label / PDF) | EPIC 4 (Seller & Admin) | Should Have |
| `US-16.5` | UC16 | Tự động cập nhật trạng thái đơn hàng qua Webhook ĐVVC | EPIC 4 (Seller & Admin) | Must Have |

---

## 3. Phân rã Công việc Chi tiết (Work Breakdown Structure - WBS)

### 3.1. Phân hệ Cơ sở dữ liệu (Database & Schema)
*   **DB-3.1:** Cập nhật Lược đồ Collection `orders` (bổ sung các trường `paymentGateway`, `transactionId`, `paymentStatus` với các giá trị `PENDING`, `PAID`, `FAILED`, `REFUNDED`).
*   **DB-3.2:** Cập nhật Lược đồ Collection `orders` (bổ sung cụm thông tin vận chuyển `shippingProvider`, `trackingNumber`, `shippingStatus`, `estimatedDeliveryDate`).
*   **DB-3.3:** Khởi tạo Lược đồ Collection `payment_logs` (lưu trữ toàn bộ payload request/response từ cổng VNPay để phục vụ đối soát và tra cứu lỗi).
*   **DB-3.4:** Khởi tạo Lược đồ Collection `webhook_logs` (lưu trữ các bản ghi nhận từ Webhook của VNPay và GHTK/Grab).

### 3.2. Phân hệ Backend (Spring Boot APIs & Integrations)
*   **BE-3.1:** Cấu hình tham số bảo mật VNPay (`vnp_TmnCode`, `vnp_HashSecret`, `vnp_Url`) trong `application.yml`.
*   **BE-3.2:** Triển khai `VNPayService` & `PaymentController` (Sinh URL thanh toán với mã băm an toàn SHA512, xử lý endpoint Return URL để điều hướng Frontend).
*   **BE-3.3:** Triển khai `VNPayIpnController` (Endpoint public nhận IPN từ VNPay, kiểm tra chữ ký hợp lệ, cập nhật trạng thái đơn hàng thành `PAID` và kích hoạt luồng thông báo cho Seller).
*   **BE-3.4:** Triển khai `SellerOrderController` & `FulfillmentService` (API cho phép Seller chuyển trạng thái đơn từ `PENDING` sang `CONFIRMED` hoặc `CANCELLED`).
*   **BE-3.5:** Tích hợp `GhtkGrabClientService` (Giao tiếp HTTP/REST với API GHTK/Grab để gửi thông tin lấy hàng, nhận lại `trackingNumber` và lưu vào DB).
*   **BE-3.6:** Triển khai `ShippingWebhookController` (Endpoint public nhận trạng thái vận chuyển từ GHTK/Grab như `PICKING`, `SHIPPED`, `DELIVERED`, tự động cập nhật đơn hàng).
*   **BE-3.7:** Triển khai `ShippingLabelService` (Kết xuất file PDF phiếu giao hàng chứa mã vạch/QR code đơn hàng).

### 3.3. Phân hệ Frontend (React, TypeScript & Redux Toolkit)
*   **FE-3.1:** Nâng cấp `CheckoutPage`: Thêm lựa chọn phương thức thanh toán "Thanh toán qua VNPay (Thẻ ATM / VNPAY-QR)".
*   **FE-3.2:** Xây dựng Trang Kết quả Thanh toán: `PaymentResultPage` (Hiển thị trạng thái Thành công/Thất bại dựa trên tham số URL trả về từ VNPay, kèm nút xem chi tiết đơn hàng).
*   **FE-3.3:** Nâng cấp Giao diện Quản lý Đơn hàng của Seller (`SellerOrderManagementPage`):
    *   Hiển thị danh sách đơn hàng phân loại theo Tab (`Chờ xác nhận`, `Đã xác nhận`, `Đang giao`, `Hoàn thành`, `Đã hủy`).
    *   Tích hợp các nút thao tác: "Xác nhận đơn", "Từ chối đơn" (kèm popup nhập lý do).
    *   Tích hợp nút "Đẩy đơn vận chuyển" (gọi API GHTK/Grab) và nút "In phiếu giao hàng" (tải PDF).
*   **FE-3.4:** Nâng cấp `OrderDetailModal` (phía Khách hàng): Hiển thị rõ ràng Mã vận đơn (`Tracking ID`), Đơn vị vận chuyển, và dòng thời gian (Timeline) giao hàng cập nhật real-time.

---

## 4. Quy tắc Nghiệp vụ & Ràng buộc (Business Rules & NFRs)

*   **BR05-5 (Bảo mật Thanh toán):** Tuyệt đối không lưu trữ thông tin thẻ ngân hàng hoặc tài khoản vnpay của khách hàng trên CSDL hệ thống. Mọi thao tác nhập thẻ phải được thực hiện trên môi trường bảo mật của cổng VNPay.
*   **BR05-6 (Xác thực IPN):** Hệ thống Backend chỉ công nhận đơn hàng đã thanh toán thành công khi và chỉ khi nhận được thông báo IPN hợp lệ (đúng chữ ký mã băm) từ server VNPay, không phụ thuộc vào kết quả trên trang Return URL của Frontend.
*   **BR16-1 (QĐ_SL3 - Mã Vận Đơn Tự Động):** Mã vận đơn (`Tracking ID`) bắt buộc phải được sinh tự động thông qua việc gọi API của ĐVVC (GHTK/Grab). Seller tuyệt đối không được phép nhập tay mã này nhằm ngăn chặn gian lận đối soát.
*   **NFR05-2:** API xử lý Webhook/IPN phải phản hồi cho VNPay hoặc GHTK/Grab với tốc độ dưới 500ms và đạt độ sẵn sàng 99.9%.

---

## 5. Tiêu chí Nghiệm thu & Kịch bản Demo (Vertical Slice Demo)

Cuối Release 3, hệ thống phải vận hành trơn tru kịch bản tích hợp thực tế sau:

1.  **Bước 1 (Thanh toán VNPay):** Khách hàng thực hiện Checkout, chọn phương thức VNPay. Nhấp "Đặt hàng", hệ thống lập tức chuyển hướng sang cổng thanh toán VNPay.
2.  **Bước 2 (Mô phỏng Thanh toán & IPN):** Khách hàng sử dụng tài khoản thẻ sandbox của VNPay để thanh toán. Cổng VNPay chuyển hướng khách hàng về `PaymentResultPage` với thông báo "Thanh toán thành công". Cùng lúc, VNPay gửi Webhook IPN ngầm về Backend, trạng thái đơn hàng chuyển thành `PAID`.
3.  **Bước 3 (Seller Xác nhận Đơn):** Người bán đăng nhập `SellerDashboard`, vào Quản lý đơn hàng, thấy đơn mới ở trạng thái `PAID`. Người bán nhấp "Xác nhận đơn". Trạng thái đơn chuyển thành `CONFIRMED`.
4.  **Bước 4 (Đẩy Vận chuyển & Lấy Tracking ID):** Người bán nhấp "Đẩy đơn vận chuyển". Backend gọi API GHTK/Grab, nhận về mã vận đơn (VD: `GHTK123456789`). Giao diện Seller cập nhật hiển thị mã vận đơn và hiển thị nút "In phiếu giao hàng".
5.  **Bước 5 (Cập nhật Webhook Vận chuyển):** Hệ thống GHTK/Grab gửi Webhook thông báo đơn hàng đã được giao thành công (`DELIVERED`). Backend nhận Webhook, tự động chuyển trạng thái đơn hàng thành `DELIVERED`. Khách hàng xem lịch sử đơn hàng thấy trạng thái cập nhật hoàn tất.
