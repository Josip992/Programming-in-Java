package com.oss.service;

import com.oss.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oss.repository.ClientRepository;

import java.util.List;


//import javax.persistence.EntityNotFoundException;
import jakarta.persistence.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(Client newClient) {
        clientRepository.save(newClient);
    }
}
