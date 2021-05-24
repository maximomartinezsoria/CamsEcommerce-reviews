package com.mms.CamsEcommerceReviews.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    List<Product> findAll () {
        return productRepository.findAll();
    }

    Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID=" + id + " doesn't exist");
        }
        return productOptional.get();
    }

     Product save(Product product) {
        return productRepository.save(product);
     }

     void delete(Long id) {
        productRepository.deleteById(id);
     }
}
