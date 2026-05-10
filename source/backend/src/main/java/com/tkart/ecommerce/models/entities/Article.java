package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.ArticleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "articles")
@Data
public class Article extends BaseDocument {

    private String title;

    @Indexed(unique = true)
    private String slug;

    @Indexed
    private ArticleType type;

    private Long viewCount = 0L;
    private String content;
}
