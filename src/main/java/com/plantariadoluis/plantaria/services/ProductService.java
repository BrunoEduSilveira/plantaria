package com.plantariadoluis.plantaria.services;

import com.plantariadoluis.plantaria.models.ProductModel;
import com.plantariadoluis.plantaria.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public Page<ProductModel> findAll(Pageable page){
        return productRepository.findAll(page);
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(ProductModel customerModel) {
        productRepository.delete(customerModel);
    }
}
