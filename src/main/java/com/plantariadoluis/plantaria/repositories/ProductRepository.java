package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    boolean existsByName(String name);
}
