package com.plantariadoluis.plantaria.controllers;

import com.plantariadoluis.plantaria.dtos.ClientDto;
import com.plantariadoluis.plantaria.models.ClientModel;
import com.plantariadoluis.plantaria.services.ClientService;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plantaria")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clienteService) {
        this.clientService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid @NotNull ClientDto clientDto) {
        if (clientService.existsByCpf(clientDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use.");
        }
        if (clientService.existsByEmail(clientDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use.");
        }
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<Page<ClientModel>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDto clientDto) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        ClientModel clientModel = clientModelOptional.get();
        BeanUtils.copyProperties(clientDto, clientModel);
        clientModel.setId(clientModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }


}
