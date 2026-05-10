# Agile Product Backlog – TKart E-commerce Platform

> Chuẩn hóa theo **INVEST** (Independent, Negotiable, Valuable, Estimable, Small, Testable).
> Acceptance Criteria trình bày theo **GWT (Given–When–Then)** để dễ kiểm thử.

---

## EPIC 1: TRẢI NGHIỆM & TƯƠNG TÁC (Discovery & Interaction)
### Feature 1.1: Khám phá nền tảng (UC01)

#### US-01.1: Trải nghiệm Trang chủ
**Story:** Là một Người dùng (Khách vãng lai/Khách hàng), tôi muốn xem Trang chủ với các chương trình khuyến mãi và danh mục, để nắm bắt nhanh các sản phẩm nổi bật đang bán trên sàn.

**AC1: Tải dữ liệu trang chủ (Basic Flow)**
- **GIVEN** Thiết bị của người dùng có kết nối Internet
- **WHEN** Người dùng truy cập vào URL của nền tảng
- **THEN** Hệ thống hiển thị giao diện Trang chủ với các khối: Khuyến mãi (Deals), Banner quảng cáo và Lưới danh mục nổi bật
- **AND** Thời gian tải và hiển thị toàn bộ trang phải dưới 2 giây (NFR01-1)

#### US-01.2: Tìm kiếm và Lọc sản phẩm
**Story:** Là một Người dùng, tôi muốn tìm kiếm và lọc sản phẩm theo nhiều tiêu chí (giá, màu sắc, danh mục), để dễ dàng thu hẹp phạm vi và tìm đúng món hàng mong muốn.

**AC1: Trả về kết quả hợp lệ (Basic Flow)**
- **GIVEN** Người dùng đang ở màn hình có thanh tìm kiếm
- **WHEN** Người dùng nhập từ khóa và áp dụng bộ lọc nâng cao
- **THEN** Hệ thống truy xuất dữ liệu và trả về danh sách các sản phẩm đáp ứng tiêu chí

**AC2: Không tìm thấy sản phẩm (Exception Flow 4a)**
- **GIVEN** Hệ thống không có sản phẩm nào khớp với từ khóa hoặc bộ lọc của người dùng
- **WHEN** Người dùng thực hiện lệnh tìm kiếm
- **THEN** Hệ thống hiển thị thông báo "Không tìm thấy sản phẩm phù hợp"
- **AND** Hệ thống hiển thị nút/gợi ý "Xóa bộ lọc" để người dùng thử lại

#### US-01.3: Xem Chi tiết sản phẩm
**Story:** Là một Người dùng, tôi muốn xem chi tiết thông tin của một sản phẩm, để có đủ cơ sở đưa ra quyết định mua hàng.

**AC1: Hiển thị đầy đủ thông tin (Basic Flow)**
- **GIVEN** Người dùng đang ở trang danh sách sản phẩm
- **WHEN** Người dùng nhấp chọn một thẻ sản phẩm cụ thể
- **THEN** Hệ thống hiển thị màn hình Chi tiết sản phẩm bao gồm: hình ảnh, giá gốc, giá bán, mô tả, và đánh giá (chỉ đọc)
- **AND** Thời gian tải màn hình chi tiết phải dưới 2 giây (NFR01-1)

**AC2: Thuật toán Gợi ý Sản phẩm liên quan (Basic Flow & Business Rule)**
- **GIVEN** Người dùng cuộn xuống phần "Sản phẩm liên quan" ở cuối trang chi tiết
- **WHEN** Hệ thống hiển thị danh sách sản phẩm gợi ý
- **THEN** Các sản phẩm được trả về bắt buộc phải có cùng Danh mục cấp 3 (Level 3 Category) với sản phẩm đang xem (BR01-1)

#### US-01.4: Ràng buộc quyền hạn của Khách vãng lai (Guest)
**Story:** Là Chủ sàn (hoặc Quản trị viên), tôi muốn yêu cầu Khách vãng lai phải đăng nhập khi họ muốn tương tác sâu, để thúc đẩy họ đăng ký tài khoản và gia tăng lượng thành viên cho nền tảng.

**AC1: Chặn tương tác mua sắm (Exception Flow 6b & Business Rule)**
**Scenario Outline: Khách vãng lai cố tình thao tác các tính năng yêu cầu định danh.**
- **GIVEN** Người dùng đang ở trạng thái Khách vãng lai (chưa đăng nhập)
- **WHEN** Người dùng nhấp vào nút <tên_thao_tác>
- **THEN** Hệ thống chặn thao tác này lại
- **AND** Hệ thống tự động hiển thị yêu cầu / chuyển hướng sang trang Đăng nhập/Đăng ký (BR01-2)

**Examples (Bảng dữ liệu kiểm thử):**

| tên_thao_tác |
| :--- |
| Thêm vào giỏ hàng |
| Mua ngay |
| Thêm vào Wishlist |
| Gửi đánh giá |

#### US-01.5: Truy cập các trang Thông tin tĩnh
**Story:** Là một Người dùng, tôi muốn đọc các trang thông tin tĩnh, để hiểu rõ về các quy định và chính sách hoạt động của sàn giao dịch.

**AC1: Điều hướng trang tĩnh (Alternative Flow 3a)**
**Scenario Outline: Người dùng truy cập các liên kết Footer/Menu.**
- **GIVEN** Người dùng đang ở bất kỳ giao diện nào trên nền tảng
- **WHEN** Người dùng nhấp vào liên kết <tên_trang_tĩnh>
- **THEN** Hệ thống điều hướng và hiển thị chính xác nội dung tĩnh của <tên_trang_tĩnh>

**Examples (Bảng dữ liệu kiểm thử):**

| tên_trang_tĩnh |
| :--- |
| FAQ |
| Tin tức |
| Chính sách giao hàng |
| Chính sách hoàn trả |

### Feature 1.2: Chatbot AI (UC07)

#### US-07.1: Tra cứu thông tin chung (Public Data)
**Story:** Là một Người dùng (Guest/Customer), tôi muốn hỏi Chatbot các thông tin chung về nền tảng, để tôi có thể hiểu rõ về sàn và xu hướng sản phẩm mà không cần tự tìm kiếm.

**AC1: Tra cứu thành công (Basic Flow & NFR07-1 & BR07-1)**
- **GIVEN** Người dùng đang mở khung Chatbot AI
- **AND** Hệ thống AI Server đang hoạt động bình thường
- **WHEN** Người dùng gửi câu hỏi về thông tin chung (hướng dẫn, sản phẩm nổi bật, khuyến mãi)
- **THEN** Chatbot phân tích ý định (intent) và phản hồi thông tin chính xác dựa trên kho dữ liệu Public
- **AND** Thời gian xử lý và trả lời của AI không vượt quá 3 giây (NFR07-1)

**AC2: AI không hiểu ý định (Exception Flow 5b)**
- **GIVEN** Người dùng đang mở khung Chatbot AI
- **WHEN** Người dùng gửi câu hỏi nằm ngoài phạm vi dữ liệu hệ thống (Ví dụ: thời tiết, chính trị)
- **THEN** Chatbot hiển thị thông báo từ chối khéo léo
- **AND** Chatbot đưa ra hướng dẫn gợi ý khách hàng hỏi các vấn đề liên quan đến mua sắm

#### US-07.2: Nhận diện ngữ cảnh sản phẩm hiện tại (Context-Aware)
**Story:** Là một Người dùng, tôi muốn Chatbot tự động hiểu tôi đang hỏi về sản phẩm nào khi tôi đang ở trang chi tiết của sản phẩm đó, để tôi không phải gõ lại tên sản phẩm.

**AC1: Bắt đúng ngữ cảnh sản phẩm (Alternative Flow 2a)**
- **GIVEN** Người dùng đang đứng tại giao diện "Chi tiết sản phẩm"
- **WHEN** Người dùng gửi câu hỏi tra cứu thông số qua Chatbot (Ví dụ: "Sản phẩm này giảm giá bao nhiêu?")
- **THEN** Hệ thống ngầm định đính kèm Product_ID của sản phẩm đang xem vào payload gửi lên AI Server
- **AND** Chatbot trả về chính xác thông số (% giảm giá, màu sắc) của riêng sản phẩm đó

#### US-07.3: Truy vấn dữ liệu cá nhân & Phân quyền (Private Data)
**Story:** Là một Khách hàng (đã đăng nhập), tôi muốn tra cứu nhanh tình trạng đơn hàng hoặc giỏ hàng cá nhân qua Chatbot, để tiết kiệm thời gian điều hướng vào trang quản lý.

**AC1: Khách hàng tra cứu thành công (Basic Flow & BR07-2)**
- **GIVEN** Người dùng có trạng thái Khách hàng
- **WHEN** Người dùng gửi câu hỏi tra cứu dữ liệu cá nhân (Ví dụ: "Đơn hàng của tôi đâu?")
- **THEN** Chatbot được cấp quyền truy xuất cơ sở dữ liệu Private của Khách hàng
- **AND** Chatbot phản hồi chính xác thông tin cá nhân tương ứng

**AC2: Chặn Khách vãng lai truy vấn (Exception Flow 5a & BR07-1)**
**Scenario Outline: Khách vãng lai cố tình hỏi thông tin cá nhân.**
- **GIVEN** Người dùng đang ở trạng thái Khách vãng lai (Guest)
- **WHEN** Người dùng gửi câu hỏi tra cứu về <loại_dữ_liệu>
- **THEN** Chatbot từ chối cung cấp thông tin
- **AND** Chatbot hiển thị yêu cầu đăng nhập kèm theo nút "Đi đến trang Đăng nhập"

**Examples (Bảng dữ liệu kiểm thử):**

| loại_dữ_liệu |
| :--- |
| Tình trạng đơn hàng |
| Giỏ hàng cá nhân |
| Số dư ví xu |

### Feature 1.3: Chat real-time (UC13)

#### US-13.1: Khởi tạo cuộc trò chuyện
**Story:** Là một Người dùng (Khách hàng hoặc Người bán), tôi muốn chủ động mở hộp thoại chat với đối phương, để trao đổi trực tiếp về sản phẩm hoặc đơn hàng.

**AC1: Khách hàng khởi tạo (Basic Flow)**
- **GIVEN** Khách hàng đã đăng nhập và đang ở trang Chi tiết sản phẩm (hoặc trang Đơn hàng)
- **WHEN** Khách hàng nhấp vào nút "Chat với Người bán"
- **THEN** Hệ thống thiết lập kênh kết nối WebSockets
- **AND** Hệ thống hiển thị cửa sổ hộp thoại trò chuyện

**AC2: Người bán chủ động khởi tạo (Alternative Flow 1a)**
- **GIVEN** Người bán đang ở giao diện Bảng điều khiển (Quản lý đơn hàng)
- **WHEN** Người bán nhấp vào nút "Phản hồi Khách hàng" tại một đơn hàng cụ thể (Ví dụ: để báo hết màu/size)
- **THEN** Hệ thống thiết lập kênh kết nối WebSockets
- **AND** Hệ thống mở cửa sổ hộp thoại trò chuyện với Khách hàng đó

**AC3: Đảm bảo quyền riêng tư (Business Rule)**
- **GIVEN** Cửa sổ trò chuyện đã được mở
- **WHEN** Hệ thống tải lịch sử tin nhắn
- **THEN** Dữ liệu trò chuyện chỉ được phép hiển thị cho đúng Khách hàng và Gian hàng đang tham gia (Bảo mật riêng tư 1-1) (BR13-1)

#### US-13.2: Gửi và hiển thị tin nhắn thời gian thực
**Story:** Là một Người dùng tham gia trò chuyện, tôi muốn tin nhắn được gửi và hiển thị ngay lập tức, để cuộc trao đổi diễn ra liền mạch như giao tiếp ngoài đời.

**AC1: Nhắn tin thành công (Basic Flow)**
- **GIVEN** Người dùng đang mở cửa sổ hộp thoại trò chuyện
- **WHEN** Người dùng nhập nội dung và nhấn nút "Gửi"
- **THEN** Hệ thống lưu tin nhắn vào cơ sở dữ liệu
- **AND** Hệ thống ngay lập tức đẩy (push) tin nhắn đó đến màn hình của đối phương mà không cần tải lại trang web

**AC2: Ràng buộc hiệu năng WebSockets (Non-Functional Requirement)**
- **GIVEN** Nền tảng đang duy trì kết nối WebSockets bằng STOMP/SockJS
- **WHEN** Một tin nhắn mới được gửi đi
- **THEN** Độ trễ hiển thị (Latency) từ lúc gửi đến lúc xuất hiện trên thiết bị của người nhận không được vượt quá 1 giây (NFR13-1)

#### US-13.3: Xử lý gián đoạn kết nối mạng
**Story:** Là một Người dùng, tôi muốn hộp thoại tự động bảo vệ nội dung khi rớt mạng và đồng bộ lại khi có mạng, để tôi không bị mất các tin nhắn quan trọng.

**AC1: Xử lý khi rớt mạng (Exception Flow 2a)**
- **GIVEN** Người dùng đang trong cửa sổ trò chuyện
- **WHEN** Thiết bị của người dùng bị mất kết nối Internet
- **THEN** Hệ thống hiển thị thông báo "Đang mất kết nối..."
- **AND** Hệ thống tạm thời vô hiệu hóa (làm mờ) nút "Gửi" để ngăn chặn mất dữ liệu

**AC2: Khôi phục kết nối tự động (Exception Flow 2a)**
- **GIVEN** Hệ thống đang ở trạng thái mất kết nối
- **WHEN** Thiết bị có kết nối Internet trở lại
- **THEN** Hệ thống tự động thiết lập lại kết nối WebSockets (Re-connect)
- **AND** Hệ thống tự động truy xuất (fetch) và cập nhật toàn bộ các tin nhắn bị nhỡ trong lúc rớt mạng
- **AND** Hệ thống gỡ bỏ làm mờ và kích hoạt lại nút "Gửi"

---

## EPIC 2: QUẢN LÝ TÀI KHOẢN & XÁC THỰC (Identity & Access)
### Feature 2.1: Đăng nhập (UC27)

#### US-27.1: Đăng nhập bằng Email và Mật khẩu truyền thống
**Story:** Là một Người dùng, tôi muốn đăng nhập bằng Email và Mật khẩu đã đăng ký, để hệ thống xác thực danh tính và cho phép tôi truy cập an toàn vào không gian làm việc của mình.

**AC1: Đăng nhập thành công (Basic Flow & NFR27-1, NFR27-2)**
- **GIVEN** Người dùng đang ở màn hình Đăng nhập
- **AND** Dữ liệu Email tồn tại và Mật khẩu nhập vào trùng khớp với mật khẩu băm BCrypt trong cơ sở dữ liệu (NFR27-2)
- **WHEN** Người dùng nhấn nút "Đăng nhập"
- **THEN** Hệ thống khởi tạo phiên làm việc (NFR27-1)
- **AND** Hệ thống tự động nhận diện Phân quyền và điều hướng (Khách hàng về Trang chủ, Người bán/Admin về Bảng điều khiển)

**AC2: Sai thông tin đăng nhập (Exception Flow 3a)**
- **GIVEN** Người dùng đang ở màn hình Đăng nhập
- **WHEN** Người dùng nhập sai Email hoặc Mật khẩu và nhấn "Đăng nhập"
- **THEN** Hệ thống chặn truy cập (không tạo phiên làm việc)
- **AND** Hệ thống hiển thị báo lỗi "Email hoặc mật khẩu không chính xác"

#### US-27.2: Đăng nhập không mật khẩu bằng mã OTP (Passwordless)
**Story:** Là một Người dùng, tôi muốn đăng nhập bằng mã OTP gửi qua Email mà không cần nhớ mật khẩu, để thao tác nhanh chóng và thuận tiện hơn.

**AC1: Yêu cầu gửi OTP (Alternative Flow 1a)**
- **GIVEN** Người dùng đã nhập Email hợp lệ vào ô dữ liệu
- **WHEN** Người dùng nhấn nút "Đăng nhập bằng OTP"
- **THEN** Hệ thống khởi tạo và gửi một mã xác thực 6 số về Email của người dùng đó

**AC2: Đăng nhập thành công với OTP (Alternative Flow 1a & BR27-1)**
- **GIVEN** Người dùng đang ở màn hình nhập mã OTP
- **AND** Mã OTP vẫn còn hiệu lực (chưa quá 5 phút và chưa từng được sử dụng) (BR27-1)
- **WHEN** Người dùng nhập đúng mã OTP và nhấn "Xác nhận"
- **THEN** Hệ thống khởi tạo phiên làm việc (NFR27-1)
- **AND** Hệ thống tự động điều hướng người dùng dựa theo phân quyền hiện tại

**AC3: Xác thực OTP thất bại (Exception Flow 1a3.1 & BR27-1)**
**Scenario Outline: Người dùng nhập mã OTP không hợp lệ.**
- **GIVEN** Người dùng đang ở màn hình nhập mã OTP
- **WHEN** Người dùng nhập mã <trạng_thái_mã> và nhấn "Xác nhận"
- **THEN** Hệ thống chặn truy cập
- **AND** Hệ thống báo lỗi "Mã OTP không hợp lệ hoặc đã hết hạn"

**Examples (Bảng dữ liệu kiểm thử):**

| trạng_thái_mã |
| :--- |
| Mã OTP sai (không khớp với mã hệ thống gửi) |
| Mã OTP đã quá thời gian 5 phút (hết hạn) |
| Mã OTP đã được sử dụng 1 lần trước đó (BR27-1) |

#### US-27.3: Đăng nhập bằng tài khoản Google
**Story:** Là một Khách hàng, tôi muốn đăng nhập nhanh bằng tài khoản Google có sẵn, để tôi có thể bắt đầu mua sắm ngay mà không cần tốn thời gian điền form đăng ký.

**AC1: Xác thực Google thành công (Alternative Flow 1b & BR27-2, NFR27-3)**
- **GIVEN** Người dùng đang ở trang Đăng nhập và nhấp vào nút "Đăng nhập với Google"
- **AND** Giao thức kết nối được sử dụng là chuẩn bảo mật OAuth2 (NFR27-3)
- **WHEN** Người dùng đồng ý cấp quyền trên màn hình xác thực của Google
- **THEN** Hệ thống đối chiếu Email, tự động tạo mới tài khoản nếu chưa từng tồn tại
- **AND** Hệ thống mặc định chỉ cấp quyền ROLE_CUSTOMER cho tài khoản đăng nhập theo cách này (BR27-2)
- **AND** Hệ thống cấp khởi tạo phiên làm việc và điều hướng về Trang chủ

**AC2: Khách hàng từ chối cấp quyền (Exception Flow 1b3.1)**
- **GIVEN** Người dùng đang ở màn hình xác thực của Google
- **WHEN** Người dùng chọn nút "Hủy" hoặc từ chối cấp quyền
- **THEN** Quá trình đăng nhập bị hệ thống hủy bỏ
- **AND** Hệ thống đưa người dùng về lại trang Đăng nhập mặc định của nền tảng

#### US-27.4: Đăng nhập bằng tài khoản Facebook
**Story:** Là một Khách hàng, tôi muốn đăng nhập nhanh bằng tài khoản Facebook, để tôi có đa dạng sự lựa chọn xác thực mà không phải tạo thêm tài khoản mới.

**AC1: Xác thực Facebook thành công (Alternative Flow 1b & BR27-2, NFR27-3)**
- **GIVEN** Người dùng đang ở trang Đăng nhập và nhấp vào nút "Đăng nhập với Facebook"
- **AND** Giao thức kết nối được sử dụng là chuẩn bảo mật OAuth2 (NFR27-3)
- **WHEN** Người dùng đồng ý cấp quyền trên màn hình xác thực của Facebook
- **THEN** Hệ thống đối chiếu Email, tự động tạo mới tài khoản nếu chưa từng tồn tại
- **AND** Hệ thống mặc định chỉ cấp quyền ROLE_CUSTOMER cho tài khoản đăng nhập theo cách này (BR27-2)
- **AND** Hệ thống khởi tạo phiên làm việc và điều hướng về Trang chủ

**AC2: Khách hàng từ chối cấp quyền (Exception Flow 1b3.1)**
- **GIVEN** Người dùng đang ở màn hình xác thực của Facebook
- **WHEN** Người dùng chọn nút "Hủy" hoặc từ chối cấp quyền
- **THEN** Quá trình đăng nhập bị hệ thống hủy bỏ
- **AND** Hệ thống đưa người dùng về lại trang Đăng nhập mặc định của nền tảng

#### US-27.5: Đăng nhập Bảo mật 2 lớp (2FA) dành riêng cho Admin
**Story:** Là một Quản trị viên (Admin), tôi muốn hệ thống yêu cầu xác thực thêm bằng mã OTP sau khi nhập đúng mật khẩu, để ngăn chặn rủi ro truy cập trái phép vào không gian vận hành cao nhất của sàn.

**AC1: Xác thực bước 2 thành công (Alternative Flow 1c)**
- **GIVEN** Quản trị viên truy cập đường dẫn đăng nhập riêng và đã nhập đúng Email/Mật khẩu
- **AND** Hệ thống đã tự động gửi mã OTP xác thực bước 2 về Email
- **WHEN** Admin nhập đúng mã OTP và nhấn "Xác nhận"
- **THEN** Hệ thống cấp quyền truy cập  (NFR27-1)
- **AND** Hệ thống điều hướng Admin vào Admin Dashboard an toàn

### Feature 2.2: Đăng ký & Đăng xuất (UC02, UC28)

#### US-02.1: Điền thông tin và Yêu cầu gửi mã OTP
**Story:** Là một Khách vãng lai, tôi muốn điền thông tin cá nhân và yêu cầu gửi mã xác thực, để bắt đầu quá trình tạo tài khoản an toàn.

**AC1: Gửi OTP thành công (Basic Flow & BR02-1, NFR02-1)**
- **GIVEN** Người dùng đang ở biểu mẫu Đăng ký và chưa đăng nhập
- **AND** Người dùng đã điền đầy đủ các thông tin bắt buộc và Email hợp lệ
- **WHEN** Người dùng nhấn nút "Gửi mã OTP"
- **THEN** Hệ thống tạo một mã OTP 6 số ngẫu nhiên và gửi đến Email người dùng (BR02-1)
- **AND** Thời gian hệ thống gửi Email không vượt quá 5 giây (NFR02-1)
- **AND** Hệ thống chuyển sang giao diện chờ nhập mã OTP

**AC2: Kiểm tra định dạng Email đầu vào (Exception Flow 4a)**
**Scenario Outline: Người dùng nhập thông tin Email không hợp lệ để lấy mã**.
- **GIVEN** Người dùng đang điền biểu mẫu Đăng ký
- **WHEN** Người dùng nhập giá trị <email> và nhấn nút "Gửi mã OTP"
- **THEN** Hệ thống phản hồi trạng thái <kết_quả>

**Examples:**

| email | kết_quả |
| :--- | :--- |
| nguyenvan.a@gmail.com | Gửi mã OTP thành công, chuyển màn hình nhập mã. |
| nguyenvan.agmail.com | Báo lỗi "Email không đúng định dạng" tại ô nhập liệu và chặn gửi OTP. |
| (Bỏ trống) | Báo lỗi "Vui lòng nhập Email" tại ô nhập liệu và chặn gửi OTP. |

#### US-02.2: Xác thực OTP và Hoàn tất đăng ký
**Story:** Là một Khách vãng lai, tôi muốn xác thực mã OTP vừa nhận được, để hệ thống khởi tạo tài khoản Khách hàng mới cho tôi.

**AC1: Đăng ký thành công (Basic Flow)**
- **GIVEN** Người dùng đang ở màn hình nhập mã xác thực
- **AND** Mã OTP vẫn còn trong thời gian hiệu lực
- **WHEN** Người dùng nhập đúng mã OTP và nhấn "Tạo tài khoản"
- **THEN** Hệ thống khởi tạo tài khoản mới với quyền ROLE_CUSTOMER
- **AND** Hệ thống tự động khởi tạo phiên làm việc (Đăng nhập tự động)
- **AND** Hệ thống thông báo thành công và chuyển hướng về Trang chủ

**AC2: Xác thực OTP thất bại (Exception Flow 7b)**
**Scenario Outline: Người dùng nhập sai hoặc mã hết hạn**.
- **GIVEN** Người dùng đang ở màn hình nhập mã xác thực
- **WHEN** Người dùng nhập mã <trạng_thái_mã> và nhấn "Tạo tài khoản"
- **THEN** Hệ thống chặn lệnh tạo tài khoản và hiển thị cảnh báo <cảnh_báo>

**Examples:**

| trạng_thái_mã | cảnh_báo |
| :--- | :--- |
| Sai mã (không khớp) | "Mã OTP không chính xác hoặc đã hết hạn" |
| Quá thời gian 5 phút | "Mã OTP không chính xác hoặc đã hết hạn" |

#### US-02.3: Điều hướng giao diện Đăng ký / Đăng nhập
**Story:** Là một Khách vãng lai, tôi muốn có thể chuyển đổi nhanh sang màn hình Đăng nhập từ biểu mẫu Đăng ký, để tiết kiệm thao tác nếu nhớ ra mình đã có tài khoản.

**AC1: Chuyển sang Đăng nhập (Alternative Flow 3a)**
- **GIVEN** Người dùng đang ở màn hình biểu mẫu Đăng ký
- **WHEN** Người dùng nhấp vào liên kết "Chuyển sang Đăng nhập"
- **THEN** Hệ thống ngay lập tức chuyển đổi biểu mẫu sang màn hình Đăng nhập

#### US-28.1 (Update): Đăng xuất an toàn và Thu hồi phiên làm việc
**Story:** Là một Người dùng, tôi muốn đăng xuất khỏi hệ thống để kết thúc phiên làm việc an toàn, tránh bị người khác truy cập trái phép vào tài khoản của mình.

**AC1: Đăng xuất thành công (Basic Flow & NFR28-1)**
- **GIVEN** Người dùng đang ở trạng thái đăng nhập hợp lệ
- **WHEN** Người dùng nhấp chọn lệnh "Đăng xuất"
- **THEN** Hệ thống thu hồi quyền truy cập của phiên làm việc hiện tại ngay lập tức (NFR28-1)
- **AND** Hệ thống xóa bỏ các dữ liệu cá nhân tạm thời đang hiển thị trên giao diện
- **AND** Hệ thống tự động điều hướng người dùng trở về Trang chủ mặc định.

**AC2: Bảo vệ đường dẫn riêng tư sau khi Đăng xuất (Business Rule BR28-1)**
**Scenario Outline: Người dùng cố tình quay lại các liên kết làm việc sau khi đã đăng xuất.**
- **GIVEN** Người dùng đã đăng xuất thành công khỏi hệ thống
- **WHEN** Người dùng cố tình truy cập lại liên kết <url_riêng_tư> thông qua thanh địa chỉ trình duyệt
- **THEN** Hệ thống nhận diện không có phiên đăng nhập hợp lệ và chặn truy cập
- **AND** Hệ thống tự động chuyển hướng người dùng về trang Đăng nhập.

**Examples:**

| <url_riêng_tư> |
| :--- |
| /cart (Giỏ hàng) |
| /checkout (Thanh toán) |
| /my-orders (Đơn hàng của tôi) |
| /seller/dashboard (Bảng điều khiển Người bán) |

### Feature 2.3: Đổi / Quên mật khẩu (Tách từ UC29)

#### US-29.1: Đổi và Quên mật khẩu
**Story:** Là một Người dùng, tôi muốn lấy lại mật khẩu khi quên hoặc chủ động đổi mật khẩu mới, để bảo vệ an toàn cho tài khoản cá nhân.

**AC1: Ràng buộc lưu trữ mật khẩu mới (Business Rule & NFR)**
- **GIVEN** Người dùng đang ở bước thiết lập mật khẩu mới (đã xác thực OTP)
- **AND** Người dùng đã điền mật khẩu mới vào ô nhập liệu
- **WHEN** Người dùng nhấn nút "Xác nhận"
- **THEN** Hệ thống kiểm tra mật khẩu bắt buộc phải có độ dài tối thiểu 8 ký tự (BR29-1)
- **AND** Hệ thống phải mã hóa bằng BCrypt trước khi lưu dữ liệu vào Database (NFR29-1)

**AC2: Nhập Email không tồn tại khi quên mật khẩu (Exception Flow)**
- **GIVEN** Người dùng đang sử dụng biểu mẫu "Quên mật khẩu"
- **AND** Email vừa nhập không tồn tại trên hệ thống
- **WHEN** Người dùng nhấn nút "Gửi mã"
- **THEN** Hệ thống chặn lệnh gửi mã OTP
- **AND** Hệ thống hiển thị báo lỗi "Tài khoản Email không tồn tại"

**AC3: Nhập sai mật khẩu hiện tại khi đổi chủ động (Exception Flow)**
- **GIVEN** Người dùng đang sử dụng tính năng "Đổi mật khẩu" trong phần Quản lý tài khoản
- **AND** Mật khẩu hiện tại (Old Password) được nhập không khớp với Database
- **WHEN** Người dùng nhấn nút "Lưu"
- **THEN** Hệ thống từ chối cập nhật
- **AND** Hệ thống bôi đỏ ô nhập liệu và hiển thị thông báo lỗi tương ứng
### Feature 2.4: Cập nhật thông tin cá nhân (Tách từ UC10)

#### US-10.1: Chỉnh sửa thông tin hồ sơ
**Story:** Là một Khách hàng, tôi muốn cập nhật thông tin cá nhân (Họ tên, SĐT), để hệ thống lưu trữ đúng dữ liệu liên lạc chăm sóc khách hàng.

**AC1: Cập nhật thành công và đồng bộ UI (Basic Flow & NFR)**
- **GIVEN** Người dùng đang ở màn hình Hồ sơ cá nhân
- **AND** Các trường thông tin (Họ tên, Số điện thoại) đã được nhập hợp lệ
- **WHEN** Người dùng nhấn nút "Lưu thay đổi"
- **THEN** Hệ thống lưu dữ liệu thành công
- **AND** Tên Avatar trên Header được làm mới ngay lập tức qua Redux Toolkit mà không cần tải lại trang (NFR10-1)

**AC2: Ràng buộc trường Email (Business Rule)**
- **GIVEN** Người dùng đang ở màn hình Hồ sơ cá nhân
- **WHEN** Người dùng click chuột vào trường Email
- **THEN** Hệ thống không cho phép thao tác chỉnh sửa
- **AND** Trường Email luôn được khóa ở chế độ Chỉ đọc (Read-only) (BR10-1)

**AC3: Báo lỗi nhập liệu (Exception Flow)**
**Scenario Outline: Người dùng nhập sai định dạng hoặc thiếu thông tin bắt buộc.**
- **GIVEN** Người dùng đang điền biểu mẫu cập nhật thông tin
- **WHEN** Người dùng nhập <thông_tin_lỗi> và nhấn nút "Lưu thay đổi"
- **THEN** Hệ thống chặn lệnh lưu
- **AND** Hệ thống hiển thị cảnh báo lỗi bôi đỏ tương ứng

**Examples (Bảng dữ liệu kiểm thử):**

| thông_tin_lỗi |
| :--- |
| Bỏ trống trường "Họ tên" |
| Nhập sai định dạng "Số điện thoại" (Ví dụ: chứa chữ cái) |
### Feature 2.5: Quản lý Sổ địa chỉ (Tách từ UC11)

#### US-11.1 (Read): Xem danh sách địa chỉ
**Story:** Là một Khách hàng, tôi muốn xem danh sách các địa chỉ giao hàng đã lưu, để có thể bao quát được các điểm nhận hàng của mình.

**AC1: Tải và hiển thị danh sách (Basic Flow)**
- **GIVEN** Người dùng đã đăng nhập vào hệ thống
- **WHEN** Người dùng truy cập tab "Sổ địa chỉ"
- **THEN** Hệ thống tải và hiển thị danh sách các Thẻ địa chỉ (Address Cards)

#### US-11.2 (Create): Thêm địa chỉ mới
**Story:** Là một Khách hàng, tôi muốn thêm một địa chỉ giao hàng mới, để sử dụng cho các đơn hàng tiếp theo.

**AC1: Tích hợp API Hành chính (Basic Flow & NFR)**
- **GIVEN** Người dùng đang điền form Thêm địa chỉ mới
- **WHEN** Người dùng nhấp chọn ô nhập liệu Tỉnh/Thành/Phường/Xã
- **THEN** Hệ thống bắt buộc phải hiển thị dữ liệu dưới dạng Dropdown Select được gọi từ API hành chính chuẩn (NFR11-1)

**AC2: Bỏ trống thông tin (Exception Flow)**
- **GIVEN** Người dùng đang ở form Thêm địa chỉ mới
- **AND** Người dùng bỏ trống một (hoặc nhiều) trường thông tin bắt buộc
- **WHEN** Người dùng nhấn nút "Lưu"
- **THEN** Hệ thống chặn lệnh lưu
- **AND** Hệ thống hiển thị báo lỗi bôi đỏ tại các ô chưa nhập

#### US-11.3 (Update): Cập nhật địa chỉ hiện có
**Story:** Là một Khách hàng, tôi muốn sửa thông tin của một địa chỉ đã lưu, để đảm bảo shipper giao đúng nơi nếu tôi có thay đổi số điện thoại hoặc chuyển nhà.

**AC1: Tự động điền dữ liệu cũ (Basic Flow)**
- **GIVEN** Người dùng đang xem danh sách Sổ địa chỉ
- **WHEN** Người dùng nhấn nút "Chỉnh sửa" tại một Thẻ địa chỉ cụ thể
- **THEN** Hệ thống hiển thị Form cập nhật thông tin
- **AND** Form phải được tự động điền sẵn (pre-filled) dữ liệu cũ của địa chỉ đó

#### US-11.4 (Delete): Xóa địa chỉ và Ràng buộc an toàn
**Story:** Là một Khách hàng, tôi muốn xóa những địa chỉ không còn sử dụng, để làm gọn giao diện sổ địa chỉ của mình.

**AC1: Xóa thành công (Basic Flow)**
- **GIVEN** Người dùng chọn một địa chỉ đang không dính líu tới bất kỳ đơn hàng nào
- **WHEN** Người dùng nhấn lệnh "Xóa" tại địa chỉ đó
- **THEN** Hệ thống gỡ địa chỉ đó khỏi danh sách thành công

**AC2: Chặn xóa địa chỉ đang giao (Exception Flow)**
- **GIVEN** Người dùng chọn một địa chỉ đang được sử dụng cho một đơn hàng chưa giao xong
- **WHEN** Người dùng nhấn lệnh "Xóa" tại địa chỉ đó
- **THEN** Hệ thống hiển thị cảnh báo để bảo đảm an toàn tiến trình giao hàng
- **AND** Hệ thống từ chối lệnh xóa
### Feature 2.6: Theo dõi ví xu (Tách từ UC12)

#### US-12.1: Xem lịch sử biến động Ví Xu
**Story:** Là một Khách hàng, tôi muốn theo dõi biến động số dư Ví Xu, để biết khoản tiền có thể dùng giảm giá.

**AC1: Hiển thị đúng định dạng và thứ tự (Basic Flow, BR & NFR)**
- **GIVEN** Người dùng đã đăng nhập vào hệ thống
- **WHEN** Người dùng truy cập tab "Ví Xu"
- **THEN** Hệ thống tải và hiển thị Lịch sử biến động (Transaction History)
- **AND** Dữ liệu bắt buộc chỉ hiển thị ở chế độ Chỉ đọc (Read-only) (BR12-1)
- **AND** Danh sách phải được sắp xếp theo thứ tự thời gian mới nhất lên đầu (Sort DESC by Time) (NFR12-1)

---

## EPIC 3: MUA SẮM & THANH TOÁN (Cart & Checkout)
### Feature 3.1: Quản lý Giỏ hàng (Tách từ UC03)

#### US-03.1 (Create): Thêm sản phẩm vào giỏ
**Story:** Là một Khách hàng, tôi muốn thêm sản phẩm vào giỏ hàng, để chuẩn bị thanh toán cho món đồ mình thích.

**AC1: Thêm thành công (Basic Flow)**
- **GIVEN** Sản phẩm vẫn còn hàng trong kho (In-stock)
- **WHEN** Người dùng nhấn nút "Thêm vào giỏ"
- **THEN** Hệ thống cộng thêm sản phẩm vào giỏ hàng của người dùng (BR03-1)

**AC2: Chặn vượt tồn kho (Exception Flow)**
- **GIVEN** Sản phẩm chỉ còn 2 cái trong kho
- **WHEN** Người dùng nhập số lượng là 3 và nhấn "Thêm vào giỏ"
- **THEN** Hệ thống chặn thao tác và hiển thị báo lỗi bôi đỏ "Vượt quá số lượng tồn kho hiện tại"

#### US-03.2 (Update): Điều chỉnh sản phẩm trong giỏ (Số lượng & Phân loại)
**Story:** Là một Khách hàng, tôi muốn tăng/giảm số lượng hoặc thay đổi phân loại (màu sắc, kích cỡ) của món hàng ngay trong giỏ, để tôi linh hoạt tinh chỉnh đơn hàng mà không phải quay lại trang chi tiết sản phẩm.

**AC1: Tăng/giảm số lượng (Basic Flow & NFR03-1)**
- **GIVEN** Người dùng đang ở màn hình Giỏ hàng
- **WHEN** Người dùng nhấn nút Tăng (+) hoặc Giảm (-) số lượng
- **THEN** Hệ thống tự động tính toán lại Tổng tiền gốc (Total MRP) và Tổng tiền thanh toán của giỏ hàng
- **AND** Số tiền mới phải được hiển thị tức thì (Real-time) trên giao diện (NFR03-1)

**AC2: Thay đổi phân loại biến thể thành công (Alternative Flow)**
- **GIVEN** Sản phẩm trong giỏ hàng có cấu hình nhiều phân loại (Ví dụ: áo có nhiều màu/size)
- **AND** Người dùng đang mở menu (dropdown/popup) điều chỉnh phân loại của sản phẩm đó
- **WHEN** Người dùng chọn một Phân loại mới (Ví dụ: Đổi từ Size M sang Size L)
- **THEN** Hệ thống cập nhật lại biến thể của thẻ sản phẩm đó trong giỏ hàng
- **AND** Hệ thống tự động truy xuất và áp dụng mức giá mới ngay lập tức (nếu biến thể mới có mức giá khác với biến thể cũ)

**AC3: Báo lỗi khi biến thể mới hết tồn kho (Exception Flow)**
- **GIVEN** Người dùng đang mở menu điều chỉnh phân loại
- **AND** Phân loại mới mà người dùng định chọn đã hết hàng trong kho (Out of stock)
- **WHEN** Người dùng nhấn chọn Phân loại mới đó
- **THEN** Hệ thống hiển thị cảnh báo "Phân loại này hiện đã hết hàng"
- **AND** Hệ thống làm mờ (disable) nút xác nhận để chặn thao tác cập nhật

#### US-03.3 (Delete): Gỡ sản phẩm khỏi giỏ
**Story:** Là một Khách hàng, tôi muốn xóa sản phẩm ra khỏi giỏ hàng, vì tôi đã thay đổi ý định mua nó.

**AC1: Xóa thành công (Basic Flow)**
- **GIVEN** Người dùng đang ở màn hình Giỏ hàng
- **WHEN** Người dùng nhấn nút "Xóa" tại một thẻ sản phẩm
- **THEN** Hệ thống gỡ sản phẩm đó ra khỏi giỏ
- **AND** Hệ thống tự động trừ khoản tiền của sản phẩm đó khỏi Tổng hóa đơn

#### US-03.4 (Read): Xem chi tiết Giỏ hàng
**Story:** Là một Khách hàng, tôi muốn truy cập vào màn hình Giỏ hàng để xem lại toàn bộ danh sách các sản phẩm đã chọn và tổng tiền, nhằm kiểm tra lại trước khi quyết định đi đến bước Thanh toán.

**AC1: Hiển thị giỏ hàng có sản phẩm (Basic Flow)**
- **GIVEN** Khách hàng đã đăng nhập và giỏ hàng đang có ít nhất 1 sản phẩm
- **WHEN** Người dùng nhấp vào biểu tượng "Giỏ hàng" trên thanh điều hướng (hoặc truy cập trực tiếp URL)
- **THEN** Hệ thống tải và hiển thị danh sách các thẻ sản phẩm (bao gồm: hình ảnh, tên, phân loại, giá bán và số lượng)
- **AND** Hệ thống hiển thị bảng tính tóm tắt Tổng tiền gốc (Total MRP) và Tổng tiền thanh toán hiện tại

**AC2: Xử lý hiển thị Giỏ hàng trống (Alternative Flow)**
- **GIVEN** Khách hàng đã đăng nhập nhưng chưa có bất kỳ sản phẩm nào trong giỏ
- **WHEN** Người dùng nhấp vào biểu tượng "Giỏ hàng"
- **THEN** Hệ thống hiển thị giao diện thông báo "Giỏ hàng của bạn đang trống"
- **AND** Hệ thống hiển thị kèm một nút "Tiếp tục mua sắm" để điều hướng khách hàng trở lại Trang chủ hoặc Trang danh mục
### Feature 3.2: Danh sách yêu thích - Wishlist (Tách từ UC04)

#### US-04.1 (Create/Delete): Thêm hoặc Bỏ yêu thích sản phẩm (Toggle)
**Story:** Là một Khách hàng, tôi muốn nhấp vào biểu tượng Trái tim để đưa sản phẩm vào danh sách yêu thích (và ngược lại), để dễ dàng tìm lại chúng sau này.

**AC1: Thêm vào Wishlist (Basic Flow & NFR04-1, BR04-1)**
- **GIVEN** Sản phẩm CHƯA tồn tại trong Wishlist của người dùng
- **WHEN** Người dùng nhấn biểu tượng "Trái tim"
- **THEN** Hệ thống lưu sản phẩm vào Wishlist
- **AND** Biểu tượng Trái tim ngay lập tức được highlight (đổi màu) mà không cần tải lại trang (NFR04-1)

**AC2: Gỡ khỏi Wishlist (Alternative Flow 2a)**
- **GIVEN** Sản phẩm ĐÃ tồn tại trong Wishlist của người dùng
- **WHEN** Người dùng nhấn biểu tượng "Trái tim" đang highlight
- **THEN** Hệ thống gỡ sản phẩm khỏi Wishlist
- **AND** Biểu tượng Trái tim ngay lập tức mất highlight mà không cần tải lại trang

#### US-04.2 (Read): Xem danh sách Wishlist
**Story:** Là một Khách hàng, tôi muốn xem lại toàn bộ các sản phẩm đã thả tim, để quyết định xem hôm nay sẽ mua món nào.

**AC1: Hiển thị danh sách (Basic Flow)**
- **GIVEN** Người dùng đã đăng nhập
- **WHEN** Người dùng truy cập trang "Danh sách yêu thích"
- **THEN** Hệ thống truy xuất và hiển thị dạng lưới toàn bộ các sản phẩm khách hàng đã lưu
### Feature 3.3: Áp dụng ưu đãi & tính tiền (Tách từ UC05)

#### US-05.1: Tự động đề xuất mã giảm giá tốt nhất (Auto-apply)
**Story:** Là một Khách hàng, tôi muốn hệ thống tự động quét và áp dụng mã giảm giá mang lại mức chiết khấu cao nhất, để tôi tiết kiệm được tối đa chi phí mà không cần tự tính toán.

**AC1: Tự động áp dụng khi vào trang Thanh toán (Basic Flow)**
- **GIVEN** Người dùng vừa chuyển từ Giỏ hàng sang màn hình Thanh toán (Checkout)
- **WHEN** Hệ thống tải danh sách sản phẩm và tổng tiền hàng
- **THEN** Hệ thống tự động quét Kho Voucher và chọn ra 1 mã hợp lệ có mức giảm cao nhất
- **AND** Hệ thống áp dụng mã này vào hóa đơn và hiển thị số tiền được giảm
- **AND** Thuật toán quét và áp dụng phải xử lý xong dưới 1 giây (NFR05-1)

**AC2: Quét lại khi có sự thay đổi tham số (Business Rule 05-1)**
- **GIVEN** Người dùng đang ở màn hình Thanh toán
- **WHEN** Người dùng thay đổi "Địa chỉ giao nhận" HOẶC "Phương thức thanh toán"
- **THEN** Hệ thống phải tự động quét lại toàn bộ dữ liệu Voucher để cập nhật lại mã giảm giá và phí ship theo thời gian thực (BR05-1)

#### US-05.2: Thay đổi mã giảm giá thủ công (Manual Apply)
**Story:** Là một Khách hàng, tôi muốn chủ động chọn mã giảm giá khác từ kho hoặc tự nhập mã tay, để tôi có thể sử dụng các ưu đãi đặc biệt theo ý muốn.

**AC1: Chọn mã khác từ kho (Alternative Flow 4a)**
- **GIVEN** Người dùng mở danh sách "Chọn mã giảm giá"
- **WHEN** Người dùng nhấp chọn một mã hợp lệ khác và nhấn "Áp dụng"
- **THEN** Hệ thống gỡ bỏ mã cũ và áp dụng mức giảm của mã mới vào hóa đơn

**AC2: Nhập mã không đủ điều kiện (Exception Flow 4a2)**
**Scenario Outline: Người dùng nhập thủ công các mã không hợp lệ.**
- **GIVEN** Người dùng đang ở ô nhập mã giảm giá
- **WHEN** Người dùng nhập <mã> và nhấn "Áp dụng"
- **THEN** Hệ thống từ chối áp dụng và hiển thị lỗi <cảnh_báo>

**Examples (Bảng dữ liệu kiểm thử):**

| mã_vào | cảnh_báo |
| :--- | :--- |
| Mã đã hết hạn sử dụng | "Mã không hợp lệ hoặc đã hết hạn" |
| Mã yêu cầu thanh toán thẻ nhưng khách đang chọn COD | "Mã giảm giá không áp dụng cho phương thức thanh toán này" |
| Mã yêu cầu đơn tối thiểu 500k nhưng đơn hiện tại chỉ 200k | "Đơn hàng chưa đạt giá trị tối thiểu để sử dụng mã này" |

#### US-05.3: Sử dụng Xu tích lũy
**Story:** Là một Khách hàng, tôi muốn sử dụng Xu tích lũy trong ví để thanh toán, nhằm giảm thêm số tiền phải trả tiền mặt/thẻ.

**AC1: Áp dụng Xu hợp lệ (Basic Flow & BR05-3)**
- **GIVEN** Người dùng có số dư Xu lớn hơn 0 và đang chọn thanh toán trực tuyến
- **WHEN** Người dùng bật công tắc (toggle) "Dùng Xu tích lũy"
- **THEN** Hệ thống trừ đi số tiền tương ứng với tỷ giá quy đổi Xu (Ví dụ: 1 Xu = 1đ)
- **AND** Cho phép áp dụng Xu ĐỒNG THỜI với Mã giảm giá (Coupon)

**AC2: Chặn sử dụng Xu khi thanh toán COD (Business Rule 05-4)**
- **GIVEN** Người dùng đang chọn Phương thức thanh toán là "Thanh toán khi nhận hàng" (COD)
- **WHEN** Hệ thống hiển thị mục Xu tích lũy
- **THEN** Công tắc "Dùng Xu tích lũy" bị làm mờ (disabled)
- **AND** Hệ thống hiển thị dòng chú thích "Không hỗ trợ dùng Xu cho đơn hàng COD"

#### US-05.4: Tính toán bảng Tóm tắt đơn hàng (Final Payment)
**Story:** Là một Khách hàng, tôi muốn xem bảng tóm tắt chi tiết các khoản phí để xác nhận số tiền cuối cùng (Final Payment) cần phải trả.

**AC1: Hiển thị và tính toán đúng công thức (Business Rule 05-3 & CT_KH1)**
- **GIVEN** Các tham số ưu đãi (Voucher, Xu) và Địa chỉ nhận hàng đã được chốt
- **WHEN** Hệ thống hiển thị bảng Tóm tắt đơn hàng
- **THEN** Hệ thống phải hiển thị rõ các dòng: Tổng tiền hàng, Phí vận chuyển, Giảm giá Voucher, Giảm giá Xu
- **AND** Cột "Tổng thanh toán" (Final Payment) phải được tính chính xác theo công thức: Tổng tiền hàng - Coupon - Quy đổi Xu + Phí vận chuyển
### Feature 3.4: Xử lý Đặt hàng & Tách đơn (Tách từ UC05 - Split Order Core)

#### US-05.5: Checkout
**Story:** Là một Khách hàng, tôi muốn tiến hành đặt hàng từ Giỏ hàng, để hệ thống tổng hợp các sản phẩm đã chọn và cho phép tôi thiết lập thông tin giao nhận, phương thức thanh toán.

**AC1: Điều hướng và tải dữ liệu thành công (Basic Flow 1, 2, 3)**
- **GIVEN** Khách hàng đang ở màn hình Giỏ hàng và có ít nhất 1 sản phẩm được chọn
- **WHEN** Người dùng nhấn nút "Tiến hành đặt hàng" (Checkout)
- **THEN** Hệ thống điều hướng sang giao diện màn hình Thanh toán
- **AND** Hệ thống tải và hiển thị danh sách các sản phẩm chuẩn bị mua
- **AND** Hệ thống truy xuất Sổ địa chỉ của người dùng (Mặc định chọn địa chỉ được sử dụng gần nhất)
- **AND** Hệ thống hiển thị danh sách các Phương thức thanh toán (VNPay, SePay, Momo, COD) để khách hàng thao tác chọn.

**AC2: Ràng buộc Giỏ hàng trống (Business Rule / Exception Flow)**
- **GIVEN** Giỏ hàng của người dùng đang không có sản phẩm nào
- **WHEN** Người dùng cố tình truy cập thẳng vào đường dẫn /checkout trên trình duyệt
- **THEN** Hệ thống chặn truy cập vào luồng thanh toán
- **AND** Hệ thống tự động chuyển hướng người dùng về lại trang Giỏ hàng kèm cảnh báo "Giỏ hàng của bạn đang trống".

**AC3: Tích hợp Thêm địa chỉ mới tại chỗ (Alternative Flow 3a)**
- **GIVEN** Người dùng đang ở màn hình Thanh toán
- **WHEN** Người dùng nhấn nút "Thêm địa chỉ mới" và lưu thành công
- **THEN** Hệ thống lưu địa chỉ mới vào Sổ địa chỉ
- **AND** Tự động chọn địa chỉ vừa tạo làm địa chỉ giao nhận cho đơn hàng hiện tại mà không cần tải lại trang.

#### US-05.6: Gom nhóm và Tách đơn hàng đa nhà cung cấp (Split Order)
**Story:** Là một Chủ sàn (Business Owner), tôi muốn giỏ hàng chứa nhiều món đồ từ các shop khác nhau phải được tách thành các đơn hàng riêng biệt, để đối soát tài chính và theo dõi vận chuyển độc lập cho từng gian hàng nhưng Khách hàng vẫn chỉ cần thao tác thanh toán 1 lần duy nhất.

**AC1: Tách đơn thành công theo Seller (Basic Flow 8 & BR05-2, QĐ_KH4)**
- **GIVEN** Giỏ hàng của người dùng đang chứa nhiều sản phẩm có nguồn gốc từ các nhà cung cấp (Seller_ID) khác nhau
- **AND** Người dùng đã chốt Tổng tiền thanh toán (Final Payment) ở bước cuối cùng
- **WHEN** Người dùng nhấn nút "Xác nhận và Thanh toán" (Pay Now)
- **THEN** Hệ thống khởi tạo duy nhất 1 Lệnh thanh toán tổng (PaymentOrder) ghi nhận tổng số tiền giao dịch
- **AND** Hệ thống tự động phân tách và gom nhóm các thẻ sản phẩm theo từng Seller_ID để tạo ra các Đơn hàng phụ (Sub-Orders) riêng biệt trực thuộc lệnh thanh toán đó

#### US-05.7: Xử lý giao dịch COD và Ghi nhận đơn hàng
**Story:** Là một Khách hàng, tôi muốn hệ thống chốt đơn hàng và thông báo thành công cho hình thức trả tiền mặt, để tôi yên tâm là giao dịch đã được ghi nhận mà không phải qua bước quẹt thẻ rườm rà.

**AC1: Đặt hàng thành công qua COD (Alternative Flow 9b)**
- **GIVEN** Người dùng đang ở màn hình Thanh toán và Phương thức được chọn là "Thanh toán khi nhận hàng" (COD)
- **WHEN** Người dùng nhấn nút "Xác nhận và Đặt hàng"
- **THEN** Hệ thống chặn luồng và bỏ qua bước chuyển hướng sang Cổng thanh toán
- **AND** Các Đơn hàng (Orders) được tạo ra được gắn trạng thái "PLACED" (Đã đặt)
- **AND** Lệnh thanh toán tổng (PaymentOrder) được gắn trạng thái "COD_PENDING" (Chờ thu hộ)
- **AND** Hệ thống chuyển hướng người dùng sang màn hình "Đặt hàng thành công"

#### US-05.8: Cập nhật Tồn kho và Dọn dẹp dữ liệu (Post-processing)
**Story:** Là một Người bán, tôi muốn số lượng tồn kho của sản phẩm phải được trừ đi ngay sau khi khách chốt đơn, để tôi không bị rơi vào tình trạng bán lố hàng (Overselling) cho khách đến sau.

**AC1: Đồng bộ dữ liệu sau đặt hàng (Post-Condition & Alternative Flow 9b2)**
- **GIVEN** Đơn hàng của người dùng vừa được tạo và ghi nhận thành công trên hệ thống
- **WHEN** Hệ thống bắt đầu tiến trình xử lý hậu kỳ (Post-processing)
- **THEN** Hệ thống tự động trừ chính xác số lượng hàng đã mua khỏi Kho (In-stock) của từng Seller tương ứng
- **AND** Hệ thống xóa sạch các sản phẩm đã được thanh toán này ra khỏi Giỏ hàng của Khách hàng
- **AND** Hệ thống tự động gửi thông báo có đơn hàng mới đến Dashboard của các Seller liên quan
### Feature 3.5: Tích hợp Cổng thanh toán (Tách từ UC05 - Payment Gateways)

#### US-05.9: Chuyển hướng thanh toán qua cổng VNPay
**Story:** Là một Khách hàng, tôi muốn được chuyển hướng đến cổng thanh toán VNPay, để tôi có thể sử dụng thẻ ATM nội địa hoặc quét mã VNPAY-QR một cách an toàn tuyệt đối.

**AC1: Khởi tạo URL và chuyển hướng (Basic Flow & NFR05-2)**
- **GIVEN** Khách hàng đang ở màn hình Thanh toán và đã chọn phương thức "VNPay"
- **WHEN** Khách hàng nhấn nút "Xác nhận và Thanh toán" (Pay Now)
- **THEN** Hệ thống khởi tạo một liên kết giao dịch hợp lệ tương ứng với giá trị đơn hàng
- **AND** Hệ thống ngay lập tức chuyển hướng (Redirect) trình duyệt của Khách hàng sang trang thanh toán bảo mật của VNPay
- **AND** Hệ thống tuyệt đối không lưu trữ thông tin thẻ tín dụng nhạy cảm trên Cơ sở dữ liệu của sàn (NFR05-2)

#### US-05.10: Chuyển hướng thanh toán qua ví điện tử Momo
**Story:** Là một Khách hàng, tôi muốn được chuyển hướng đến ứng dụng hoặc cổng thanh toán Momo, để tôi thanh toán nhanh chóng bằng ví điện tử quen thuộc của mình.

**AC1: Khởi tạo URL và chuyển hướng (Basic Flow & NFR05-2)**
- **GIVEN** Khách hàng đang ở màn hình Thanh toán và đã chọn phương thức "Ví điện tử Momo"
- **WHEN** Khách hàng nhấn nút "Xác nhận và Thanh toán" (Pay Now)
- **THEN** Hệ thống khởi tạo một liên kết giao dịch Momo hợp lệ
- **AND** Hệ thống chuyển hướng trình duyệt sang màn hình quét mã QR của Momo hoặc tự động mở app Momo (nếu thao tác trên điện thoại)
- **AND** Hệ thống tuyệt đối không yêu cầu nhập hay lưu trữ thông tin thanh toán (tài khoản/mật khẩu ví) trên hệ thống sàn (NFR05-2)

#### US-05.11: Chuyển hướng thanh toán qua cổng SePay
**Story:** Là một Khách hàng, tôi muốn được chuyển hướng đến cổng thanh toán SePay, để thực hiện chuyển khoản ngân hàng tự động với nội dung được tạo sẵn.

**AC1: Khởi tạo URL và hiển thị QR (Basic Flow & NFR05-2)**
- **GIVEN** Khách hàng đang ở màn hình Thanh toán và đã chọn phương thức "SePay (Chuyển khoản)"
- **WHEN** Khách hàng nhấn nút "Xác nhận và Thanh toán" (Pay Now)
- **THEN** Hệ thống khởi tạo thông tin giao dịch chuyển khoản tự động
- **AND** Hệ thống chuyển hướng sang trang hiển thị mã QR Chuyển khoản chứa sẵn số tiền và cú pháp nội dung chính xác
- **AND** Hệ thống tuyệt đối không yêu cầu nhập hay lưu trữ thông tin thẻ/tài khoản ngân hàng trên hệ thống sàn (NFR05-2)

#### US-05.12: Xử lý giao dịch thành công qua Webhook
**Story:** Là một Hệ thống (Back-end), tôi muốn tự động nhận kết quả từ cổng thanh toán qua Webhook, để cập nhật trạng thái đơn hàng ngay khi tiền đã vào tài khoản sàn.

**AC1: Ghi nhận giao dịch thành công (Basic Flow 10)**
- **GIVEN** Khách hàng đã hoàn tất thanh toán hợp lệ trên trang của đối tác
- **WHEN** Máy chủ nhận được dữ liệu (Webhook callback) trả về trạng thái giao dịch thành công
- **THEN** Hệ thống cập nhật Lệnh thanh toán (PaymentOrder) sang trạng thái "SUCCESS"
- **AND** Hệ thống tự động chuyển trạng thái của các Đơn hàng phụ (Sub-Orders) liên quan thành "PLACED" (Đã đặt)
- **AND** Trình duyệt của Khách hàng được tự động chuyển hướng về màn hình "Giao dịch thành công".

#### US-05.13: Xử lý giao dịch thất bại hoặc bị hủy
**Story:** Là một Khách hàng, tôi muốn hệ thống không ghi nhận đơn hàng nếu tôi đổi ý hủy giao dịch hoặc thẻ bị từ chối, để tôi có thể thực hiện lại quá trình thanh toán bằng thẻ khác.

**AC1: Hủy giao dịch tại cổng thanh toán (Exception Flow 9a)**
- **GIVEN** Khách hàng đang ở giao diện của cổng thanh toán trực tuyến
- **WHEN** Khách hàng nhấn nút "Hủy" hoặc thông tin thẻ bị ngân hàng từ chối
- **THEN** Cổng thanh toán trả về trạng thái thất bại cho hệ thống
- **AND** Hệ thống ghi nhận thanh toán thất bại và chặn luồng tạo đơn hàng (Không tạo đơn).
- **AND** Hệ thống điều hướng Khách hàng trở lại trang Thanh toán (Checkout) kèm cảnh báo "Thanh toán thất bại, vui lòng thử lại".
### Feature 3.6: Theo dõi & Hủy đơn hàng (Tách từ UC06)

#### US-06.1 (Read): Xem danh sách lịch sử đơn hàng
**Story:** Là một Khách hàng, tôi muốn xem danh sách toàn bộ các đơn hàng đã đặt, để có thể kiểm tra lại lịch sử mua sắm và chi tiêu của mình.

**AC1: Tải và hiển thị danh sách (Basic Flow & NFR06-1)**
- **GIVEN** Khách hàng đã đăng nhập và truy cập vào mục "Đơn hàng của tôi" (My Orders)
- **WHEN** Hệ thống tải dữ liệu
- **THEN** Hệ thống truy xuất và hiển thị danh sách các đơn hàng đã đặt
- **AND** Hệ thống phải đảm bảo hiển thị đầy đủ dữ liệu lịch sử mua hàng trong thời gian tối thiểu 12 tháng qua để phục vụ tra cứu (NFR06-1).

#### US-06.2 (Read): Theo dõi chi tiết và tiến trình giao hàng (Order Stepper)
**Story:** Là một Khách hàng, tôi muốn tra cứu chi tiết tiến trình vận chuyển của một đơn hàng cụ thể, để biết chính xác khi nào món hàng sẽ được giao đến tay mình.

**AC1: Hiển thị Order Stepper đồng bộ (Basic Flow & BR06-1)**
- **GIVEN** Khách hàng đang ở danh sách đơn hàng
- **WHEN** Khách hàng nhấp vào xem chi tiết một đơn hàng cụ thể
- **THEN** Hệ thống hiển thị thanh tiến trình (Order Stepper) trực quan với các mốc: Đã đặt (Placed), Đã xác nhận (Confirmed), Đang giao (Shipped), và Đã giao (Delivered).
- **AND** Trạng thái hiển thị trên thanh này bắt buộc phải được đồng bộ trực tiếp từ Webhook của Đơn vị vận chuyển (GHTK/Grab) mà không bắt Khách hàng phải rời khỏi sàn để tra cứu (BR06-1).

#### US-06.3 (Update): Hủy đơn hàng và tự động hoàn tồn kho
**Story:** Là một Khách hàng, tôi muốn hủy đơn hàng vừa đặt, để không phải nhận hoặc thanh toán cho món hàng mà tôi đã thay đổi ý định mua.

**AC1: Hủy đơn thành công và trả lại kho (Alternative Flow & BR06-2)**
- **GIVEN** Khách hàng đang xem chi tiết đơn hàng
- **AND** Đơn hàng hiện tại đang ở trạng thái "Chờ xử lý" (Pending) hoặc "Đã đặt" (Placed) (BR06-2)
- **WHEN** Khách hàng nhấn lệnh "Hủy đơn hàng" và đồng ý xác nhận
- **THEN** Hệ thống chuyển trạng thái đơn hàng sang "Đã hủy" (Canceled)
- **AND** Hệ thống tự động cộng lại chính xác số lượng sản phẩm vào kho tồn (In-stock) của Seller tương ứng
- **AND** Hệ thống hiển thị thông báo hủy thành công.

**AC2: Vô hiệu hóa tính năng hủy đơn (Exception Flow)**
- **GIVEN** Khách hàng đang xem chi tiết đơn hàng
- **AND** Đơn hàng đã được Seller giao cho ĐVVC, chuyển sang trạng thái "Đang giao" (Shipped) hoặc "Đã giao" (Delivered)
- **WHEN** Hệ thống tải giao diện chi tiết
- **THEN** Hệ thống tự động ẩn đi hoặc làm mờ (disable) nút "Hủy đơn hàng" để chặn Khách hàng thực hiện thao tác này.

---

## EPIC 4: QUẢN LÝ BÁN HÀNG & VẬN CHUYỂN (Seller Center)
### Feature 4.1: Hồ sơ & Gian hàng (UC14)

#### US-14.1: Cập nhật thông tin doanh nghiệp và tài chính (Business Details)
**Story:** Là một Người bán, tôi muốn thiết lập thông tin định danh doanh nghiệp, tài khoản ngân hàng và địa chỉ kho, để hệ thống có cơ sở dữ liệu phục vụ đối soát dòng tiền và lấy hàng.

**AC1: Cập nhật thành công (Basic Flow)**
- **GIVEN** Người bán đang ở trang cấu hình Hồ sơ gian hàng
- **AND** Người bán đã điền đầy đủ các thông tin (Tên Shop, Tên chủ tài khoản)
- **WHEN** Người bán nhấn nút "Lưu thay đổi"
- **THEN** Hệ thống lưu dữ liệu mới vào cơ sở dữ liệu
- **AND** Hệ thống hiển thị thông báo "Cập nhật thành công"

**AC2: Ràng buộc thông tin đối soát bắt buộc (Exception Flow 5a & BR14-1)**
- **GIVEN** Người bán đang điền biểu mẫu Hồ sơ gian hàng
- **AND** Người bán bỏ trống một (hoặc nhiều) thông tin cốt lõi bao gồm: Mã số thuế (GST), Số tài khoản ngân hàng, hoặc Địa chỉ kho
- **WHEN** Người bán nhấn nút "Lưu thay đổi"
- **THEN** Hệ thống hiển thị báo lỗi validation bôi đỏ ngay dưới trường nhập liệu bị thiếu
- **AND** Hệ thống chặn lệnh lưu để đảm bảo an toàn cho quy trình đối soát tài chính sau này (BR14-1).

#### US-14.2: Tải lên Logo và Banner gian hàng (Storefront Media)
**Story:** Là một Người bán, tôi muốn tải lên Logo và Banner, để trang trí giao diện mặt tiền shop giúp tăng độ nhận diện thương hiệu và thu hút Khách hàng.

**AC1: Tải ảnh và tối ưu hóa hệ thống (Basic Flow 4, 6 & NFR14-1)**
- **GIVEN** Người bán đang thao tác tại khu vực cập nhật hình ảnh gian hàng
- **WHEN** Người bán chọn một tệp tin hình ảnh hợp lệ và nhấn "Tải lên"
- **THEN** Hệ thống tiếp nhận, lưu trữ hình ảnh thành công đồng thời xử lý tối ưu luồng tải để không làm ảnh hưởng đến băng thông và hiệu năng của máy chủ (NFR14-1)
- **AND** Hệ thống hiển thị trực quan hình ảnh vừa tải lên trên giao diện để Người bán xác nhận.

#### US-14.3: Xem trước giao diện mặt tiền gian hàng (Preview Storefront)
**Story:** Là một Người bán, tôi muốn xem trước giao diện gian hàng công khai của mình, để đảm bảo các thay đổi về hồ sơ và hình ảnh hiển thị đúng như những gì Khách hàng sẽ nhìn thấy.

**AC1: Điều hướng xem trước (Alternative Flow 7a)**
- **GIVEN** Người bán vừa lưu thành công các thay đổi hồ sơ hoặc hình ảnh
- **WHEN** Người bán nhấn nút "Xem gian hàng" (Preview Storefront)
- **THEN** Hệ thống mở một thẻ trình duyệt (Tab) mới
- **AND** Hệ thống truy xuất và hiển thị giao diện mặt tiền công khai của Shop (bao gồm Logo, Banner và danh sách sản phẩm) với dữ liệu mới nhất.
### Feature 4.2: Quản lý kho sản phẩm (UC15)

#### US-15.1 (Create): Đăng tải sản phẩm mới
**Story:** Là một Người bán, tôi muốn thêm sản phẩm mới vào gian hàng với đầy đủ hình ảnh và mức giá, để hàng hóa sẵn sàng được kinh doanh trên nền tảng.

**AC1: Đăng sản phẩm và Trạng thái mặc định (Basic Flow & BR15-2)**
- **GIVEN** Người bán đã điền hợp lệ các thông tin bắt buộc (Tên, Danh mục, Giá, Hình ảnh)
- **WHEN** Người bán nhấn lệnh "Đăng sản phẩm"
- **THEN** Hệ thống lưu sản phẩm mới vào kho
- **AND** Hệ thống bắt buộc đưa sản phẩm vào trạng thái "Chờ duyệt" (PENDING) để Quản trị viên kiểm tra, không cho phép hiển thị công khai ngay.

**AC2: Tự động tính toán chiết khấu (NFR15-1)**
- **GIVEN** Người bán đang thao tác tại biểu mẫu nhập giá tiền
- **WHEN** Người bán điền xong "Giá gốc" (MRP) và "Giá bán thực tế" hợp lệ
- **THEN** Hệ thống tự động tính toán chính xác phần trăm (%) Giảm giá và hiển thị ngay lập tức mà không cần tải lại trang.

**AC3: Báo lỗi giá bán không hợp lệ (Exception Flow)**
- **GIVEN** Người bán đang nhập giá tiền
- **WHEN** Người bán nhập "Giá bán thực tế" LỚN HƠN "Giá gốc"
- **THEN** Hệ thống bôi đỏ ô nhập liệu kèm thông báo "Giá bán không được lớn hơn giá gốc"
- **AND** Hệ thống làm mờ (disable) nút Đăng sản phẩm để chặn thao tác.

#### US-15.2 (Update): Chỉnh sửa chi tiết sản phẩm
**Story:** Là một Người bán, tôi muốn thay đổi thông tin chi tiết (tên, mô tả, giá, hình ảnh) của sản phẩm đã đăng, để tôi có thể cập nhật nội dung khi có sự thay đổi về mẫu mã hoặc chính sách giá.

**AC1: Tự động tải dữ liệu cũ (Basic Flow)**
- **GIVEN** Người bán đang xem danh sách sản phẩm
- **WHEN** Người bán chọn lệnh "Chỉnh sửa" tại một sản phẩm cụ thể
- **THEN** Hệ thống mở biểu mẫu cập nhật
- **AND** Toàn bộ dữ liệu hiện tại của sản phẩm đó (hình ảnh, tên, giá, mô tả) phải được tự động điền sẵn (pre-filled).

**AC2: Lưu thay đổi thành công (Basic Flow)**
- **GIVEN** Người bán đã chỉnh sửa xong các thông tin hợp lệ
- **WHEN** Người bán nhấn lệnh "Lưu cập nhật"
- **THEN** Hệ thống ghi đè thông tin mới lên sản phẩm hiện tại
- **AND** Hệ thống hiển thị thông báo "Cập nhật sản phẩm thành công".

#### US-15.3 (Read): Xem lưới danh sách sản phẩm
**Story:** Là một Người bán, tôi muốn truy cập màn hình Kho sản phẩm để bao quát được toàn bộ các mặt hàng mình đang kinh doanh cùng với tình trạng tồn kho hiện tại.

**AC1: Hiển thị lưới sản phẩm (Basic Flow)**
- **GIVEN** Người bán đã đăng nhập vào Bảng điều khiển
- **WHEN** Người bán truy cập trang "Quản lý sản phẩm"
- **THEN** Hệ thống hiển thị lưới danh sách các sản phẩm đang có
- **AND** Mỗi dòng sản phẩm phải hiển thị đủ: Hình ảnh, Tên, Giá bán, Số lượng tồn kho và Trạng thái duyệt.

#### US-15.4 (Update): Cập nhật số lượng tồn kho nhanh (Quick Update)
**Story:** Là một Người bán, tôi muốn sửa trực tiếp con số tồn kho ngay trên lưới danh sách, để tôi cập nhật số lượng nhanh chóng mà không phải mở trang chỉnh sửa chi tiết.

**AC1: Chỉnh sửa trực tiếp (Inline edit)**
- **GIVEN** Người bán đang xem lưới danh sách sản phẩm
- **WHEN** Người bán gõ số lượng mới tại cột "Tồn kho" và nhấn nút "Cập nhật" (Update) ở dòng đó
- **THEN** Hệ thống ghi nhận số lượng tồn kho mới
- **AND** Lưới danh sách cập nhật lại dữ liệu ngay lập tức mà không cần tải trang.
### Feature 4.3: Vận chuyển & Giao hàng (UC16)

#### US-16.1: Xác nhận đơn hàng mới
**Story:** Là một Người bán, tôi muốn kiểm tra và xác nhận các đơn hàng khách vừa đặt, để bắt đầu tiến hành quy trình đóng gói.

**AC1: Xác nhận thành công (Basic Flow)**
- **GIVEN** Người bán đang xem danh sách đơn hàng và có đơn ở trạng thái "Mới đặt" (PLACED)
- **WHEN** Người bán nhấn chọn lệnh "Xác nhận đơn" (Confirm)
- **THEN** Hệ thống cập nhật trạng thái đơn hàng đó sang "Đã xác nhận" (CONFIRMED)

**AC2: Báo lỗi do xung đột trạng thái (Exception Flow) (Mới bổ sung)**
- **GIVEN** Người bán đang xem thông tin một đơn hàng "Mới đặt" (PLACED)
- **AND** Đơn hàng đó vừa bị Khách hàng thao tác Hủy thành công từ trước (chuyển sang CANCELED)
- **WHEN** Người bán nhấn lệnh "Xác nhận đơn"
- **THEN** Hệ thống chặn thao tác và hiển thị cảnh báo: "Không thể xác nhận. Đơn hàng này vừa bị hủy bởi Khách hàng"
- **AND** Hệ thống tự động làm mới (reload) lại trạng thái đơn hàng trên lưới dữ liệu.

#### US-16.2: Từ chối / Hủy đơn hàng từ phía Seller (Hoàn toàn mới)
**Story:** Là một Người bán, tôi muốn có quyền từ chối/hủy các đơn hàng mới đặt, để tôi xử lý các trường hợp kho thực tế đã hết hàng hoặc sai sót hệ thống mà không bị hệ thống phạt tỷ lệ giao hàng muộn.

**AC1: Từ chối đơn hàng thành công (Alternative Flow)**
- **GIVEN** Đơn hàng đang ở trạng thái "Mới đặt" (PLACED) hoặc "Đã xác nhận" (CONFIRMED)
- **WHEN** Người bán nhấn lệnh "Hủy đơn" (Cancel Order) và chọn lý do (Ví dụ: Hết hàng, Sai giá)
- **THEN** Hệ thống chuyển trạng thái đơn sang "Đã hủy" (CANCELED)
- **AND** Hệ thống tự động cộng lại số lượng sản phẩm vào kho tồn (In-stock)
- **AND** Hệ thống tự động hoàn lại tiền cho Khách hàng nếu họ đã thanh toán trả trước (Theo luật hoàn tiền tự động QĐ_AD7).

**AC2: Ràng buộc chặn hủy đơn (Business Rule)**
- **GIVEN** Đơn hàng đã được Seller nhấn "Đẩy đơn vận chuyển" và chuyển sang trạng thái "Đang giao" (SHIPPED)
- **WHEN** Người bán mở giao diện chi tiết đơn
- **THEN** Nút "Hủy đơn" phải bị vô hiệu hóa (disabled) hoặc ẩn đi để chặn thao tác.

#### US-16.3: Tự động đẩy đơn và lấy Mã vận đơn
**Story:** Là một Người bán, tôi muốn hệ thống tự động liên kết với đơn vị vận chuyển để sinh mã vận đơn, giúp tôi tránh sai sót và gian lận khi phải nhập tay.

**AC1: Lấy mã vận đơn thành công (Basic Flow 4, 5, 6 & BR16-1)**
- **GIVEN** Đơn hàng đang ở trạng thái "Đã xác nhận" (CONFIRMED) và Người bán đã đóng gói xong
- **WHEN** Người bán nhấn lệnh "Đẩy đơn vận chuyển" (Ship Order)
- **THEN** Hệ thống tự động gửi thông tin giao nhận cho đối tác vận chuyển (GHTK/Grab) để lấy Mã vận đơn (Tracking ID)
- **AND** Hệ thống lưu Mã vận đơn này vào hệ thống và đổi trạng thái đơn sang "Đang giao" (SHIPPED)
- **AND** Tuyệt đối không cung cấp ô nhập liệu để chặn thao tác Người bán tự nhập tay Mã vận đơn (BR16-1).

**AC2: Ngăn chặn thao tác đúp (NFR16-1)**
- **GIVEN** Người bán vừa nhấn lệnh "Đẩy đơn vận chuyển"
- **WHEN** Quá trình kết nối với hãng vận chuyển đang diễn ra và có độ trễ
- **THEN** Giao diện phải hiển thị Vòng xoay tải (Loading Spinner) và làm mờ nút bấm để chặn Người bán nhấn đúp gây ra mã vận đơn rác (NFR16-1).

**AC3: Báo lỗi khi kết nối thất bại (Exception Flow 5a)**
- **GIVEN** Hệ thống đối tác vận chuyển bị lỗi mạng hoặc địa chỉ kho của Người bán không hợp lệ
- **WHEN** Người bán nhấn lệnh "Đẩy đơn vận chuyển"
- **THEN** Hệ thống hiển thị cảnh báo "Kết nối hãng vận chuyển thất bại. Vui lòng thử lại sau"
- **AND** Giữ nguyên trạng thái đơn hàng ở mức CONFIRMED.

#### US-16.4: In phiếu giao hàng (Vận đơn)
**Story:** Là một Người bán, tôi muốn in phiếu giao hàng chuẩn định dạng để dán lên kiện hàng, giúp tài xế đến lấy có thể dùng máy quét dễ dàng.

**AC1: Kết xuất PDF có mã vạch (Basic Flow 7 & BR16-2)**
- **GIVEN** Đơn hàng đã có Mã vận đơn hợp lệ
- **WHEN** Người bán nhấn lệnh "In phiếu giao hàng"
- **THEN** Hệ thống tự động kết xuất (generate) tài liệu định dạng PDF
- **AND** Trên tài liệu PDF bắt buộc phải chứa Mã vạch (Barcode) hoặc mã QR tương ứng với Tracking ID để quét được (BR16-2).

#### US-16.5: Tự động đồng bộ trạng thái giao hàng thành công (Auto-Update)
**Story:** Là một Người bán, tôi muốn trạng thái đơn hàng tự động chuyển sang "Đã giao" khi khách nhận được hàng, để tôi không phải theo dõi và thao tác cập nhật thủ công từng đơn một.

**AC1: Đồng bộ trạng thái ngầm (Alternative Flow 6a)**
- **GIVEN** Đơn hàng đang trên đường giao (trạng thái SHIPPED)
- **WHEN** Đối tác vận chuyển (Shipper) báo cáo đã giao hàng thành công cho khách
(💡 Ghi chú cho Dev: Kịch bản này được trigger ngầm qua Webhook của ĐVVC)
- **THEN** Hệ thống tự động tiếp nhận dữ liệu và chuyển trạng thái đơn hàng sang "Đã giao" (DELIVERED).
### Feature 4.4: Đối soát dòng tiền (UC18)

#### US-18.1 (Read & Calculate): Thống kê tổng quan Bảng điều khiển (Dashboard)
**Story:** Là một Người bán, tôi muốn xem các chỉ số thống kê và biểu đồ doanh thu tổng quan, để nắm bắt tình hình kinh doanh của gian hàng một cách trực quan mà không cần tính tay.

**AC1: Hiển thị thẻ chỉ số và biểu đồ (Basic Flow)**
- **GIVEN** Người bán truy cập vào trang Bảng điều khiển tổng quan
- **WHEN** Hệ thống tổng hợp dữ liệu giao dịch
- **THEN** Hệ thống hiển thị chính xác các thẻ thống kê: Tổng thu nhập (Total Earning), Tổng số đơn (Total Orders), Đơn bị hủy (Canceled Orders) và Tổng hoàn tiền (Total Refund)
- **AND** Hiển thị Biểu đồ doanh thu (Earning graphs) phân bổ theo thời gian (ngày, tuần, tháng)

**AC2: Đối soát tự động đơn hoàn tiền (Business Rule 18-2 & CT_SL2)**
- **GIVEN** Có một đơn hàng vừa được xử lý hoàn tiền thành công cho Khách hàng
- **WHEN** Hệ thống tự động tính toán lại báo cáo doanh thu
- **THEN** Khoản tiền hoàn trả đó phải được tự động trừ đi khỏi "Tổng thu nhập" (Total Earning)
- **AND** Được cộng dồn vào thống kê "Tổng hoàn tiền" (Total Refund) để đảm bảo minh bạch dòng tiền đối soát (BR18-2).

**AC3: Xử lý ngoại lệ chưa có dữ liệu (Exception Flow 2a)**
- **GIVEN** Người bán là gian hàng mới hoặc chưa phát sinh bất kỳ giao dịch nào
- **WHEN** Truy cập vào Bảng điều khiển
- **THEN** Hệ thống không báo lỗi mà chỉ hiển thị giá trị "0" tại các thẻ chỉ số
- **AND** Hiển thị giao diện "Chưa có dữ liệu" (Empty state) tại khu vực vẽ biểu đồ.

#### US-18.2 (Read): Theo dõi chi tiết Lịch sử giao dịch (Transactions)
**Story:** Là một Người bán, tôi muốn xem chi tiết dòng tiền ra/vào của từng đơn hàng cụ thể, để có thể đối soát minh bạch các khoản thu chi thực tế với nền tảng.

**AC1: Hiển thị lưới giao dịch chi tiết (Basic Flow)**
- **GIVEN** Người bán truy cập vào tab "Lịch sử giao dịch" (Transactions)
- **WHEN** Hệ thống tải dữ liệu
- **THEN** Hệ thống hiển thị danh sách chi tiết các luồng tiền ra/vào tương ứng với thông tin của từng mã đơn hàng cụ thể.

#### US-18.3 (Export): Xuất báo cáo dữ liệu định dạng Excel
**Story:** Là một Người bán, tôi muốn trích xuất dữ liệu lịch sử giao dịch và doanh thu ra file Excel, để phục vụ cho các nghiệp vụ kế toán và lưu trữ nội bộ của doanh nghiệp.

**AC1: Kết xuất file Excel thành công (Alternative Flow 5a & NFR18-1)**
- **GIVEN** Người bán đang xem màn hình Lịch sử giao dịch hoặc Báo cáo
- **WHEN** Người bán chọn lệnh "Xuất báo cáo" (Export)
- **THEN** Hệ thống tự động kết xuất dữ liệu thành tệp tin định dạng bảng tính (.xlsx) và tải xuống thiết bị của Người bán
- **AND** Dữ liệu trong file Excel phải hiển thị chuẩn font chữ tiếng Việt (Unicode), tuyệt đối không bị lỗi định dạng ký tự (NFR18-1).

---

## EPIC 5: HẬU MÃI & KHIẾU NẠI (Returns & Disputes)
### Feature 5.1: Đánh giá & Yêu cầu hoàn trả (UC08, UC09)

#### US-08.1 (Create): Đánh giá chất lượng sản phẩm
**Story:** Là một Khách hàng, tôi muốn chấm điểm và viết nhận xét cho món hàng mình đã mua, để phản hồi chất lượng cho Người bán và những người mua sau.

**AC1: Viết đánh giá thành công (Basic Flow & NFR08-1)**
- **GIVEN** Đơn hàng của Khách hàng đã được cập nhật trạng thái "Đã giao" (DELIVERED).
- **WHEN** Khách hàng điền số sao, nội dung bình luận, tải ảnh lên và nhấn lệnh "Gửi đánh giá".
- **THEN** Hệ thống lưu trữ bài đánh giá và hiển thị công khai trên trang chi tiết sản phẩm.
- **AND** Hệ thống ngay lập tức tính toán và cập nhật lại điểm đánh giá trung bình của sản phẩm đó.

**AC2: Ràng buộc chưa nhận hàng (Business Rule BR08-1)**
- **GIVEN** Đơn hàng đang ở các trạng thái vận chuyển khác (Ví dụ: Đang giao, Đã xác nhận).
- **WHEN** Khách hàng truy cập giao diện Lịch sử đơn hàng.
- **THEN** Hệ thống tự động ẩn hoặc vô hiệu hóa nút "Viết đánh giá" để chặn thao tác.

**AC3: Báo lỗi bỏ trống số sao (Exception Flow 4a)**
- **GIVEN** Khách hàng đang thao tác tại biểu mẫu đánh giá.
- **WHEN** Khách hàng để trống số sao (Rating) và nhấn "Gửi đánh giá".
- **THEN** Hệ thống báo lỗi bôi đỏ "Vui lòng chọn số sao đánh giá" và chặn thao tác gửi.

#### US-08.2 (Delete): Thu hồi bài đánh giá
**Story:** Là một Khách hàng, tôi muốn xóa bài đánh giá mình đã viết, để thu hồi lại những nhận xét không còn chính xác.

**AC1: Xóa đánh giá thành công (Alternative Flow 1a)**
- **GIVEN** Khách hàng đang xem lại bài đánh giá của chính mình.
- **WHEN** Khách hàng nhấn lệnh "Xóa" và đồng ý xác nhận.
- **THEN** Hệ thống gỡ bỏ bài đánh giá khỏi trang sản phẩm.
- **AND** Hệ thống tự động tính toán lại điểm đánh giá trung bình.

#### US-09.1 (Create): Khởi tạo Yêu cầu trả hàng và Hoàn tiền
**Story:** Là một Khách hàng, tôi muốn gửi yêu cầu trả lại hàng lỗi kèm theo bằng chứng, để hệ thống xem xét hoàn lại tiền cho tôi.

**AC1: Gửi yêu cầu thành công (Basic Flow)**
- **GIVEN** Đơn hàng đang ở trạng thái "Đã giao" (DELIVERED) và nằm trong thời hạn 7 ngày kể từ ngày nhận hàng.
- **WHEN** Khách hàng điền lý do chi tiết, tải lên minh chứng (hình ảnh/video) và gửi yêu cầu.
- **THEN** Hệ thống ghi nhận và chuyển đơn hàng sang trạng thái "Yêu cầu hoàn trả" (RETURN_REQUESTED).
- **AND** Hệ thống ngay lập tức tạm thời đóng băng khoản tiền đối soát của đơn hàng này đối với Người bán.

**AC2: Ràng buộc thời hạn hoàn trả (Exception Flow 1a & BR09-1)**
- **GIVEN** Đơn hàng đã được giao thành công vượt quá 7 ngày.
- **WHEN** Khách hàng mở màn hình chi tiết đơn hàng.
- **THEN** Hệ thống tự động ẩn hoặc vô hiệu hóa nút "Yêu cầu trả hàng", Khách hàng không thể thực hiện thao tác này.

#### US-09.2 : Kích hoạt quyền Khiếu nại (Escalate)
**Story:** Là một Khách hàng, tôi muốn khiếu nại lên Ban Quản trị nếu yêu cầu trả hàng của tôi bị Người bán làm khó dễ, để bảo vệ quyền lợi chính đáng của mình.

**AC1: Gửi khiếu nại lên Admin thành công (Alternative Flow 6a & BR09-2)**
- **GIVEN** Khách hàng có một yêu cầu trả hàng vừa bị Người bán từ chối.
- **WHEN** Khách hàng nhấn lệnh "Khiếu nại lên Admin" (Escalate to Admin).
- **THEN** Hệ thống ghi nhận và chuyển trạng thái đơn hàng sang "Đang tranh chấp" (DISPUTED) để chờ Quản trị viên (Admin) can thiệp làm trọng tài.

### Feature 5.2: Xử lý Hoàn trả & Khiếu nại (UC17, UC23)

#### US-17.1 : Người bán chấp nhận yêu cầu trả hàng
**Story:** Là một Người bán, tôi muốn chấp nhận yêu cầu trả hàng khi minh chứng của khách hợp lý, để hệ thống hướng dẫn khách gửi hàng về kho và xử lý hoàn tiền.

**AC1: Chấp nhận yêu cầu (Basic Flow)**
- **GIVEN** Đơn hàng đang ở trạng thái "Yêu cầu hoàn trả" (RETURN_REQUESTED)
- **WHEN** Người bán xem xét minh chứng và nhấn lệnh "Chấp nhận"
- **THEN** Hệ thống gửi thông báo yêu cầu Khách hàng đóng gói và gửi trả hàng về kho
- **AND** Hệ thống kích hoạt nút thao tác "Xác nhận hoàn tiền" cho Người bán.

**AC2: Hoàn tất trả hàng và Hoàn tiền (Basic Flow)**
- **GIVEN** Người bán đã nhận được kiện hàng trả về
- **WHEN** Người bán nhấn lệnh "Xác nhận hoàn tiền"
- **THEN** Hệ thống tự động gọi Cổng thanh toán để chuyển trả tiền về tài khoản Khách hàng
- **AND** Đơn hàng chuyển sang trạng thái "Đã hoàn tiền" (REFUNDED).

**AC3: Trải nghiệm xem minh chứng trực quan (NFR17-1)**
- **GIVEN** Người bán đang xem chi tiết yêu cầu hoàn trả
- **WHEN** Người bán thao tác phóng to (zoom) ảnh hoặc phát video minh chứng
- **THEN** Hệ thống tải và phát phương tiện mượt mà trực tiếp trên Bảng điều khiển mà không bắt buộc tải file về máy.

#### US-17.2 : Người bán từ chối yêu cầu trả hàng
**Story:** Là một Người bán, tôi muốn từ chối yêu cầu trả hàng nếu minh chứng không thuyết phục, để bảo vệ doanh thu hợp đáng của gian hàng.

**AC1: Từ chối bắt buộc nhập lý do (Alternative Flow & BR17-1)**
- **GIVEN** Người bán quyết định không chấp nhận yêu cầu trả hàng
- **WHEN** Người bán nhấn lệnh "Từ chối"
- **THEN** Hệ thống hiển thị biểu mẫu và bắt buộc Người bán phải nhập lý do từ chối
- **AND** Nếu bỏ trống lý do, hệ thống bôi đỏ báo lỗi và chặn thao tác gửi.

**AC2: Xử lý từ chối thành công (Alternative Flow)**
- **GIVEN** Người bán đã nhập lý do từ chối hợp lệ
- **WHEN** Người bán nhấn nút "Xác nhận"
- **THEN** Hệ thống lưu lại quyết định và gửi thông báo lý do từ chối cho Khách hàng
- **AND** Hệ thống mở khóa quyền "Khiếu nại" (Dispute) trên giao diện của Khách hàng.

#### US-17.3 : Cảnh báo vi phạm thời gian xử lý (SLA)
**Story:** Là một Hệ thống, tôi muốn cảnh báo Người bán nếu họ "ngâm" yêu cầu quá lâu, để đảm bảo quyền lợi và trải nghiệm của Khách hàng.

**AC1: Cảnh báo vi phạm SLA (Exception Flow & BR17-1)**
- **GIVEN** Có một yêu cầu trả hàng đang ở trạng thái chờ xử lý (RETURN_REQUESTED)
- **WHEN** Thời gian trôi qua vượt quá 3 ngày (SLA) kể từ lúc Khách hàng khởi tạo yêu cầu mà Người bán chưa có phản hồi
- **THEN** Hệ thống tự động hiển thị cảnh báo vi phạm thời gian xử lý đối với gian hàng đó.

#### US-23.1 : Admin chấp nhận khiếu nại và hoàn tiền tự động
**Story:** Là Quản trị viên, tôi muốn chấp nhận khiếu nại khi lỗi thuộc về Người bán, để hệ thống tự động hoàn lại tiền cho Khách hàng.

**AC1: Chấp nhận khiếu nại và Tự động hoàn tiền (Basic Flow & BR23-1, BR23-2)**
- **GIVEN** Đơn hàng đang bị khóa ở trạng thái "Đang tranh chấp" (DISPUTED)
- **WHEN** Quản trị viên chọn lệnh "Chấp nhận khiếu nại - Hoàn tiền"
- **THEN** Hệ thống Backend tự động gọi API của Cổng thanh toán để chuyển trả tiền thẳng về tài khoản của Khách hàng
- **AND** Chuyển đơn hàng sang trạng thái "Đã hoàn tiền" (REFUNDED) và đóng băng mọi thao tác khiếu nại tiếp theo.

**AC2: Xử lý ngoại lệ lỗi Cổng thanh toán (Exception Flow 5a)**
- **GIVEN** Quản trị viên vừa nhấn lệnh hoàn tiền
- **WHEN** Cổng thanh toán (VnPay/SePay) bị lỗi kết nối timeout hoặc từ chối lệnh
- **THEN** Hệ thống báo lỗi "Hoàn tiền thất bại do lỗi cổng thanh toán" cho Admin
- **AND** Giữ nguyên đơn hàng ở trạng thái DISPUTED để Admin có thể thử lại sau.

#### US-23.2 : Admin từ chối khiếu nại và giải phóng dòng tiền
**Story:** Là Quản trị viên, tôi muốn từ chối khiếu nại của Khách hàng nếu phát hiện gian lận hoặc bằng chứng vô lý, để giải phóng dòng tiền đối soát cho Người bán.

**AC1: Từ chối khiếu nại (Alternative Flow)**
- **GIVEN** Đơn hàng đang ở trạng thái "Đang tranh chấp" (DISPUTED)
- **WHEN** Quản trị viên chọn lệnh "Từ chối khiếu nại"
- **THEN** Hệ thống đóng hồ sơ khiếu nại vĩnh viễn
- **AND** Tự động mở khóa dòng tiền (đã bị đóng băng lúc khách yêu cầu trả hàng) để cộng doanh thu cho Người bán.

---

## EPIC 6: QUẢN TRỊ NỀN TẢNG (Admin Operations)
### Feature 6.1: Kiểm duyệt (UC19, UC20)

#### US-19.1 : Admin phê duyệt hồ sơ Người bán mới
**Story:** Là Quản trị viên, tôi muốn kiểm tra và phê duyệt các hồ sơ đăng ký gian hàng mới, để đảm bảo chỉ những doanh nghiệp cung cấp đủ thông tin hợp lệ mới được bán hàng trên sàn.

**AC1: Phê duyệt thành công (Basic Flow & BR19-1)**
- **GIVEN** Admin đang xem chi tiết hồ sơ của một Người bán ở trạng thái chờ xác minh (PENDING_VERIFICATION).
- **WHEN** Admin kiểm tra thông tin hợp lệ và chọn lệnh "Phê duyệt" (Approve).
- **THEN** Hệ thống chuyển trạng thái tài khoản của Seller sang "Đang hoạt động" (ACTIVE).
- **AND** Hệ thống tự động gửi Email thông báo chúc mừng và chính thức cấp quyền đăng tải sản phẩm cho Seller (BR19-1),.

**AC2: Từ chối do hồ sơ thiếu/sai thông tin (Exception Flow)**
- **GIVEN** Admin đang xem xét hồ sơ Người bán.
- **WHEN** Admin chọn lệnh "Từ chối" (Reject) và nhập lý do (VD: sai mã số thuế).
- **THEN** Hệ thống lưu lại quyết định và gửi Email yêu cầu Seller bổ sung, chỉnh sửa thông tin.

**AC3: Ghi nhận nhật ký kiểm toán ngầm (NFR19-1)**
- **GIVEN** Admin vừa thực hiện thành công lệnh Phê duyệt hoặc Từ chối.
- **WHEN** Hệ thống xử lý xong thao tác cập nhật dữ liệu.
- **THEN** Hệ thống bắt buộc phải tự động lưu vết hành động này vào bảng Nhật ký hệ thống (Audit Log) một cách bất đồng bộ để phục vụ kiểm toán,.

#### US-19.2 : Admin xử lý gian hàng vi phạm
**Story:** Là Quản trị viên, tôi muốn có quyền đình chỉ hoặc cấm vĩnh viễn các gian hàng vi phạm chính sách, để duy trì môi trường kinh doanh minh bạch và bảo vệ Khách hàng.

**AC1: Đình chỉ hoặc cấm vĩnh viễn (Alternative Flow)**
- **GIVEN** Admin đang thao tác trên một tài khoản Seller đang ở trạng thái hoạt động (ACTIVE).
- **WHEN** Admin chọn lệnh "Đình chỉ" (Suspend) hoặc "Cấm vĩnh viễn" (Ban),.
- **THEN** Hệ thống ngay lập tức khóa quyền đăng nhập vào Seller Dashboard của người bán đó.
- **AND** Hệ thống tự động ẩn toàn bộ sản phẩm thuộc sở hữu của gian hàng này khỏi Trang chủ và kết quả tìm kiếm của Khách hàng,.

#### US-20.1 : Admin kiểm duyệt Sản phẩm
**Story:** Là Quản trị viên, tôi muốn xem xét và duyệt các sản phẩm do Seller đăng tải, để chặn hàng giả, hàng cấm trước khi chúng được hiển thị công khai trên mặt tiền cửa hàng.

**AC1: Duyệt sản phẩm hiển thị công khai (Basic Flow & BR20-1)**
- **GIVEN** Có danh sách các sản phẩm mới đang bị hệ thống khóa mặc định ở trạng thái "Chờ duyệt" (PENDING) (BR20-1),.
- **WHEN** Admin kiểm tra thông tin và chọn lệnh "Phê duyệt" (Approve).
- **THEN** Hệ thống chuyển trạng thái sản phẩm sang "Đang bán" (ACTIVE).
- **AND** Sản phẩm ngay lập tức xuất hiện trên giao diện tìm kiếm và mặt tiền (Storefront) của Khách hàng.

**AC2: Từ chối sản phẩm (Alternative Flow)**
- **GIVEN** Admin phát hiện sản phẩm sai danh mục hoặc vi phạm.
- **WHEN** Admin chọn lệnh "Từ chối" (Reject) và nhập lý do.
- **THEN** Hệ thống chuyển sản phẩm về trạng thái từ chối (REJECTED) và gửi thông báo lý do để Seller chỉnh sửa lại.

**AC3: Giao diện tối ưu duyệt hàng loạt (NFR20-1)**
- **GIVEN** Admin đang ở trang danh sách sản phẩm chờ duyệt.
- **WHEN** Admin thao tác trên lưới dữ liệu.
- **THEN** Giao diện phải cho phép tick chọn nhiều sản phẩm cùng lúc để thực thi lệnh duyệt hàng loạt (Mass approval) một cách nhanh chóng,.

### Feature 6.2: Marketing & Homepage (UC21, UC22)

#### US-21.1 : Cấu hình Giao diện Trang chủ (Homepage Config)
**Story:** Là Quản trị viên, tôi muốn cấu hình động các Banner quảng cáo và lưới danh mục (Grid Categories) trên Trang chủ, để cập nhật diện mạo nền tảng theo các chiến dịch Marketing mà không cần nhờ lập trình viên can thiệp mã nguồn.

**AC1: Cập nhật và áp dụng thay đổi tức thì (Basic Flow & NFR21-1, BR21-1)**
- **GIVEN** Admin đang thao tác trên công cụ quản lý Trang chủ trực quan (WYSIWYG)
- **WHEN** Admin thiết lập xong một hình ảnh Banner hoặc Lưới danh mục và nhấn "Cập nhật"
- **THEN** Hệ thống lưu lại dữ liệu cấu hình mới.
- **AND** Hệ thống áp dụng thay đổi hiển thị ngay lập tức trên Trang chủ của mọi Khách hàng với độ trễ phản hồi dưới 1 giây (NFR21-1).

**AC2: Cảnh báo liên kết hình ảnh lỗi (Exception Flow 4a)**
- **GIVEN** Admin đang thêm hoặc chỉnh sửa hình ảnh hiển thị trên Trang chủ
- **WHEN** Admin cung cấp một đường dẫn hình ảnh (Image URL) sai định dạng hoặc bị lỗi
- **THEN** Khung xem trước (Preview) bị vỡ hình
- **AND** Hệ thống hiển thị thông báo "URL hình ảnh không hợp lệ" và chặn thao tác lưu.

#### US-22.1 : Phát hành Mã giảm giá toàn sàn (Coupons)
**Story:** Là Quản trị viên, tôi muốn tạo ra các Mã giảm giá (Coupon) áp dụng cho toàn bộ nền tảng, để kích thích nhu cầu mua sắm của Khách hàng.

**AC1: Khởi tạo Coupon thành công (Basic Flow & BR22-1)**
- **GIVEN** Admin đang mở biểu mẫu "Tạo Coupon mới"
- **WHEN** Admin điền đầy đủ Mã Coupon, % giảm giá, thời hạn, giá trị đơn hàng tối thiểu và nhấn "Khởi tạo".
- **THEN** Hệ thống lưu lại và kích hoạt trạng thái (Active) cho mã giảm giá này để Khách hàng có thể sử dụng.
- **AND** Khoản tiền giảm giá này sẽ được hệ thống đối soát nội bộ tự động bù đắp lại vào ví của Seller để đảm bảo Seller không bị lỗ doanh thu (BR22-1).

**AC2: Ràng buộc logic thời gian (Exception Flow 3a)**
- **GIVEN** Admin đang thiết lập thời hạn hiệu lực cho Mã giảm giá
- **WHEN** Admin chọn "Ngày kết thúc" (End Date) diễn ra TRƯỚC "Ngày bắt đầu" (Start Date).
- **THEN** Hệ thống bôi đỏ trường nhập liệu, cảnh báo lỗi logic thời gian và chặn lệnh khởi tạo.

#### US-22.2 : Cập nhật thông số Khuyến mãi (Deals)
**Story:** Là Quản trị viên, tôi muốn thay đổi mức chiết khấu và hình ảnh của các chương trình Deal hiện có, để linh hoạt điều chỉnh chiến lược giảm giá (Ví dụ: Deal Flash Sale trong ngày).

**AC1: Cập nhật Deal thành công (Alternative Flow 1a)**
- **GIVEN** Admin đang quản lý danh sách các Deal hiện có
- **WHEN** Admin thay đổi thông số phần trăm (%) giảm giá hoặc ảnh banner của một Deal và lưu lại.
- **THEN** Hệ thống ghi nhận mức giảm giá mới
- **AND** Giao diện khu vực khuyến mãi (Today's Deal) trên Trang chủ của Khách hàng được làm mới ngay lập tức với thông số vừa cập nhật.

### Feature 6.3: Khách hàng & Tài chính (UC24, UC25)

#### US-24.1 (Read): Xem danh sách khách hàng
**Story:** Là Quản trị viên, tôi muốn xem danh sách toàn bộ khách hàng trên hệ thống, để có cái nhìn tổng quan về tệp người dùng đang hoạt động.

**AC1: Hiển thị lưới khách hàng phân trang (Basic Flow & NFR24-1)**
- **GIVEN** Admin truy cập trang "Khách hàng" (Customers)
- **WHEN** Hệ thống tải dữ liệu danh sách
- **THEN** Hệ thống hiển thị lưới khách hàng với cơ chế phân trang (Pagination) để đảm bảo không bị quá tải bộ nhớ máy chủ (NFR24-1).

**AC2: Bảo mật thông tin riêng tư (Business Rule BR24-1)**
- **GIVEN** Lưới danh sách khách hàng hoặc hồ sơ chi tiết được hiển thị
- **WHEN** Admin kiểm tra thông tin
- **THEN** Hệ thống chỉ hiển thị thông tin cơ bản (Tên, Email, SĐT)
- **AND** Tuyệt đối không hiển thị mật khẩu của người dùng dưới bất kỳ hình thức nào (BR24-1).

#### US-24.2 : Khóa tài khoản khách hàng vi phạm
**Story:** Là Quản trị viên, tôi muốn có quyền khóa các tài khoản gian lận, để bảo vệ nền tảng khỏi các hành vi lạm dụng (boom hàng, trục lợi mã giảm giá).

**AC1: Khóa tài khoản và thu hồi phiên làm việc (Alternative Flow)**
- **GIVEN** Admin đang xem chi tiết hồ sơ của một Khách hàng có dấu hiệu vi phạm
- **WHEN** Admin chọn lệnh "Khóa tài khoản" (Ban Account) và đồng ý xác nhận
- **THEN** Hệ thống chuyển trạng thái tài khoản khách hàng đó thành "Bị cấm" (BANNED)
- **AND** Hệ thống ngay lập tức thu hồi phiên đăng nhập hiện tại, buộc người dùng này đăng xuất và không cho phép thực hiện thêm bất kỳ giao dịch nào trên nền tảng.

#### US-25.1 : Cấu hình Phí sàn và Thông số Ví Xu
**Story:** Là Quản trị viên, tôi muốn thiết lập động tỉ lệ quy đổi Xu thưởng và phần trăm Phí nền tảng (Platform Fee), để linh hoạt điều tiết các chương trình tri ân khách hàng và lợi nhuận của doanh nghiệp.

**AC1: Cập nhật thông số thành công (Basic Flow & BR25-1, BR25-2)**
- **GIVEN** Admin đang ở màn hình Cấu hình Tài chính
- **WHEN** Admin nhập các thông số hợp lệ cho Ví Xu (Tỉ lệ kiếm, Tỉ giá tiêu, Hạn mức) và Phí nền tảng (%) rồi nhấn "Lưu cấu hình"
- **THEN** Hệ thống cập nhật các hằng số này vào cơ sở dữ liệu
- **AND** Mức Phí nền tảng mới sẽ tự động được dùng làm cơ sở để đối soát doanh thu thực nhận cho Seller (BR25-2).

**AC2: Chặn nhập thông số âm (Exception Flow 3a)**
- **GIVEN** Admin đang thao tác trên biểu mẫu cấu hình tài chính
- **WHEN** Admin nhập số âm cho bất kỳ trường Tỉ lệ phần trăm hoặc Tỉ giá nào
- **THEN** Hệ thống bôi đỏ ô nhập liệu kèm thông báo "Giá trị không hợp lệ, phải lớn hơn hoặc bằng 0"
- **AND** Hệ thống chặn thao tác lưu để tránh lỗi logic tính toán.

**AC3: Nguyên tắc kế toán không hồi tố (Non-Functional Requirement NFR25-1)**
- **GIVEN** Một Đơn hàng đã được thanh toán xong trước thời điểm Admin thay đổi mức Phí nền tảng/Tỉ giá Xu
- **WHEN** Hệ thống thực hiện kết xuất báo cáo hoặc đối soát
- **THEN** Dữ liệu của đơn hàng đó bắt buộc phải được tính toán dựa trên cấu hình cũ
- **AND** Tuyệt đối không được áp dụng hồi tố cấu hình mới làm thay đổi lịch sử kế toán và dòng tiền đã chốt với Seller (NFR25-1).

### Feature 6.4: Audit Log (UC26)

#### US-26.1 (Read): Xem nhật ký hệ thống (Audit Log)
**Story:** Là Quản trị viên cấp cao, tôi muốn xem lại vết của mọi thao tác thay đổi dữ liệu quan trọng, để tôi có thể truy cứu trách nhiệm khi hệ thống xảy ra sự cố hoặc có dấu hiệu phá hoại nội bộ.

**AC1: Hiển thị danh sách log an toàn (Basic Flow & BR26-1)**
- **GIVEN** Admin truy cập vào trang "Nhật ký hệ thống" (Audit Logs)
- **WHEN** Hệ thống truy xuất dữ liệu từ máy chủ
- **THEN** Hệ thống hiển thị danh sách các bản ghi nhật ký (bao gồm: Thời gian, Người thực hiện, Hành động)
- **AND** Danh sách này bắt buộc phải ở chế độ Tuyệt đối Chỉ Đọc (Read-only). Tuyệt đối không cung cấp nút Xóa (Delete) hay Sửa (Update) cho bất kỳ ai để đảm bảo tính minh bạch kiểm toán (BR26-1).

**AC2: Xử lý hiệu năng ghi Log (NFR26-1)**
- **GIVEN** Có một thao tác quan trọng vừa được thực hiện ở các phân hệ khác (Ví dụ: Duyệt sản phẩm, Khóa user, Sửa phí sàn)
- **WHEN** Hệ thống tiến hành ghi vết lại hành động đó vào cơ sở dữ liệu Nhật ký
- **THEN** Quá trình "Ghi Log" bắt buộc phải chạy ngầm (Asynchronous - Bất đồng bộ) để không làm chậm trễ thời gian phản hồi của tính năng chính (NFR26-1).

---

## 🔲 Phần cần bổ sung
- **Mapping Wireframe**: thêm cột/đường dẫn wireframe cho từng User Story.
- **Ước lượng (Estimation)**: story points hoặc T-shirt size.
- **Definition of Done**: tiêu chuẩn hoàn tất (code, test, docs).
- **Dependency**: phụ thuộc giữa stories (nếu có).
