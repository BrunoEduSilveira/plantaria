package com.plantariadoluis.plantaria.controllers;

import com.plantariadoluis.plantaria.dtos.ProductDto;
import com.plantariadoluis.plantaria.models.ProductModel;
import com.plantariadoluis.plantaria.services.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plantaria/produto")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid @NotNull ProductDto productDto) {
        if(productDto.getPrice() < 0.1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: You are trying to set value equal to zero.");
        }
        if (productService.existsByName(productDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The product name is already in use.");
        }
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProductModel>> getAllProducts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        ProductModel productModel = productModelOptional.get();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setId(productModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
}
