package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.common.ApiResponse;
import com.tkart.ecommerce.models.dto.common.PaginationMeta;
import com.tkart.ecommerce.models.dto.product.*;
import com.tkart.ecommerce.services.interfaces.ProductPublicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
@Tag(name = "Public Products", description = "Tìm kiếm và lọc sản phẩm công khai")
public class ProductPublicController {

    private final ProductPublicService productPublicService;

    @GetMapping("/products")
    @Operation(summary = "Tìm kiếm & lọc sản phẩm", description = "Hỗ trợ keyword, danh mục, khoảng giá, thuộc tính, rating, sắp xếp và phân trang")
    public ResponseEntity<ApiResponse<List<ProductSummaryResponse>>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double minRating,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int limit
    ) {
        ProductFilterRequest filter = new ProductFilterRequest();
        filter.setKeyword(keyword);
        filter.setCategory(category);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setMinDiscount(minDiscount);
        filter.setColor(color);
        filter.setSize(size);
        filter.setMinRating(minRating);
        filter.setSortBy(sortBy);
        filter.setSortDir(sortDir);
        filter.setPage(page);
        filter.setLimit(limit);

        Page<ProductSummaryResponse> result = productPublicService.searchProducts(filter);

        PaginationMeta paginationMeta = PaginationMeta.builder()
                .total(result.getTotalElements())
                .count(result.getNumberOfElements())
                .perPage(limit)
                .currentPage(page + 1)
                .totalPages(result.getTotalPages())
                .build();

        return ResponseEntity.ok(
                ApiResponse.ok("Products retrieved successfully", result.getContent(),
                        Map.of("pagination", paginationMeta))
        );
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Chi tiết sản phẩm", description = "Trả về thông tin chi tiết và danh sách sản phẩm liên quan")
    public ResponseEntity<ApiResponse<ProductDetailResponse>> getProduct(@PathVariable String id) {
        ProductDetailResponse detail = productPublicService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.ok("Product retrieved successfully", detail));
    }

    @GetMapping("/categories")
    @Operation(summary = "Cây danh mục 3 cấp", description = "Trả về toàn bộ cây danh mục để hiển thị sidebar")
    public ResponseEntity<ApiResponse<List<CategoryTreeResponse>>> getCategories() {
        List<CategoryTreeResponse> tree = productPublicService.getCategoryTree();
        return ResponseEntity.ok(ApiResponse.ok("Categories retrieved successfully", tree));
    }
}
