# FRD — Functional Requirements Document

> Tài liệu này được tách ra từ `design/Agile/Scrum/O.md` (phiên bản 3.0, cập nhật 08/05/2026) để dễ quản lý.
>
> **Lưu ý:** File nguồn `O.md` **được giữ nguyên**.

---

## 1) Yêu cầu chức năng (Functional Requirements)

### 1.1. Yêu cầu nghiệp vụ theo nhóm tác nhân

#### 1.1.1. Khách vãng lai (GUEST)

**Bảng yêu cầu (tóm tắt)**
| STT | Nội dung |
|---:|---|
| 1 | Xem Trang chủ (Banner, Deals, Lưới danh mục) — QĐ_KVL1 |
| 2 | Tìm kiếm, lọc sản phẩm — QĐ_KVL2 |
| 3 | Xem chi tiết sản phẩm + liên quan — QĐ_KVL3 |
| 4 | Xem trang thông tin tĩnh/tin tức — QĐ_KVL4 |
| 5 | Xem Review (chỉ đọc) — QĐ_KVL5 |
| 6 | Đăng ký/Đăng nhập (bắt buộc để mua) — QĐ_KVL6 |

**Quy định/Business rules chi tiết**
- **QĐ_KVL1**: Homepage data là public, không cần JWT vẫn tải được deals/grid category/sản phẩm nổi bật.
- **QĐ_KVL2**: Tìm kiếm keyword; lọc danh mục 3 cấp, min/max, % giảm tối thiểu, màu; sắp xếp giá tăng/giảm.
- **QĐ_KVL3**: Xem thông tin public (MRP, selling, %discount, ảnh Cloudinary, màu, seller). Related products theo Category Level 3.
- **QĐ_KVL4**: Đọc bài viết/tin tức toàn sàn hoặc nội bộ shop, các static pages public.
- **QĐ_KVL5**: Guest chỉ được đọc rating/review, không được gửi review.
- **QĐ_KVL6**: Guest không có cart/wishlist; bấm add-to-cart/buy-now bắt buộc redirect sang đăng nhập/đăng ký OTP.

#### 1.1.2. Quản trị viên (ADMIN)

**Bảng yêu cầu (tóm tắt)**
| STT | Nội dung |
|---:|---|
| 1 | Kiểm duyệt Seller — QĐ_AD1 |
| 2 | Quản lý danh mục 3 cấp — QĐ_AD2 |
| 3 | Quản lý Trang chủ — QĐ_AD3 |
| 4 | Quản lý Deals & Coupons — QĐ_AD4 |
| 5 | Quản lý tài khoản toàn hệ thống — QĐ_AD5 |
| 6 | Xử lý khiếu nại (Disputes) — QĐ_AD6 |
| 7 | Xử lý hoàn tiền — QĐ_AD7 |
| 8 | Cấu hình hệ thống Xu — QĐ_AD8 |
| 9 | Đối soát doanh thu seller — CT_AD1 |
| 10 | Quản lý đối tác vận chuyển — QĐ_AD10 |
| 11 | Kiểm duyệt sản phẩm — QĐ_AD11 |
| 12 | Cấu hình phí nền tảng — QĐ_AD12 |

**Quy định/Business rules chi tiết**
- **QĐ_AD1**: Seller đăng ký ở PENDING; admin duyệt → ACTIVE; vi phạm có thể SUSPEND/BAN.
- **QĐ_AD2**: Danh mục 3 cấp (L1/L2/L3).
- **QĐ_AD3**: Admin cấu hình grid category/banner/deals trang chủ.
- **QĐ_AD4**: Admin tạo deal, phát hành coupon chung (code, %, thời hạn, min order…).
- **QĐ_AD5**: Quản lý thông tin/quyền truy cập của customer và seller.
- **QĐ_AD6**: Đơn DISPUTED, admin xem bằng chứng 2 bên; quyết định cuối (chấp nhận/không).
- **QĐ_AD7**: Khi hoàn tiền được duyệt, backend gọi Refund API của payment gateway; trạng thái REFUNDED.
- **QĐ_AD8**: Admin cấu hình: tỉ lệ kiếm xu, tỉ giá tiêu xu, hạn mức dùng xu (vd tối đa 50%).
- **CT_AD1**: Doanh thu seller = Total Selling Price − Platform fee (%). Phần xu đã trừ của khách được admin bù vào ví seller khi đối soát.
- **QĐ_AD10**: CRUD danh sách đối tác vận chuyển; chỉ đối tác Active được phép dùng/hiển thị.
- **QĐ_AD11**: Sản phẩm mới pending; admin approve/reject.
- **QĐ_AD12**: Platform fee cấu hình động qua UI, không fix cứng.

#### 1.1.3. Nhà cung cấp / Người bán (SELLER — SL)

**Bảng yêu cầu (tóm tắt)**
| STT | Nội dung |
|---:|---|
| 1 | Cập nhật hồ sơ & thanh toán — QĐ_SL1 |
| 2 | Quản lý sản phẩm/biến thể/giá — QĐ_SL2 |
| 3 | Xử lý đơn & vận chuyển qua API — QĐ_SL3 |
| 4 | In vận đơn PDF (QR/Barcode) — QĐ_SL4 |
| 5 | Dashboard thống kê — CT_SL1 |
| 6 | Xử lý return/refund — QĐ_SL5 |
| 7 | Cập nhật Total Refund — CT_SL2 |
| 8 | Chat real-time — QĐ_SL6 |
| 9 | Xuất báo cáo Excel — QĐ_SL7 |

**Quy định/Business rules chi tiết**
- **QĐ_SL1**: Seller cung cấp GST, pickup address, thông tin ngân hàng hợp lệ.
- **QĐ_SL2**: Mỗi sản phẩm thuộc 1 category level 3; bắt buộc MRP + Selling price; tự tính %discount; ảnh lưu Cloudinary.
- **QĐ_SL3**: Khi order CONFIRMED, seller đẩy đơn vận chuyển; backend gọi API ĐVVC để lấy tracking; trạng thái SHIPPED/DELIVERED update bằng webhook.
- **CT_SL1**: Dashboard gồm Total Earning, Total Orders, Canceled Orders, biểu đồ.
- **QĐ_SL4**: In phiếu giao hàng PDF chứa tracking code.
- **QĐ_SL5**: Khi RETURN_REQUESTED, seller có tối đa 3 ngày phản hồi: accept (chờ hàng trả, xác nhận hoàn tiền) hoặc reject (bắt buộc lý do).
- **CT_SL2**: Refund thành công → trừ khỏi Total Earning và cộng vào Total Refund.
- **QĐ_SL6**: Chat real-time qua WebSockets.
- **QĐ_SL7**: Export transactions/doanh thu ra .xlsx.

#### 1.1.4. Khách hàng (CUSTOMER — KH)

**Bảng yêu cầu (tóm tắt)**
| STT | Nội dung |
|---:|---|
| 1 | Xác thực OTP — QĐ_KH1 |
| 2 | Hỏi đáp Chatbot AI — QĐ_KH2 |
| 3 | Tìm kiếm/lọc — QĐ_KH3 |
| 4 | Cart & Wishlist — QĐ_KH4 |
| 5 | Đặt hàng & thanh toán — QĐ_KH5 |
| 6 | Theo dõi đơn — QĐ_KH6 |
| 7 | Đánh giá sản phẩm — QĐ_KH7 |
| 8 | Thông báo/ưu đãi/hỗ trợ — QĐ_KH8 |
| 9 | Return/refund — QĐ_KH9 |
| 10 | Khiếu nại lên admin (dispute) — QĐ_KH10 |
| 11 | Ví xu — QĐ_KH11 |
| 12 | Áp xu khi thanh toán — QĐ_KH12 |
| 13 | Chat real-time với seller — QĐ_KH13 |
| 14 | Quản lý đa địa chỉ — QĐ_KH14 |

**Quy định/Business rules chi tiết**
- **QĐ_KH1**: OTP email, hiệu lực giới hạn.
- **QĐ_KH2**: Chatbot truy xuất DB cho câu hỏi về đơn hàng, tổng tiền giỏ, sản phẩm, khuyến mãi.
- **QĐ_KH3**: Tìm kiếm & lọc tương tự guest, theo danh mục/giá/% giảm/màu/sort.
- **QĐ_KH4**: Cart multi-seller; checkout nhóm theo seller thành orders, gộp chung thành PaymentOrder.
- **QĐ_KH5**: Thanh toán VnPay/SePay/Momo; success → PaymentOrder SUCCESS; trừ hàng.
- **QĐ_KH6**: Theo dõi shipping trên UI stepper; đồng bộ từ ĐVVC; lưu lịch sử tối thiểu 12 tháng.
- **QĐ_KH7**: Chỉ được review sau DELIVERED; 1–5 sao; kèm ảnh.
- **QĐ_KH9**: Return chỉ cho đơn DELIVERED trong 7 ngày; bắt buộc lý do & minh chứng; trạng thái RETURN_REQUESTED.
- **QĐ_KH10**: Nếu seller reject return → customer có quyền escalate → DISPUTED.
- **QĐ_KH11**: Khi DELIVERED, cộng xu theo tỉ lệ; xu dựa trên final payment.
- **QĐ_KH12**: Có thể dùng xu khi checkout; kết hợp với coupon.
- **CT_KH1**: 
  - Total selling = ∑(price × qty)
  - Coupon discount = Total selling × (%/100)
  - Coin discount = coins_used × coin_rate
  - Shipping fee tính động qua API
  - Final payment = Total selling − coupon − coin + shipping
- **QĐ_KH13**: Chat real-time 2 chiều, không reload, WebSockets.
- **QĐ_KH14**: Quản lý nhiều địa chỉ; checkout chọn địa chỉ đã lưu hoặc tạo mới.

---

## 2) Yêu cầu hệ thống & ràng buộc

### 2.1. Môi trường hoạt động
| STT | Hạng mục | Mô tả |
|---:|---|---|
| 1 | Nền tảng | Web client-server 3-tier, responsive |
| 2 | Front-end | React, TypeScript, Tailwind, MUI, Redux Toolkit |
| 3 | Back-end | Java Spring Boot (REST), Spring Security, Spring Data JPA |
| 4 | Database | MySQL |
| 5 | Media | Cloudinary |
| 6 | Payment | VnPay, SePay hoặc Momo |
| 7 | Shipping | API GHTK/Grab + webhook |

### 2.2. Phân quyền (RBAC)
| Vai trò | Mã quyền | Phạm vi |
|---|---|---|
| Customer | ROLE_CUSTOMER | Store UI: search/cart/chatbot/order/review |
| Seller | ROLE_SELLER | Seller Dashboard: product/order/revenue/chat; chỉ dữ liệu shop mình |
| Admin | ROLE_ADMIN | Admin Dashboard: moderation/homepage/marketing/dispute/settlement |

### 2.3. Bảo mật
- JWT qua Spring Security filter.
- BCrypt băm mật khẩu.
- Chống truy cập chéo (isolation theo role + theo shop).
- Dữ liệu thẻ không lưu DB, đi thẳng qua cổng thanh toán.

---

## 3) Yêu cầu phi chức năng (NFR)

### 3.1. NFR hướng người dùng
- **Khả năng mở rộng**: dễ mở rộng seller, thêm dịch vụ giao/ cổng thanh toán.
- **UX/UI**: responsive, thao tác cốt lõi đơn giản.
- **Hiệu năng/ổn định**: tải trang < 2s; xử lý đồng thời các giao dịch nhiều.
- **Tương thích**: Chrome/Safari/Firefox/Edge.
- **Real-time consistency**: đồng bộ trạng thái kho/đơn/tracking.

### 3.2. NFR hướng đội phát triển
- **Tái sử dụng**: FE theo component; BE module hoá.
- **Bảo trì**: tách miền nghiệp vụ (product/order/payment/account…).
- **Bảo mật**: tuân thủ JWT/RBAC, payment data không lưu.

---

## 4) Giả định & ràng buộc

### 4.1. Giả định
- Người dùng có internet ổn định.
- Payment/shipping API khả dụng trong giờ kinh doanh.
- Email SMTP ổn định (gửi OTP < 5 giây).
- Cloudinary đủ dung lượng.

### 4.2. Ràng buộc
- Kiến trúc bắt buộc 3-tier (React + Spring Boot + MySQL).
- BCrypt + JWT + RBAC.
- Không lưu thẻ trong DB.
- Tracking ID sinh tự động từ ĐVVC; seller không nhập tay.
- Trình duyệt hiện đại; UI tiếng Việt.

---

## 5) Quy trình tác nghiệp (Operational processes)

### 5.1. Guest → Customer conversion
1) Guest truy cập homepage public.
2) Duyệt/tìm kiếm/xem chi tiết.
3) Khi bấm add-to-cart/wishlist/chat AI → bị chặn và redirect login/register.
4) OTP thành công → cấp JWT → trở thành customer.
5) Tiếp tục luồng mua sắm dang dở.

### 5.2. Customer shopping & checkout
1) Tìm kiếm hoặc hỏi AI.
2) Chọn biến thể → add to cart / wishlist.
3) Checkout: split order theo seller.
4) Auto áp coupon tốt nhất.
5) (Tuỳ chọn) nhập xu.
6) Tính final payment.
7) Xác nhận và thanh toán (online hoặc COD).
8) Success: trừ kho, xóa giỏ, notify seller.

### 5.3. Order tracking & interaction
1) Xem “Đơn hàng của tôi”.
2) Stepper: Placed → Confirmed → Shipped → Delivered.
3) (Tuỳ chọn) hỏi chatbot.
4) Delivered → cộng xu.
5) Review.
6) Return trong 7 ngày.

### 5.4. Seller operations
1) Seller vào dashboard.
2) CRUD sản phẩm + ảnh Cloudinary.
3) Pending → admin duyệt.
4) Xử lý đơn: confirm → đẩy vận chuyển → in PDF.

### 5.5. Admin operations
1) Admin dashboard.
2) Duyệt seller (PENDING→ACTIVE) hoặc suspend/ban.
3) Duyệt sản phẩm (approve/reject).
4) Config homepage.
5) Coupon/deals.
6) Config xu + platform fee.

### 5.6. Refund & dispute
1) Customer yêu cầu return (DELIVERED trong 7 ngày) + minh chứng.
2) Order → RETURN_REQUESTED, đóng băng tiền đối soát.
3) Seller accept → nhận hàng trả → xác nhận hoàn tiền → gọi Refund API → REFUNDED.
4) Seller reject → phải có lý do.
5) Customer escalate → DISPUTED.
6) Admin phán quyết cuối; nếu duyệt refund → gọi refund API; update Total Refund.

---

## 6) BR Catalog (Business Rules)

> Mục đích: Tổng hợp các Business Rules (BR) rút ra từ đặc tả use case cũ và/hoặc từ các quy định (QĐ_*/CT_*) trong tài liệu này để dễ trace sang User Story và test.

| BR ID | Rule (mô tả ngắn) | Applies to (Module/Flow) | Related US | Priority | Notes/Test idea |
|---|---|---|---|---|---|
| BR29-1 | Mật khẩu bắt buộc phải có độ dài tối thiểu 8 ký tự để đảm bảo tiêu chuẩn an toàn | Identity → Reset/Change password | (map later) | Must | Negative test: 7 ký tự → reject; 8 ký tự → accept |
| BR01-4 | Guest bị chặn các thao tác yêu cầu định danh (add-to-cart/buy-now/wishlist/chat) và được điều hướng sang login/register | Storefront → Guard/Route protection | US-01.4, US-02.1, US-27.1 | Must | Verify guest click “Add to cart” → redirect; after login return to intended action |
| BR05-6 | Checkout phải tách đơn theo seller: mỗi seller tạo 1 Order; các Order thuộc 1 PaymentOrder giao dịch tổng | Checkout → Order/Payment model | US-05.6 | Must | Create cart 2 sellers → expect 2 orders + 1 payment order; totals consistent |
| BR05-4 | Final payment = Total selling − coupon − coin + shipping; coin discount = coins_used × coin_rate | Checkout pricing | US-05.3, US-05.4 | Must | Unit test pricing; boundary: coin_rate changes; min final payment not negative |
| BR16-3 | Tracking ID phải được sinh từ API ĐVVC; seller không nhập tay để tránh gian lận | Shipping integration | US-16.3 | Must | Attempt manual tracking edit → forbidden; create shipment must call carrier API |
| BR-RBAC-1 | RBAC: dữ liệu seller chỉ trong phạm vi shop mình; admin có thể xem toàn hệ thống | Authorization | (map later) | Must | API tests: seller A cannot access seller B orders/products; admin can |
| BR01-1 | “Sản phẩm liên quan” bắt buộc truy xuất các sản phẩm cùng danh mục cấp 3 với sản phẩm đang xem | Storefront → Product detail | (map later) | Must | Verify related products query filtered by Category L3 |
| BR01-2 | Guest tuyệt đối không có quyền tạo Cart/Wishlist/Gửi đánh giá | Storefront → Authorization | (map later) | Must | Guest attempts create cart/wishlist/review → 401/403 |
| BR02-1 | OTP gồm 6 chữ số ngẫu nhiên và có thời gian hiệu lực giới hạn | Identity → Register | (map later) | Must | OTP length=6; expired OTP rejected |
| BR03-1 | Mỗi Khách hàng chỉ sở hữu duy nhất 1 Giỏ hàng (quan hệ 1–1) | Cart | (map later) | Must | DB constraint/unique: one cart per customer |
| BR04-1 | Mỗi người dùng chỉ có đúng 1 Wishlist (1–1); một sản phẩm chỉ xuất hiện 1 lần trong wishlist | Wishlist | (map later) | Should | Add same product twice → no duplicates |
| BR05-1 | Khi thay đổi Địa chỉ hoặc Phương thức thanh toán, Voucher Engine phải quét lại và auto-apply theo thời gian thực | Checkout → Voucher engine | (map later) | Must | Changing address/payment triggers recalculation |
| BR05-2 | Bắt buộc tách Order riêng biệt theo Seller_ID | Checkout → Split order | (map later) | Must | Cart multi-seller → many orders |
| BR05-3 | Công thức Final Payment: Total selling − Coupon − Coin + Shipping | Checkout pricing | (map later) | Must | Pricing contract/unit tests |
| BR05-4 | Đơn hàng COD không được phép dùng Xu (chỉ cho phép Coupon); một số coupon có thể giới hạn chỉ áp dụng cho thanh toán online; phí COD (nếu có) cộng vào phí ship | Checkout → COD | (map later) | Must | COD with coins → reject; COD fee impacts shipping total |
| BR06-1 | Thanh tiến trình đơn hàng (Order Stepper) được cập nhật tự động và đồng bộ từ ĐVVC; khách không cần rời sàn để tra cứu | Orders → Tracking | (map later) | Must | Webhook updates stepper states |
| BR06-2 | Chỉ cho phép Khách hàng hủy đơn khi trạng thái là Pending hoặc Placed | Orders → Cancel | (map later) | Must | Cancel Shipped/Delivered → not allowed |
| BR07-1 | Guest: Chatbot chỉ được truy xuất dữ liệu Public (FAQ/tổng quan/khuyến mãi/sản phẩm phổ biến) | Chatbot → Authorization | (map later) | Should | Guest asks private data → denied |
| BR07-2 | Customer (đã đăng nhập): Chatbot được truy xuất dữ liệu Private thuộc sở hữu User đó (orders/cart/history) | Chatbot → Authorization | (map later) | Should | Customer asks own orders/cart → answered |
| BR08-1 | Chỉ kích hoạt “Viết đánh giá” khi trạng thái sản phẩm trong đơn chính xác là DELIVERED; chưa mua/chưa nhận hàng không được đánh giá | Reviews | (map later) | Should | Button disabled until delivered |
| BR09-1 | Policy trả hàng cấu hình 7 ngày kể từ DELIVERED; qua ngày thứ 8 hệ thống tự khóa tính năng | Returns/Refund | (map later) | Must | Delivered+8days → return request blocked |
| BR09-2 | Khi đơn hàng ở trạng thái DISPUTED, quyết định của Admin là kết quả bắt buộc cuối cùng | Disputes | (map later) | Must | After admin decision, no further dispute actions |
| BR10-1 | Khách hàng không được phép thay đổi Email đăng nhập; Email read-only trên UI | Profile | (map later) | Must | Attempt update email → rejected |
| BR11-1 | Sổ địa chỉ cho phép lưu nhiều bản ghi; danh sách gọi ra dạng Address Cards tại Checkout (UC05) | Address book | (map later) | Must | Checkout shows saved addresses |
| BR12-1 | Ví Xu ở chế độ Read-only với Khách hàng; cộng/trừ xu do hệ thống tự động dựa trên trạng thái đơn hàng | Coins wallet | (map later) | Should | No manual coin edit endpoints |
| BR13-1 | Cuộc trò chuyện riêng tư giữa một Customer cụ thể và một Shop cụ thể của sản phẩm đó | Chat | (map later) | Must | Ensure conversation scoping by (customerId, shopId) |
| BR14-1 | Seller bắt buộc cung cấp GST, pickup address và thông tin ngân hàng hợp lệ để đối soát/payout | Seller profile | (map later) | Must | Missing core fields → cannot save/activate |
| BR15-1 | Mỗi sản phẩm phải gắn đúng 1 Category Level 3; khách dùng category ID để lọc ở UC01 | Product management | (map later) | Must | Product must have categoryL3Id |
| BR15-2 | Sản phẩm tạo mới mặc định ẩn (PENDING); chỉ Admin duyệt (UC20) mới hiển thị công khai | Moderation | (map later) | Must | New product not searchable until approved |
| BR16-1 | Tracking ID sinh tự động từ API ĐVVC; Seller không được nhập tay để tránh gian lận | Shipping | (map later) | Must | Tracking created only via carrier integration |
| BR16-2 | Vận đơn PDF bắt buộc chứa Barcode/QR Code của ĐVVC | Shipping | (map later) | Must | PDF contains scannable code |
| BR17-1 | Seller có tối đa 3 ngày phản hồi yêu cầu; nếu từ chối phải ghi rõ lý do | Returns/Refund | (map later) | Must | SLA timer + mandatory reject reason |
| BR18-1 | Báo cáo doanh thu tự động tổng hợp chính xác các chỉ số từ tất cả đơn thuộc Seller | Seller dashboard | (map later) | Should | Aggregation correctness tests |
| BR18-2 | Refund thành công (UC17) → tự động trừ Total Earning và cộng dồn Total Refund để đối soát minh bạch | Seller dashboard | (map later) | Should | Verify metrics update on refund |
| BR19-1 | Chỉ Seller trạng thái ACTIVE mới truy cập Seller Dashboard và đăng tải sản phẩm | Seller access control | (map later) | Must | PENDING/SUSPENDED/BANNED → forbidden |
| BR20-1 | Bắt buộc kiểm duyệt: mọi sản phẩm tạo mới trạng thái PENDING; Seller không thể tự Publish | Product moderation | (map later) | Must | Publish endpoint admin-only |
| BR21-1 | Homepage config phải là API public độc lập để client render tự động theo cấu hình Admin lưu | Homepage config | (map later) | Should | Client renders sections from config JSON |
| BR22-1 | Campaign/coupon do Admin tạo áp dụng toàn nền tảng; khi khách áp dụng ở UC03/UC05 hệ thống đối soát để Seller không lỗ doanh thu | Promotions | (map later) | Must | Settlement rules ensure seller not negative |
| BR23-1 | Phán quyết của Admin là quyết định cao nhất và bắt buộc thực thi; sau xử lý, customer/seller không được khiếu nại lại cho đơn đó | Disputes | (map later) | Must | Lock further dispute actions |
| BR23-2 | Tuyệt đối không hoàn tiền thủ công; hoàn tiền phải qua luồng tự động (Refund API) để an toàn dòng tiền | Payments | (map later) | Must | Admin UI has no manual payout; refunds via gateway API |
| BR24-1 | Admin xem thông tin cơ bản Customer để hỗ trợ nhưng không được xem mật khẩu (đã băm BCrypt) | Admin user mgmt | (map later) | Should | No password plaintext exposure |
| BR25-1 | Cấu hình xu phải có 3 tham số: tỉ lệ kiếm, tỉ giá tiêu, hạn mức tối đa (vd xu tối đa 50% hóa đơn) | Finance config | (map later) | Must | Validate config fields; enforce max coin usage |
| BR25-2 | % Platform fee là cấu hình động, không fix cứng; dùng để áp dụng CT_AD1 khi đối soát cho Seller (UC18) | Finance config | (map later) | Must | Fee retrieved from config, not constants |
| BR26-1 | Audit Log là dữ liệu tuyệt đối Read-only; không có chức năng Delete/Update cho bất kỳ ai (kể cả Root Admin) | Audit log | (map later) | Should | Ensure write-only append; no delete endpoints |
| BR27-1 | OTP chỉ hiệu lực 1 lần và tồn tại trong thời gian giới hạn (vd 5 phút) | Identity → Login OTP | (map later) | Must | Reuse OTP rejected; expired rejected |
| BR27-2 | Đăng nhập Google/Facebook mặc định chỉ cấp quyền Customer | Identity → Social login | (map later) | Must | Social login cannot create seller/admin roles |
| BR28-1 | Sau logout, mọi URL riêng tư (orders/checkout…) phải bị chặn và yêu cầu đăng nhập | Identity → Logout | (map later) | Must | Access protected route after logout → redirect/login |

---

## 7) NFR Catalog (Non-Functional Requirements)

> Mục đích: Tổng hợp các NFR (NFRxx-x) từ đặc tả use case cũ để trace sang User Story, KPI/benchmark và test.

| NFR ID | Requirement (mô tả ngắn) | Quality attribute | Applies to (Module/Flow) | Related US | Priority | Notes/Metric/Test idea |
|---|---|---|---|---|---|---|
| NFR01-1 | Tốc độ tải Trang chủ và Trang chi tiết sản phẩm < 2 giây dù lưu lượng lớn | Performance | Storefront | (map later) | Must | Lighthouse/DevTools; p95 < 2s |
| NFR02-1 | Gửi email OTP không quá 5 giây kể từ lúc nhấn nút | Performance/Availability | Identity → OTP | (map later) | Must | Measure end-to-end from click to email received |
| NFR03-1 | Tính toán tổng tiền giỏ hàng phản hồi tức thì trên UI khi nhấn +/- | Performance/UX | Cart | (map later) | Must | UI state update <100ms; no full reload |
| NFR04-1 | Thêm/xóa wishlist đồng bộ DB và phản hồi UI không cần reload trang | UX/Consistency | Wishlist | (map later) | Should | Optimistic UI + server confirmation |
| NFR05-1 | Voucher scan/apply < 1 giây để không lag UX | Performance | Checkout → Voucher engine | (map later) | Must | API p95 < 1s under load |
| NFR05-2 | Không lưu thông tin thẻ trên DB; truyền mã hóa 100% qua VnPay/SePay/Momo | Security/Compliance | Payment | (map later) | Must | Security review; ensure no PAN storage |
| NFR06-1 | Lưu lịch sử đơn hàng tối thiểu 12 tháng để tra cứu/đối soát | Data retention | Orders | (map later) | Must | Retention policy; backup/archival |
| NFR07-1 | Chatbot AI phản hồi không quá 3 giây | Performance | Chatbot | (map later) | Should | p95 response time <3s |
| NFR08-1 | Cập nhật điểm rating trung bình ngay sau khi submit review | Consistency | Reviews | (map later) | Should | Verify average recalculated immediately |
| NFR09-1 | Evidences ảnh/video tối ưu nén trên Cloudinary để giảm tải DB | Performance/Cost | Returns/Dispute evidence | (map later) | Should | Store URLs only; enforce media compression |
| NFR10-1 | Thông tin hồ sơ cập nhật ngay lên Header nhờ state management, không reload | UX | Profile | (map later) | Should | Header reflects change instantly |
| NFR11-1 | Form tỉnh/thành/phường/xã dùng dropdown gọi API hành chính để chuẩn dữ liệu | Data quality/UX | Address book | (map later) | Should | Validate codes; prevent free-text |
| NFR12-1 | Lịch sử ví xu sort theo thời gian mới nhất trước (DESC) | UX | Coins wallet | (map later) | Should | Default sort desc |
| NFR13-1 | Chat real-time bắt buộc dùng WebSockets (Spring WebSockets + STOMP, SockJS/stompjs); latency < 1 giây | Performance/Realtime | Chat | (map later) | Must | Measure message RTT <1s |
| NFR14-1 | Upload Logo/Banner shop đẩy trực tiếp lên Cloudinary từ client (FormData) để giảm tải backend | Performance | Seller profile | (map later) | Should | Backend receives only URL, not file bytes |
| NFR15-1 | %Giảm giá tự tính client-side ngay khi đổi giá ở bước nhập liệu | UX/Performance | Product management | (map later) | Should | JS calc; validation tests |
| NFR16-1 | Do API hãng vận chuyển có độ trễ, UI phải có loading spinner chặn thao tác “Đẩy đơn” liên tiếp | UX/Resilience | Shipping | (map later) | Must | Prevent duplicate shipment creation |
| NFR17-1 | Xem evidence trên dashboard hỗ trợ zoom và phát media mượt, không cần tải file về | UX | Returns/Refund | (map later) | Should | Video streaming; zoom supported |
| NFR18-1 | Export Excel đúng định dạng bảng tính, không lỗi font Unicode tiếng Việt | Compatibility | Seller reports | (map later) | Should | Open in Excel; verify UTF-8/Unicode |
| NFR19-1 | Mọi thay đổi trạng thái tài khoản seller phải ghi audit log | Auditability | Admin/Seller management | (map later) | Must | Verify audit record per state change |
| NFR20-1 | UI duyệt sản phẩm tối ưu load ảnh nhanh để admin duyệt hàng loạt | Performance/UX | Product moderation | (map later) | Should | Lazy load; batch actions |
| NFR21-1 | Trải nghiệm WYSIWYG: đổi config phản hồi dưới 1 giây | Performance/UX | Homepage config | (map later) | Should | UI update <1s after save |
| NFR22-1 | Check coupon tại giỏ hàng < 100ms | Performance | Promotions | (map later) | Must | API p95 <100ms |
| NFR23-1 | So sánh evidences cho phép tải/phát video trực tiếp trên admin dashboard, không redirect | UX | Disputes | (map later) | Should | In-app video player |
| NFR24-1 | Danh sách khách hàng bắt buộc pagination từ backend để đảm bảo hiệu suất quy mô triệu user | Performance | Admin customer list | (map later) | Must | Enforce paging params; no unbounded query |
| NFR25-1 | Thay đổi thông số tài chính chỉ áp dụng cho orders tạo sau thời điểm cập nhật, không tính lại lịch sử | Integrity | Finance config | (map later) | Must | Versioned configs; test historical orders unchanged |
| NFR26-1 | Ghi log DB chạy ngầm (async) để không tăng latency luồng chính | Performance | Audit logging | (map later) | Should | Async queue/background worker |
| NFR27-1 | Quản lý phiên bằng JWT qua Spring Security filter | Security | Identity | (map later) | Must | Verify token validation/expiry |
| NFR27-2 | Mật khẩu bắt buộc băm một chiều BCrypt khi đối chiếu | Security | Identity | (map later) | Must | No plaintext, proper BCrypt strength |
| NFR27-3 | Login Google/Facebook giao tiếp theo OAuth2 | Security/Interoperability | Identity | (map later) | Must | OAuth2 flow tests |
| NFR28-1 | Logout xử lý ngay client-side: xóa JWT token và clear Redux store | UX/Security | Identity → Logout | (map later) | Must | After logout, token removed |
| NFR29-1 | Mật khẩu mới không lưu plaintext; backend băm BCrypt trước khi lưu DB | Security | Identity → Reset/Change password | (map later) | Must | DB stores hash only; verify BCrypt |


