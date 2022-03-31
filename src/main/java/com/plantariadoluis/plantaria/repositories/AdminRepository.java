package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, UUID> {

    boolean existsByUser(String user);

    boolean existsByEmail(String email);
}
