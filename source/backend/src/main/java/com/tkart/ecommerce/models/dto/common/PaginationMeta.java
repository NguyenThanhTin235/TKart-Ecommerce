package com.tkart.ecommerce.models.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationMeta {
    private long total;
    private int count;
    private int perPage;
    private int currentPage;
    private int totalPages;
}
