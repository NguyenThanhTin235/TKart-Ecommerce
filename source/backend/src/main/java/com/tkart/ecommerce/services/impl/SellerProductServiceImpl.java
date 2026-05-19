package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.models.dto.product.ProductCreateRequest;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.enums.ProductStatus;
import com.tkart.ecommerce.repositories.ProductRepository;
import com.tkart.ecommerce.services.interfaces.SellerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerProductServiceImpl implements SellerProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(String sellerId, ProductCreateRequest request) {
        if (request.getSellingPrice() > request.getMrp()) {
            throw new com.tkart.ecommerce.exceptions.BadRequestException("Giới hạn logic: Giá bán (Selling Price) không được lớn hơn Giá gốc (MRP)");
        }
        
        Product product = new Product();
        product.setSellerId(sellerId);
        product.setName(request.getName());
        product.setTitle(request.getName());
        product.setSlug(request.getName().toLowerCase().replace(" ", "-") + "-" + UUID.randomUUID().toString().substring(0, 5));
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategoryId());
        product.setImages(request.getImages());
        product.setMrp(request.getMrp());
        product.setSellingPrice(request.getSellingPrice());
        product.setStock(request.getStock());
        product.setAttributes(request.getAttributes());
        
        // Cập nhật giá trị tính toán
        product.setMinSellingPrice(request.getSellingPrice());
        product.setTotalQuantity(request.getStock());
        
        if (request.getMrp() != null && request.getSellingPrice() != null && request.getMrp() > 0) {
            double discount = (double) (request.getMrp() - request.getSellingPrice()) / request.getMrp() * 100;
            product.setMaxDiscountPercent((int) Math.round(discount));
        } else {
            product.setMaxDiscountPercent(0);
        }

        // Mặc định trạng thái là PENDING để chờ Admin duyệt
        product.setStatus(ProductStatus.PENDING);
        product.setRating(0.0);
        product.setReviewsCount(0);

        return productRepository.save(product);
    }
}
