package com.tkart.ecommerce.database;

import com.tkart.ecommerce.models.entities.Category;
import com.tkart.ecommerce.models.entities.PlatformConfig;
import com.tkart.ecommerce.models.entities.User;
import com.tkart.ecommerce.models.enums.Role;
import com.tkart.ecommerce.repositories.CategoryRepository;
import com.tkart.ecommerce.repositories.PlatformConfigRepository;
import com.tkart.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlatformConfigRepository platformConfigRepository;
    // Commenting out PasswordEncoder for now if it's not yet defined as a bean
    // private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedCategories();
        seedPlatformConfig();
        System.out.println("✅ Database Seeding Completed!");
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFullName("System Admin");
            admin.setEmail("admin@tkart.com");
            // admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPassword("admin123"); // Change this to encrypted password later
            admin.setPhone("0987654321");
            admin.setRole(Role.ROLE_ADMIN);

            User seller = new User();
            seller.setFullName("Demo Seller");
            seller.setEmail("seller@tkart.com");
            seller.setPassword("seller123");
            seller.setRole(Role.ROLE_SELLER);

            User customer = new User();
            customer.setFullName("Demo Customer");
            customer.setEmail("customer@tkart.com");
            customer.setPassword("customer123");
            customer.setRole(Role.ROLE_CUSTOMER);

            userRepository.saveAll(Arrays.asList(admin, seller, customer));
            System.out.println("🌱 Seeded Users");
        }
    }

    private void seedCategories() {
        if (categoryRepository.count() == 0) {
            // Level 1
            Category fashion = new Category();
            fashion.setName("Thời trang");
            fashion.setSlug("thoi-trang");
            fashion.setLevel(1);
            fashion = categoryRepository.save(fashion);

            Category electronics = new Category();
            electronics.setName("Điện tử");
            electronics.setSlug("dien-tu");
            electronics.setLevel(1);
            electronics = categoryRepository.save(electronics);

            // Level 2 (Child of Fashion)
            Category menFashion = new Category();
            menFashion.setName("Thời trang Nam");
            menFashion.setSlug("thoi-trang-nam");
            menFashion.setLevel(2);
            menFashion.setParentId(fashion.getId());
            menFashion = categoryRepository.save(menFashion);

            // Level 3 (Child of Men Fashion)
            Category tShirt = new Category();
            tShirt.setName("Áo thun Nam");
            tShirt.setSlug("ao-thun-nam");
            tShirt.setLevel(3);
            tShirt.setParentId(menFashion.getId());
            categoryRepository.save(tShirt);

            System.out.println("🌱 Seeded Categories");
        }
    }

    private void seedPlatformConfig() {
        if (platformConfigRepository.count() == 0) {
            PlatformConfig config = new PlatformConfig();
            config.setCoinEarnRate(0.01); // 1%
            config.setCoinValueRate(1.0); // 1 xu = 1 vnd
            config.setPlatformFeePercent(5.0); // 5% fee
            platformConfigRepository.save(config);
            System.out.println("🌱 Seeded Platform Config");
        }
    }
}
