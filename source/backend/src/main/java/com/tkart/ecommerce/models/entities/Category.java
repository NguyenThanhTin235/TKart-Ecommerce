package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "categories")
@Data
public class Category extends BaseDocument {

    private String name;

    @Indexed(unique = true)
    private String slug;

    @Indexed
    private Integer level; // 1, 2, or 3

    @Indexed
    private String parentId; // Self-reference to Category

    private String image;
}
