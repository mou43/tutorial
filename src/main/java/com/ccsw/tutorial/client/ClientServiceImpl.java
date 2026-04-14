package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.DuplicateClientException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client get(Long id) {
        return this.clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void save(Long id, ClientDto dto) {
        Client client;

        Client existingClient = clientRepository.findByNameIgnoreCase(dto.getName());

        if (id == null) {
            if (existingClient != null) {
                throw new DuplicateClientException(dto.getName());
            }
            client = new Client();

        } else {
            client = this.get(id);

        }

        client.setName(dto.getName());
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("El cliente que intentas eliminar por ese ID no existe");
        }

        this.clientRepository.deleteById(id);
    }
}
