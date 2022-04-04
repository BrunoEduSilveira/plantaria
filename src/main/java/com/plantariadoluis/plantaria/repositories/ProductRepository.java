package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    boolean existsByName(String name);
}
