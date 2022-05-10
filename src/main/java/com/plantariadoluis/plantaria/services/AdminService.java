package com.plantariadoluis.plantaria.services;

import com.plantariadoluis.plantaria.models.AdminModel;
import com.plantariadoluis.plantaria.repositories.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminService {

    final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public AdminModel save(AdminModel adminModel) {
        return adminRepository.save(adminModel);
    }

    public boolean existsByUser(String user) {
        return adminRepository.existsByUser(user);
    }

    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    public Page<AdminModel> findAll(Pageable page) {
        return adminRepository.findAll(page);
    }

    public Optional<AdminModel> findById(long id) {
        return adminRepository.findById(id);
    }

    @Transactional
    public void delete(AdminModel adminModel) {
        adminRepository.delete(adminModel);
    }
}
