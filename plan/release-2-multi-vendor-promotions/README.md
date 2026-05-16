# Kế hoạch Phát hành Release 2: Multi-vendor Checkout & Promotions

## 1. Mục tiêu & Phạm vi (Objectives & Scope)

**Release 2 (Multi-vendor Checkout & Promotions)** giải quyết bài toán phức tạp cốt lõi của một mô hình Sàn Thương mại Điện tử Đa Nhà Cung Cấp (Multi-vendor Marketplace). Trọng tâm của phiên bản này là kiến trúc phân rã đơn hàng (Split Order) và hệ thống Động cơ Khuyến mãi (Promotions Engine).

### Các mục tiêu chính:
*   **Tách đơn hàng đa nhà cung cấp (Split Order):** Đảm bảo trải nghiệm thanh toán liền mạch cho Khách hàng (chỉ thanh toán 1 lần cho toàn bộ giỏ hàng), nhưng tự động tách thành các đơn hàng phụ độc lập cho từng Người bán (Seller) quản lý.
*   **Động cơ Khuyến mãi (Voucher Engine):** Tự động tính toán và đề xuất Mã giảm giá (Voucher) tốt nhất cho khách hàng, đồng thời hỗ trợ áp dụng thủ công.
*   **Hệ thống Ví Xu (Reward Coins):** Cho phép khách hàng sử dụng Xu tích lũy để giảm giá hóa đơn và tra cứu lịch sử biến động số dư.
*   **Danh sách Yêu thích (Wishlist):** Cho phép khách hàng lưu trữ các sản phẩm yêu thích để mua sắm trong tương lai.

---

## 2. Danh sách User Story & Use Case

| ID User Story | ID Use Case | Tên Chức năng / Mô tả | EPIC liên quan | Độ ưu tiên |
| :--- | :--- | :--- | :--- | :--- |
| `US-04.1` | UC04 | Thêm/Xóa sản phẩm khỏi Danh sách Yêu thích (Wishlist) | EPIC 3 (Cart & Checkout) | Should Have |
| `US-04.2` | UC04 | Xem danh sách sản phẩm trong Wishlist & Chuyển vào Giỏ | EPIC 3 (Cart & Checkout) | Should Have |
| `US-05.1` | UC05 | Tự động quét & đề xuất Voucher tốt nhất tại Giỏ hàng | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.2` | UC05 | Khách hàng chọn hoặc nhập mã Voucher thủ công | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.3` | UC05 | Khách hàng sử dụng số dư Ví Xu để giảm giá đơn hàng | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.4` | UC05 | Bảng tổng hợp chi phí thanh toán (Final Payment Summary) | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.6` | UC05 | Tách đơn hàng theo Seller (Split Order) tại bước Checkout | EPIC 3 (Cart & Checkout) | Must Have |
| `US-12.1` | UC12 | Tra cứu lịch sử giao dịch & biến động số dư Ví Xu | EPIC 2 (Identity & Access) | Should Have |

---

## 3. Phân rã Công việc Chi tiết (Work Breakdown Structure - WBS)

### 3.1. Phân hệ Cơ sở dữ liệu (Database & Schema)
*   **DB-2.1:** Khởi tạo Lược đồ Collection `wishlists` (liên kết `userId` với mảng `productIds`).
*   **DB-2.2:** Thiết kế Lược đồ Collection `vouchers` (lưu trữ mã voucher, loại giảm giá `%` hoặc `tiền mặt`, điều kiện đơn tối thiểu, hạn mức tối đa, danh sách `applicableSellerIds` hoặc áp dụng toàn sàn).
*   **DB-2.3:** Cập nhật Lược đồ Collection `users` (thêm trường `coinBalance` lưu số dư xu hiện tại).
*   **DB-2.4:** Khởi tạo Lược đồ Collection `coin_transactions` (lưu lịch sử cộng/trừ xu kèm lý do và `orderId` liên quan).
*   **DB-2.5:** Tái cấu trúc Lược đồ Collection `orders` (Thiết lập mô hình `PaymentOrder` cha chứa thông tin tổng thanh toán, và mảng các `Sub-Orders` con chứa thông tin sản phẩm của từng `sellerId`).

### 3.2. Phân hệ Backend (Spring Boot APIs & Logic)
*   **BE-2.1:** Triển khai `WishlistController` & `WishlistService` (Các endpoint CRUD cho danh sách yêu thích của người dùng).
*   **BE-2.2:** Xây dựng `VoucherEngineService` (Thuật toán quét danh sách voucher hợp lệ dựa trên tổng giá trị giỏ hàng, phân loại voucher của Seller và voucher của Admin, tự động chọn voucher mang lại mức giảm giá cao nhất).
*   **BE-2.3:** Triển khai `CoinService` (Kiểm tra số dư xu, tính toán số tiền được giảm dựa trên tỉ giá quy đổi, ghi nhận lịch sử biến động số dư vào `coin_transactions`).
*   **BE-2.4:** Nâng cấp `CheckoutService` & `OrderSplitterHelper` (Thực thi logic tách giỏ hàng: Phân rã `cartItems` theo `sellerId`, phân bổ tỉ lệ giảm giá từ Voucher/Xu của đơn cha vào từng đơn con để phục vụ đối soát chính xác sau này).
*   **BE-2.5:** Triển khai `CoinHistoryController` (API truy xuất lịch sử biến động ví xu cho trang quản lý tài khoản).

### 3.3. Phân hệ Frontend (React, TypeScript & Redux Toolkit)
*   **FE-2.1:** Bổ sung Redux Slices: `wishlistSlice`, `voucherSlice`, `coinSlice`.
*   **FE-2.2:** Xây dựng Giao diện Wishlist: `WishlistPage` (hiển thị lưới sản phẩm, tích hợp nút "Thêm vào giỏ hàng" và nút "Xóa").
*   **FE-2.3:** Nâng cấp Giao diện `CartPage` & `CheckoutPage`:
    *   Tích hợp khu vực hiển thị "Voucher được đề xuất" và nút "Chọn Voucher khác" (mở Modal danh sách Voucher).
    *   Tích hợp nút gạt (Toggle Switch) "Sử dụng TKart Xu" hiển thị số dư hiện tại và số tiền được giảm.
    *   Cập nhật bảng `Final Payment Summary` hiển thị rõ ràng: Tổng tiền hàng, Phí vận chuyển, Giảm giá Voucher, Giảm giá Xu, và Tổng thanh toán cuối cùng.
*   **FE-2.4:** Nâng cấp Giao diện `OrderHistoryPage`: Hiển thị cấu trúc Đơn hàng cha và danh sách các Đơn hàng con trực thuộc theo từng chi nhánh/gian hàng.
*   **FE-2.5:** Xây dựng Giao diện Ví Xu: `CoinHistoryPage` (hiển thị số dư hiện tại và bảng lịch sử biến động xu).

---

## 4. Quy tắc Nghiệp vụ & Ràng buộc (Business Rules & NFRs)

*   **BR05-1 (Voucher Engine):** Thuật toán phải tự động chọn Voucher có giá trị giảm trừ cao nhất cho khách hàng. Nếu khách hàng thay đổi địa chỉ (làm thay đổi phí vận chuyển) hoặc thêm/bớt sản phẩm, hệ thống bắt buộc phải tính toán lại toàn bộ giỏ hàng ngay lập tức.
*   **BR05-2 (QĐ_KH4 - Split Order):** Khách hàng chỉ thực hiện thanh toán 1 lần duy nhất cho toàn bộ đơn hàng cha (`PaymentOrder`). Hệ thống Backend tự động tách thành các `Sub-Orders` theo `sellerId`.
*   **BR05-3 (CT_KH1 - Final Payment):** Công thức tính toán tổng tiền thanh toán:
    $$\text{Final Payment} = \text{Total MRP} - \text{Coupon Discount} - \text{Coin Discount} + \text{Shipping Fee}$$
*   **BR12-1:** Hạn mức thanh toán bằng Xu không được vượt quá % quy định của đơn hàng (VD: Tối đa 50% giá trị hóa đơn).
*   **NFR05-1:** Thời gian xử lý của Voucher Engine và thuật toán tách đơn hàng tại bước Checkout phải hoàn thành dưới 1 giây để đảm bảo trải nghiệm người dùng không bị gián đoạn.

---

## 5. Tiêu chí Nghiệm thu & Kịch bản Demo (Vertical Slice Demo)

Cuối Release 2, hệ thống phải vượt qua kịch bản kiểm chứng thực tế sau:

1.  **Bước 1 (Wishlist):** Khách hàng truy cập trang chi tiết sản phẩm "Giày thể thao", nhấp biểu tượng Trái tim. Truy cập `WishlistPage`, thấy sản phẩm xuất hiện. Nhấp "Thêm vào giỏ hàng" từ Wishlist, sản phẩm được chuyển thành công vào giỏ.
2.  **Bước 2 (Chuẩn bị Giỏ hàng Multi-vendor):** Khách hàng tiếp tục thêm sản phẩm "Áo thun" (thuộc Gian hàng A) và "Tai nghe" (thuộc Gian hàng B) vào giỏ hàng. Truy cập `CartPage`.
3.  **Bước 3 (Voucher & Xu):** Tại giỏ hàng, hệ thống tự động hiển thị thông báo "Đã áp dụng Voucher giảm 10% (Tối đa 50k)". Khách hàng bật nút gạt "Sử dụng 20,000 Xu (giảm 20,000đ)". Bảng `Final Payment Summary` tính toán lại tức thì, trừ chính xác số tiền giảm từ Voucher và Xu.
4.  **Bước 4 (Split Order Checkout):** Khách hàng nhấp "Tiến hành Thanh toán", chọn phương thức COD và đặt hàng. Hệ thống thông báo thành công.
5.  **Bước 5 (Kiểm chứng Tách đơn):** Khách hàng truy cập `OrderHistoryPage`, thấy 1 Lệnh thanh toán tổng, bên dưới tách thành 2 Đơn hàng con: Đơn #1 (Gian hàng A - Áo thun) và Đơn #2 (Gian hàng B - Tai nghe).
6.  **Bước 6 (Kiểm chứng Ví Xu):** Khách hàng truy cập `CoinHistoryPage`, thấy số dư xu bị trừ đi 20,000 Xu kèm dòng lịch sử "Thanh toán cho đơn hàng #xxx".
