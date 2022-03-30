package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelephone(String telephone);
}
