package com.plantariadoluis.plantaria.controllers;

import com.plantariadoluis.plantaria.dtos.AdminDto;
import com.plantariadoluis.plantaria.models.AdminModel;
import com.plantariadoluis.plantaria.services.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plantaria/admin")
public class AdminController {

    final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAdmin(@RequestBody @Valid @NotNull AdminDto adminDto) {
        if (adminService.existsByUser(adminDto.getUser())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Login user is already in use.");
        }
        if (adminService.existsByEmail(adminDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use.");
        }
        AdminModel adminModel = new AdminModel();
        BeanUtils.copyProperties(adminDto, adminModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(adminModel));
    }

    @GetMapping
    public ResponseEntity<Page<AdminModel>> getAllAdmins(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdmin(@PathVariable(value = "id") UUID id) {
        Optional<AdminModel> adminModelOptional = adminService.findById(id);
        if (!adminModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(adminModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable(value = "id") UUID id) {
        Optional<AdminModel> adminModelOptional = adminService.findById(id);
        if (!adminModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        }
        adminService.delete(adminModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Administrator deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable(value = "id") UUID id, @RequestBody @Valid AdminDto adminDto) {
        Optional<AdminModel> adminModelOptional = adminService.findById(id);
        if (!adminModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        }
        if (!adminDto.getUser().equals(adminModelOptional.get().getUser())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login user can't be change.");
        }
        AdminModel adminModel = adminModelOptional.get();
        BeanUtils.copyProperties(adminDto, adminModel);
        adminModel.setId(adminModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(adminService.save(adminModel));
    }
}
