package org.git.yhugorocha.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.git.yhugorocha.dto.AuditDTO;
import org.git.yhugorocha.dto.Product;
import org.git.yhugorocha.entities.ProductEntity;
import org.git.yhugorocha.message.KafkaEvents;
import org.git.yhugorocha.repository.ProductRepository;
import org.git.yhugorocha.service.ProductService;

import java.util.List;

@AllArgsConstructor
@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final KafkaEvents kafkaEvents;

    private final String mockUsername = "mock_username";

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().list().stream().map(Product::fromProduct).toList();
    }

    @Transactional
    @Override
    public Product created(Product product) {
        var productEntity = new ProductEntity(product);
        productRepository.persist(productEntity);
        sendToKafka(mockUsername, null, productEntity);
        return Product.fromProduct(productEntity);
    }

    @Transactional
    @Override
    public Product update(Product product, Long id) {
        var productEntity = productRepository.findByIdOptional(id).orElseThrow();
        var productBefore = new ProductEntity(productEntity);
        productEntity.setName(product.getName());
        productRepository.persist(productEntity);
        sendToKafka(mockUsername, productBefore, productEntity);
        return Product.fromProduct(productEntity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var productEntity = productRepository.findByIdOptional(id).orElseThrow();
        var productBefore = new ProductEntity(productEntity);
        productEntity.setIsDeleted(true);
        productRepository.persist(productEntity);
        sendToKafka(mockUsername, productBefore, productEntity);
    }

    public void sendToKafka(String username, Object objectBefore, Object objectAfter){
        kafkaEvents.sendNewKafkaEvent(AuditDTO.fromAuditDTO(username, objectBefore, objectAfter));
    }
}
