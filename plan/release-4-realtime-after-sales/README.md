# Kế hoạch Phát hành Release 4: Real-time Interaction & After-sales

## 1. Mục tiêu & Phạm vi (Objectives & Scope)

**Release 4 (Real-time Interaction & After-sales)** nâng tầm trải nghiệm người dùng bằng cách bổ sung các tính năng tương tác thời gian thực (Real-time Chat), hỗ trợ tự động bằng Trí tuệ Nhân tạo (AI Chatbot), và hoàn thiện toàn bộ quy trình chăm sóc khách hàng hậu mãi (After-sales).

### Các mục tiêu chính:
*   **Tương tác Thời gian thực (WebSockets Chat 1-1):** Kênh giao tiếp trực tiếp giữa Khách hàng và Người bán để tư vấn sản phẩm và hỗ trợ đơn hàng.
*   **Trợ lý Ảo Thông minh (AI Chatbot NLP):** Hỗ trợ giải đáp tự động 24/7 các câu hỏi thường gặp, truy vấn trạng thái đơn hàng và chính sách sàn.
*   **Hệ thống Đánh giá Sản phẩm (Reviews & Ratings):** Khách hàng đánh giá chất lượng sản phẩm sau khi mua, hỗ trợ cơ chế đính kèm hình ảnh và thu hồi/chỉnh sửa đánh giá.
*   **Quy trình Trả hàng & Trọng tài Khiếu nại (Returns & Disputes):** Khách hàng tạo yêu cầu hoàn trả, Người bán xử lý trong thời hạn SLA, và Quản trị viên đóng vai trò trọng tài phân xử nếu xảy ra tranh chấp (Dispute).

---

## 2. Danh sách User Story & Use Case

| ID User Story | ID Use Case | Tên Chức năng / Mô tả | EPIC liên quan | Độ ưu tiên |
| :--- | :--- | :--- | :--- | :--- |
| `US-13.1` | UC13 | Khởi tạo phiên Chat 1-1 giữa Khách hàng và Người bán | EPIC 1 (Discovery & Chat) | Must Have |
| `US-13.2` | UC13 | Gửi/Nhận tin nhắn thời gian thực qua WebSockets | EPIC 1 (Discovery & Chat) | Must Have |
| `US-13.3` | UC13 | Lưu trữ & Tra cứu lịch sử tin nhắn trò chuyện | EPIC 1 (Discovery & Chat) | Must Have |
| `US-07.1` | UC07 | AI Chatbot giải đáp thông tin công khai (FAQ/Chính sách) | EPIC 1 (Discovery & Chat) | Should Have |
| `US-07.2` | UC07 | AI Chatbot tư vấn ngữ cảnh theo sản phẩm đang xem | EPIC 1 (Discovery & Chat) | Should Have |
| `US-07.3` | UC07 | AI Chatbot tra cứu trạng thái đơn hàng cá nhân | EPIC 1 (Discovery & Chat) | Should Have |
| `US-08.1` | UC08 | Khách hàng viết Đánh giá & Chấm điểm sao (1-5 sao) | EPIC 5 (After-sales) | Must Have |
| `US-08.2` | UC08 | Khách hàng chỉnh sửa / thu hồi Đánh giá đã đăng | EPIC 5 (After-sales) | Should Have |
| `US-09.1` | UC09 | Khách hàng tạo Yêu cầu Trả hàng & Hoàn tiền (RETURN_REQUESTED) | EPIC 5 (After-sales) | Must Have |
| `US-09.2` | UC09 | Khách hàng nâng cấp Tranh chấp (Escalate to Admin) | EPIC 5 (After-sales) | Must Have |
| `US-17.1` | UC17 | Người bán Chấp nhận yêu cầu trả hàng (REFUNDED) | EPIC 5 (After-sales) | Must Have |
| `US-17.2` | UC17 | Người bán Từ chối yêu cầu trả hàng (kèm lý do) | EPIC 5 (After-sales) | Must Have |
| `US-17.3` | UC17 | Xử lý vi phạm SLA 3 ngày của Người bán | EPIC 5 (After-sales) | Must Have |
| `US-23.1` | UC23 | Admin tiếp nhận & phân xử đơn hàng tranh chấp (DISPUTED) | EPIC 5 (After-sales) | Must Have |
| `US-23.2` | UC23 | Admin gọi lệnh Hoàn tiền Tự động (Automated Refund API) | EPIC 5 (After-sales) | Must Have |

---

## 3. Phân rã Công việc Chi tiết (Work Breakdown Structure - WBS)

### 3.1. Phân hệ Cơ sở dữ liệu (Database & Schema)
*   **DB-4.1:** Khởi tạo Lược đồ Collection `chat_rooms` (liên kết `customerId` và `sellerId`) và `chat_messages` (lưu nội dung tin nhắn, `senderId`, `timestamp`, `isRead`).
*   **DB-4.2:** Khởi tạo Lược đồ Collection `reviews` (liên kết `userId`, `productId`, `orderId`, `rating`, `comment`, `mediaUrls`).
*   **DB-4.3:** Khởi tạo Lược đồ Collection `return_requests` (liên kết `orderId`, `userId`, `sellerId`, `reason`, `evidences`, `status` với các giá trị `PENDING`, `ACCEPTED`, `REJECTED`, `DISPUTED`, `REFUNDED`).
*   **DB-4.4:** Cập nhật Lược đồ Collection `orders` (bổ bổ sung trạng thái `RETURN_REQUESTED`, `RETURN_REJECTED`, `DISPUTED`, `REFUNDED`).

### 3.2. Phân hệ Backend (Spring Boot APIs, WebSockets & AI Integration)
*   **BE-4.1:** Cấu hình Spring WebSockets & STOMP Protocol (Bảo mật endpoint `/ws/**` bằng JWT Token đính kèm trong quá trình handshake).
*   **BE-4.2:** Triển khai `ChatController` & `ChatMessageService` (Xử lý các message mapping `/app/chat.send`, phát tin nhắn đến `/topic/messages/{roomId}`, lưu tin nhắn vào MongoDB).
*   **BE-4.3:** Tích hợp `AiChatbotService` (Giao tiếp qua REST API với máy chủ NLP AI hoặc OpenAI/Gemini API, cung cấp ngữ cảnh dữ liệu tĩnh và tra cứu đơn hàng).
*   **BE-4.4:** Triển khai `ReviewController` & `ReviewService` (CRUD đánh giá sản phẩm, tự động tính toán lại điểm `averageRating` của sản phẩm khi có review mới).
*   **BE-4.5:** Triển khai `ReturnRequestController` & `ReturnService` (Khách hàng tạo yêu cầu trả hàng, tải minh chứng lên Cloudinary, thiết lập bộ đếm thời gian SLA 3 ngày).
*   **BE-4.6:** Triển khai `SellerReturnController` (API cho phép Seller chấp nhận hoặc từ chối yêu cầu trả hàng của Khách).
*   **BE-4.7:** Triển khai `AdminDisputeController` & `RefundService` (API cho Admin xem song song minh chứng của Khách và Seller, ra quyết định phân xử và gọi tự động cổng Refund API của VNPay/SePay/Momo).

### 3.3. Phân hệ Frontend (React, TypeScript & Redux Toolkit)
*   **FE-4.1:** Bổ sung Redux Slices: `chatSlice`, `reviewSlice`, `returnSlice`, `disputeSlice`.
*   **FE-4.2:** Xây dựng Giao diện Chat:
    *   Tích hợp Cửa sổ Chat thu nhỏ (Chat Widget / Drawer) ở góc dưới màn hình Khách hàng.
    *   Xây dựng Cửa sổ Chat dành cho Người bán (`SellerChatPage`) quản lý nhiều hội thoại cùng lúc.
*   **FE-4.3:** Xây dựng Giao diện Chatbot: Tích hợp Cửa sổ Trợ lý Ảo AI (AI Chatbot Widget) hỗ trợ gạch đầu dòng các câu hỏi nhanh.
*   **FE-4.4:** Nâng cấp `ProductDetailPage`: Bổ sung khu vực Hiển thị Đánh giá & Biểu đồ sao, tích hợp Modal "Viết đánh giá" (hỗ trợ upload ảnh/video).
*   **FE-4.5:** Xây dựng Giao diện Trả hàng & Khiếu nại:
    *   `ReturnRequestModal` (dành cho Khách hàng tại trang Lịch sử đơn hàng, cho phép chọn lý do và upload ảnh/video minh chứng).
    *   `SellerReturnManagementPage` (dành cho Người bán, hiển thị danh sách yêu cầu trả hàng kèm đồng hồ đếm ngược SLA 3 ngày, tích hợp nút "Chấp nhận" / "Từ chối").
    *   `AdminDisputeManagementPage` (dành cho Admin, giao diện so sánh bằng chứng hai bên, tích hợp nút "Phán quyết Hoàn tiền" / "Phán quyết Chuyển tiền cho Seller").

---

## 4. Quy tắc Nghiệp vụ & Ràng buộc (Business Rules & NFRs)

*   **BR08-1:** Khách hàng chỉ được phép viết đánh giá (Review) cho những sản phẩm thuộc đơn hàng đã hoàn thành (`DELIVERED`). Mỗi sản phẩm trong một đơn hàng chỉ được đánh giá 1 lần.
*   **BR17-1 (QĐ_SL5 - SLA 3 Ngày):** Seller có tối đa 3 ngày để phản hồi yêu cầu trả hàng. Quá thời hạn này mà Seller không phản hồi, hệ thống tự động ghi nhận vi phạm SLA và cho phép Khách hàng khiếu nại lên Admin.
*   **BR23-1 (QĐ_AD6 - Quyết định Trọng tài):** Phán quyết của Admin tại màn hình Dispute là quyết định cuối cùng và bắt buộc thực thi. Sau khi Admin đã xử lý, hai bên không được thao tác khiếu nại lại.
*   **BR23-2 (QĐ_AD7 - Automated Refund):** Tuyệt đối không thực hiện hoàn tiền thủ công ngoài hệ thống. Quá trình hoàn tiền phải được kích hoạt qua luồng API tự động để đảm bảo an toàn dòng tiền.
*   **NFR13-1:** Độ trễ (Latency) của tin nhắn WebSockets phải dưới 200ms.
*   **NFR17-1 & NFR23-1:** Giao diện xem hình ảnh/video minh chứng trên Dashboard của Seller và Admin phải hỗ trợ phóng to (Zoom) và phát trực tiếp mượt mà không cần tải file về máy.

---

## 5. Tiêu chí Nghiệm thu & Kịch bản Demo (Vertical Slice Demo)

Cuối Release 4, hệ thống phải vượt qua kịch bản kiểm chứng thực tế sau:

1.  **Bước 1 (Chat Real-time):** Khách hàng đang xem sản phẩm "Điện thoại", nhấp nút "Chat với Người bán". Cửa sổ chat bật lên. Khách hàng gõ "Sản phẩm này còn màu đen không?". Ngay lập tức, giao diện `SellerChatPage` của Người bán nhận được tin nhắn và phát âm thanh thông báo. Người bán trả lời "Còn hàng ạ", khách hàng nhận tin nhắn tức thì.
2.  **Bước 2 (AI Chatbot):** Khách hàng mở Widget AI Chatbot, chọn câu hỏi "Chính sách đổi trả thế nào?". Chatbot NLP lập tức phản hồi chi tiết quy định trả hàng trong 7 ngày của sàn.
3.  **Bước 3 (Đánh giá Sản phẩm):** Khách hàng vào Lịch sử đơn hàng, chọn một đơn đã giao thành công (`DELIVERED`), nhấp "Đánh giá". Chọn 5 sao, tải lên 1 ảnh thực tế và viết nhận xét. Nhấp "Gửi". Trang chi tiết sản phẩm cập nhật điểm số sao và hiển thị bài đánh giá.
4.  **Bước 4 (Yêu cầu Trả hàng):** Khách hàng chọn một đơn hàng khác, nhấp "Yêu cầu Trả hàng/Hoàn tiền". Chọn lý do "Hàng bị lỗi kỹ thuật", tải lên video minh chứng. Trạng thái đơn chuyển thành `RETURN_REQUESTED`.
5.  **Bước 5 (Seller Từ chối & Escalate):** Người bán vào `SellerReturnManagementPage`, xem video của khách, chọn "Từ chối" kèm lý do "Hàng bị rơi vỡ do khách". Khách hàng nhận thông báo từ chối, nhấp nút "Khiếu nại lên Admin" (Escalate). Trạng thái đơn chuyển thành `DISPUTED`.
6.  **Bước 6 (Admin Phân xử & Hoàn tiền):** Admin vào `AdminDisputeManagementPage`, xem bằng chứng hai bên. Admin nhận định lỗi thuộc về sản phẩm, nhấp "Chấp nhận khiếu nại - Hoàn tiền". Hệ thống tự động gọi API hoàn tiền của cổng thanh toán, đơn hàng chuyển sang `REFUNDED`.
