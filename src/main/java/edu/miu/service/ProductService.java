package edu.miu.service;

import edu.miu.annotation.ExecutionTime;
import edu.miu.model.Product;
import edu.miu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @ExecutionTime
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @ExecutionTime
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
