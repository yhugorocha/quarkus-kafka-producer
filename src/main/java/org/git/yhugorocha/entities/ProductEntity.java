package org.git.yhugorocha.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.git.yhugorocha.dto.Product;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.isDeleted = Optional.ofNullable(product.getIsDeleted()).orElse(false);
        this.dateCreated = Optional.ofNullable(product.getIsDeleted()).isPresent() ? product.getDateCreated() : LocalDateTime.now();
    }

    public ProductEntity(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.isDeleted = productEntity.getIsDeleted();
        this.dateCreated = productEntity.getDateCreated();
    }
}
