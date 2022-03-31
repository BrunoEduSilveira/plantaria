package com.plantariadoluis.plantaria.controllers;

import com.plantariadoluis.plantaria.dtos.CustomerDto;
import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.services.CustomerService;
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
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plantaria/cliente")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid @NotNull CustomerDto customerDto) {
        if (customerService.existsByCpf(customerDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use.");
        }
        if (customerService.existsByEmail(customerDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use.");
        }
        if (customerService.existsByTelephone(customerDto.getTelephone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Telephone is already in use.");
        }
        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setEmail(customerModel.getEmail().toLowerCase(Locale.ROOT));

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerModel));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerModel>> getAllCustomers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable(value = "id") UUID id) {
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") UUID id) {
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        customerService.delete(customerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable(value = "id") UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        if (!customerDto.getCpf().equals(customerModelOptional.get().getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF can't be change.");
        }
        if (!customerDto.getName().equals(customerModelOptional.get().getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name can't be change.");
        }
        CustomerModel customerModel = customerModelOptional.get();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setId(customerModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerModel));
    }


}
