package com.plantariadoluis.plantaria.services;

import com.plantariadoluis.plantaria.models.ClientModel;
import com.plantariadoluis.plantaria.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public boolean existsByCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    public Page<ClientModel> findAll(Pageable page) {
        return clientRepository.findAll(page);
    }

    public Optional<ClientModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
