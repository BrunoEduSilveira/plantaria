package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
