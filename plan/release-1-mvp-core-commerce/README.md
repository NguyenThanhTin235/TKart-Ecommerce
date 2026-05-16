# Kế hoạch Phát hành Release 1: MVP Core Commerce

## 1. Mục tiêu & Phạm vi (Objectives & Scope)

**Release 1 (MVP Core Commerce)** tập trung xây dựng nền tảng cốt lõi của hệ thống TKart, đảm bảo hoàn thành một vòng đời mua bán hàng hóa cơ bản từ bước khám phá, tìm kiếm sản phẩm cho đến đặt hàng với phương thức thanh toán Tiền mặt khi nhận hàng (COD).

### Các mục tiêu chính:
*   **Khách hàng (Customer):** Có thể đăng ký tài khoản (xác thực OTP), đăng nhập, duyệt sản phẩm trên trang chủ, tìm kiếm và lọc sản phẩm nâng cao đa tiêu chí, xem/cập nhật hồ sơ cá nhân và sổ địa chỉ, thêm vào giỏ hàng, tiến hành Checkout bằng COD, xem lịch sử đơn hàng và hủy đơn khi cần.
*   **Khám phá & Tìm kiếm (Discovery & Search):** Cung cấp công cụ tìm kiếm từ khóa mạnh mẽ kết hợp bộ lọc nâng cao (danh mục 3 cấp, khoảng giá, % giảm giá, thuộc tính màu sắc/kích cỡ, đánh giá sao) và cơ chế sắp xếp, phân trang tối ưu.
*   **Hồ sơ Người dùng (User Profile):** Cung cấp khả năng quản lý thông tin cá nhân, thay đổi mật khẩu, quản lý sổ địa chỉ (Address Book) cho Khách hàng và thông tin gian hàng (Store Profile) cho Người bán.
*   **Người bán (Seller):** Có thể cập nhật hồ sơ gian hàng và đăng tải các sản phẩm cơ bản lên hệ thống chờ kiểm duyệt.
*   **Quản trị viên (Admin):** Có thể phê duyệt tài khoản Seller và kiểm duyệt sản phẩm mới đăng để đảm bảo tính hợp lệ của dữ liệu trên sàn.
*   **Khách vãng lai (Guest):** Bị ràng buộc chặt chẽ, chỉ được xem thông tin công khai (Trang chủ, tìm kiếm, lọc, chi tiết sản phẩm) và bị chặn khi thực hiện thao tác yêu cầu định danh (thêm giỏ hàng, checkout, quản lý profile).

---

## 2. Danh sách User Story & Use Case

| ID User Story | ID Use Case | Tên Chức năng / Mô tả | EPIC liên quan | Độ ưu tiên |
| :--- | :--- | :--- | :--- | :--- |
| `US-02.1` | UC02 | Đăng ký tài khoản Khách hàng / Người bán | EPIC 2 (Identity & Access) | Must Have |
| `US-02.2` | UC02 | Xác thực OTP qua Email (hiệu lực 5 phút) | EPIC 2 (Identity & Access) | Must Have |
| `US-02.3` | UC02 | Điều hướng biểu mẫu sau đăng ký thành công | EPIC 2 (Identity & Access) | Must Have |
| `US-02.4` | UC02b | Quản lý thông tin cá nhân & Sổ địa chỉ Khách hàng | EPIC 2 (Identity & Access) | Must Have |
| `US-02.5` | UC02b | Quản lý Hồ sơ & Gian hàng Người bán (Store Profile) | EPIC 2 (Identity & Access) | Must Have |
| `US-27.1` | UC27 | Đăng nhập truyền thống bằng Email & Mật khẩu | EPIC 2 (Identity & Access) | Must Have |
| `US-28.1` | UC28 | Đăng xuất & Bảo vệ các Route riêng tư (Private Routes) | EPIC 2 (Identity & Access) | Must Have |
| `US-01.1` | UC01 | Hiển thị Trang chủ (Banner, Deals, Danh mục nổi bật) | EPIC 1 (Discovery) | Must Have |
| `US-01.2a` | UC01 | Tìm kiếm sản phẩm theo từ khóa (Tên, mô tả sản phẩm) | EPIC 1 (Discovery) | Must Have |
| `US-01.2b` | UC01 | Lọc sản phẩm nâng cao (Danh mục 3 cấp, giá min/max, % giảm, màu/size, rating) | EPIC 1 (Discovery) | Must Have |
| `US-01.2c` | UC01 | Sắp xếp kết quả (Giá tăng/giảm, mới nhất, bán chạy) & Phân trang | EPIC 1 (Discovery) | Must Have |
| `US-01.3` | UC01 | Xem chi tiết sản phẩm (Thông tin, ảnh, thuộc tính, sản phẩm liên quan) | EPIC 1 (Discovery) | Must Have |
| `US-01.4` | UC01 | Ràng buộc quyền truy cập của Khách vãng lai (Guest) | EPIC 1 (Discovery) | Must Have |
| `US-01.5` | UC01 | Hiển thị các Trang tĩnh (FAQ, Chính sách bảo mật/hoàn tiền) | EPIC 1 (Discovery) | Should Have |
| `US-03.1` | UC03 | Thêm sản phẩm vào Giỏ hàng cá nhân | EPIC 3 (Cart & Checkout) | Must Have |
| `US-03.2` | UC03 | Xem danh sách sản phẩm trong Giỏ hàng | EPIC 3 (Cart & Checkout) | Must Have |
| `US-03.3` | UC03 | Cập nhật số lượng sản phẩm trong Giỏ hàng | EPIC 3 (Cart & Checkout) | Must Have |
| `US-03.4` | UC03 | Xóa sản phẩm khỏi Giỏ hàng | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.5` | UC05 | Khung luồng Checkout cơ bản (Nhập địa chỉ giao hàng) | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.7` | UC05 | Thanh toán COD (Thanh toán khi nhận hàng) | EPIC 3 (Cart & Checkout) | Must Have |
| `US-05.8` | UC05 | Xử lý hậu thanh toán (Trừ tồn kho, làm sạch giỏ hàng) | EPIC 3 (Cart & Checkout) | Must Have |
| `US-06.1` | UC06 | Xem danh sách & Lịch sử đơn hàng cá nhân | EPIC 3 (Cart & Checkout) | Must Have |
| `US-06.2` | UC06 | Theo dõi chi tiết tiến trình đơn hàng (Order Stepper) | EPIC 3 (Cart & Checkout) | Must Have |
| `US-06.3` | UC06 | Hủy đơn hàng (khi ở trạng thái PENDING) & Hoàn tồn kho | EPIC 3 (Cart & Checkout) | Must Have |
| `US-15.1` | UC15 | Người bán đăng tải sản phẩm mới (trạng thái PENDING) | EPIC 4 (Seller & Admin) | Must Have |
| `US-19.1` | UC19 | Admin phê duyệt tài khoản Người bán (ACTIVE) | EPIC 4 (Seller & Admin) | Must Have |
| `US-20.1` | UC20 | Admin kiểm duyệt sản phẩm (PUBLISHED) | EPIC 4 (Seller & Admin) | Must Have |

---

## 3. Phân rã Công việc Chi tiết (Work Breakdown Structure - WBS)

### 3.1. Phân hệ Cơ sở dữ liệu (Database & Schema)
*   **DB-1.1:** Chuẩn hóa Lược đồ Collection `users` trên MongoDB (hỗ trợ các trường `roles`, `isVerified`, `gstNumber`, `storeName`, `bankAccount`, `addresses`).
*   **DB-1.2:** Chuẩn hóa Lược đồ Collection `products` (trạng thái `PENDING`, `PUBLISHED`, `REJECTED`, thông tin giá `mrp`, `sellingPrice`, `stock`, cấu trúc `attributes` chứa màu sắc/kích cỡ, `rating`).
*   **DB-1.3:** Thiết lập Chỉ mục (Indexing) tối ưu cho truy vấn tìm kiếm: Tạo Text Index trên trường `name` và `description`; tạo Compound Indexes trên các trường `categoryId`, `sellingPrice`, `rating`, `attributes.name`, `attributes.value` để tăng tốc độ lọc.
*   **DB-1.4:** Chuẩn hóa Lược đồ Collection `carts` (lưu trữ danh sách `cartItems` kèm `productId`, `sellerId`, `quantity`).
*   **DB-1.5:** Chuẩn hóa Lược đồ Collection `orders` cho luồng COD (trạng thái `PENDING`, `CONFIRMED`, `CANCELLED`, phương thức thanh toán `COD`).
*   **DB-1.6:** Khởi tạo dữ liệu mẫu (Data Seeding) cho các tài khoản Admin, Seller (đã duyệt), danh mục 3 cấp và danh sách sản phẩm mẫu đa dạng thuộc tính để phục vụ kiểm thử bộ lọc.

### 3.2. Phân hệ Backend (Spring Boot APIs & Security)
*   **BE-1.1:** Cấu hình Spring Security & Bộ lọc JWT Authentication Filter (Bảo vệ các endpoint `/api/v1/cart/**`, `/api/v1/orders/**`, `/api/v1/seller/**`, `/api/v1/admin/**`, `/api/v1/profile/**`).
*   **BE-1.2:** Triển khai `AuthController` & `AuthService` (Đăng ký, băm mật khẩu BCrypt, sinh/xác thực OTP qua Redis, Đăng nhập cấp phát JWT).
*   **BE-1.3:** Triển khai `ProductPublicController` & `ProductService` với endpoint `GET /api/v1/public/products` hỗ trợ đầy đủ các tham số truy vấn:
    *   `keyword` (tìm kiếm từ khóa).
    *   `category` (ID danh mục cấp 1, 2 hoặc 3).
    *   `minPrice`, `maxPrice` (khoảng giá).
    *   `minDiscount` (% giảm giá tối thiểu).
    *   `color`, `size` (thuộc tính sản phẩm).
    *   `minRating` (số sao đánh giá tối thiểu từ 1-5).
    *   `sortBy` (`price`, `createdAt`, `sales`), `sortDir` (`asc`, `desc`).
    *   `page`, `limit` (phân trang).
*   **BE-1.4:** Triển khai `ProductCustomRepositoryImpl` sử dụng Spring Data MongoDB `MongoTemplate` và `Criteria API` để xây dựng các truy vấn động (Dynamic Filtering & Aggregation) kết hợp nhiều điều kiện lọc cùng lúc một cách hiệu quả.
*   **BE-1.5:** Triển khai `CartController` & `CartService` (CRUD giỏ hàng trong MongoDB, kiểm tra tồn kho real-time khi thêm vào giỏ).
*   **BE-1.6:** Triển khai `CheckoutController` & `OrderService` (Xử lý đặt hàng COD, tính toán tổng tiền cơ bản, trừ tồn kho `stock` và chuyển trạng thái giỏ hàng).
*   **BE-1.7:** Triển khai `OrderController` (Lấy danh sách đơn hàng của User, cập nhật trạng thái hủy đơn và cộng lại tồn kho).
*   **BE-1.8:** Triển khai `AdminApprovalController` (API cho phép Admin duyệt Seller từ `PENDING_VERIFICATION` sang `ACTIVE` và duyệt Product từ `PENDING` sang `PUBLISHED`).
*   **BE-1.9:** Triển khai `UserProfileController` & `UserService` (Xem/cập nhật thông tin cá nhân, thay đổi mật khẩu, CRUD danh sách địa chỉ `addresses` phục vụ Checkout, cập nhật thông tin cửa hàng Người bán `storeName`, `bankAccount`).

### 3.3. Phân hệ Frontend (React, TypeScript & Redux Toolkit)
*   **FE-1.1:** Thiết lập cấu trúc Redux Store (`authSlice`, `cartSlice`, `productSlice`, `orderSlice`, `profileSlice`) và cấu hình Axios Interceptors (tự động đính kèm Bearer JWT, xử lý lỗi 401/403).
*   **FE-1.2:** Xây dựng các Trang Xác thực: `RegisterPage`, `OtpVerificationPage`, `LoginPage`.
*   **FE-1.3:** Xây dựng Giao diện Khám phá & Trang chủ: `HomePage` (Banner tĩnh, danh sách sản phẩm nổi bật, danh mục hot).
*   **FE-1.4:** Xây dựng Giao diện Tìm kiếm & Lọc (`SearchFilterPage`):
    *   `SearchBar`: Thanh tìm kiếm đính kèm trên Header, hỗ trợ gõ từ khóa và ấn Enter hoặc click icon kính lúp.
    *   `CategorySidebar`: Cây danh mục 3 cấp cho phép click chọn để lọc nhanh.
    *   `FilterSidebar`: Bộ lọc nâng cao chứa `PriceRangeSlider` (thanh trượt giá), `AttributeFilter` (checkbox màu sắc, kích cỡ), `RatingFilter` (chọn từ 1 đến 5 sao).
    *   `SortDropdown`: Dropdown chọn chế độ sắp xếp (Giá thấp-cao, Giá cao-thấp, Mới nhất, Bán chạy).
    *   `ProductGrid`: Lưới hiển thị danh sách thẻ sản phẩm (`ProductCard`) đính kèm badge % giảm giá.
    *   `Pagination`: Thanh phân trang chuyển trang mượt mà.
    *   `EmptyState`: Màn hình thông báo trực quan "Không tìm thấy sản phẩm phù hợp" kèm nút "Xóa bộ lọc".
*   **FE-1.5:** Xây dựng Giao diện Chi tiết Sản phẩm (`ProductDetailPage`): Hiển thị ảnh, thông tin giá, mô tả, chọn thuộc tính (màu/size) và danh sách "Sản phẩm liên quan" cùng danh mục.
*   **FE-1.6:** Xây dựng Giao diện Giỏ hàng & Thanh toán: `CartPage` (hiển thị danh sách, tính tổng tiền tạm tính), `CheckoutPage` (biểu mẫu địa chỉ, chọn phương thức COD).
*   **FE-1.7:** Xây dựng Giao diện Đơn hàng: `OrderHistoryPage`, `OrderDetailModal` (tích hợp Order Stepper theo dõi tiến trình), xử lý nút "Hủy đơn hàng".
*   **FE-1.8:** Xây dựng Giao diện Quản trị cơ bản: `AdminDashboard` (Tab duyệt Seller và Tab duyệt Sản phẩm).
*   **FE-1.9:** Xây dựng Giao diện Người bán cơ bản: `SellerDashboard` (Biểu mẫu đăng tải sản phẩm mới kèm upload ảnh cơ bản).
*   **FE-1.10:** Xây dựng Giao diện Quản lý Hồ sơ (`UserProfilePage`):
    *   `ProfileTab`: Biểu mẫu cập nhật thông tin cá nhân (Họ tên, SĐT, ngày sinh, avatar) và đổi mật khẩu.
    *   `AddressBookTab`: Quản lý danh sách địa chỉ (Thêm, Sửa, Xóa, Đặt làm mặc định).
    *   `StoreProfileTab` (dành cho Người bán): Cập nhật Tên gian hàng, Mã số thuế, Thông tin tài khoản ngân hàng.

---

## 4. Quy tắc Nghiệp vụ & Ràng buộc (Business Rules & NFRs)

*   **BR01-1 (QĐ_KH1):** Khách vãng lai (Guest) không được phép truy cập giỏ hàng, thanh toán hoặc quản lý profile. Khi nhấp vào các nút chức năng này, hệ thống phải lưu trạng thái hành động và chuyển hướng sang trang Đăng nhập.
*   **BR01-2 (QĐ_KVL2 / QĐ_KH3):** Bộ lọc tìm kiếm phải hoạt động kết hợp (AND logic giữa các tiêu chí: Danh mục + Giá + Thuộc tính + Rating). Chỉ các sản phẩm ở trạng thái `PUBLISHED` mới được phép xuất hiện trong kết quả tìm kiếm.
*   **BR01-3 (QĐ_KVL3):** Khi chọn danh mục cha (Level 1 hoặc Level 2), hệ thống tự động đệ quy lấy toàn bộ sản phẩm thuộc các danh mục con (Level 3) trực thuộc bên trong. Mục "Sản phẩm liên quan" trên trang chi tiết bắt buộc phải lấy các sản phẩm cùng danh mục Level 3.
*   **BR02-1:** Mã OTP đăng ký chỉ có hiệu lực trong 5 phút và chỉ được thử tối đa 3 lần trước khi bị khóa tạm thời.
*   **BR02-2 (QĐ_PF1):** Khách hàng bắt buộc phải có ít nhất 1 địa chỉ giao hàng (hoặc nhập trực tiếp khi Checkout) mới được phép hoàn tất đơn hàng COD. Địa chỉ được chọn làm mặc định trong Address Book sẽ tự động điền vào biểu mẫu Checkout.
*   **BR03-1 (QĐ_KH2):** Số lượng sản phẩm thêm vào giỏ hàng không được vượt quá số lượng tồn kho hiện tại (`stock`) của sản phẩm đó.
*   **BR05-4 (QĐ_KH5):** Đơn hàng COD mặc định ở trạng thái `PENDING` sau khi đặt thành công. Khách hàng chỉ được phép hủy đơn khi đơn hàng chưa chuyển sang trạng thái `CONFIRMED` hoặc `SHIPPED`.
*   **BR19-1 (QĐ_AD1):** Chỉ Seller có trạng thái `ACTIVE` mới được đăng sản phẩm. Sản phẩm mới đăng mặc định ở trạng thái `PENDING` (chưa hiển thị cho khách hàng).
*   **NFR01-1:** Tốc độ tải trang chủ và trang chi tiết sản phẩm phải dưới 2 giây.
*   **NFR01-2:** Tốc độ truy vấn tìm kiếm và lọc phức tạp phải đạt dưới 500ms thông qua việc đánh chỉ mục (Text Index, Compound Index) tối ưu trên MongoDB.
*   **NFR27-1 & NFR27-2:** Mật khẩu phải được băm bằng thuật toán BCrypt. Phiên làm việc quản lý hoàn toàn bằng JWT không lưu trạng thái trên server (Stateless).

---

## 5. Tiêu chí Nghiệm thu & Kịch bản Demo (Vertical Slice Demo)

Cuối Release 1, hệ thống phải chạy mượt mà các kịch bản trình diễn (Demo) toàn trình dưới đây, đặc biệt tập trung vào luồng Tìm kiếm, Lọc sản phẩm, Quản lý Hồ sơ và Đặt hàng COD:

### Kịch bản 1: Khám phá, Tìm kiếm & Lọc sản phẩm nâng cao (Guest / Customer)
1.  **Bước 1 (Tìm kiếm từ khóa):** Người dùng truy cập `HomePage`, nhập từ khóa "Áo thun" vào thanh tìm kiếm và nhấn Enter. Hệ thống chuyển sang `SearchFilterPage` hiển thị danh sách các sản phẩm có chứa từ "Áo thun" trong tên hoặc mô tả.
2.  **Bước 2 (Lọc theo Danh mục & Giá):** Tại sidebar bộ lọc, người dùng nhấp chọn danh mục "Thời trang nam" (Level 1), kéo thanh trượt giá từ `100,000đ` đến `300,000đ`. Kết quả trên lưới sản phẩm tự động cập nhật (không cần reload trang), chỉ hiển thị các áo thun nam trong tầm giá 100k - 300k.
3.  **Bước 3 (Lọc thuộc tính & Rating):** Người dùng tiếp tục tích chọn checkbox màu "Đen", size "L" và chọn đánh giá "Từ 4 sao trở lên". Hệ thống lọc chính xác các sản phẩm áo thun nam màu đen, size L, giá 100k-300k và có rating >= 4 sao.
4.  **Bước 4 (Sắp xếp & Phân trang):** Người dùng chọn chế độ sắp xếp "Giá: Thấp đến Cao". Lưới sản phẩm đảo trật tự hiển thị các sản phẩm giá rẻ nhất lên đầu. Người dùng cuộn xuống cuối trang và nhấp sang "Trang 2", hệ thống tải mượt mà danh sách tiếp theo.
5.  **Bước 5 (Kiểm thử Empty State):** Người dùng gõ một từ khóa không tồn tại (VD: "xyz123999"). Hệ thống hiển thị ngay màn hình thông báo "Không tìm thấy sản phẩm phù hợp" kèm hình minh họa trực quan và nút "Xóa bộ lọc". Khi nhấn "Xóa bộ lọc", hệ thống reset về danh sách mặc định.

### Kịch bản 2: Quản lý Hồ sơ cá nhân & Sổ địa chỉ (Customer / Seller Profile)
1.  **Bước 1 (Cập nhật Profile):** Khách hàng đăng nhập, truy cập trang `UserProfilePage`. Tại tab Thông tin cá nhân, khách hàng thay đổi ảnh đại diện và cập nhật số điện thoại. Nhấp "Lưu thay đổi", hệ thống thông báo thành công và cập nhật real-time trên Header.
2.  **Bước 2 (Quản lý Sổ địa chỉ):** Khách hàng sang tab Sổ địa chỉ, nhấp "Thêm địa chỉ mới", điền thông tin và tích chọn "Đặt làm địa chỉ mặc định". Danh sách địa chỉ hiển thị thẻ địa chỉ mới kèm badge "Mặc định".
3.  **Bước 3 (Hồ sơ Người bán):** Người bán đăng nhập, truy cập tab Thông tin Gian hàng (Store Profile). Cập nhật Tên cửa hàng thành "TKart Premium Store" và điền thông tin tài khoản ngân hàng. Khi Admin vào xem chi tiết Người bán, các thông tin mới được phản ánh chính xác.

### Kịch bản 3: Đặt hàng COD toàn trình & Quản trị
1.  **Bước 1 (Ràng buộc Guest):** Khách vãng lai nhấp chọn nút "Thêm vào giỏ hàng" tại một sản phẩm. Hệ thống lập tức hiển thị thông báo yêu cầu đăng nhập và chuyển hướng sang màn hình `LoginPage`.
2.  **Bước 2 (Đăng ký & Xác thực):** Khách hàng chọn "Đăng ký tài khoản", điền thông tin hợp lệ. Hệ thống gửi mã OTP 6 số qua Email. Khách hàng nhập đúng OTP, tài khoản được kích hoạt thành công.
3.  **Bước 3 (Đăng nhập & Giỏ hàng):** Khách hàng đăng nhập bằng Email/Mật khẩu vừa tạo. Hệ thống đưa trở lại trang chi tiết sản phẩm. Khách hàng thêm sản phẩm vào giỏ, truy cập `CartPage` và thấy số lượng, giá tiền hiển thị chính xác.
4.  **Bước 4 (Checkout COD tự động điền địa chỉ):** Khách hàng nhấp "Tiến hành Thanh toán". Hệ thống tự động điền sẵn địa chỉ mặc định từ Sổ địa chỉ (Address Book). Khách hàng chọn phương thức "Thanh toán khi nhận hàng (COD)" và nhấp "Đặt hàng". Hệ thống thông báo đặt hàng thành công, trừ số lượng tồn kho trong DB và làm sạch giỏ hàng.
5.  **Bước 5 (Quản lý & Hủy đơn):** Khách hàng truy cập `OrderHistoryPage`, thấy đơn hàng mới ở trạng thái `PENDING`. Khách hàng nhấp "Hủy đơn hàng", chọn lý do. Trạng thái đơn hàng cập nhật thành `CANCELLED`, số lượng tồn kho được hoàn lại chính xác trên DB.
6.  **Bước 6 (Admin Ops):** Admin đăng nhập vào `AdminDashboard`, kiểm tra danh sách Seller và Product đang ở trạng thái `PENDING`. Admin nhấp "Approve", các sản phẩm này lập tức chuyển sang `PUBLISHED` và xuất hiện trên `HomePage` / `SearchFilterPage` của Khách hàng.
