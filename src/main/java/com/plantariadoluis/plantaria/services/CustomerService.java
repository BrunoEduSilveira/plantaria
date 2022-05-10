package com.plantariadoluis.plantaria.services;

import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerModel save(CustomerModel customerModel) {
        return customerRepository.save(customerModel);
    }

    public boolean existsByCpf(String cpf) {
        return customerRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return customerRepository.existsByPhone(phone);
    }

    public Page<CustomerModel> findAll(Pageable page) {
        return customerRepository.findAll(page);
    }

    public Optional<CustomerModel> findById(long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }


}
