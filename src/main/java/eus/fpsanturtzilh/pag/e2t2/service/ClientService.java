package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Client;
import eus.fpsanturtzilh.pag.e2t2.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id " + id));
    }

    public Client createClient(Client client) {
        if (client.getName() == null || client.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client name cannot be null or blank");
        }
        if (client.getSurname() == null || client.getSurname().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client surname cannot be null or blank");
        }
        if (client.getPhone() == null || client.getPhone().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client phone cannot be null or blank");
        }
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client email cannot be null or blank");
        }
        if (client.getHome_client() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client home_client cannot be null");
        }
        return clientRepo.save(client);
    }

    @Transactional
    public Client updateClient(Long id, Client updatedClient) {
        Client clientOld = clientRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id " + id));

        if (updatedClient.getName() == null || updatedClient.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client name cannot be null or blank");
        }
        if (updatedClient.getSurname() == null || updatedClient.getSurname().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client surname cannot be null or blank");
        }
        if (updatedClient.getPhone() == null || updatedClient.getPhone().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client phone cannot be null or blank");
        }
        if (updatedClient.getEmail() == null || updatedClient.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client email cannot be null or blank");
        }
        if (updatedClient.getHome_client() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client home_client cannot be null");
        }

        clientOld.setName(updatedClient.getName());
        clientOld.setSurname(updatedClient.getSurname());
        clientOld.setPhone(updatedClient.getPhone());
        clientOld.setEmail(updatedClient.getEmail());
        clientOld.setHome_client(updatedClient.getHome_client());

        return clientRepo.save(clientOld);
    }

    public void deleteClient(Long id) {
        if (!clientRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id " + id);
        }
        clientRepo.deleteById(id);
    }
}
