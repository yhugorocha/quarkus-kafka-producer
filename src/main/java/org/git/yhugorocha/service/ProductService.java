package org.git.yhugorocha.service;

import org.git.yhugorocha.dto.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product created(Product product);
    Product update(Product product, Long id);
    void delete(Long id);

}
