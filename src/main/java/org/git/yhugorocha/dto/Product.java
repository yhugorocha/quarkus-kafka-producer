package org.git.yhugorocha.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.git.yhugorocha.entities.ProductEntity;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    @NotEmpty(message = "Product name is required.")
    private String name;

    private Boolean isDeleted;

    private LocalDateTime dateCreated;

    public static Product fromProduct(ProductEntity productEntity){
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .isDeleted(productEntity.getIsDeleted())
                .dateCreated(productEntity.getDateCreated())
                .build();
    }
}
