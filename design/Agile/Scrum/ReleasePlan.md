# Kế hoạch Release – TKart 

> Nguồn backlog: `design/Agile/Scrum/UserStory.md` (6 EPIC).
>
> Mục tiêu: Mỗi release đều bàn giao được (UI → API → DB → phân quyền → trạng thái).

---

## 1) Nguyên tắc chọn phạm vi cho team nhỏ

### 1.1. Tiêu chí vào release
- **Có luồng hoàn chỉnh** cho ít nhất 1 persona chính (Customer/Seller/Admin).
- **Giảm rủi ro sớm**: auth/role, mô hình Order–Payment–Inventory, tách đơn multi‑vendor.
- Ưu tiên các US có **phụ thuộc thấp** và tạo được luồng demo rõ ràng.

### 1.2. Definition of Done 
- Hoàn thành đúng **AC (GWT)** của US trong release.
- Có seed data tối thiểu để demo.
- Có log/handling cho các case fail quan trọng (ví dụ: thanh toán thất bại, hết tồn kho, guest bị chặn…).

---

## 2) Release 1 — MVP Core Commerce (luồng mua bán COD chạy được)

### Mục tiêu
Customer có thể **đăng ký/đăng nhập → xem sản phẩm → giỏ hàng → checkout COD → theo dõi đơn → hủy đơn**.
Seller có thể **đăng bán sản phẩm cơ bản**.
Admin có thể **duyệt seller/sản phẩm** để dữ liệu sạch.

### Phạm vi User Story

#### EPIC 2 — Identity & Access (tối thiểu để dùng được)
- `US-02.1`, `US-02.2`, `US-02.3` — Đăng ký + OTP + điều hướng form
- `US-27.1` — Đăng nhập email/password
- `US-28.1` — Đăng xuất + bảo vệ route sau logout

> Ghi chú: `US-27.2` (OTP login), `US-27.3/27.4` (OAuth) và `US-27.5` (2FA Admin) để sau.

#### EPIC 1 — Discovery tối thiểu để mua được
- `US-01.1` — Trang chủ
- `US-01.2` — Tìm kiếm & lọc
- `US-01.3` — Chi tiết sản phẩm
- `US-01.4` — Ràng buộc Guest (chặn các thao tác yêu cầu định danh)
- `US-01.5` — Trang tĩnh (FAQ/Chính sách)

#### EPIC 3 — Cart & Checkout COD
- `US-03.1`, `US-03.2`, `US-03.3`, `US-03.4` — Giỏ hàng (thêm/sửa/xóa/xem)
- `US-05.5` — Checkout (khung luồng)
- `US-05.7` — COD: ghi nhận đơn hàng
- `US-05.8` — Post-processing: cập nhật tồn kho, dọn dữ liệu
- `US-06.1`, `US-06.2`, `US-06.3` — Lịch sử đơn / stepper / hủy đơn + hoàn tồn

#### EPIC 4 — Seller & Admin tối thiểu để có dữ liệu thật
- Seller đăng sản phẩm (tối thiểu): `US-15.1`
- Admin duyệt seller/sản phẩm (tối thiểu): `US-19.1`, `US-20.1`

### Demo cuối Release 1 (vertical slice)
1) Guest vào trang chủ → tìm sản phẩm → xem chi tiết.
2) Guest bấm “Thêm vào giỏ” bị chặn theo `US-01.4`.
3) Đăng ký (`US-02.1/02.2`) → đăng nhập (`US-27.1`).
4) Thêm giỏ (`US-03.1/03.2`) → checkout COD (`US-05.5/05.7`).
5) Xem đơn (`US-06.1/06.2`) → hủy đơn (`US-06.3`).
6) Admin duyệt seller/sản phẩm để hàng lên sàn (`US-19.1`, `US-20.1`).

---

## 3) Release 2 — Multi-vendor Checkout + Khuyến mãi cơ bản (giải bài toán sàn)

### Mục tiêu
Hoàn thiện checkout theo hướng “sàn thật”: **wishlist**, **voucher/coin** mức cơ bản, **tách đơn theo seller**, và chuẩn bị để tích hợp thanh toán online.

### Phạm vi User Story

#### EPIC 3 — Wishlist & Voucher/Coin
- `US-04.1`, `US-04.2` — Wishlist
- `US-05.1`, `US-05.2` — Voucher auto-apply + manual apply
- `US-05.3` — Sử dụng xu
- `US-05.4` — Final payment summary

#### EPIC 3 — Multi-vendor order
- `US-05.6` — Split order (ưu tiên cao vì ảnh hưởng kiến trúc Order/OrderItem/Seller)

#### EPIC 2 — Ví xu (mức đọc)
- `US-12.1` — Lịch sử ví xu

### Demo cuối Release 2
- Buyer thêm nhiều sản phẩm khác seller → checkout → hệ thống tách đơn theo seller (`US-05.6`).
- Áp voucher/xu và thấy final payment thay đổi rõ ràng (`US-05.1–05.4`).
- Wishlist hoạt động end‑to‑end (`US-04.1–04.2`).

---

## 4) Release 3 — Thanh toán online + Webhook + Seller fulfillment (tích hợp thực tế)

### Mục tiêu
Tích hợp cổng thanh toán và đóng vòng đời giao dịch: **redirect → webhook success/fail → cập nhật đơn**.
Sau đó nối sang Seller để **xác nhận/từ chối đơn và đẩy vận chuyển**.

### Phạm vi User Story

#### EPIC 3 — Payment online
- `US-05.9` — VNPay redirect
- `US-05.12` — Webhook thành công
- `US-05.13` — Thất bại / bị hủy

> Tuỳ nguồn lực có thể để `US-05.10` (Momo) / `US-05.11` (SePay) sang Release 4.

#### EPIC 4 — Seller xử lý đơn & vận chuyển
- `US-16.1` — Seller xác nhận đơn mới
- `US-16.2` — Seller từ chối/hủy đơn
- `US-16.3` — Đẩy đơn qua đơn vị vận chuyển và lấy mã vận đơn
- `US-16.4` — In phiếu giao hàng
- `US-16.5` — Auto update trạng thái giao hàng thành công

### Demo cuối Release 3
- Buyer checkout VNPay → thanh toán thành công → webhook cập nhật đơn.
- Seller xác nhận đơn, lấy mã vận đơn, in phiếu, trạng thái đơn tự cập nhật.

---

## 5) Release 4 — Real-time & After-sales (tăng trải nghiệm + hậu mãi)

### Mục tiêu
Tạo “wow factor” (real‑time, AI) và hoàn thiện vòng đời sau bán: **review → return → escalate → admin dispute**.

### Phạm vi User Story

#### EPIC 1 — Chat real-time & Chatbot
- `US-13.1`, `US-13.2`, `US-13.3` — Chat 1-1 real-time
- `US-07.1`, `US-07.2`, `US-07.3` — Chatbot public/context/private

#### EPIC 5 — Review & Returns/Disputes
- `US-08.1`, `US-08.2` — Review + thu hồi review
- `US-09.1`, `US-09.2` — Return request + escalate
- `US-17.1`, `US-17.2`, `US-17.3` — Seller accept/reject + SLA
- `US-23.1`, `US-23.2` — Admin xử lý khiếu nại

### Demo cuối Release 4
- Buyer chat với seller (realtime) về đơn/sản phẩm.
- Buyer tạo return → seller accept/reject → nếu escalate thì admin quyết định.

---

## 6) Release 5 — Admin Ops & Growth (vận hành, marketing, báo cáo, security nâng cao)

### Mục tiêu
Tập trung công cụ vận hành sàn: cấu hình homepage, coupon/deal, quản lý người dùng, audit log, phí sàn… và “polish” seller reporting.

### Phạm vi User Story

#### EPIC 6 — Admin Operations
- `US-21.1` — Homepage config
- `US-22.1`, `US-22.2` — Coupon & Deals
- `US-24.1`, `US-24.2` — Quản lý user + khóa tài khoản
- `US-25.1` — Cấu hình phí sàn và tham số ví xu
- `US-26.1` — Audit log
- `US-19.2` — Xử lý gian hàng vi phạm

#### EPIC 4 — Seller reporting & storefront media
- `US-14.1`, `US-14.2`, `US-14.3` — Business details + media + preview storefront
- `US-18.1`, `US-18.2`, `US-18.3` — Dashboard + transactions + export excel

#### EPIC 2 — Auth nâng cao (nếu chưa làm)
- `US-27.2` — OTP login
- `US-27.3`, `US-27.4` — OAuth Google/Facebook
- `US-27.5` — Admin 2FA

---

## 7) Gợi ý chia nhỏ nếu bạn làm theo Sprint 2 tuần

Nếu muốn tối ưu hơn cho 2 dev, có thể coi 5 release ở trên như **5 mốc lớn**, và tách thành các Sprint như sau:
- Sprint 1: Auth cơ bản + Guest restriction
- Sprint 2: Discovery + product detail
- Sprint 3: Cart + COD + order history
- Sprint 4: Voucher/coin + split order
- Sprint 5: VNPay + webhook + seller fulfillment/shipping
- Sprint 6: Returns + admin dispute
- Sprint 7: Admin ops + marketing + audit

---

## 8) Phụ thuộc quan trọng (để tránh vỡ kế hoạch)
- `US-05.6` (Split order) nên làm **trước hoặc cùng lúc** với các tích hợp vận chuyển (`US-16.x`) để tránh làm lại mô hình dữ liệu.
- Payment online (`US-05.9/05.12/05.13`) cần “khung” order-state rõ ràng từ Release 1.
- Returns/disputes (`US-09.x/17.x/23.x`) phụ thuộc trạng thái đơn hàng và cơ chế hoàn tiền (có thể mô phỏng trước nếu chưa có tích hợp thật).
