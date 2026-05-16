package com.tkart.ecommerce.database;

import com.tkart.ecommerce.models.embedded.CartItem;
import com.tkart.ecommerce.models.embedded.OrderItem;
import com.tkart.ecommerce.models.embedded.ProductAttribute;
import com.tkart.ecommerce.models.entities.*;
import com.tkart.ecommerce.models.enums.*;
import com.tkart.ecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlatformConfigRepository platformConfigRepository;
    private final ShippingProviderRepository shippingProviderRepository;
    private final HomepageConfigRepository homepageConfigRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final WishlistRepository wishlistRepository;
    private final CoinWalletRepository coinWalletRepository;
    private final ArticleRepository articleRepository;
    private final DealRepository dealRepository;
    private final ReviewRepository reviewRepository;
    private final AddressRepository addressRepository;
    private final AuditLogRepository auditLogRepository;
    private final CouponRepository couponRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final TransactionRepository transactionRepository;
    private final ReturnRequestRepository returnRequestRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final CouponRedemptionRepository couponRedemptionRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            System.out.println("⚡ Database already seeded. Skipping DataSeeder.");
            return;
        }

        System.out.println("🚀 Starting Full Database Seeding for Release 1 MVP...");

        // 0. Clean up existing collections to ensure fresh seeding
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        sellerRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();

        // 1. Independent & Config
        seedPlatformConfig();
        seedShippingProviders();
        seedArticles();
        seedHomepageConfig();

        // 2. Categories (3 Levels)
        Map<String, Category> categories = seedCategories();

        // 3. Users (Admin, 2 Sellers, Customer)
        User adminUser = seedUser("System Admin", "admin@tkart.com", "Admin@123", Role.ROLE_ADMIN, true, null);
        User sellerUser1 = seedUser("Fashion Seller", "seller1@tkart.com", "password123", Role.ROLE_SELLER, true, "TKart Fashion Store");
        User sellerUser2 = seedUser("Electronics Seller", "seller2@tkart.com", "password123", Role.ROLE_SELLER, true, "TKart Electronics Store");
        User customerUser = seedUser("John Customer", "customer@tkart.com", "password123", Role.ROLE_CUSTOMER, true, null);

        // 4. Seller Profiles
        Seller sellerProfile1 = seedSellerProfile(sellerUser1, "TKart Fashion Store", "GST111222333");
        Seller sellerProfile2 = seedSellerProfile(sellerUser2, "TKart Electronics Store", "GST444555666");

        // 5. Products (25-30 Sample products with rich attributes)
        List<Product> seededProducts = seedRichProducts(sellerProfile1, sellerProfile2, categories);

        // 6. User Extensions (Wallet, Cart, Wishlist, Address)
        seedUserExtensions(customerUser, seededProducts.get(0));

        // 7. Commerce (Coupon, Order, Payment)
        Coupon coupon = seedCoupon();
        Order order = seedOrder(customerUser, sellerProfile1, seededProducts.get(0));
        seedPaymentOrder(customerUser, order, coupon);

        // 8. Post-Sale (Review, Return, Transaction)
        seedReview(customerUser, seededProducts.get(0), order);
        seedReturnRequest(customerUser, sellerProfile1, order);
        seedTransaction(sellerProfile1, order);

        // 9. Platform (Chat, Audit, OTP)
        seedChat(customerUser, sellerUser1);
        seedAuditLog(customerUser);
        seedOtp(customerUser);

        System.out.println("✅ Full Database Seeding Completed Successfully! 26 Products seeded.");
    }

    private void seedPlatformConfig() {
        PlatformConfig config = new PlatformConfig();
        config.setCoinEarnRate(0.01);
        config.setCoinValueRate(1.0);
        config.setPlatformFeePercent(5.0);
        platformConfigRepository.save(config);
        System.out.println("  - Seeded PlatformConfig");
    }

    private void seedShippingProviders() {
        ShippingProvider ghtk = new ShippingProvider();
        ghtk.setName("Giao Hàng Tiết Kiệm");
        ghtk.setCode("GHTK");
        ghtk.setActive(true);
        shippingProviderRepository.save(ghtk);
        System.out.println("  - Seeded ShippingProvider");
    }

    private void seedArticles() {
        Article article = new Article();
        article.setTitle("Chính sách bảo mật");
        article.setSlug("privacy-policy");
        article.setContent("Nội dung chính sách bảo mật hệ thống TKart...");
        articleRepository.save(article);
        System.out.println("  - Seeded Article");
    }

    private void seedHomepageConfig() {
        HomepageConfig config = new HomepageConfig();
        config.setBanners(Arrays.asList(Map.of("image", "banner1.jpg", "url", "/deals")));
        config.setActive(true);
        homepageConfigRepository.save(config);
        System.out.println("  - Seeded HomepageConfig");
    }

    private Map<String, Category> seedCategories() {
        Map<String, Category> map = new HashMap<>();

        // Level 1: Thời trang
        Category fashion = new Category();
        fashion.setName("Thời trang");
        fashion.setSlug("thoi-trang");
        fashion.setLevel(1);
        categoryRepository.save(fashion);

        // Level 2: Thời trang Nam
        Category mens = new Category();
        mens.setName("Nam");
        mens.setSlug("thoi-trang-nam");
        mens.setLevel(2);
        mens.setParentId(fashion.getId());
        categoryRepository.save(mens);

        // Level 3: Áo thun nam
        Category tshirts = new Category();
        tshirts.setName("Áo thun");
        tshirts.setSlug("ao-thun");
        tshirts.setLevel(3);
        tshirts.setParentId(mens.getId());
        categoryRepository.save(tshirts);
        map.put("tshirts", tshirts);

        // Level 3: Áo khoác nam
        Category jackets = new Category();
        jackets.setName("Áo khoác");
        jackets.setSlug("ao-khoac");
        jackets.setLevel(3);
        jackets.setParentId(mens.getId());
        categoryRepository.save(jackets);
        map.put("jackets", jackets);

        // Level 2: Thời trang Nữ
        Category womens = new Category();
        womens.setName("Nữ");
        womens.setSlug("thoi-trang-nu");
        womens.setLevel(2);
        womens.setParentId(fashion.getId());
        categoryRepository.save(womens);

        // Level 3: Váy đầm nữ
        Category dresses = new Category();
        dresses.setName("Váy đầm");
        dresses.setSlug("vay-dam");
        dresses.setLevel(3);
        dresses.setParentId(womens.getId());
        categoryRepository.save(dresses);
        map.put("dresses", dresses);

        // Level 1: Điện tử
        Category electronics = new Category();
        electronics.setName("Điện tử");
        electronics.setSlug("dien-tu");
        electronics.setLevel(1);
        categoryRepository.save(electronics);

        // Level 2: Điện thoại & Thiết bị thông minh
        Category phones = new Category();
        phones.setName("Điện thoại");
        phones.setSlug("dien-thoai");
        phones.setLevel(2);
        phones.setParentId(electronics.getId());
        categoryRepository.save(phones);

        // Level 3: Smartphone
        Category smartphone = new Category();
        smartphone.setName("Smartphone");
        smartphone.setSlug("smartphone");
        smartphone.setLevel(3);
        smartphone.setParentId(phones.getId());
        categoryRepository.save(smartphone);
        map.put("smartphone", smartphone);

        // Level 2: Phụ kiện âm thanh
        Category audio = new Category();
        audio.setName("Phụ kiện âm thanh");
        audio.setSlug("phu-kien-am-thanh");
        audio.setLevel(2);
        audio.setParentId(electronics.getId());
        categoryRepository.save(audio);

        // Level 3: Tai nghe
        Category headphones = new Category();
        headphones.setName("Tai nghe");
        headphones.setSlug("tai-nghe");
        headphones.setLevel(3);
        headphones.setParentId(audio.getId());
        categoryRepository.save(headphones);
        map.put("headphones", headphones);

        // Level 3: Loa Bluetooth
        Category speakers = new Category();
        speakers.setName("Loa Bluetooth");
        speakers.setSlug("loa-bluetooth");
        speakers.setLevel(3);
        speakers.setParentId(audio.getId());
        categoryRepository.save(speakers);
        map.put("speakers", speakers);

        System.out.println("  - Seeded Category Tree (3 Levels)");
        return map;
    }

    private User seedUser(String name, String email, String password, Role role, boolean isVerified, String storeName) {
        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setRoles(Arrays.asList(role));
        user.setVerified(isVerified);
        user.setStoreName(storeName);
        user.setStatus(AccountStatus.ACTIVE);
        userRepository.save(user);
        System.out.println("  - Seeded User: " + email);
        return user;
    }

    private Seller seedSellerProfile(User user, String shopName, String gst) {
        Seller seller = new Seller();
        seller.setUserId(user.getId());
        seller.setShopName(shopName);
        seller.setGstNumber(gst);
        seller.setStatus(SellerStatus.ACTIVE);
        sellerRepository.save(seller);
        System.out.println("  - Seeded Seller Profile: " + shopName);
        return seller;
    }

    private List<Product> seedRichProducts(Seller fashionSeller, Seller elecSeller, Map<String, Category> cats) {
        List<Product> list = new ArrayList<>();

        // 1. T-Shirts (Fashion Seller)
        list.add(createProduct(fashionSeller, cats.get("tshirts"), "Áo thun Cotton Premium Đen", "ao-thun-cotton-premium-den", "Áo thun nam 100% cotton thoáng mát, thấm hút mồ hôi tốt.", 250000L, 150000L, 100, 4.8, 120, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500"));
        list.add(createProduct(fashionSeller, cats.get("tshirts"), "Áo thun Cotton Premium Trắng", "ao-thun-cotton-premium-trang", "Áo thun nam 100% cotton form chuẩn dáng trẻ trung.", 250000L, 150000L, 85, 4.7, 95, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Kích cỡ", "L")), "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500"));
        list.add(createProduct(fashionSeller, cats.get("tshirts"), "Áo thun thể thao nam Xanh", "ao-thun-the-thao-nam-xanh", "Áo thun thể thao poly lạnh co giãn 4 chiều.", 200000L, 120000L, 150, 4.5, 60, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Kích cỡ", "XL")), "https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?w=500"));
        list.add(createProduct(fashionSeller, cats.get("tshirts"), "Áo thun cổ tròn Basic Đen", "ao-thun-co-tron-basic-den", "Áo thun trơn basic dễ phối đồ cho nam giới.", 180000L, 99000L, 200, 4.6, 210, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Kích cỡ", "L")), "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500"));
        list.add(createProduct(fashionSeller, cats.get("tshirts"), "Áo thun tay dài Thu Đông Trắng", "ao-thun-tay-dai-thu-dong-trang", "Áo thun tay dài chất nỉ mỏng ấm áp.", 300000L, 189000L, 60, 4.9, 45, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500"));

        // 2. Jackets (Fashion Seller)
        list.add(createProduct(fashionSeller, cats.get("jackets"), "Áo khoác Bomber Khaki Đen", "ao-khoac-bomber-khaki-den", "Áo khoác bomber nam chất khaki 2 lớp dày dặn.", 550000L, 399000L, 50, 4.8, 88, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Kích cỡ", "XL")), "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500"));
        list.add(createProduct(fashionSeller, cats.get("jackets"), "Áo khoác Denim Classic Xanh", "ao-khoac-denim-classic-xanh", "Áo khoác bò denim phong cách bụi bặm cá tính.", 650000L, 489000L, 40, 4.7, 65, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Kích cỡ", "L")), "https://images.unsplash.com/photo-1576995853123-5a10305d93c0?w=500"));
        list.add(createProduct(fashionSeller, cats.get("jackets"), "Áo khoác Gió Chống Nước Xanh", "ao-khoac-gio-chong-nuoc-xanh", "Áo khoác gió siêu nhẹ, chống nước nhẹ và cản gió.", 400000L, 250000L, 120, 4.4, 110, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500"));
        list.add(createProduct(fashionSeller, cats.get("jackets"), "Áo khoác Da Biker Đen", "ao-khoac-da-biker-den", "Áo khoác da PU cao cấp không bong tróc.", 850000L, 650000L, 25, 4.9, 30, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Kích cỡ", "L")), "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500"));
        list.add(createProduct(fashionSeller, cats.get("jackets"), "Áo khoác Nỉ Hoodie Trắng", "ao-khoac-ni-hoodie-trang", "Áo nỉ hoodie có mũ dáng unisex.", 350000L, 220000L, 90, 4.6, 75, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500"));

        // 3. Dresses (Fashion Seller)
        list.add(createProduct(fashionSeller, cats.get("dresses"), "Váy đầm dự tiệc dáng xòe Đỏ", "vay-dam-du-tiec-dang-xoe-do", "Váy đầm nữ thiết kế sang trọng phù hợp đi tiệc.", 750000L, 520000L, 35, 4.9, 52, Arrays.asList(new ProductAttribute("Màu sắc", "Đỏ"), new ProductAttribute("Kích cỡ", "S")), "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"));
        list.add(createProduct(fashionSeller, cats.get("dresses"), "Váy suông công sở thanh lịch Trắng", "vay-suong-cong-so-thanh-lich-trang", "Váy công sở chất liệu tuyết mưa đứng form.", 450000L, 320000L, 70, 4.7, 40, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"));
        list.add(createProduct(fashionSeller, cats.get("dresses"), "Váy hoa nhí mùa hè Xanh", "vay-hoa-nhi-mua-he-xanh", "Váy voan hoa nhí mát mẻ cho mùa hè dịu dàng.", 380000L, 250000L, 80, 4.6, 68, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Kích cỡ", "S")), "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"));
        list.add(createProduct(fashionSeller, cats.get("dresses"), "Váy maxi đi biển quyến rũ Trắng", "vay-maxi-di-bien-quyen-ru-trang", "Váy maxi dài thướt tha chụp ảnh đi biển cực đẹp.", 500000L, 350000L, 45, 4.8, 33, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Kích cỡ", "M")), "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"));
        list.add(createProduct(fashionSeller, cats.get("dresses"), "Váy body ôm dáng gợi cảm Đen", "vay-body-om-dang-goi-cam-den", "Váy body thun gân co giãn tôn đường cong.", 420000L, 280000L, 60, 4.5, 48, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Kích cỡ", "S")), "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500"));

        // 4. Smartphones (Electronics Seller)
        list.add(createProduct(elecSeller, cats.get("smartphone"), "iPhone 15 Pro Max 256GB Titan Tự Nhiên", "iphone-15-pro-max-256gb-titan", "Điện thoại Apple iPhone 15 Pro Max chính hãng VN/A.", 34990000L, 29500000L, 15, 5.0, 320, Arrays.asList(new ProductAttribute("Màu sắc", "Xám"), new ProductAttribute("Bộ nhớ", "256GB")), "https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=500"));
        list.add(createProduct(elecSeller, cats.get("smartphone"), "Samsung Galaxy S24 Ultra 512GB Xám", "samsung-galaxy-s24-ultra-512gb-xam", "Flagship đỉnh cao từ Samsung với AI tích hợp mạnh mẽ.", 37990000L, 31200000L, 12, 4.9, 180, Arrays.asList(new ProductAttribute("Màu sắc", "Xám"), new ProductAttribute("Bộ nhớ", "512GB")), "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=500"));
        list.add(createProduct(elecSeller, cats.get("smartphone"), "Xiaomi 14 5G 256GB Đen", "xiaomi-14-5g-256gb-den", "Điện thoại cao cấp cấu hình khủng camera Leica.", 22990000L, 19500000L, 20, 4.7, 95, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Bộ nhớ", "256GB")), "https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=500"));
        list.add(createProduct(elecSeller, cats.get("smartphone"), "OPPO Reno11 5G 256GB Xanh", "oppo-reno11-5g-256gb-xanh", "Chuyên gia chân dung với thiết kế độc đáo ấn tượng.", 10990000L, 9490000L, 30, 4.6, 110, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Bộ nhớ", "256GB")), "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=500"));
        list.add(createProduct(elecSeller, cats.get("smartphone"), "iPhone 13 128GB Chính hãng Trắng", "iphone-13-128gb-chinh-hang-trang", "Chiếc iPhone quốc dân với hiệu năng vẫn cực kỳ mượt mà.", 17990000L, 13790000L, 40, 4.8, 450, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Bộ nhớ", "128GB")), "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?w=500"));

        // 5. Headphones (Electronics Seller)
        list.add(createProduct(elecSeller, cats.get("headphones"), "Tai nghe Apple AirPods Pro 2 Trắng", "apple-airpods-pro-2-trang", "Tai nghe True Wireless chống ồn chủ động đỉnh cao.", 6190000L, 5290000L, 50, 4.9, 280, Arrays.asList(new ProductAttribute("Màu sắc", "Trắng"), new ProductAttribute("Loại", "Không dây")), "https://images.unsplash.com/photo-1600294037681-c80b4cb5b434?w=500"));
        list.add(createProduct(elecSeller, cats.get("headphones"), "Tai nghe chụp tai Sony WH-1000XM5 Đen", "sony-wh-1000xm5-den", "Tai nghe over-ear chống ồn hàng đầu thế giới từ Sony.", 7990000L, 6490000L, 25, 4.9, 150, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Loại", "Chụp tai")), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500"));
        list.add(createProduct(elecSeller, cats.get("headphones"), "Tai nghe Marshall Major IV Đen", "marshall-major-iv-den", "Thiết kế cổ điển đậm chất rock, pin sử dụng 80 giờ.", 4290000L, 3490000L, 35, 4.8, 92, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Loại", "Chụp tai")), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500"));

        // 6. Speakers (Electronics Seller)
        list.add(createProduct(elecSeller, cats.get("speakers"), "Loa Bluetooth JBL Flip 6 Đen", "loa-bluetooth-jbl-flip-6-den", "Loa di động chống nước IP67, âm trầm mạnh mẽ.", 2990000L, 2390000L, 60, 4.7, 140, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Công suất", "20W")), "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=500"));
        list.add(createProduct(elecSeller, cats.get("speakers"), "Loa Bluetooth Marshall Emberton II Đen", "marshall-emberton-ii-den", "Loa bluetooth nhỏ gọn sang trọng, âm thanh 360 độ.", 4990000L, 3990000L, 40, 4.8, 115, Arrays.asList(new ProductAttribute("Màu sắc", "Đen"), new ProductAttribute("Công suất", "20W")), "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=500"));
        list.add(createProduct(elecSeller, cats.get("speakers"), "Loa Sony SRS-XB13 Siêu Nhỏ Xanh", "sony-srs-xb13-sieu-nho-xanh", "Loa mini siêu nhỏ gọn kèm dây treo tiện lợi.", 1290000L, 990000L, 80, 4.6, 70, Arrays.asList(new ProductAttribute("Màu sắc", "Xanh"), new ProductAttribute("Công suất", "5W")), "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=500"));

        System.out.println("  - Seeded 26 Sample Rich Products");
        return list;
    }

    private Product createProduct(Seller seller, Category cat, String name, String slug, String desc, Long mrp, Long selling, int stock, Double rating, int reviews, List<ProductAttribute> attrs, String image) {
        Product p = new Product();
        p.setSellerId(seller.getId());
        p.setName(name);
        p.setTitle(name);
        p.setSlug(slug);
        p.setDescription(desc);
        p.setCategoryId(cat.getId());
        p.setCategoryL3Id(cat.getId());
        p.setMrp(mrp);
        p.setSellingPrice(selling);
        p.setMinSellingPrice(selling);
        p.setStock(stock);
        p.setTotalQuantity(stock);
        p.setAttributes(attrs);
        p.setRating(rating);
        p.setReviewsCount(reviews);
        p.setStatus(ProductStatus.PUBLISHED);
        p.setImages(Arrays.asList(image));
        return productRepository.save(p);
    }

    private void seedUserExtensions(User user, Product product) {
        // Address
        Address addr = new Address();
        addr.setUserId(user.getId());
        addr.setFullName(user.getFullName());
        addr.setPhone("0988777666");
        addr.setCity("Hồ Chí Minh");
        addr.setDefault(true);
        addressRepository.save(addr);

        // Wallet
        CoinWallet wallet = new CoinWallet();
        wallet.setUserId(user.getId());
        wallet.setBalance(5000L);
        coinWalletRepository.save(wallet);

        // Cart
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        CartItem item = new CartItem();
        item.setProductId(product.getId());
        item.setQty(2);
        item.setQuantity(2);
        item.setPrice(product.getSellingPrice());
        cart.setCartItems(Arrays.asList(item));
        cartRepository.save(cart);

        // Wishlist
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(user.getId());
        wishlist.setProductIds(Arrays.asList(product.getId()));
        wishlistRepository.save(wishlist);

        System.out.println("  - Seeded User Extensions (Address, Wallet, Cart, Wishlist)");
    }

    private Coupon seedCoupon() {
        Coupon coupon = new Coupon();
        coupon.setCode("TKART50");
        coupon.setDiscountPercent(10.0);
        coupon.setMinOrderValue(100000L);
        coupon.setActive(true);
        couponRepository.save(coupon);
        System.out.println("  - Seeded Coupon");
        return coupon;
    }

    private Order seedOrder(User user, Seller seller, Product product) {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setSellerId(seller.getId());
        order.setStatus(OrderStatus.DELIVERED);
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setPaymentMethod(PaymentMethod.COD);
        order.setPaymentStatus(PaymentStatus.COLLECTED);
        
        OrderItem item = new OrderItem();
        item.setProductId(product.getId());
        item.setTitle(product.getTitle());
        item.setPrice(product.getSellingPrice());
        item.setQty(1);
        order.setOrderItems(Arrays.asList(item));
        order.setTotalSellingPrice(product.getSellingPrice());
        order.setTotalAmount(product.getSellingPrice());
        
        orderRepository.save(order);
        System.out.println("  - Seeded Order");
        return order;
    }

    private void seedPaymentOrder(User user, Order order, Coupon coupon) {
        PaymentOrder payment = new PaymentOrder();
        payment.setUserId(user.getId());
        payment.setOrderIds(Arrays.asList(order.getId()));
        payment.setFinalPayment(order.getTotalAmount() != null ? order.getTotalAmount() : 135000L);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentOrderRepository.save(payment);
        
        CouponRedemption redemption = new CouponRedemption();
        redemption.setCouponId(coupon.getId());
        redemption.setUserId(user.getId());
        redemption.setOrderId(order.getId());
        couponRedemptionRepository.save(redemption);
        
        System.out.println("  - Seeded Payment & CouponRedemption");
    }

    private void seedReview(User user, Product product, Order order) {
        Review review = new Review();
        review.setUserId(user.getId());
        review.setProductId(product.getId());
        review.setOrderId(order.getId());
        review.setRating(5);
        review.setComment("Sản phẩm tuyệt vời, đóng gói rất cẩn thận!");
        reviewRepository.save(review);
        System.out.println("  - Seeded Review");
    }

    private void seedReturnRequest(User user, Seller seller, Order order) {
        ReturnRequest req = new ReturnRequest();
        req.setOrderId(order.getId());
        req.setUserId(user.getId());
        req.setSellerId(seller.getId());
        req.setStatus(ReturnStatus.REQUESTED);
        req.setReason("Hàng lỗi do vận chuyển");
        returnRequestRepository.save(req);
        System.out.println("  - Seeded ReturnRequest");
    }

    private void seedTransaction(Seller seller, Order order) {
        Transaction tx = new Transaction();
        tx.setSellerId(seller.getId());
        tx.setPaymentOrderId(order.getId());
        tx.setAmount(order.getTotalAmount() != null ? order.getTotalAmount() : 135000L);
        tx.setType(TransactionType.ORDER_PAYMENT);
        transactionRepository.save(tx);
        System.out.println("  - Seeded Transaction");
    }

    private void seedChat(User customer, User seller) {
        ChatRoom room = new ChatRoom();
        room.setCustomerId(customer.getId());
        room.setSellerId(seller.getId());
        chatRoomRepository.save(room);

        ChatMessage msg = new ChatMessage();
        msg.setChatRoomId(room.getId());
        msg.setSenderId(customer.getId());
        msg.setContent("Chào shop, sản phẩm này còn hàng không ạ?");
        chatMessageRepository.save(msg);
        System.out.println("  - Seeded ChatRoom & Message");
    }

    private void seedAuditLog(User user) {
        AuditLog log = new AuditLog();
        log.setActorId(user.getId());
        log.setActorRole(user.getRole());
        log.setAction("LOGIN");
        auditLogRepository.save(log);
        System.out.println("  - Seeded AuditLog");
    }

    private void seedOtp(User user) {
        OtpToken otp = new OtpToken();
        otp.setEmail(user.getEmail());
        otp.setCode("123456");
        otp.setType(OtpType.LOGIN);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        otpTokenRepository.save(otp);
        System.out.println("  - Seeded OtpToken");
    }
}
