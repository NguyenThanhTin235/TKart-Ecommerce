package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "homepage_configs")
@Data
public class HomepageConfig extends BaseDocument {

    // Dynamic config using Maps for flexible schema
    private List<Map<String, Object>> banners;
    private List<Map<String, Object>> gridCategories;
    
    private boolean isActive = true;
}
