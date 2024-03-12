package org.backend.issuementor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ProductCategoryId implements Serializable {
    @Column(name = "product_id")
    private long productId;

    @Column(name = "category_id")
    private long categoryId;
}
