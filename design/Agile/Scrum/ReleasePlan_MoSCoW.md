# MoSCoW Matrix — Release Plan (TKart)

---

## Quy ước
- **Must**: Không có là không thể demo end-to-end / không tạo ra giá trị cốt lõi của release.
- **Should**: Rất quan trọng, nhưng có thể dời sang release sau nếu thiếu nguồn lực.
- **Could**: Tăng trải nghiệm/polish, làm khi còn thời gian.
- **Won’t (this release)**: Chủ động không làm trong release này để tránh vỡ phạm vi.

---

## Release 1 — MVP Core Commerce (luồng mua bán COD chạy được)

| Must (bắt buộc) | Should (nên có) | Could (có thì tốt) | Won’t (release này) |
|---|---|---|---|
| **Identity tối thiểu để dùng được**: `US-02.1`, `US-02.2`, `US-02.3` (Đăng ký + OTP + điều hướng), `US-27.1` (Login email/password), `US-28.1` (Logout + guard route) | Tối ưu UI/UX auth (form polish, message lỗi rõ ràng) | Remember-me/refresh token (nếu kiến trúc phù hợp) | `US-27.2` (OTP login), `US-27.3/27.4` (OAuth), `US-27.5` (2FA admin) |
| **Discovery tối thiểu để mua được**: `US-01.1` (Trang chủ), `US-01.2` (Search/filter), `US-01.3` (Product detail), `US-01.4` (Guest restriction), `US-01.5` (Trang tĩnh) | SEO meta cơ bản cho trang public | A/B layout homepage | Realtime chat/chatbot |
| **Cart & Checkout COD**: `US-03.1`–`US-03.4`, `US-05.5` (Checkout khung), `US-05.7` (COD), `US-05.8` (Post-processing) | Pricing unit tests đầy đủ, seed data demo | Mini analytics conversion | Payment online |
| **Orders**: `US-06.1`, `US-06.2`, `US-06.3` (Order history/stepper/cancel + hoàn tồn) | Notification cơ bản (email/onsite) | Download invoice đơn giản | Shipping integration thực tế |
| **Seller/Admin tối thiểu để có dữ liệu**: `US-15.1` (Seller đăng sản phẩm tối thiểu), `US-19.1` + `US-20.1` (Admin duyệt seller/sản phẩm tối thiểu) | Quy trình duyệt có ghi chú lý do | Bulk approve/reject | Admin ops nâng cao |

---

## Release 2 — Multi-vendor Checkout + Khuyến mãi cơ bản

| Must (bắt buộc) | Should (nên có) | Could (có thì tốt) | Won’t (release này) |
|---|---|---|---|
| **Wishlist**: `US-04.1`, `US-04.2` | Sync đa thiết bị tốt hơn (optimistic UI + retry) | Folder/tag wishlist | Review & returns |
| **Voucher/Coin cơ bản**: `US-05.1`, `US-05.2` (Voucher), `US-05.3` (Coin), `US-05.4` (Final payment) | Rule engine rõ ràng + log quyết định apply | Suggest best deal nâng cao | Payment online |
| **Bài toán sàn (marketplace core)**: `US-05.6` (Split order) | Settle model phác thảo (chuẩn bị R5) | Mixed fulfillment | Shipping integration |
| **Ví xu (mức đọc)**: `US-12.1` | Export lịch sử ví | Hiển thị dự đoán xu nhận được | Audit/finance config |

---

## Release 3 — Thanh toán online + Webhook + Seller fulfillment (tích hợp thực tế)

| Must (bắt buộc) | Should (nên có) | Could (có thì tốt) | Won’t (release này) |
|---|---|---|---|
| **Payment online (VNPay)**: `US-05.9` (redirect), `US-05.12` (webhook success), `US-05.13` (fail/cancel) | Idempotency webhook + retry policy rõ ràng | Support partial fail handling | `US-05.10` (Momo) / `US-05.11` (SePay) nếu thiếu nguồn lực |
| **Seller fulfillment + shipping**: `US-16.1`–`US-16.5` (confirm/reject/đẩy ĐVVC/in PDF/webhook shipped→delivered) | Dashboard seller cho xử lý đơn (view/filter) | Auto-cancel khi quá SLA | Returns/dispute |

---

## Release 4 — Real-time & After-sales (tăng trải nghiệm + hậu mãi)

| Must (bắt buộc) | Should (nên có) | Could (có thì tốt) | Won’t (release này) |
|---|---|---|---|
| **After-sales core**: `US-08.1`, `US-08.2` (Review), `US-09.1`, `US-09.2` (Return + escalate), `US-17.1`–`US-17.3` (Seller accept/reject + SLA), `US-23.1`, `US-23.2` (Admin dispute) | Evidence/media UX tốt (preview/zoom) | Auto template trả lời | Admin ops/growth |
| **Real-time & AI**: `US-13.1`–`US-13.3` (Chat 1-1 realtime), `US-07.1`–`US-07.3` (Chatbot public/context/private) | Rate limit/chat moderation | Multi-agent bot | Advanced security |

---

## Release 5 — Admin Ops & Growth (vận hành, marketing, báo cáo, security nâng cao)

| Must (bắt buộc) | Should (nên có) | Could (có thì tốt) | Won’t (release này) |
|---|---|---|---|
| **Admin Operations**: `US-21.1` (Homepage config), `US-22.1`, `US-22.2` (Coupon/Deals), `US-24.1`, `US-24.2` (User mgmt/lock), `US-25.1` (Finance/coin params/platform fee), `US-26.1` (Audit log), `US-19.2` (Shop vi phạm) | Bulk tools (mass action), workflow approval nâng cao | Experiment/feature flag | — |
| **Seller reporting & polish**: `US-14.1`–`US-14.3` (Business details/media/preview storefront), `US-18.1`–`US-18.3` (Dashboard/transactions/export) | Benchmark report, reconciliation view | BI integration | — |
| **Auth nâng cao (nếu chưa làm)**: `US-27.2` (OTP login), `US-27.3`, `US-27.4` (OAuth Google/Facebook), `US-27.5` (Admin 2FA) | Device management | Session dashboard | — |

---

## Ghi chú kiểm soát scope (tóm tắt)
- **Release 1** ưu tiên “xương sống giao dịch” và giảm rủi ro bằng COD.
- **Release 2** khóa mô hình marketplace (split order, voucher/coin) trước khi đi sâu vào payment/shipping.
- **Release 3** xử lý rủi ro tích hợp (payment + webhook + shipping) sau khi core state đã ổn.
- **Release 4** tập trung hậu mãi & realtime/AI (tăng trải nghiệm) khi đã có đơn hàng thực tế.
- **Release 5** bật năng lực vận hành/quản trị để scale.
