Dưới đây là toàn bộ nội dung từ tài liệu nguồn của bạn được định dạng lại chuẩn xác bằng Markdown. (Lưu ý: Phần nội dung này là văn bản gốc trích xuất từ file nguồn. Để có một báo cáo hoàn thiện nhất, bạn hãy thay thế các bảng Use Case và bảng Phân tích Tác nhân bằng những phiên bản chuẩn mà chúng ta đã cùng nhau tinh chỉnh trong phần trao đổi trước đó nhé - thông tin này nằm ngoài tài liệu nguồn và dựa trên lịch sử trò chuyện của chúng ta).

---

<div align="center">
<b>BỘ GIÁO DỤC VÀ ĐÀO TẠO</b>.<br>
<b>TRƯỜNG ĐẠI HỌC SƯ PHẠM KỸ THUẬT TP. HỒ CHÍ MINH</b>.<br>
<b>KHOA CÔNG NGHỆ THÔNG TIN</b>.<br>
--------------------<br>
<b>ĐỒ ÁN MÔN HỌC</b>.<br>
<b>LỚP HỌC PHẦN:</b>.<br>
<b>GVHD:</b>.<br>
<b>SINH VIÊN THỰC HIỆN: NHÓM 01</b>.
</div>

| Sinh viên | MSSV |
| ------ | ------ |
| Trác Ngọc Đăng Khoa | 23110243 |
| Nguyễn Thành Tin | 23110343 |

<div align="center">
*(Phần bảng này lấy từ)*
</div>

<div align="center">
<b>Thành phố Hồ Chí Minh – tháng năm 2026</b>.
</div>

**NHẬN XÉT CỦA GIẢNG VIÊN HƯỚNG DẪN**.
Ngày , tháng 10 năm 2025.
GVHD.

**MỤC LỤC**.
**DANH MỤC BẢNG**.
**DANH MỤC HÌNH ẢNH**.

**LỜI CẢM ƠN**.


<div align="center">
*(Phần bảng này lấy từ)*
</div>

---

### **PHẦN MỞ ĐẦU**.
**Lý do chọn đề tài**.
**Mục tiêu của đề tài**.
**Phạm vi nghiên cứu**.
**Phương pháp nghiên cứu**.

### **KHẢO SÁT HIỆN TRẠNG VÀ XÁC ĐỊNH YÊU CẦU**.
**Phân tích hiện trạng**.
Trong bối cảnh thương mại điện tử phát triển mạnh mẽ, việc xây dựng một nền tảng bán hàng không chỉ dừng lại ở mô hình một cửa hàng mà đang chuyển dịch sang mô hình sàn giao dịch thương mại điện tử đa nhà cung cấp. Hiện tại, người tiêu dùng cần một nền tảng tích hợp nơi họ có thể tìm kiếm, lọc, sắp xếp sản phẩm từ nhiều nhà bán hàng khác nhau, quản lý giỏ hàng mượt mà, thanh toán trực tuyến an toàn và tương tác qua Chatbot AI. Về phía người bán, họ thiếu một công cụ tập trung để đăng bán sản phẩm, theo dõi đơn hàng, cập nhật kho và thống kê doanh thu qua biểu đồ trực quan. Về phía quản trị viên, cần có một hệ thống toàn diện để kiểm duyệt người bán, quản lý mã giảm giá, các chương trình khuyến mãi và tùy biến giao diện trang chủ động. Việc xây dựng một hệ thống E-commerce đa nhà cung cấp là giải pháp hoàn chỉnh, tự động hóa quy trình giao dịch, quản lý tài chính thông qua tích hợp cổng thanh toán và nâng cao trải nghiệm người dùng.

**Phân tích yêu cầu**.
**Yêu cầu chức năng**.
**Yêu cầu chức năng nghiệp vụ**.

**Bảng yêu cầu chức năng nghiệp vụ**.
**Bộ phận: Khách vãng lai | Mã số: GUEST**.

| **STT** | **Công việc** | **Loại công việc** | **Quy định/Công thức liên quan** | **Biểu mẫu liên quan** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ | ------ |
| **1** | Xem giao diện Trang chủ | Tra cứu | QĐ_KVL1 | | Hiển thị các Banner, Deals, Lưới danh mục. |
| **2** | Tìm kiếm, lọc sản phẩm | Tra cứu | QĐ_KVL2 | | Lọc theo giá, màu, thương hiệu, danh mục. |
| **3** | Xem chi tiết sản phẩm | Tra cứu | QĐ_KVL3 | | Xem ảnh, giá, mô tả, tồn kho. |
| **4** | Xem bài viết, tin tức | Tra cứu | QĐ_KVL4 | | Đọc tin tức từ hệ thống hoặc gian hàng. |
| **5** | Xem đánh giá (Review) | Tra cứu | QĐ_KVL5 | | Chỉ xem, không được viết đánh giá. |
| **6** | Đăng ký / Đăng nhập | Tương tác | QĐ_KVL6 | | Yêu cầu bắt buộc để tiến hành mua hàng. |

*Bảng 11: Bảng yêu cầu chức năng nghiệp vụ Khách vãng lai*.

| **STT** | **Mã số** | **Tên Quy định/ Công thức** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ |
| **1** | QĐ_KVL1 | Quy định hiển thị Trang chủ | Dữ liệu trang chủ (Homepage data) được hệ thống public mở hoàn toàn, Khách vãng lai không cần truyền JWT Token vẫn có thể tải được danh sách Deals, Grid Category và các sản phẩm nổi bật. | API public |
| **2** | QĐ_KVL2 | Quy định tìm kiếm và lọc | Hỗ trợ tìm kiếm bằng từ khóa. Lọc nâng cao theo: Danh mục 3 cấp (Level 1, 2, 3), mức giá (Min/Max), % giảm giá tối thiểu, màu sắc và sắp xếp (Giá từ thấp đến cao/Cao xuống thấp). | |
| **3** | QĐ_KVL3 | Quy định xem chi tiết sản phẩm | Khách vãng lai được xem toàn bộ thông tin công khai của sản phẩm bao gồm: Giá niêm yết (MRP Price), Giá bán (Selling Price), % giảm giá, hình ảnh (từ Cloudinary), màu sắc và thông tin Cửa hàng (Seller). | |
| **4** | QĐ_KVL4 | Quy định xem bài viết, tin tức | Khách vãng lai có thể truy cập đọc các bài viết quảng bá, tin tức sự kiện do Admin phát hành trên toàn sàn hoặc bài viết nội bộ của từng gian hàng Seller. | |
| **5** | QĐ_KVL5 | Quy định giới hạn đánh giá | Khách vãng lai chỉ có quyền ĐỌC các số sao (Rating) và nội dung bình luận (Review) của các sản phẩm. Tuyệt đối không được phép GỬI đánh giá mới. | |
| **6** | QĐ_KVL6 | Quy định giới hạn nghiệp vụ Mua sắm | Khách vãng lai **không có giỏ hàng (Cart)** và **không có Danh sách yêu thích (Wishlist)**. Nếu cố tình nhấn nút "Thêm vào giỏ" hoặc "Mua ngay", hệ thống (React Router) bắt buộc chuyển hướng (Redirect) sang trang Đăng nhập / Đăng ký qua OTP. | |

*Bảng 12: Bảng yêu quy định/ công thức liên quan Khách vãng lai*.

**Bộ phận: Quản trị viên | Mã số: ADMIN**.

| **STT** | **Công việc** | **Loại công việc** | **Quy định/Công thức liên quan** | **Biểu mẫu liên quan** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ | ------ |
| **1** | Quản lý kiểm duyệt Seller | Tra cứu/Lưu trữ | QĐ_AD1 | | Thay đổi trạng thái tài khoản |
| **2** | Quản lý danh mục sản phẩm | Tra cứu/Lưu trữ | QĐ_AD2 | | Quản lý cấu trúc 3 cấp |
| **3** | Quản lý trang chủ (Homepage) | Lưu trữ/Cập nhật | QĐ_AD3 | | Banner, lưới danh mục |
| **4** | Quản lý khuyến mãi (Deals & Coupons) | Tra cứu/Lưu trữ | QĐ_AD4 | | Áp dụng toàn sàn |
| **5** | Quản lý tài khoản toàn hệ thống | Tra cứu/Lưu trữ | QĐ_AD5 | | |
| **6** | Quản lý khiếu nại (Disputes) | Tra cứu/Xử lý | QĐ_AD6 | | Quyết định cuối cùng giữa Khách và Seller. |
| **7** | Xử lý Hoàn tiền | Tính toán/Xử lý | QĐ_AD7 | | |
| **8** | Cấu hình hệ thống Xu (Coins) | Lưu trữ/Cập nhật | QĐ_AD8 | | Thiết lập tỉ lệ quy đổi và hạn mức sử dụng Xu. |
| **9** | Đối soát doanh thu Seller | Tính toán/Kết xuất | CT_AD1 | | Quản lý dòng tiền và tính toán phí nền tảng. |
| **10**| Quản lý đơn vị vận chuyển | Tra cứu/Lưu trữ | QĐ_AD10 | | |

*Bảng 11: Bảng yêu cầu chức năng nghiệp vụ Admin*.

| **STT** | **Mã số** | **Tên Quy định/ Công thức** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ |
| **1** | QĐ_AD1 | Quy định kiểm duyệt Seller | Khi Seller đăng ký (Cung cấp GST, thông tin ngân hàng), tài khoản ở trạng thái PENDING. Admin kiểm duyệt và chuyển thành ACTIVE. Nếu vi phạm, Admin có thể SUSPEND hoặc BAN. | |
| **2** | QĐ_AD2 | Quy định quản lý danh mục | Danh mục được tổ chức theo 3 cấp độ (Level 1, Level 2, Level 3). | |
| **3** | QĐ_AD3 | Quy định quản lý trang chủ | Admin được phép thiết lập lưới danh mục hiển thị, danh mục đồ điện tử, nội thất và cập nhật các hình ảnh hiển thị trên trang chủ. | |
| **4** | QĐ_AD4 | Quy định quản lý khuyến mãi | Admin tạo Deal giảm giá cho các danh mục hoặc phát hành Coupon chung (yêu cầu mã code, phần trăm giảm, thời hạn, giá trị tối thiểu). | |
| **5** | QĐ_AD5 | Quy định quản lý tài khoản | Quản lý quyền truy cập và thông tin của tất cả Customer và Seller. | |
| **6** | QĐ_AD6 | Quy định xử lý khiếu nại | Đối với các đơn hàng DISPUTED, Admin đóng vai trò trọng tài xem xét bằng chứng của cả Khách hàng và Seller. Phán quyết của Admin (Chấp nhận hoàn tiền hoặc Không chấp nhận) là quyết định cuối cùng. | |
| **7** | QĐ_AD7 | Quy định hoàn tiền | Khi yêu cầu hoàn tiền được duyệt (bởi Seller hoặc Admin), hệ thống Back-end tự động gọi API Refund của cổng thanh toán để trả tiền về tài khoản ngân hàng gốc của khách hàng. Trạng thái đơn chuyển thành REFUNDED | |
| **8** | QĐ_AD8 | Quy định cấu hình Xu | Admin thiết lập 3 thông số chính: 1. **Tỉ lệ kiếm xu** (VD: 1000đ = 1 Xu). 2. **Tỉ giá tiêu xu** (VD: 1 Xu = 1đ). 3. **Hạn mức thanh toán** (VD: Xu chỉ thanh toán tối đa 50% giá trị đơn hàng). | |
| **9** | CT_AD1 | Công thức đối soát doanh thu Seller | Doanh thu Seller thực nhận = Tổng Selling Price - Phí nền tảng (Platform Fee). *Lưu ý:* Phần tiền mà khách hàng đã dùng Xu để trừ thẳng vào đơn hàng sẽ do Admin bù lại vào ví của Seller trong quá trình đối soát để không làm thiệt hại đến doanh thu của Seller. | Yêu cầu đối soát dòng tiền minh bạch. |
| **10**| QĐ_AD10| Quy định Cấu hình API Vận chuyển | Admin có quyền thiết lập các khóa bảo mật (API Key, Client Secret, Webhook URL) của các đối tác vận chuyển (GHTK, GrabExpress). Cho phép Bật/Tắt (Active/Deactive) các ĐVVC hiển thị trên sàn. | |

*Bảng 12: Bảng yêu quy định/ công thức liên quan Admin*.

**Bộ phận: Nhà cung cấp (Seller) | Mã số: SL**.

| **STT** | **Công việc** | **Loại công việc** | **Quy định/Công thức liên quan** | **Biểu mẫu liên quan** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ | ------ |
| **1** | Cập nhật hồ sơ & thanh toán | Lưu trữ | QĐ_SL1 | | Cung cấp GST, Ngân hàng |
| **2** | Quản lý sản phẩm | Lưu trữ/Cập nhật | QĐ_SL2 | | Thêm biến thể, giá bán |
| **3** | Xử lý đơn hàng | Cập nhật | QĐ_SL3 | | Tự động hóa qua API GHTK/Grab. |
| **4** | In phiếu giao hàng (Vận đơn) | Kết xuất | QĐ_SL4 | | Mã vạch/QR code để dán lên gói hàng. |
| **5** | Báo cáo doanh thu & Thống kê | Kết xuất | CT_SL1 | | Xem biểu đồ doanh số |
| **6** | Xử lý Yêu cầu trả hàng/Hoàn tiền | Tra cứu/Xử lý | QĐ_SL5 | | Phê duyệt hoặc từ chối yêu cầu từ khách. |
| **7** | Cập nhật thống kê hoàn tiền | Kết xuất | CT_SL2 | | Tự động cập nhật vào Total Refund |

*Bảng 13: Bảng yêu cầu chức năng nghiệp vụ seller*.

| **STT** | **Mã số** | **Tên Quy định/ Công thức** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ |
| **1** | QĐ_SL1 | Quy định cập nhật hồ sơ | Seller phải cung cấp mã số thuế doanh nghiệp (GST), địa chỉ kho hàng (Pickup address), và thông tin ngân hàng hợp lệ để đối soát. | |
| **2** | QĐ_SL2 | Quy định đăng tải sản phẩm | Mỗi sản phẩm phải thuộc 1 danh mục Level 3. Bắt buộc có Giá gốc (MRP) và Giá bán thực tế (Selling Price). Hệ thống tự động tính % Discount. Hình ảnh lưu qua Cloudinary. | |
| **3** | QĐ_SL3 | Quy định xử lý đơn hàng & Vận chuyển | Khi đơn hàng có trạng thái CONFIRMED, Seller sử dụng chức năng **"Đẩy đơn vận chuyển"**. Hệ thống Back-end tự động gọi API của ĐVVC (GHTK/Grab) để lấy Mã vận đơn. Các trạng thái tiếp theo (SHIPPED, DELIVERED) sẽ do **Webhook của ĐVVC tự động cập nhật về hệ thống**, Seller không cần thao tác tay. | Đồng bộ Real-time |
| **4** | CT_SL1 | Tính toán báo cáo doanh thu | Báo cáo tự động tổng hợp: Tổng thu nhập (Total Earning), Tổng số đơn (Total Orders), Đơn bị hủy (Canceled Orders) và thể hiện qua biểu đồ trực quan. | |
| **5** | QĐ_SL4 | Quy định in vận đơn | Sau khi đẩy đơn thành công, Seller được phép kết xuất và in Phiếu giao hàng chứa Mã vận đơn (Tracking Code) định dạng PDF để đóng gói. | |
| **6** | QĐ_SL5 | Quy định xử lý yêu cầu trả hàng | Xử lý yêu cầu trả hàng. Khi nhận được yêu cầu RETURN_REQUESTED, Seller có tối đa 3 ngày để phản hồi. - Nếu **Chấp nhận**: Chờ nhận lại hàng, sau đó xác nhận để hệ thống hoàn tiền. - Nếu **Từ chối**: Phải ghi rõ lý do từ chối. | |
| **7** | CT_SL2 | Cập nhật tổng hoàn tiền (Total Refund). | Khi một đơn hàng hoàn tiền thành công, số tiền này bị trừ khỏi tổng thu nhập (Total Earning) và được cộng dồn vào thống kê Total Refund trên Seller Dashboard | |

*Bảng 14: Bảng yêu quy định/ công thức liên quan Nhân viên*.

**Bộ phận: Khách hàng (Customer) | Mã số: KH**.

| **STT** | **Công việc** | **Loại công việc** | **Quy định/Công thức liên quan** | **Biểu mẫu liên quan** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ | ------ |
| **1** | Xác thực tài khoản bằng OTP | Tương tác/Xác thực | QĐ_KH1 | | |
| **2** | Tương tác Chatbot AI | Tra cứu | QĐ_KH2 | | Hỏi đáp tự động. |
| **3** | Tìm kiếm, lọc sản phẩm | Tra cứu | QĐ_KH3 | | Lọc theo giá, màu, danh mục. |
| **4** | Quản lý giỏ hàng & Wishlist | Tương tác/Lưu trữ | QĐ_KH4 | | |
| **5** | Đặt hàng & thanh toán | Xử lý/Tính toán | QĐ_KH5 | | Thanh toán qua Stripe/Razorpay. |
| **6** | Theo dõi & quản lý đơn hàng | Tra cứu | QĐ_KH6 | | Xem trạng thái đơn hàng. |
| **7** | Đánh giá & Phản hồi | Kết xuất | QĐ_KH7 | | Rating 1-5 sao, kèm ảnh thực tế. |
| **8** | Nhận thông báo, ưu đãi, hỗ trợ | Tra cứu/Tương tác | QĐ_KH8 | | Hỗ trợ qua chat, email hoặc hotline. |
| **9** | Yêu cầu trả hàng & hoàn tiền | Tương tác/Cập nhật | QĐ_KH9 | | Chỉ áp dụng cho đơn đã giao thành công. |
| **10**| Khiếu nại lên Admin (Dispute) | Tương tác/Xử lý | QĐ_KH10 | | Dùng khi Seller từ chối yêu cầu trả hàng. |
| **11**| Quản lý ví Xu (Reward Coins) | Tra cứu | QĐ_KH11 | | Xem số dư xu hiện tại và lịch sử nhận/tiêu xu. |
| **12**| Áp dụng Xu khi thanh toán | Tính toán/Xử lý | QĐ_KH12 | | Trừ tiền tương ứng với số xu khách muốn sử dụng. |

*Bảng 15: Bảng yêu cầu chức năng nghiệp vụ Khách hàng*.

| **STT** | **Mã số** | **Tên Quy định/ Công thức** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ |
| **1** | QĐ_KH1 | Quy định đăng nhập bằng OTP | Xác thực thông qua email sử dụng Java Mail Sender. Hệ thống gửi OTP gồm 6 chữ số để đăng ký/đăng nhập, thời gian hiệu lực giới hạn. | |
| **2** | QĐ_KH2 | Quy định tương tác Chatbot AI | Chatbot có khả năng truy xuất cơ sở dữ liệu để trả lời các câu hỏi về: Tình trạng đơn hàng, tổng tiền giỏ hàng, thông tin chi tiết sản phẩm và khuyến mãi. | |
| **3** | QĐ_KH3 | Quy định tìm kiếm và lọc | Hỗ trợ tìm kiếm theo từ khóa. Lọc nâng cao theo: Danh mục, mức giá (Min/Max), % giảm giá tối thiểu, màu sắc và sắp xếp (Giá từ thấp đến cao/Cao xuống thấp). | |
| **4** | QĐ_KH4 | Quy định tách đơn hàng giỏ hàng | **Nghiệp vụ cốt lõi:** Một giỏ hàng có thể chứa sản phẩm từ nhiều Seller. Khi Checkout, hệ thống nhóm các món hàng theo Seller ID thành các Orders riêng biệt tương ứng với từng Seller, nhưng gộp chung vào 1 PaymentOrder duy nhất để thanh toán 1 lần. | Tính toán tổng tiền: CT_KH1 |
| **5** | QĐ_KH5 | Quy định thanh toán quốc tế/nội địa | Hỗ trợ cổng thanh toán VnPay, SePay hoặc Momo. Thanh toán thành công sẽ đổi trạng thái PaymentOrder thành SUCCESS và tự động trừ hàng trong kho. | |
| **6** | QĐ_KH6 | Quy định theo dõi & quản lý đơn hàng | Khách hàng tra cứu nhật ký vận chuyển **trực tiếp ngay trên giao diện website** thông qua thanh tiến trình (Order Stepper). Hệ thống liên tục đồng bộ và hiển thị chi tiết các mốc thời gian, vị trí và trạng thái giao hàng từ Đơn vị vận chuyển (GHTK/Grab). Khách hàng được cung cấp Mã vận đơn để đối chiếu nếu cần, nhưng không bắt buộc phải rời khỏi sàn để tra cứu. Lịch sử đơn hàng được lưu trữ tối thiểu 12 tháng. | Giao tiếp qua API ĐVVC |
| **7** | QĐ_KH7 | Quy định đánh giá (Review) | Chỉ được đánh giá sản phẩm sau khi đã nhận hàng (DELIVERED). Chấm điểm từ 1 đến 5 sao, kèm bình luận và cho phép đính kèm hình ảnh thực tế sản phẩm. | |
| **8** | QĐ_KH8 | Quy định nhận thông báo, ưu đãi, hỗ trợ | Hệ thống gửi thông báo về đơn hàng, khuyến mãi, sự kiện. Hỗ trợ khách hàng qua chat, email hoặc hotline. | |
| **9** | QĐ_KH9 | Quy định yêu cầu trả hàng/hoàn tiền | Khách hàng chỉ được gửi yêu cầu trả hàng/hoàn tiền đối với đơn hàng có trạng thái là DELIVERED (Đã giao) trong vòng **7 ngày** kể từ ngày nhận. Bắt buộc phải cung cấp lý do (hàng lỗi, sai mẫu...) và đính kèm hình ảnh/video minh chứng. Trạng thái đơn hàng chuyển sang RETURN_REQUESTED. | |
| **10**| QĐ_KH10| Quy định khiếu nại (Escalate) | Nếu Người bán từ chối yêu cầu trả hàng, Khách hàng có quyền nhấn nút "Khiếu nại lên Admin". Đơn hàng chuyển sang trạng thái DISPUTED (Đang tranh chấp) để Admin can thiệp. | |
| **11**| QĐ_KH11| Quy định Tích xu | Tích điểm (hoặc xu) cho khách hàng dựa trên lịch sử mua hàng. Khi đơn hàng đạt trạng thái DELIVERED, hệ thống tự động cộng số Xu = Final Payment Amount * Tỉ lệ tích xu (VD: 1%). | |
| **12**| QĐ_KH12| Quy định Tiêu xu | Khách hàng có thể dùng Xu ở bước Thanh toán. Số tiền giảm được trừ trực tiếp vào Final Payment Amount. Có thể kết hợp sử dụng Xu và Mã giảm giá (Coupon) cùng lúc. | |
| **13**| **CT_KH1** | Công thức tính tiền Giỏ hàng | Tổng giá bán (Total Selling Price) = ∑ (Giá bán × Số lượng). Giảm giá Coupon = Total Selling Price × (% Coupon / 100). Giảm giá Xu = Số Xu sử dụng × Tỉ giá. Phí vận chuyển (Dynamic Shipping Fee): Được Back-end gọi API bên thứ 3 tính toán tự động dựa trên khoảng cách địa chỉ Kho Seller và Khách nhận. Tổng thanh toán (Final Payment) = Total Selling Price - Coupon - Xu + Phí vận chuyển. | Công thức tổng quát áp dụng tại bước Checkout, tự động đối soát cả Mã giảm giá và Xu thưởng. |

*Bảng 16: Bảng yêu quy định/ công thức liên quan Khách hàng*.

**Yêu cầu chức năng hệ thống:**
**Môi trường:** Hệ thống được xây dựng trên nền tảng Web Application, hoạt động qua mạng Internet, hỗ trợ truy cập trên máy tính và thiết bị di động. Front-end phát triển bằng React, TypeScript, Tailwind CSS, MUI và Redux Toolkit. Back-end sử dụng Java Spring Boot, MySQL Database. Hệ thống tích hợp trực tiếp với Cloudinary để lưu trữ phương tiện và các cổng thanh toán VnPay, SePay hoặc Momo.
**Phân quyền:** Hệ thống phân chia người dùng thành 3 nhóm quyền chính bằng Spring Security và JSON Web Token (JWT):
*Khách hàng (ROLE_CUSTOMER):* Có quyền truy cập giao diện cửa hàng, tra cứu sản phẩm, tương tác Chatbot, quản lý giỏ hàng, theo dõi đơn hàng cá nhân, và để lại đánh giá. Bị chặn truy cập vào các API thuộc quyền quản lý.
*Người bán (ROLE_SELLER):* Được cấp quyền vào bảng điều khiển (Seller Dashboard). Quản lý không gian bán hàng, đăng sản phẩm, theo dõi và xử lý các đơn hàng thuộc quyền sở hữu của mình, xem thống kê dòng tiền.
*Quản trị viên (ROLE_ADMIN):* Nắm quyền cao nhất vào bảng điều khiển hệ thống (Admin Dashboard). Quản lý trạng thái mọi tài khoản Seller/Customer, thiết lập giao diện Home page, cấu hình Coupon/Deal toàn sàn.

**Bảng Yêu cầu Hệ thống**

| **STT** | **Nội dung** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ |
| **1** | Nền tảng hoạt động | Ứng dụng Web nhiều lớp (Client-Server 3-tier) xây dựng với React và Spring Boot. Hệ thống hoạt động qua mạng Internet, cho phép truy cập thông qua trình duyệt web trên máy tính hoặc thiết bị di động. | Đảm bảo tương thích Responsive tốt trên đa thiết bị nhờ Tailwind CSS và MUI. |
| **2** | Tích hợp bên thứ ba | Tích hợp VNPay, một số tài khoản ngân hàng; Java Mail Sender gửi mã OTP; Cloudinary lưu trữ tài nguyên hình ảnh. | Yêu cầu API Keys bảo mật chặt chẽ. |
| **3** | Bảo mật và Phân quyền | Quản lý luồng truy cập qua JWT Token. Mật khẩu người dùng băm qua BCrypt. Phân định rõ 3 roles: Admin, Seller, Customer. | Ngăn chặn truy cập chéo giữa các Roles. |

**Yêu cầu phi chức năng**
Liên quan đến người dùng (Khách vãng lai, Khách hàng, Người bán, Quản trị viên):
**Tính tiến hóa (Khả năng mở rộng):**
Hệ thống phải có kiến trúc linh hoạt, cho phép Quản trị viên (Admin) dễ dàng tùy biến giao diện trang chủ, cấu hình lưới danh mục sản phẩm, banner quảng cáo và các chương trình khuyến mãi (Deals) để phù hợp với thị hiếu thay đổi của khách hàng. Phải dễ dàng nâng cấp, mở rộng trong tương lai để đáp ứng quy mô số lượng lớn Người bán (Seller) tham gia vào sàn. Đồng thời, hệ thống được thiết kế sẵn sàng tích hợp thêm các dịch vụ giao hàng nội địa của bên thứ ba (như GHTK, GHN...) hoặc mở rộng các cổng thanh toán điện tử quốc tế mới bên cạnh Stripe và Razorpay hiện tại.

**Tính tiện dụng (Dễ sử dụng - UX/UI):**
Giao diện của hệ thống phải trực quan, thân thiện và có thiết kế đáp ứng (Responsive) hoàn toàn nhờ sử dụng Tailwind CSS và Material UI (MUI), đảm bảo hiển thị và thao tác mượt mà trên nhiều loại thiết bị (máy tính, máy tính bảng, điện thoại di động). Các luồng thao tác cốt lõi của Khách hàng như: tìm kiếm, lọc sản phẩm đa tiêu chí (khoảng giá, màu sắc, % giảm giá), chọn biến thể kích cỡ, thao tác giỏ hàng và thanh toán gộp cho đa nhà cung cấp phải diễn ra đơn giản và tiện lợi nhất. Hệ thống Bảng điều khiển (Dashboard) dành riêng cho Người bán và Quản trị viên phải được sắp xếp logic, các công cụ thống kê doanh thu thể hiện qua biểu đồ trực quan giúp giảm thiểu số lần nhấp chuột không cần thiết và nâng cao hiệu suất làm việc.

**Tính hiệu quả (Hiệu suất và Độ ổn định):**
Nền tảng phải tối ưu hóa tốc độ tải trang (dưới 2 giây), phản hồi các thao tác tìm kiếm, chuyển đổi tab và phân trang (Pagination) nhanh chóng. Việc xử lý tải và lưu trữ khối lượng lớn hình ảnh sản phẩm đa phương tiện phải được thực hiện hiệu quả thông qua dịch vụ đám mây Cloudinary. Hệ thống phải hoạt động ổn định và xử lý chính xác dòng tiền đối soát, áp dụng đúng mã giảm giá (Coupon)/Xu thưởng cho hàng loạt giao dịch thanh toán trực tuyến diễn ra cùng lúc, đặc biệt trong các khung giờ cao điểm có lưu lượng truy cập khổng lồ.

**Tính tương thích:**
Hệ thống web hoạt động và hiển thị nhất quán trên các trình duyệt hiện đại phổ biến (Google Chrome, Safari, Firefox, Edge). Phải đảm bảo tính đồng bộ dữ liệu theo thời gian thực (Real-time) giữa hoạt động đặt hàng của Khách hàng, tình trạng kho hàng và thông báo trạng thái vận chuyển hiển thị trên Bảng điều khiển của Người bán (Seller Dashboard).

Liên quan đến chuyên viên tin học (Đội ngũ phát triển):
**Tính tái sử dụng:**
Ứng dụng Front-end (React) cần được xây dựng theo kiến trúc Component (ví dụ: tái sử dụng các component ProductCard, OrderTable, AddressForm, DrawerList ở nhiều màn hình khác nhau). Cấu trúc Back-end (Spring Boot RESTful APIs) cần được tách biệt độc lập, tuân thủ các chuẩn lập trình API giúp hệ thống dễ dàng được tái sử dụng để giao tiếp khi phát triển thêm nền tảng Ứng dụng di động (Mobile App) sau này.

**Tính bảo trì:**
Mã nguồn dự án và cơ sở dữ liệu MySQL phải được phân tách theo các miền nghiệp vụ rõ ràng (Module Sản phẩm, Module Đơn hàng, Module Thanh toán, Module Tài khoản...). Cấu trúc này cho phép đội ngũ bảo trì dễ dàng sửa lỗi hoặc mở rộng tính năng mới ở một phân hệ mà không gây đổ vỡ (Crash) tới các module khác của hệ thống.

**Tính bảo mật:**
Mật khẩu của người dùng bắt buộc phải được mã hóa một chiều an toàn bằng thuật toán BCrypt trước khi lưu trữ vào Cơ sở dữ liệu. Hệ thống phải áp dụng cơ chế xác thực phiên làm việc chặt chẽ bằng chuẩn JSON Web Token (JWT) thông qua bộ lọc Spring Security. Cần thiết lập kiểm soát truy cập dựa trên vai trò (Role-Based Access Control) để ngăn chặn tuyệt đối tình trạng truy cập chéo tài nguyên giữa 3 nhóm quyền biệt lập: Khách hàng (ROLE_CUSTOMER), Người bán (ROLE_SELLER) và Quản trị viên (ROLE_ADMIN). Thông tin nhạy cảm về thẻ tín dụng khi thanh toán phải được tuân thủ chuẩn bảo mật trực tiếp thông qua API của VnPay, SePay hoặc Momo.

**Bảng Yêu cầu chất lượng**

| **STT** | **Nội dung** | **Tiêu chuẩn** | **Mô tả chi tiết** | **Ghi chú** |
| ------ | ------ | ------ | ------ | ------ |
| **1** | Xử lý đa giao dịch & Tách đơn hàng | Hiệu quả / Chính xác | Xử lý tự động và chính xác việc tách giỏ hàng thành nhiều đơn hàng phụ (tương ứng với từng Seller) và gộp thanh toán một lần. | Nghiệp vụ cốt lõi của sàn Multivendor. Xử lý logic tại tầng Back-end (Spring Boot). |
| **2** | Tùy biến Trang chủ & Khả năng mở rộng | Tính tiến hóa (Khả năng mở rộng) | Admin dễ dàng tùy biến linh hoạt banner, lưới danh mục và khuyến mãi từ Dashboard. Hệ thống sẵn sàng mở rộng không giới hạn số lượng Seller và tích hợp thêm cổng thanh toán mới. | Đáp ứng nhu cầu thay đổi giao diện theo các chiến dịch Marketing mà không cần sửa code. |
| **3** | Trải nghiệm mua sắm UX/UI đa thiết bị | Tính tiện dụng | Giao diện thân thiện, hiển thị Responsive hoàn hảo trên Mobile, Tablet, PC. Các thao tác tìm kiếm, lọc sản phẩm đa tiêu chí, đánh giá và Checkout diễn ra mượt mà, trực quan. | Sử dụng Tailwind CSS và Material UI (MUI). Giảm tỷ lệ thoát trang. |
| **4** | Tốc độ tải trang & Xử lý tải cao | Tính hiệu quả (Hiệu năng) | Thời gian tải trang (Homepage, Product details) dưới 2 giây. Hệ thống hoạt động ổn định khi có lưu lượng truy cập đột biến (Flash Sale, Deals). | Tối ưu hóa truy vấn MySQL và lưu trữ media qua Cloudinary. |
| **5** | Hiển thị đa trình duyệt & Đồng bộ dữ liệu | Tính tương thích | Hệ thống Front-end tương thích hoàn toàn với Chrome, Safari, Firefox. Đồng bộ trạng thái đơn hàng (từ Khách hàng tới Seller Dashboard) theo thời gian thực. | Tối ưu trải nghiệm liền mạch giữa người mua và người bán. |
| **6** | Kiến trúc API & Component | Tính tái sử dụng | Các RESTful API (Spring Boot) và giao diện Front-end (React Components) được xây dựng độc lập. Dễ dàng tái sử dụng API nếu phát triển thêm Mobile App. | Tiết kiệm chi phí và thời gian phát triển mở rộng. |
| **7** | Module hóa & Sửa lỗi hệ thống | Tính bảo trì | Mã nguồn phân tách rõ ràng theo các miền nghiệp vụ (Sản phẩm, Đơn hàng, User, Thanh toán). Dễ dàng dò tìm lỗi, nâng cấp tính năng mà không gây "crash" chéo. | Đảm bảo tính bền vững của dự án phần mềm. |
| **8** | Xác thực & Mã hóa dữ liệu người dùng | Tính bảo mật | Mật khẩu băm một chiều (BCrypt). Luồng truy cập kiểm soát chặt chẽ bằng JWT Token. Ngăn chặn tuyệt đối việc truy cập chéo tài nguyên giữa Admin, Seller và Customer. | Tích hợp Spring Security. Không lưu trữ thông tin thẻ tín dụng nhạy cảm (Xử lý qua Stripe/Razorpay). |

**Quy trình tác nghiệp**
**Quy trình tham quan và chuyển đổi của Khách vãng lai**
Quy trình trải nghiệm và chuyển đổi của một Khách vãng lai trên sàn E-commerce diễn ra theo trình tự sau: Đầu tiên, Khách vãng lai truy cập vào nền tảng thông qua các trình duyệt web (có thể từ link chia sẻ, tìm kiếm Google hoặc trực tiếp URL). Hệ thống lập tức hiển thị **Trang chủ (Homepage)** với các Banner quảng cáo, các chương trình Khuyến mãi (Deals) đang diễn ra và các Danh mục nổi bật (Điện tử, Nội thất, Thời trang...) mà không yêu cầu đăng nhập. Tiếp theo, khách tự do điều hướng, sử dụng thanh tìm kiếm (Search) hoặc nhấp vào cây danh mục 3 cấp để duyệt sản phẩm. Tại trang danh sách, khách sử dụng bộ lọc nâng cao (lọc theo khoảng giá, màu sắc, thương hiệu) để thu hẹp kết quả. Khi tìm thấy sản phẩm ưng ý, khách nhấp vào để xem **Chi tiết sản phẩm** (đọc mô tả, xem hình ảnh thực tế, xem đánh giá 1-5 sao từ người dùng trước) hoặc đọc các bài viết/tin tức liên quan đến gian hàng đó để tăng độ tin cậy.
Khi khách quyết định mua hàng và thực hiện hành động nhấn nút **"Thêm vào giỏ hàng" (Add to Cart)**, **"Thêm vào Wishlist"**, hoặc **"Chat với AI Chatbot"**, hệ thống Spring Security ở Backend và React Router ở Frontend sẽ chặn thao tác này và tự động bật Pop-up / chuyển hướng (Redirect) khách sang **Màn hình Đăng nhập / Đăng ký**. Tại đây, khách vãng lai bắt buộc phải nhập Email/SĐT và xác thực mã OTP. Sau khi nhập OTP thành công, hệ thống cấp JWT Token, Khách vãng lai chính thức chuyển đổi trạng thái thành **Khách hàng (Customer)** và được tiếp tục quy trình mua sắm, thanh toán bị gián đoạn trước đó.

**Quy trình khách hàng mua sắm trực tuyến**
Quy trình khách hàng mua sắm diễn ra theo các bước sau: Đầu tiên, khách hàng truy cập vào nền tảng và tìm kiếm sản phẩm thông qua thanh tìm kiếm, bộ lọc nâng cao (theo mức giá, màu sắc, % giảm giá) hoặc nhận tư vấn trực tiếp từ AI Chatbot. Khi chọn được sản phẩm ưng ý, khách chọn biến thể (size, màu sắc) và thêm vào giỏ hàng hoặc đưa vào danh sách yêu thích (Wishlist) để mua sau.
Tại bước thanh toán, do đặc thù đa nhà cung cấp, hệ thống sẽ tự động tách giỏ hàng thành các đơn hàng phụ tương ứng với từng người bán. Khách hàng tiến hành nhập mã giảm giá (nếu có) có thể tích chọn sử dụng "Xu tích lũy" từ ví tài khoản để trừ trực tiếp vào tổng số tiền phải trả. Hệ thống sẽ tự động tính toán lại số tiền cuối cùng. Thanh toán một lần duy nhất thông qua các cổng thanh toán trực tuyến an toàn như VnPay, SePay hoặc Momo. Cuối cùng, hệ thống ghi nhận giao dịch thành công và chuyển thông tin đơn hàng đến các gian hàng tương ứng.

**Quy trình khách hàng tra cứu đơn hàng và tương tác**
Để theo dõi đơn hàng, khách hàng đăng nhập vào hệ thống và chọn chức năng “Đơn hàng của tôi” (My Orders). Tại đây, họ có thể xem trạng thái hiện tại của đơn hàng, bao gồm các bước: Đã đặt (Placed), Đã xác nhận (Confirmed), Đang giao (Shipped) và Đã giao (Delivered). Điểm đặc biệt của hệ thống là khách hàng có thể mở giao diện AI Chatbot và hỏi trực tiếp bằng ngôn ngữ tự nhiên (ví dụ: "Tôi có bao nhiêu đơn hàng đã giao?") để tra cứu trạng thái đơn hàng hoặc chi tiết giỏ hàng nhanh chóng.
Khi đơn hàng hoàn tất, hệ thống tự động kích hoạt tiến trình cộng Xu thưởng vào ví của khách hàng dựa trên tổng giá trị thanh toán của đơn hàng đó. Khách hàng có thể kiểm tra biến động số dư Xu tại màn hình Quản lý tài khoản cá nhân. Khách hàng có thể đánh giá (từ 1-5 sao) và đính kèm hình ảnh thực tế của sản phẩm. Nếu không vừa ý với món hàng, khách hàng có thể yêu cầu hoàn tiền, trả hàng với lý do hợp lý và tuân thủ đúng chính sách của sàn.

**Quy trình quản lý gian hàng và sản phẩm (Dành cho Người bán)**
Người bán (Seller) sau khi được cấp tài khoản sẽ đăng nhập vào hệ thống bảng điều khiển riêng (Seller Dashboard). Họ thực hiện các thao tác quản lý kho hàng bao gồm: thêm mới, chỉnh sửa hoặc xóa sản phẩm. Các thông tin cần cung cấp gồm có tên, mô tả, giá gốc (MRP), giá bán thực tế, số lượng tồn kho và tải hình ảnh lên hệ thống (thông qua Cloudinary). Khi có sự thay đổi về giá gốc và giá bán, hệ thống tự động tính toán phần trăm giảm giá để hiển thị. Ngoài ra, người bán cũng có trách nhiệm tiếp nhận đơn hàng từ khách và cập nhật trạng thái xử lý đơn (từ Chờ xử lý đến Đã giao hàng).

**Quy trình kiểm duyệt và quản trị nền tảng (Dành cho Admin)**
Quản trị viên (Admin) nắm quyền kiểm soát toàn bộ nền tảng thông qua Bảng điều khiển quản trị. Khi một người bán mới đăng ký, tài khoản sẽ ở trạng thái chờ duyệt (Pending Verification). Admin sẽ kiểm tra hồ sơ và thực hiện phê duyệt (Active), hoặc có thể đình chỉ (Suspend), cấm vĩnh viễn (Ban) đối với các tài khoản vi phạm chính sách. Bên cạnh quản lý người dùng, Admin thực hiện việc tùy chỉnh giao diện trang chủ, thay đổi lưới danh mục, banner, và phát hành các mã giảm giá (Coupon), chương trình khuyến mãi (Deals) cho toàn bộ hệ thống.

**Quy trình thống kê và đối soát doanh thu**
Hệ thống tự động tổng hợp và tính toán các chỉ số kinh doanh theo thời gian thực. Từ bảng điều khiển, Người bán có thể xem chi tiết tổng thu nhập, tổng số sản phẩm đã bán, số lượng đơn hàng bị hủy và theo dõi lịch sử dòng tiền (Transactions). Báo cáo doanh thu được xuất ra dưới dạng các biểu đồ trực quan (Earning graphs) theo ngày, tuần hoặc tháng, giúp người bán dễ dàng phân tích tình hình kinh doanh của gian hàng. Đồng thời, dữ liệu này là cơ sở để hệ thống tiến hành đối soát và thanh toán tiền hàng cho Người bán sau khi đơn hàng giao thành công.

**Quy trình yêu cầu trả hàng và hoàn tiền (Refund & Return Process)**
Quy trình xử lý trả hàng và hoàn tiền được diễn ra chặt tiếp giữa 3 bên nhằm đảm bảo tính công bằng:
**Bước 1: Khởi tạo yêu cầu (Khách hàng):** Khách hàng đăng nhập, truy cập lịch sử mua hàng và chọn đơn hàng có trạng thái "Đã giao" (Delivered) trong thời hạn cho phép (VD: 7 ngày). Khách hàng chọn chức năng "Yêu cầu trả hàng", điền lý do và tải lên hình ảnh/video minh chứng. Hệ thống chuyển trạng thái đơn sang "Yêu cầu trả hàng" (Return Requested) và tạm thời đóng băng khoản tiền đối soát của đơn hàng này đối với Người bán.
**Bước 2: Xử lý yêu cầu (Người bán):** Người bán nhận được thông báo trên Seller Dashboard. Xem xét minh chứng của khách hàng.
*Trường hợp 2a (Đồng ý):* Người bán bấm "Chấp nhận". Khách hàng gửi trả lại hàng. Khi Người bán nhận được hàng sẽ bấm "Xác nhận hoàn tiền". Trạng thái đơn chuyển thành "Đã hoàn tiền" (Refunded).
*Trường hợp 2b (Từ chối):* Người bán bấm "Từ chối" kèm theo lý do.
**Bước 3: Khiếu nại (Khách hàng):** Nếu bị Người bán từ chối, Khách hàng có quyền nhấn "Khiếu nại". Đơn hàng chuyển sang trạng thái "Tranh chấp" (Disputed).
**Bước 4: Phán quyết (Quản trị viên - Admin):** Quản trị viên can thiệp vào các đơn "Disputed", kiểm tra đối chứng dữ liệu từ cả hai bên. Admin đưa ra phán quyết cuối cùng. Nếu Admin duyệt hoàn tiền, hệ thống sẽ tự động kích hoạt API của cổng thanh toán để đẩy tiền về thẻ của khách, đồng thời hệ thống tự động cập nhật biểu đồ thống kê "Total Refund" (Tổng số tiền hoàn) trên Dashboard của Người bán.

**MÔ HÌNH HÓA YÊU CẦU**
**Nhận diện tác nhân và chức năng trong sơ đồ Use case**
Các Usecase đang được thiết kế khóa tổng quát, như các usecase Quản lý bao gồm tra cứu, thêm, xóa, sửa. Có thể tách usecase ra riêng nhưng rất dài.

| **Tác nhân (Actor)** | **Mã UC** | **Tên Use Case (User Goal)** | **Mô tả** |
| ------ | ------ | ------ | ------ |
| **Khách vãng lai (Guest)** | **UC01** | **Khám phá nền tảng** | Xem giao diện trang chủ (Banner, Deals, Lưới danh mục), tìm kiếm từ khóa, lọc sản phẩm nâng cao (giá, màu sắc, danh mục), xem chi tiết sản phẩm và đọc đánh giá. |
| | **UC02** | **Đăng ký tài khoản** | Khách vãng lai đăng ký tài khoản qua Email và xác thực bằng mã OTP để trở thành Khách hàng chính thức. |
| **Khách hàng (Customer)** | **UC03** | **Quản lý Giỏ hàng (Cart)** | Khách hàng thực hiện thêm sản phẩm vào giỏ, cập nhật số lượng (tăng/giảm) đối với các sản phẩm đang có, hoặc xóa sản phẩm (Remove card item) khỏi giỏ hàng. Hệ thống sẽ tự động tính toán lại tổng tiền (Total Selling Price, Total MRP Price) và tổng số lượng sản phẩm trong giỏ. |
| | **UC04** | **Quản lý Danh sách yêu thích (Wishlist)** | Khách hàng thêm các sản phẩm ưng ý vào Danh sách yêu thích để lưu trữ cho các lần mua sắm sau. Xem danh sách các sản phẩm yêu thích và xóa sản phẩm khỏi danh sách này (Remove product from Wishlist) |
| | **UC05** | **Đặt hàng và Thanh toán** | Điền địa chỉ giao hàng, hệ thống tự động tách đơn theo Seller (Split Order), áp dụng Mã giảm giá (Coupon)/Xu thưởng, và thanh toán qua cổng VNPay/SePay/Momo. |
| | **UC06** | **Theo dõi và Quản lý đơn hàng** | Xem lịch sử mua hàng, tra cứu tiến trình vận chuyển theo thời gian thực (qua Webhook hãng vận chuyển), và hủy đơn hàng khi còn ở trạng thái "Mới đặt" (Pending/Placed). |
| | **UC07** | **Tương tác Chatbot AI** | Nhắn tin hỏi đáp bằng ngôn ngữ tự nhiên với AI Chatbot để tra cứu thông tin sản phẩm, đơn hàng, và giỏ hàng. |
| | **UC08** | **Đánh giá sản phẩm** | Chấm điểm (1-5 sao), viết bình luận và đính kèm hình ảnh thực tế của sản phẩm sau khi đơn hàng đã chuyển trạng thái "Đã giao". |
| | **UC09** | **Yêu cầu Trả hàng & Hoàn tiền** | Tạo yêu cầu đổi/trả hàng kèm lý do và minh chứng. Cung cấp chức năng **Khiếu nại (Dispute)** lên Quản trị viên nếu bị Seller từ chối yêu cầu. |
| | **UC010** | **Quản lý tài khoản và Ví Xu** | Quản lý sổ địa chỉ giao hàng, theo dõi biến động số dư Ví Xu (Reward Coins) và cập nhật thông tin cá nhân. |
| **Người bán (Seller)** | **UC11** | **Quản lý hồ sơ và Gian hàng** | Cập nhật thông tin doanh nghiệp (Mã số thuế GST), tài khoản ngân hàng đối soát, địa chỉ kho lấy hàng và trang trí giao diện Banner gian hàng. |
| | **UC12** | **Quản lý Kho sản phẩm** | Đăng tải sản phẩm mới (tải ảnh qua Cloudinary), chỉnh sửa thông tin (Mô tả, MRP, Giá bán thực tế), cập nhật số lượng tồn kho và cấu hình màu sắc/kích cỡ. |
| | **UC13** | **Xử lý Đơn hàng và Vận chuyển** | Tiếp nhận đơn, xác nhận, tự động đẩy đơn sang API hãng vận chuyển (GHTK/Grab) và kết xuất in Phiếu giao hàng (Waybill). |
| | **UC14** | **Xử lý Yêu cầu Hoàn trả** | Xem xét lý do và bằng chứng từ Khách hàng để đưa ra quyết định **Chấp nhận** (cho phép hoàn tiền) hoặc **Từối** yêu cầu trả hàng. |
| | **UC15** | **Theo dõi Đối soát và Doanh thu** | Xem biểu đồ doanh thu tổng quan, theo dõi lịch sử dòng tiền đối soát của từng đơn hàng cụ thể (Transactions), thống kê đơn hủy/hoàn. |
| **Quản trị viên (Admin)** | **UC16** | **Quản lý/Kiểm duyệt Người bán** | Quản lý danh sách đăng ký gian hàng, xét duyệt hồ sơ (Active), hoặc tạm đình chỉ (Suspend), cấm vĩnh viễn (Ban) đối với gian hàng vi phạm. |
| | **UC17** | **Quản lý Giao diện Trang chủ** | Tùy biến giao diện (Home page data), quản lý lưới danh mục (Grid Categories), cập nhật Banner và cấu hình các danh mục nổi bật. |
| | **UC18** | **Quản lý Chiến dịch Khuyến mãi** | Phát hành, cập nhật hoặc xóa các chiến dịch Marketing chung toàn sàn bao gồm Khuyến mãi (Deals) và Mã giảm giá (Coupons). |
| | **UC19** | **Giải quyết khiếu nại (Disputes)** | Đóng vai trò trọng tài xem xét các đơn hàng có khiếu nại, đưa ra phán quyết cuối cùng và tự động gọi API hoàn tiền cho Khách hàng. |
| | **UC20** | **Quản lý Khách hàng** | Xem danh sách khách hàng, theo dõi thông tin tài khoản và thực hiện khóa (Ban) hoặc mở khóa (Active) các tài khoản có hành vi vi phạm chính sách. |
| | **UC21** | **Cấu hình Hệ thống Xu thưởng** | Thiết lập các thông số tài chính cho ví Xu bao gồm: Tỉ lệ quy đổi khi tích xu, tỉ giá khi tiêu xu và hạn mức thanh toán tối đa bằng Xu cho một đơn hàng. |
| | **UC22** | **Quản lý Cấu hình Vận chuyển** | Thiết lập, cập nhật, kích hoạt hoặc vô hiệu hóa các khóa kết nối (API Keys) của Đơn vị vận chuyển bên thứ ba (GHTK, GrabExpress) vào hệ thống sàn. |
| | **UC23** | **Theo dõi Nhật ký hệ thống (Audit Log)** | Truy xuất và xem xét lịch sử các thao tác thay đổi dữ liệu quan trọng trên hệ thống (như tài khoản nào đã xóa mã giảm giá, ai đã kiểm duyệt Seller) nhằm mục đích kiểm toán. |
| **Khách hàng/Người bán/Quản trị viên** | **UC24** | **Xác thực người dùng (Authentication)** | Luồng nghiệp vụ dùng chung xử lý Đăng nhập, Đăng xuất, Quên mật khẩu, Phân quyền người dùng (Customer, Seller, Admin) và xác thực phiên làm việc thông qua JWT Token và mã OTP |

**Mô tả chi tiết từng tác nhân**

| **Tên tác nhân** | **Công việc/vai trò** |
| ------ | ------ |
| **Khách vãng lai (Guest)** | Người dùng truy cập vào hệ thống nhưng chưa có tài khoản hoặc chưa đăng nhập. Họ có quyền tự do tham quan trang chủ, tìm kiếm sản phẩm, đọc chi tiết mô tả hàng hóa, đọc các bài viết tin tức và xem các chương trình khuyến mãi. Tuy nhiên, họ **không được phép thao tác đặt hàng, thêm giỏ hàng hay đánh giá**. Muốn thực hiện giao dịch, họ buộc phải đăng ký/đăng nhập. |
| **Khách hàng (Customer)** | Là Khách vãng lai đã thực hiện đăng ký và đăng nhập thành công. Họ có toàn quyền thực hiện các luồng mua sắm: thêm sản phẩm vào giỏ, tương tác Chatbot, đặt hàng, thanh toán trực tuyến, tích lũy/sử dụng xu thưởng và gửi yêu cầu hoàn tiền/đánh giá sau khi nhận hàng thành công. |
| **Người bán (Seller)** | Cá nhân/doanh nghiệp sở hữu gian hàng trên hệ thống. Đóng vai trò là nhà cung cấp hàng hóa, chịu trách nhiệm đăng tải sản phẩm, thiết kế giao diện gian hàng, xuất bản tin tức của shop và trực tiếp đóng gói, cập nhật trạng thái giao hàng cho khách. |
| **Quản trị viên (Admin)** | Người quản lý cấp cao nhất của hệ thống nền tảng. Đóng vai trò kiểm duyệt (tài khoản seller, luồng tiền hoàn trả, tranh chấp), duy trì cấu hình giao diện trang chủ, cấu hình hệ thống xu thưởng, và phát hành các chiến dịch Marketing chung toàn sàn (Coupons, Deals, Tin tức hệ thống). |

*Bảng 22: Mô tả các tác nhân*.
**Sơ đồ Use case**.
**Đặc tả Use case**.

**THIẾT KẾ DỮ LIỆU**.
**Sơ đồ logic**.
**Lược đồ logic**.
**Chi tiết các bảng dữ liệu**.
**Ràng buộc toàn vẹn**.
**Ràng buộc khóa chính**.
**Ràng buộc khóa ngoại**.
**Ràng buộc miền giá trị**.
**Ràng buộc liên thuộc tính**.
**Ràng buộc liên bộ**.
**Ràng buộc liên quan hệ**.
**Sơ đồ cơ sở dữ liệu mức vật lý:**.

**THIẾT KẾ GIAO DIỆN**.
**Danh sách các màn hình và sơ đồ chuyển đổi**.
**Mô tả chi tiết các màn hình**.

**THIẾT KẾ XỬ LÝ**.

**CÀI ĐẶT VÀ THỬ NGHIỆM**.
**Cài đặt chương trình**.
**Các công cụ hỗ trợ**.
**Giới thiệu tổng quát về các công nghệ được sử dụng**.
**Cấu trúc chương trình và quy trình thực hiện**.
**Kết quả thử nghiệm**.
**Kết quả tổng quát**.
**Một số tính năng chạy thực tế**.

**TỔNG KẾT**.
**Kết quả đạt được**.
**Ưu điểm**.
**Hướng phát triển**.
**TÀI LIỆU THAM KHẢO**.
**BẢNG LIỆT KÊ KHỐI LƯỢNG CÔNG VIỆC**.