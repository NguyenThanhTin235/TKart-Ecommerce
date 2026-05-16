# Danh sách Công việc Triển khai Chi tiết Release 1: MVP Core Commerce (TODO Checklist)

Tài liệu này cung cấp danh sách kiểm tra (TODO Checklist) cực kỳ chi tiết, mang tính hành động cao dành cho đội ngũ phát triển để xây dựng hoàn chỉnh **Release 1: MVP Core Commerce** từ tầng Backend API cho đến giao diện Frontend (Phần Cơ sở dữ liệu và Dữ liệu mẫu đã hoàn thành).

---

## ⚙️ PHẦN 1: BACKEND APIs & SECURITY (SPRING BOOT)

### 1.1. Cấu hình Bảo mật & Core Security (`com.tkart.ecommerce.config`)
- [x] Cấu hình `SecurityConfig`: Phân quyền các endpoint (Mở public cho `/api/v1/auth/**`, `/api/v1/public/**`; Yêu cầu xác thực JWT cho `/api/v1/cart/**`, `/api/v1/orders/**`, `/api/v1/seller/**`, `/api/v1/admin/**`, `/api/v1/profile/**`).
- [x] Triển khai `JwtTokenProvider` / `JwtService`: Sinh token, xác thực token, trích xuất thông tin `username` và `roles` từ token.
- [x] Triển khai `JwtAuthenticationFilter`: Bộ lọc chặn các request, trích xuất Bearer token từ header, thiết lập `SecurityContextHolder`.
- [x] Triển khai `CustomAuthenticationEntryPoint` (xử lý lỗi 401 Unauthorized) và `CustomAccessDeniedHandler` (xử lý lỗi 403 Forbidden).

### 1.2. Module Xác thực & Tài khoản (`AuthController` & `AuthService`)
- [ ] `POST /api/v1/auth/register`: Đăng ký tài khoản Customer/Seller, kiểm tra email trùng, băm mật khẩu bằng BCrypt, sinh mã OTP 6 số ngẫu nhiên lưu vào Redis (TTL 5 phút), gửi email OTP qua `MailService`.
- [ ] `POST /api/v1/auth/verify-otp`: Xác thực mã OTP trong Redis, cập nhật `isVerified = true`, kích hoạt tài khoản thành công.
- [ ] `POST /api/v1/auth/login`: Xác thực email/password, kiểm tra điều kiện `isVerified`, cấp phát JWT Token (Access Token).
- [ ] `POST /api/v1/auth/logout`: Xử lý đăng xuất.

### 1.3. Module Khám phá, Tìm kiếm & Lọc (`ProductPublicController` & `ProductService`)
- [ ] Triển khai `ProductCustomRepositoryImpl`: Sử dụng `MongoTemplate` và `Criteria API` xây dựng truy vấn lọc động (Dynamic Query) kết hợp các điều kiện: `keyword` (regex matching), `categoryId`, `minPrice`, `maxPrice`, `minDiscount`, `color`, `size`, `minRating`, `status = PUBLISHED`.
- [ ] `GET /api/v1/public/products`: Endpoint lấy danh sách sản phẩm công khai hỗ trợ đầy đủ bộ lọc, phân trang (`page`, `limit`), sắp xếp (`sortBy`, `sortDir`).
- [ ] `GET /api/v1/public/products/{id}`: Lấy chi tiết sản phẩm và truy vấn kèm danh sách 5-10 "Sản phẩm liên quan" có cùng danh mục cấp 3.
- [ ] `GET /api/v1/public/categories`: Lấy toàn bộ cây danh mục 3 cấp để hiển thị lên Sidebar.

### 1.4. Module Giỏ hàng cá nhân (`CartController` & `CartService`)
- [ ] `GET /api/v1/cart`: Lấy thông tin giỏ hàng của user đang đăng nhập (tự động tạo giỏ hàng rỗng nếu chưa tồn tại).
- [ ] `POST /api/v1/cart/items`: Thêm sản phẩm vào giỏ, kiểm tra số lượng tồn kho `stock` của sản phẩm. Nếu sản phẩm đã tồn tại trong giỏ, thực hiện cộng dồn số lượng.
- [ ] `PUT /api/v1/cart/items/{productId}`: Cập nhật số lượng của một sản phẩm trong giỏ (đảm bảo điều kiện `quantity <= stock`).
- [ ] `DELETE /api/v1/cart/items/{productId}`: Xóa một sản phẩm khỏi giỏ hàng.
- [ ] `DELETE /api/v1/cart`: Làm sạch toàn bộ giỏ hàng.

### 1.5. Module Đặt hàng & Thanh toán COD (`CheckoutController` & `OrderService`)
- [ ] `POST /api/v1/checkout/cod`: Tiến hành đặt hàng COD. Nhận thông tin địa chỉ giao hàng (`shippingAddress`), kiểm tra lại tồn kho thực tế của toàn bộ sản phẩm trong giỏ, tính toán tổng tiền `totalAmount`.
- [ ] Thực thi trừ số lượng tồn kho `stock` của các sản phẩm tương ứng trong DB.
- [ ] Tạo mới bản ghi `Order` với `orderStatus = PENDING`, `paymentMethod = COD`, `paymentStatus = PENDING`.
- [ ] Xóa sạch giỏ hàng của user sau khi tạo đơn hàng thành công.
- [ ] `GET /api/v1/orders`: Lấy danh sách đơn hàng của user đang đăng nhập (kèm phân trang, sắp xếp mới nhất).
- [ ] `GET /api/v1/orders/{orderId}`: Lấy chi tiết đơn hàng kèm các mốc thời gian tiến trình (Order Stepper).
- [ ] `POST /api/v1/orders/{orderId}/cancel`: Hủy đơn hàng (chỉ cho phép khi `orderStatus == PENDING`), hoàn lại đầy đủ số lượng tồn kho `stock` cho các sản phẩm trong đơn.

### 1.6. Module Quản trị & Người bán MVP (`AdminApprovalController` & `SellerProductController`)
- [ ] `GET /api/v1/admin/sellers/pending`: Admin lấy danh sách tài khoản Seller đang chờ duyệt.
- [ ] `POST /api/v1/admin/sellers/{id}/approve`: Admin phê duyệt tài khoản Seller sang trạng thái `ACTIVE`.
- [ ] `GET /api/v1/admin/products/pending`: Admin lấy danh sách sản phẩm mới do Seller đăng tải đang ở trạng thái `PENDING`.
- [ ] `POST /api/v1/admin/products/{id}/approve`: Admin phê duyệt sản phẩm sang trạng thái `PUBLISHED` để hiển thị công khai trên sàn.
- [ ] `POST /api/v1/seller/products`: Seller đăng tải sản phẩm mới (mặc định khởi tạo ở trạng thái `PENDING`).

### 1.7. Module Quản lý Hồ sơ & Sổ địa chỉ (`UserProfileController` & `UserService`)
- [ ] `GET /api/v1/profile`: Lấy thông tin chi tiết hồ sơ của user đang đăng nhập (Họ tên, SĐT, ngày sinh, avatar, isVerified, roles).
- [ ] `PUT /api/v1/profile`: Cập nhật thông tin cá nhân cơ bản (Họ tên, SĐT, ngày sinh, avatar).
- [ ] `PUT /api/v1/profile/password`: Thay đổi mật khẩu (kiểm tra mật khẩu cũ, băm mật khẩu mới bằng BCrypt).
- [ ] `GET /api/v1/profile/addresses`: Lấy danh sách sổ địa chỉ (Address Book) của Khách hàng.
- [ ] `POST /api/v1/profile/addresses`: Thêm địa chỉ mới vào sổ địa chỉ (hỗ trợ cờ `isDefault`).
- [ ] `PUT /api/v1/profile/addresses/{id}`: Cập nhật thông tin địa chỉ hoặc đặt làm địa chỉ mặc định.
- [ ] `DELETE /api/v1/profile/addresses/{id}`: Xóa một địa chỉ khỏi sổ địa chỉ.
- [ ] `PUT /api/v1/profile/store`: Người bán cập nhật thông tin gian hàng (Tên gian hàng `storeName`, mã số thuế `gstNumber`, chi tiết tài khoản ngân hàng `bankAccount`).

---

## 💻 PHẦN 2: FRONTEND (REACT, TYPESCRIPT, REDUX TOOLKIT)

### 2.1. Thiết lập Cấu trúc & Nền tảng Frontend
- [ ] Cấu hình thư mục dự án, kiểm tra tích hợp Tailwind CSS, Material UI (MUI) và React Router DOM.
- [ ] Thiết lập Redux Store (`store.ts`) và cấu hình các Slice: `authSlice` (lưu token, thông tin user), `cartSlice` (quản lý danh sách cart items, tổng tiền tạm tính), `productSlice` (quản lý danh sách sản phẩm, bộ lọc, phân trang), `orderSlice` (quản lý danh sách đơn hàng), `profileSlice` (quản lý thông tin user, sổ địa chỉ).
- [ ] Cấu hình Axios Instance (`api.ts`): Tự động đính kèm header `Authorization: Bearer {token}`, cấu hình interceptor xử lý lỗi 401 (hết hạn token -> tự động đăng xuất và điều hướng về trang Login).

### 2.2. Cấu hình UI Layout & Navigation
- [ ] **Xây dựng Component `Navbar / Header`:** Hiển thị Logo TKart, thanh tìm kiếm (`SearchBar`), icon Giỏ hàng có badge hiển thị số lượng sản phẩm, icon Tài khoản (Dropdown menu: Đăng nhập/Đăng ký hoặc Trang cá nhân/Đăng xuất).
- [ ] **Xây dựng Component `Footer`:** Hiển thị thông tin bản quyền và các liên kết tĩnh (FAQ, Chính sách).
- [ ] **Thiết lập Router (`App.tsx`):** Định nghĩa các Public Routes, Private Routes (dành cho Customer) và Admin/Seller Routes.

### 2.3. Nhóm Trang Xác thực (Auth Pages)
- [ ] **`RegisterPage`:** Biểu mẫu đăng ký (Họ tên, Email, Mật khẩu, Chọn vai trò Customer/Seller). Xử lý validate form, gọi API register và chuyển hướng sang trang xác thực OTP.
- [ ] **`OtpVerificationPage`:** Biểu mẫu nhập mã OTP 6 số. Tích hợp bộ đếm ngược (thời gian hiệu lực 5 phút) và nút "Gửi lại mã". Gọi API verify-otp và chuyển hướng sang trang Login.
- [ ] **`LoginPage`:** Biểu mẫu đăng nhập Email/Password. Gọi API login, lưu JWT vào LocalStorage / Redux và điều hướng về HomePage (hoặc Admin/Seller Dashboard tùy thuộc role).

### 2.4. Nhóm Trang Khám phá, Tìm kiếm & Lọc (Discovery & Search)
- [ ] **`HomePage`:** Hiển thị Banner quảng cáo tĩnh, danh sách danh mục nổi bật, lưới sản phẩm hot (`ProductGrid` chứa các `ProductCard`).
- [ ] **`SearchFilterPage` (Trang cốt lõi Tìm kiếm & Lọc):**
  - [ ] `CategorySidebar`: Hiển thị cây danh mục 3 cấp, cho phép click để lọc nhanh sản phẩm theo danh mục.
  - [ ] `FilterSidebar`: Component chứa thanh trượt khoảng giá (`PriceRangeSlider`), danh sách checkbox màu sắc/kích cỡ (`AttributeFilter`), và danh sách radio chọn sao đánh giá (`RatingFilter`).
  - [ ] `SortDropdown`: Dropdown chọn tiêu chí sắp xếp (Giá từ thấp đến cao, Giá từ cao xuống thấp, Mới nhất, Bán chạy).
  - [ ] `ProductGrid`: Lưới hiển thị danh sách thẻ sản phẩm (`ProductCard`). Mỗi thẻ hiển thị ảnh, tên, giá bán, giá gốc, badge % giảm giá và điểm rating.
  - [ ] `Pagination`: Component phân trang (Trang 1, 2, 3...), xử lý chuyển trang mượt mà.
  - [ ] `EmptyState`: Màn hình thông báo trực quan "Không tìm thấy sản phẩm phù hợp" kèm icon minh họa và nút "Xóa bộ lọc" để reset các tiêu chí.
- [ ] **`ProductDetailPage`:** Trang chi tiết sản phẩm. Hiển thị gallery ảnh, tên, giá bán, giá gốc, mô tả chi tiết, khu vực chọn thuộc tính (Màu sắc, Kích cỡ), thông tin gian hàng Seller, nút "Thêm vào giỏ hàng" / "Mua ngay", và khu vực hiển thị danh sách "Sản phẩm liên quan".

### 2.5. Nhóm Trang Giỏ hàng & Đặt hàng COD (Cart & Checkout)
- [ ] **`CartPage`:** Hiển thị danh sách các sản phẩm trong giỏ dưới dạng bảng/danh sách. Mỗi dòng có ảnh, tên, thuộc tính đã chọn, đơn giá, bộ đếm tăng/giảm số lượng (`+/-`), nút xóa sản phẩm. Hiển thị bảng Tóm tắt đơn hàng (Tổng tiền tạm tính) và nút "Tiến hành Thanh toán".
- [ ] **`CheckoutPage`:** Hiển thị biểu mẫu nhập thông tin giao hàng (Sổ địa chỉ hoặc nhập địa chỉ mới), chọn phương thức thanh toán (chọn mặc định "Thanh toán khi nhận hàng - COD"). Hiển thị danh sách sản phẩm đặt mua, tổng tiền thanh toán cuối cùng và nút "Xác nhận Đặt hàng". Xử lý gọi API checkout COD và điều hướng sang trang "Đặt hàng Thành công".

### 2.6. Nhóm Trang Quản lý Đơn hàng (Order Management)
- [ ] **`OrderHistoryPage`:** Trang danh sách đơn hàng cá nhân của Khách hàng. Hiển thị các thẻ đơn hàng kèm trạng thái (`PENDING`, `CONFIRMED`, `DELIVERED`...).
- [ ] **`OrderDetailModal / Page`:** Màn hình chi tiết của một đơn hàng cụ thể. Tích hợp thanh tiến trình trực quan (`Order Stepper`) theo dõi mốc thời gian: Đặt hàng -> Xác nhận -> Đang giao -> Đã giao.
- [ ] **Chức năng Hủy đơn hàng:** Nút "Hủy đơn hàng" (chỉ hiển thị khi đơn hàng ở trạng thái `PENDING`). Khi click hiển thị popup xác nhận lý do hủy, gọi API cancel order và cập nhật lại giao diện.

### 2.7. Nhóm Trang Quản trị & Người bán MVP (Admin & Seller MVP)
- [ ] **`AdminDashboard`:** Xây dựng giao diện quản trị cơ bản với 2 Tab chính:
  - [ ] Tab Duyệt Seller: Danh sách Seller đang ở trạng thái chờ duyệt, nút "Approve" chuyển sang `ACTIVE`.
  - [ ] Tab Duyệt Sản phẩm: Danh sách sản phẩm mới đăng ở trạng thái `PENDING`, nút "Approve" chuyển sang `PUBLISHED`.
- [ ] **`SellerDashboard`:** Màn hình quản lý của Người bán. Xây dựng form đăng tải sản phẩm mới (Tên, danh mục, giá gốc, giá bán, số lượng tồn kho, thuộc tính màu/size, upload ảnh cơ bản).

### 2.8. Nhóm Trang Quản lý Hồ sơ & Sổ địa chỉ (User & Store Profile)
- [ ] **`UserProfilePage`:** Trang quản lý hồ sơ người dùng với cấu trúc Tab trực quan:
  - [ ] `ProfileTab`: Biểu mẫu hiển thị và cập nhật thông tin cá nhân (Họ tên, Số điện thoại, Ngày sinh, Giới tính, Upload Avatar) và form Thay đổi mật khẩu.
  - [ ] `AddressBookTab`: Danh sách các thẻ địa chỉ đã lưu. Tích hợp nút "Thêm địa chỉ mới", modal form nhập liệu, nút sửa/xóa và cờ chọn "Đặt làm địa chỉ mặc định".
  - [ ] `StoreProfileTab` (chỉ hiển thị cho Người bán): Biểu mẫu cập nhật thông tin gian hàng (`storeName`, `gstNumber`, thông tin ngân hàng `bankAccount`).

---

## 🧪 PHẦN 3: KIỂM THỬ TÍCH HỢP & NGHIỆM THU (INTEGRATION TESTING & DEMO)

- [ ] **3.1. Kiểm thử Luồng Khách vãng lai (Guest):** Truy cập `HomePage`, gõ tìm kiếm "Áo thun", lọc theo giá/màu sắc/rating, nhấp vào xem chi tiết sản phẩm. Click "Thêm vào giỏ", kiểm tra hệ thống có tự động chuyển hướng sang trang Login hay không.
- [ ] **3.2. Kiểm thử Luồng Xác thực (Auth):** Đăng ký tài khoản mới, kiểm tra Redis lưu OTP, kiểm tra email nhận OTP, nhập OTP kích hoạt thành công. Đăng nhập và kiểm tra JWT lưu chính xác trong Storage.
- [ ] **3.3. Kiểm thử Luồng Giỏ hàng & Đặt hàng COD:** Thêm 2 sản phẩm vào giỏ, kiểm tra tính toán tổng tiền. Vào Checkout, nhập địa chỉ, chọn COD, đặt hàng thành công. Kiểm tra DB xem tồn kho `stock` bị trừ đúng số lượng và giỏ hàng được làm sạch.
- [ ] **3.4. Kiểm thử Luồng Quản lý & Hủy đơn:** Vào Lịch sử đơn hàng, xem Order Stepper. Click "Hủy đơn hàng", kiểm tra DB xem trạng thái chuyển sang `CANCELLED` và tồn kho `stock` được hoàn lại đầy đủ.
- [ ] **3.5. Kiểm thử Luồng Quản trị (Admin Ops):** Admin đăng nhập, vào Dashboard duyệt 1 sản phẩm `PENDING` sang `PUBLISHED`. Quay lại trang chủ Khách hàng kiểm tra xem sản phẩm vừa duyệt đã xuất hiện trên lưới sản phẩm hay chưa.
- [ ] **3.6. Kiểm thử Luồng Quản lý Hồ sơ & Sổ địa chỉ (Profile & Address Book):** Đăng nhập Customer, vào `UserProfilePage`, cập nhật SĐT và thêm 1 địa chỉ mặc định mới. Vào `CheckoutPage` kiểm tra địa chỉ mặc định tự động điền sẵn. Đăng nhập Seller, vào tab thông tin gian hàng cập nhật `storeName`, kiểm tra Admin/Customer xem thông tin shop mới phản ánh chính xác.
