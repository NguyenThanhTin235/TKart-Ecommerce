package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.dto.product.ProductFilterRequest;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> findByFilter(ProductFilterRequest filter, List<String> categoryL3Ids) {
        List<Criteria> criteriaList = new ArrayList<>();

        // Chỉ trả về sản phẩm đã PUBLISHED
        criteriaList.add(Criteria.where("status").is(ProductStatus.PUBLISHED));

        // Tìm theo từ khóa (regex case-insensitive trên name và description)
        if (StringUtils.hasText(filter.getKeyword())) {
            String pattern = ".*" + filter.getKeyword().trim() + ".*";
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where("name").regex(pattern, "i"),
                    Criteria.where("description").regex(pattern, "i")
            ));
        }

        // Lọc theo danh mục (đã được đệ quy resolve sang danh sách categoryL3Id)
        if (categoryL3Ids != null && !categoryL3Ids.isEmpty()) {
            criteriaList.add(Criteria.where("categoryL3Id").in(categoryL3Ids));
        }

        // Lọc theo khoảng giá
        if (filter.getMinPrice() != null || filter.getMaxPrice() != null) {
            Criteria priceCriteria = Criteria.where("sellingPrice");
            if (filter.getMinPrice() != null) priceCriteria = priceCriteria.gte(filter.getMinPrice());
            if (filter.getMaxPrice() != null) priceCriteria = priceCriteria.lte(filter.getMaxPrice());
            criteriaList.add(priceCriteria);
        }

        // Lọc theo % giảm giá tối thiểu
        if (filter.getMinDiscount() != null) {
            criteriaList.add(Criteria.where("maxDiscountPercent").gte(filter.getMinDiscount()));
        }

        // Lọc theo màu sắc (attributes[].name = "color" hoặc "Màu sắc", .value = giá trị)
        if (StringUtils.hasText(filter.getColor())) {
            criteriaList.add(Criteria.where("attributes").elemMatch(
                    Criteria.where("name").regex("^(color|màu sắc)$", "i")
                             .and("value").regex("^" + filter.getColor().trim() + "$", "i")
            ));
        }

        // Lọc theo kích cỡ
        if (StringUtils.hasText(filter.getSize())) {
            criteriaList.add(Criteria.where("attributes").elemMatch(
                    Criteria.where("name").regex("^(size|kích cỡ)$", "i")
                             .and("value").regex("^" + filter.getSize().trim() + "$", "i")
            ));
        }

        // Lọc theo rating tối thiểu
        if (filter.getMinRating() != null) {
            criteriaList.add(Criteria.where("rating").gte(filter.getMinRating()));
        }

        Query query = new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));

        // Sắp xếp
        Sort.Direction direction = "asc".equalsIgnoreCase(filter.getSortDir())
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortField = resolveSortField(filter.getSortBy());
        query.with(Sort.by(direction, sortField));

        // Đếm tổng để tính phân trang
        long total = mongoTemplate.count(query, Product.class);

        // Phân trang
        query.with(PageRequest.of(filter.getPage(), filter.getLimit()));
        List<Product> products = mongoTemplate.find(query, Product.class);

        return new PageImpl<>(products, PageRequest.of(filter.getPage(), filter.getLimit()), total);
    }

    private String resolveSortField(String sortBy) {
        return switch (sortBy == null ? "" : sortBy) {
            case "price" -> "sellingPrice";
            case "sales" -> "reviewsCount"; // Proxy: dùng reviewsCount làm indicator bán chạy
            default -> "createdAt";
        };
    }
}
