package com.tkart.ecommerce.database;

import com.tkart.ecommerce.models.embedded.CartItem;
import com.tkart.ecommerce.models.embedded.OrderItem;
import com.tkart.ecommerce.models.entities.*;
import com.tkart.ecommerce.models.enums.*;
import com.tkart.ecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        if (userRepository.count() > 0) return; // Tránh seed trùng

        System.out.println("🚀 Starting Full Database Seeding...");

        // 1. Independent & Config
        seedPlatformConfig();
        seedShippingProviders();
        seedArticles();
        seedHomepageConfig();

        // 2. Categories
        Category tshirts = seedCategories();

        // 3. Users
        User sellerUser = seedUser("Fashion Seller", "seller@tkart.com", Role.ROLE_SELLER);
        User customerUser = seedUser("John Customer", "customer@tkart.com", Role.ROLE_CUSTOMER);
        seedUser("System Admin", "admin@tkart.com", Role.ROLE_ADMIN);

        // 4. Seller Profile
        Seller sellerProfile = seedSellerProfile(sellerUser);

        // 5. Product
        Product product = seedProduct(sellerProfile, tshirts);

        // 6. User Extensions (Wallet, Cart, Wishlist, Address)
        seedUserExtensions(customerUser, product);

        // 7. Commerce (Coupon, Order, Payment)
        Coupon coupon = seedCoupon();
        Order order = seedOrder(customerUser, sellerProfile, product);
        seedPaymentOrder(customerUser, order, coupon);

        // 8. Post-Sale (Review, Return, Transaction)
        seedReview(customerUser, product, order);
        seedReturnRequest(customerUser, sellerProfile, order);
        seedTransaction(sellerProfile, order);

        // 9. Platform (Chat, Audit, OTP)
        seedChat(customerUser, sellerUser);
        seedAuditLog(customerUser);
        seedOtp(customerUser);

        System.out.println("✅ Full Database Seeding Completed Successfully!");
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
        article.setContent("Nội dung chính sách bảo mật...");
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

    private Category seedCategories() {
        Category fashion = new Category();
        fashion.setName("Thời trang");
        fashion.setSlug("thoi-trang");
        fashion.setLevel(1);
        categoryRepository.save(fashion);

        Category mens = new Category();
        mens.setName("Nam");
        mens.setSlug("thoi-trang-nam");
        mens.setLevel(2);
        mens.setParentId(fashion.getId());
        categoryRepository.save(mens);

        Category tshirts = new Category();
        tshirts.setName("Áo thun");
        tshirts.setSlug("ao-thun");
        tshirts.setLevel(3);
        tshirts.setParentId(mens.getId());
        categoryRepository.save(tshirts);

        System.out.println("  - Seeded Category Tree");
        return tshirts;
    }

    private User seedUser(String name, String email, Role role) {
        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword("password123");
        user.setRole(role);
        user.setStatus(AccountStatus.ACTIVE);
        userRepository.save(user);
        System.out.println("  - Seeded User: " + email);
        return user;
    }

    private Seller seedSellerProfile(User user) {
        Seller seller = new Seller();
        seller.setUserId(user.getId());
        seller.setShopName("TKart Official Store");
        seller.setStatus(SellerStatus.ACTIVE);
        sellerRepository.save(seller);
        System.out.println("  - Seeded Seller Profile");
        return seller;
    }

    private Product seedProduct(Seller seller, Category category) {
        Product product = new Product();
        product.setSellerId(seller.getId());
        product.setCategoryL3Id(category.getId());
        product.setTitle("Áo thun Cotton Premium");
        product.setSlug("ao-thun-cotton-premium");
        product.setMinSellingPrice(150000L);
        product.setStatus(ProductStatus.PUBLISHED);
        productRepository.save(product);
        System.out.println("  - Seeded Product");
        return product;
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
        item.setPrice(product.getMinSellingPrice());
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
        
        OrderItem item = new OrderItem();
        item.setProductId(product.getId());
        item.setTitle(product.getTitle());
        item.setPrice(product.getMinSellingPrice());
        item.setQty(1);
        order.setOrderItems(Arrays.asList(item));
        order.setTotalSellingPrice(150000L);
        
        orderRepository.save(order);
        System.out.println("  - Seeded Order");
        return order;
    }

    private void seedPaymentOrder(User user, Order order, Coupon coupon) {
        PaymentOrder payment = new PaymentOrder();
        payment.setUserId(user.getId());
        payment.setOrderIds(Arrays.asList(order.getId()));
        payment.setFinalPayment(135000L); // 150k - 10%
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
        review.setComment("Sản phẩm tuyệt vời!");
        reviewRepository.save(review);
        System.out.println("  - Seeded Review");
    }

    private void seedReturnRequest(User user, Seller seller, Order order) {
        ReturnRequest req = new ReturnRequest();
        req.setOrderId(order.getId());
        req.setUserId(user.getId());
        req.setSellerId(seller.getId());
        req.setStatus(ReturnStatus.REQUESTED);
        req.setReason("Hàng lỗi");
        returnRequestRepository.save(req);
        System.out.println("  - Seeded ReturnRequest");
    }

    private void seedTransaction(Seller seller, Order order) {
        Transaction tx = new Transaction();
        tx.setSellerId(seller.getId());
        tx.setPaymentOrderId(order.getId());
        tx.setAmount(135000L);
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
        msg.setContent("Chào shop, sản phẩm này còn hàng không?");
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

