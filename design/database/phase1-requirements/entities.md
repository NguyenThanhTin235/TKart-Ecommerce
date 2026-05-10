# Phase 1: Requirements — All Entities & Attributes

> Truy vết từ SRS (`requirements_final.md`), User Stories (`UserStory.md`), Sequence Diagrams (`uml/sequence/`).

---

## Entity 1: User
> **Mô tả**: Tài khoản người dùng hệ thống, đại diện cho cả 3 vai trò (Customer, Seller, Admin).

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính tự sinh bởi MongoDB | — |
| 2 | fullName | String | ✅ | | Họ và tên đầy đủ | UC02 (đăng ký), UC10 (cập nhật hồ sơ), US-10.1 AC1 |
| 3 | email | String | ✅ | ✅ | Email đăng nhập (Read-only sau đăng ký) | UC02, UC27, BR10-1, US-10.1 AC2 |
| 4 | password | String | | | Mật khẩu mã hóa BCrypt (null nếu OAuth2) | UC27, UC29, NFR27-2, NFR29-1, BR29-1 |
| 5 | phone | String | | | Số điện thoại liên hệ | UC10, US-10.1 AC3 |
| 6 | role | Enum(Role) | ✅ | | ROLE_CUSTOMER / ROLE_SELLER / ROLE_ADMIN | UC27 bước 5, BR27-2, US-27.1 AC1 |
| 7 | status | Enum(AccountStatus) | ✅ | | ACTIVE / SUSPENDED / BANNED (default: ACTIVE) | UC19 (Admin quản lý), UC24, US-19 |
| 8 | authProvider | Enum(AuthProvider) | ✅ | | LOCAL / GOOGLE / FACEBOOK (default: LOCAL) | UC27 alt 1b, NFR27-3, US-27.3 AC1 |
| 9 | avatar | String | | | URL ảnh đại diện (Cloudinary) | UC10, US-14.2 |
| 10 | createdAt | DateTime | ✅ | | Thời điểm tạo tài khoản | BaseDocument |
| 11 | updatedAt | DateTime | ✅ | | Thời điểm cập nhật gần nhất | BaseDocument |

---

## Entity 2: Address
> **Mô tả**: Sổ địa chỉ giao hàng. Mỗi Customer có nhiều địa chỉ, 1 địa chỉ mặc định.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | | FK tham chiếu đến User sở hữu | UC11, US-11.1 |
| 3 | recipientName | String | ✅ | | Tên người nhận hàng | UC11, US-11.2 |
| 4 | phone | String | ✅ | | SĐT người nhận | UC11, US-11.2 |
| 5 | province | String | ✅ | | Tỉnh/Thành phố (từ API hành chính) | UC11, NFR11-1, US-11.2 AC1 |
| 6 | district | String | ✅ | | Quận/Huyện | UC11, NFR11-1 |
| 7 | ward | String | ✅ | | Phường/Xã | UC11, NFR11-1 |
| 8 | detail | String | ✅ | | Số nhà, đường, tòa nhà | UC11 |
| 9 | isDefault | Boolean | ✅ | | Địa chỉ mặc định (true/false) | UC05 bước 3, US-05.5 AC1 |
| 10 | createdAt | DateTime | ✅ | | | BaseDocument |
| 11 | updatedAt | DateTime | ✅ | | | BaseDocument |

> **Ràng buộc C17**: Mỗi User chỉ được có **tối đa 1 địa chỉ `isDefault=true`**. Khi đặt 1 địa chỉ mới làm mặc định, hệ thống phải dùng **atomic update / transaction** để gỡ `isDefault` của địa chỉ cũ trước khi set mới.
>
> **Index gợi ý**: `addresses(userId, isDefault)` — hỗ trợ query nhanh địa chỉ mặc định.

---

## Entity 3: OtpToken
> **Mô tả**: Mã OTP 6 chữ số dùng 1 lần, tự động xóa sau 5 phút (TTL index).

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | email | String | ✅ | | Email nhận OTP | UC02, UC27 alt 1a, UC29 |
| 3 | code | String | ✅ | | Mã 6 chữ số ngẫu nhiên | UC02 BR02-1, US-02.1 AC1 |
| 4 | type | Enum(OtpType) | ✅ | | REGISTER / LOGIN / RESET_PASSWORD | UC02, UC27, UC29 |
| 5 | expiresAt | DateTime | ✅ | | Thời điểm hết hạn (now + 5 phút) | BR27-1, US-27.2 AC3 |
| 6 | used | Boolean | ✅ | | Đã sử dụng chưa (default: false) | BR27-1 "dùng 1 lần" |
| 7 | createdAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 4: Seller
> **Mô tả**: Hồ sơ gian hàng mở rộng từ User (1 User = tối đa 1 Seller).

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | ✅ | FK 1-1 đến User | UC14, US-14.1 |
| 3 | shopName | String | ✅ | | Tên gian hàng | UC14, US-14.1 AC1 |
| 4 | gstNumber | String | ✅ | | Mã số thuế doanh nghiệp | UC14 BR14-1, UC19, US-14.1 AC2 |
| 5 | bankDetails | Embedded{} | ✅ | | Sub-document: {accountName, accountNumber, bankName} | UC14 BR14-1, CT_AD1 |
| 6 | pickupAddress | Embedded{} | ✅ | | Sub-document: {province, district, ward, detail, phone} | UC14, UC16 sequence bước 5 |
| 7 | logo | String | | | URL logo gian hàng (Cloudinary) | UC14, US-14.2 |
| 8 | banner | String | | | URL banner gian hàng (Cloudinary) | UC14, US-14.2 |
| 9 | status | Enum(SellerStatus) | ✅ | | PENDING_VERIFICATION → ACTIVE → SUSPENDED → BANNED | UC19, QĐ_AD1, US-19 |
| 10 | createdAt | DateTime | ✅ | | | BaseDocument |
| 11 | updatedAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 5: Category
> **Mô tả**: Danh mục sản phẩm phân cấp 3 cấp (Level 1 → 2 → 3). VD: `Men → Topwear → T-Shirt`.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | name | String | ✅ | | Tên danh mục (VD: "T-Shirt") | UC01, QĐ_AD2 |
| 3 | slug | String | ✅ | ✅ | URL-friendly name (VD: "t-shirt") | UC01 (SEO) |
| 4 | level | Integer (1/2/3) | ✅ | | Cấp độ trong cây danh mục | QĐ_AD2, QĐ_KVL2, BR15-1 |
| 5 | parentId | String (ref → Category) | | | Self-reference đến danh mục cha (null nếu Level 1) | QĐ_AD2 "3 cấp" |
| 6 | image | String | | | URL hình ảnh danh mục (Cloudinary) | UC21, US-21 |
| 7 | createdAt | DateTime | ✅ | | | BaseDocument |
| 8 | updatedAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 6: Product
> **Mô tả**: Sản phẩm do Seller đăng bán. Quản lý biến thể qua **Embedded Variants**. Mặc định `PENDING` chờ duyệt.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | sellerId | String (ref → Seller) | ✅ | | FK đến Seller sở hữu | UC15, US-15 |
| 3 | title | String | ✅ | | Tên sản phẩm | UC01, UC15, QĐ_KVL3 |
| 4 | description | String | ✅ | | Mô tả chi tiết | UC01, UC15 |
| 5 | categoryL3Id | String (ref → Category) | ✅ | | FK đến Category Level 3 | BR15-1, QĐ_KVL3, US-01.3 AC2 |
| 6 | images | List\<String\> | ✅ | | Ảnh chính sản phẩm (Cloudinary, max 5) | UC15, QĐ_KVL3, NFR20-1 |
| 7 | variants | List\<ProductVariant\> | ✅ | | **Embedded array** các biến thể | UC15, US-03.2 AC2 |
| 8 | minSellingPrice | Long | ✅ | | Giá bán thấp nhất (computed) | QĐ_KVL3, QĐ_KVL2 |
| 9 | maxDiscountPercent | Integer | ✅ | | % giảm giá cao nhất (computed) | QĐ_KVL3, QĐ_KVL2 |
| 10 | totalQuantity | Integer | ✅ | | Tổng tồn kho (computed) | UC03, US-03.1 AC2 |
| 11 | status | Enum(ProductStatus) | ✅ | | PENDING / PUBLISHED / REJECTED (default: PENDING) | BR15-2, QĐ_AD11, UC20 |
| 12 | createdAt | DateTime | ✅ | | | BaseDocument |
| 13 | updatedAt | DateTime | ✅ | | | BaseDocument |

### Embedded: ProductVariant
| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả | Truy vết |
|---|-----------|--------------|---------|-------|----------|
| 1 | sku | String | ✅ | Mã SKU biến thể | UC15 |
| 2 | attributes | Map\<String, String\> | ✅ | Cặp key-value động mô tả biến thể | UC15, QĐ_KVL2 |
| 3 | mrpPrice | Long | ✅ | Giá niêm yết gốc | QĐ_KVL3 |
| 4 | sellingPrice | Long | ✅ | Giá bán thực tế | QĐ_KVL3 |
| 5 | discountPercent | Integer | ✅ | % giảm giá | QĐ_KVL3 |
| 6 | quantity | Integer | ✅ | Tồn kho biến thể | UC15, UC03 |
| 7 | images | List\<String\> | | | Ảnh riêng biến thể | UC15 |

---

## Entity 7: Cart
> **Mô tả**: Giỏ hàng. Mỗi Customer có đúng 1 giỏ (1-1). Items được **embed** bên trong.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | ✅ | FK 1-1 đến User sở hữu | BR03-1, US-03.4 AC1 |
| 3 | cartItems | List\<CartItem\> | ✅ | | **Embedded array** các sản phẩm | UC03, US-03.1 |
| 4 | totalSellingPrice | Long | ✅ | | Tổng giá bán | UC03 |
| 5 | totalItems | Integer | ✅ | | Tổng số lượng sản phẩm | UC03 |
| 6 | createdAt | DateTime | ✅ | | | BaseDocument |
| 7 | updatedAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 8: Wishlist
> **Mô tả**: Danh sách yêu thích.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | ✅ | FK 1-1 đến User | BR04-1, US-04.1 |
| 3 | productIds | List\<String\> | ✅ | | Mảng Product IDs yêu thích | UC04 |
| 4 | createdAt | DateTime | ✅ | | | BaseDocument |
| 5 | updatedAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 9: Order
> **Mô tả**: Đơn hàng phụ (Sub-Order), tách theo Seller.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | | FK đến Customer | UC05, UC06 |
| 3 | sellerId | String (ref → Seller) | ✅ | | FK đến Seller | BR05-2 |
| 4 | paymentOrderId | String (ref → PaymentOrder) | ✅ | | FK đến lệnh thanh toán gộp | BR05-2 |
| 5 | orderItems | List\<OrderItem\> | ✅ | | **Embedded array** snapshot SP | UC05 |
| 6 | deliveryAddress | Embedded{} | ✅ | | Snapshot địa chỉ giao hàng | UC05 |
| 7 | totalSellingPrice | Long | ✅ | | Tổng giá bán của đơn phụ | UC05 |
| 8 | shippingFee | Long | ✅ | | Phí vận chuyển đơn phụ | UC05 |
| 9 | trackingId | String | | | Mã vận đơn | UC16, BR16-1 |
| 10 | shippingProvider | String | | | Tên ĐVVC | UC16 |
| 11 | trackingEvents | List\<TrackingEvent\> | | | **Embedded array** lịch sử vận chuyển | UC06, UC16 |
| 12 | status | Enum(OrderStatus) | ✅ | | Trạng thái vận chuyển & đổi trả (KHÔNG chứa REFUNDED) | UC06, UC16 |
| 13 | deliveredAt | DateTime | | | Thời điểm giao thành công | BR09-1 |
| 14 | refundedAt | DateTime | | | Thời điểm hoàn tiền thành công (tách khỏi status) | UC09, UC23 |
| 14 | createdAt | DateTime | ✅ | | | BaseDocument |
| 15 | updatedAt | DateTime | ✅ | | | BaseDocument |

### Embedded: TrackingEvent
| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả | Truy vết |
|---|-----------|--------------|---------|-------|----------|
| 1 | status | String | ✅ | Trạng thái đã chuẩn hóa (VD: "Đang vận chuyển") | UC16 |
| 2 | rawStatus | String | ✅ | Mã trạng thái gốc từ webhook ĐVVC (VD: "S6", "delivering") | UC16, Audit |
| 3 | carrierCode | String | ✅ | Mã ĐVVC (VD: "GHTK", "GRAB") — để truy vết khi provider đổi mã | UC16 |
| 4 | location | String | | | Vị trí/kho hiện tại | UC16 |
| 5 | timestamp | DateTime | ✅ | Thời điểm sự kiện | UC06 |

---

## Entity 10: PaymentOrder
> **Mô tả**: Lệnh thanh toán gộp.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | userId | String (ref → User) | ✅ | | FK đến Customer | UC05 |
| 3 | orderIds | List\<String\> | ✅ | | Danh sách Order IDs | BR05-2 |
| 4 | subtotal | Long | ✅ | | Tổng tiền hàng | UC05 |
| 5 | couponDiscount | Long | | | Giảm giá từ Coupon | UC05 |
| 6 | coinDiscount | Long | | | Giảm giá từ Xu | UC05 |
| 7 | finalPayment | Long | ✅ | | Số tiền cuối cùng | BR05-3 |
| 8 | paymentMethod | Enum(PaymentMethod) | ✅ | | COD / VNPAY / SEPAY / MOMO | UC05 |
| 9 | paymentStatus | Enum(PaymentStatus) | ✅ | | PENDING → SUCCESS / FAILED... | UC05 |
| 10 | transactionId | String | | | ID giao dịch từ cổng | UC05 |
| 11 | createdAt | DateTime | ✅ | | | BaseDocument |
| 12 | updatedAt | DateTime | ✅ | | | BaseDocument |

---

## Entity 11: Coupon
> **Mô tả**: Mã giảm giá do Admin phát hành.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Unique | Mô tả | Truy vết |
|---|-----------|--------------|---------|--------|-------|----------|
| 1 | id | String (ObjectId) | ✅ | ✅ | Khóa chính | — |
| 2 | code | String | ✅ | ✅ | Mã giảm giá | UC22 |
| 3 | discountPercent | Double | ✅ | | % giảm giá | UC22 |
| 4 | minOrderValue | Long | ✅ | | Đơn tối thiểu | UC22 |
| 5 | validFrom/To | DateTime | ✅ | | Thời gian hiệu lực | UC22 |
| 6 | isActive | Boolean | ✅ | | Trạng thái | UC22 |
| 7 | usageLimit | Integer | | | Giới hạn tổng số lượt dùng (null = không giới hạn) | UC22 |
| 8 | perUserLimit | Integer | | | Giới hạn số lần dùng mỗi user (default: 1) | UC22, C18 |
| 9 | usedCount | Integer | ✅ | | Số lần đã sử dụng | UC22 |

> **Quy tắc**: Mọi lần apply coupon vào checkout **bắt buộc** tạo `CouponRedemption` để phục vụ audit và chống gian lận.

## Entity 12: Deal
> **Mô tả**: Khuyến mãi theo danh mục.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả | Truy vết |
|---|-----------|--------------|---------|-------|----------|
| 1 | id | ObjectId | ✅ | Khóa chính | — |
| 2 | categoryId | Ref → Category | ✅ | Danh mục áp dụng | UC22 |
| 3 | discountPercent | Double | ✅ | % giảm giá | UC22 |
| 4 | title | String | ✅ | Tên chương trình | UC22 |
| 5 | validFrom/To | DateTime | ✅ | Thời hạn | UC22 |
| 6 | viewCount | Long | ✅ | Số lượt xem | — |
| 7 | isActive | Boolean | ✅ | Trạng thái | UC22 |

---

## Entity 13: CoinWallet
> **Mô tả**: Ví xu khách hàng.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả | Truy vết |
|---|-----------|--------------|---------|-------|----------|
| 1 | id | ObjectId | ✅ | Khóa chính | — |
| 2 | userId | Ref → User | ✅ | Chủ sở hữu | UC12 |
| 3 | balance | Long | ✅ | Số dư | UC12 |
| 4 | transactions | List\<Embedded\> | ✅ | Lịch sử biến động | UC12 |

---

## Entity 14: PlatformConfig
> **Mô tả**: Cấu hình hệ thống (Singleton).

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | coinEarnRate | Double | ✅ | Tỉ lệ tích xu |
| 2 | coinValueRate | Double | ✅ | Giá trị xu |
| 3 | platformFeePercent | Double | ✅ | % Phí sàn |

---

## Entity 15: Review
> **Mô tả**: Đánh giá sản phẩm.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | userId | Ref → User | ✅ | Người viết |
| 2 | productId | Ref → Product | ✅ | Sản phẩm |
| 3 | orderId | Ref → Order | ✅ | Đơn hàng |
| 4 | rating | Integer | ✅ | 1-5 sao |
| 5 | media | List\<String\> | | URL ảnh/video |

---

## Entity 16: ReturnRequest
> **Mô tả**: Yêu cầu trả hàng.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | orderId | Ref → Order | ✅ | Đơn hàng |
| 2 | reason | String | ✅ | Lý do |
| 3 | status | Enum | ✅ | Trạng thái |
| 4 | adminNote | String | | Phán quyết Admin |

---

## Entity 17: Transaction
> **Mô tả**: Giao dịch tài chính cho đối soát.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | sellerId | Ref → Seller | ✅ | Seller |
| 2 | type | Enum | ✅ | Loại giao dịch |
| 3 | amount | Long | ✅ | Số tiền |
| 4 | paymentOrderId| Ref → P.Order | ✅ | Link thanh toán |
| 5 | paymentMethod | Enum | ✅ | PTTT |
| 6 | gatewayTransactionId| String | | Mã cổng thanh toán |

---

## Entity 18: ChatRoom
> **Mô tả**: Phòng chat 1-1.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | customerId | Ref → User | ✅ | Khách hàng |
| 2 | sellerId | Ref → Seller | ✅ | Người bán |
| 3 | lastMessage | String | | Nội dung gần nhất |

---

## Entity 19: ChatMessage
> **Mô tả**: Tin nhắn.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | chatRoomId | Ref → ChatRoom| ✅ | Phòng chat (thống nhất naming với index) |
| 2 | senderId | Ref → User | ✅ | Người gửi |
| 3 | senderRole | Enum | ✅ | Vai trò người gửi |
| 4 | content | String | ✅ | Nội dung |

---

## Entity 20: HomepageConfig
> **Mô tả**: Cấu hình trang chủ.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | banners | List\<Embedded\> | ✅ | Banners |
| 2 | gridCategories| List\<Embedded\> | ✅ | Danh mục trang chủ |

---

## Entity 21: AuditLog
> **Mô tả**: Nhật ký hệ thống (Read-only).

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | actorId | Ref → User | ✅ | Người thực hiện |
| 2 | actorRole | Enum(Role) | ✅ | Vai trò tại thời điểm thực hiện |
| 3 | action | String | ✅ | Hành động (VD: "BAN_SELLER") |
| 4 | targetType | String | ✅ | Loại đối tượng (VD: "SELLER", "PRODUCT") |
| 5 | targetId | String | ✅ | ID đối tượng bị tác động |
| 6 | details | String | | Chi tiết (JSON hoặc text) |
| 7 | timestamp | DateTime | ✅ | Thời điểm ghi log |

---

## Entity 22: ShippingProvider
> **Mô tả**: ĐV Vận chuyển.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | name | String | ✅ | Tên |
| 2 | code | String | ✅ | Mã (GHTK/GRAB) |
| 3 | isActive | Boolean | ✅ | Trạng thái |

> **Bảo mật**: `apiKey` và `apiEndpoint` **không nên lưu plain-text**. Sử dụng Secret Manager hoặc encryption at rest. Nếu bắt buộc lưu DB thì phải mã hóa và hạn chế role truy cập.

## Entity 23: CouponRedemption
> **Mô tả**: Giới hạn sử dụng Coupon.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | couponId | Ref → Coupon | ✅ | Coupon |
| 2 | userId | Ref → User | ✅ | Người dùng |
| 3 | orderId | Ref → PaymentOrder | ✅ | Lệnh thanh toán áp dụng |
| 4 | usedAt | DateTime | ✅ | Thời điểm dùng |

> **Index Compound Unique**: `couponId + userId` — đảm bảo mỗi user chỉ dùng 1 coupon tối đa `perUserLimit` lần.

---

## Entity 24: Article
> **Mô tả**: Bài viết/Tin tức/FAQ.

| # | Thuộc tính | Kiểu dữ liệu | Bắt buộc | Mô tả |
|---|-----------|--------------|---------|-------|
| 1 | title | String | ✅ | Tiêu đề |
| 2 | slug | String | ✅ | URL SEO |
| 3 | type | Enum | ✅ | NEWS/FAQ/POLICY |
| 4 | viewCount | Long | ✅ | Lượt xem |

---

## Tổng hợp Enums

1. **Role**: ROLE_CUSTOMER, ROLE_SELLER, ROLE_ADMIN
2. **OrderStatus**: PLACED, CONFIRMED, SHIPPED, DELIVERED, CANCELED, RETURN_REQUESTED, DISPUTED *(KHÔNG có REFUNDED — refund được track qua PaymentOrder.paymentStatus và Order.refundedAt)*
3. **PaymentMethod**: COD, VNPAY, SEPAY, MOMO
4. **ArticleType**: NEWS, FAQ, POLICY_SHIPPING, POLICY_RETURN, ABOUT
5. **ReturnStatus**: REQUESTED, SELLER_ACCEPTED/REJECTED, DISPUTED, ADMIN_APPROVED/REJECTED, REFUNDED

---

## Tổng hợp Constraints (Ràng buộc)

| # | Mã | Ràng buộc | Truy vết |
|---|-----|----------|----------|
| 1 | C04 | 1 Customer = 1 Cart = 1 Wishlist | BR03-1, BR04-1 |
| 2 | C07 | Split Order tách theo sellerId | BR05-2 |
| 3 | C11 | Hoàn trả trong 7 ngày từ khi giao hàng thành công | BR09-1 |
| 4 | C13 | Phán quyết Admin là cuối cùng | BR23-1 |
| 5 | C17 | Mỗi User chỉ có 1 Address isDefault=true (atomic update) | US-05.5 |
| 6 | C18 | Mỗi User chỉ dùng 1 Coupon tối đa perUserLimit lần | US-05.2 |
| 7 | C19 | Mọi lần apply coupon phải tạo CouponRedemption | Audit |
| 8 | C20 | Refund được track tách biệt: PaymentOrder.paymentStatus + Order.refundedAt | UC09, UC23 |
| 9 | C21 | ShippingProvider.apiKey phải mã hóa, không lưu plain-text | Security |
