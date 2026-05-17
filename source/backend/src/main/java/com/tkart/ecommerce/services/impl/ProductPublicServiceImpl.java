package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.exceptions.ResourceNotFoundException;
import com.tkart.ecommerce.models.dto.product.*;
import com.tkart.ecommerce.models.entities.Category;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.enums.ProductStatus;
import com.tkart.ecommerce.repositories.CategoryRepository;
import com.tkart.ecommerce.repositories.ProductCustomRepository;
import com.tkart.ecommerce.repositories.ProductRepository;
import com.tkart.ecommerce.services.interfaces.ProductPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPublicServiceImpl implements ProductPublicService {

    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductSummaryResponse> searchProducts(ProductFilterRequest filter) {
        // Resolve categoryId sang danh sách categoryL3Id (đệ quy)
        List<String> categoryL3Ids = null;
        if (filter.getCategory() != null) {
            categoryL3Ids = resolveCategoryL3Ids(filter.getCategory());
        }

        Page<Product> products = productCustomRepository.findByFilter(filter, categoryL3Ids);
        return products.map(this::toSummary);
    }

    @Override
    public ProductDetailResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getStatus() == ProductStatus.PUBLISHED)
                .orElseThrow(() -> new ResourceNotFoundException("Sản phẩm không tồn tại hoặc chưa được công khai"));

        // Lấy sản phẩm liên quan cùng categoryL3Id, giới hạn 8
        List<ProductSummaryResponse> related = productRepository
                .findByCategoryL3IdAndStatusAndIdNot(product.getCategoryL3Id(), ProductStatus.PUBLISHED, id)
                .stream()
                .limit(8)
                .map(this::toSummary)
                .collect(Collectors.toList());

        return toDetail(product, related);
    }

    @Override
    public List<CategoryTreeResponse> getCategoryTree() {
        List<Category> allLevel1 = categoryRepository.findByLevel(1);
        List<Category> allLevel2 = categoryRepository.findByLevel(2);
        List<Category> allLevel3 = categoryRepository.findByLevel(3);

        // Group level2 by parentId
        Map<String, List<Category>> level2ByParent = allLevel2.stream()
                .collect(Collectors.groupingBy(Category::getParentId));

        // Group level3 by parentId
        Map<String, List<Category>> level3ByParent = allLevel3.stream()
                .collect(Collectors.groupingBy(Category::getParentId));

        return allLevel1.stream()
                .map(l1 -> {
                    List<CategoryTreeResponse> level2Children = level2ByParent
                            .getOrDefault(l1.getId(), List.of())
                            .stream()
                            .map(l2 -> {
                                List<CategoryTreeResponse> level3Children = level3ByParent
                                        .getOrDefault(l2.getId(), List.of())
                                        .stream()
                                        .map(l3 -> CategoryTreeResponse.builder()
                                                .id(l3.getId())
                                                .name(l3.getName())
                                                .slug(l3.getSlug())
                                                .image(l3.getImage())
                                                .level(3)
                                                .children(List.of())
                                                .build())
                                        .collect(Collectors.toList());

                                return CategoryTreeResponse.builder()
                                        .id(l2.getId())
                                        .name(l2.getName())
                                        .slug(l2.getSlug())
                                        .image(l2.getImage())
                                        .level(2)
                                        .children(level3Children)
                                        .build();
                            })
                            .collect(Collectors.toList());

                    return CategoryTreeResponse.builder()
                            .id(l1.getId())
                            .name(l1.getName())
                            .slug(l1.getSlug())
                            .image(l1.getImage())
                            .level(1)
                            .children(level2Children)
                            .build();
                })
                .collect(Collectors.toList());
    }

    // --- BR01-3: đệ quy lấy toàn bộ categoryL3Id trong subtree ---
    private List<String> resolveCategoryL3Ids(String categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if (cat == null) return List.of(categoryId);

        List<String> result = new ArrayList<>();
        collectL3Ids(cat, result);
        return result.isEmpty() ? List.of(categoryId) : result;
    }

    private void collectL3Ids(Category cat, List<String> result) {
        if (cat.getLevel() == 3) {
            result.add(cat.getId());
            return;
        }
        List<Category> children = categoryRepository.findByParentId(cat.getId());
        for (Category child : children) {
            collectL3Ids(child, result);
        }
    }

    // --- Mappers ---
    private ProductSummaryResponse toSummary(Product p) {
        String image = (p.getImages() != null && !p.getImages().isEmpty()) ? p.getImages().get(0) : null;
        int discount = calcDiscount(p.getMrp(), p.getSellingPrice());
        return ProductSummaryResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .slug(p.getSlug())
                .image(image)
                .sellingPrice(p.getSellingPrice())
                .mrp(p.getMrp())
                .discountPercent(discount)
                .rating(p.getRating())
                .reviewsCount(p.getReviewsCount())
                .categoryId(p.getCategoryId())
                .attributes(p.getAttributes())
                .build();
    }

    private ProductDetailResponse toDetail(Product p, List<ProductSummaryResponse> related) {
        int discount = calcDiscount(p.getMrp(), p.getSellingPrice());
        return ProductDetailResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .title(p.getTitle())
                .slug(p.getSlug())
                .description(p.getDescription())
                .images(p.getImages())
                .sellingPrice(p.getSellingPrice())
                .mrp(p.getMrp())
                .discountPercent(discount)
                .stock(p.getStock())
                .rating(p.getRating())
                .reviewsCount(p.getReviewsCount())
                .categoryId(p.getCategoryId())
                .categoryL3Id(p.getCategoryL3Id())
                .attributes(p.getAttributes())
                .variants(p.getVariants())
                .sellerId(p.getSellerId())
                .createdAt(p.getCreatedAt())
                .relatedProducts(related)
                .build();
    }

    private int calcDiscount(Long mrp, Long sellingPrice) {
        if (mrp == null || mrp == 0 || sellingPrice == null) return 0;
        return (int) Math.round((mrp - sellingPrice) * 100.0 / mrp);
    }
}
