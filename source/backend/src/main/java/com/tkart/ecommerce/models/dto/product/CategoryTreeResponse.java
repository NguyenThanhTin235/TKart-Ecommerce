package com.tkart.ecommerce.models.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryTreeResponse {
    private String id;
    private String name;
    private String slug;
    private String image;
    private Integer level;
    private List<CategoryTreeResponse> children;
}
