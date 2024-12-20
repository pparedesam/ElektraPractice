package elektra.exercise.one.service.impl;

import elektra.exercise.one.model.Client;
import elektra.exercise.one.repository.ClientRepository;
import elektra.exercise.one.service.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    public Client addClient(Client client) {
        return repository.addClient(client);
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Optional<Client> findClientById(Long id) {
        return repository.findById(id);
    }

    public Optional<Client>  updateClient(Client client) {
        return repository.updateClient(client);
    }

    public boolean deleteClient(Long id) {
        return repository.deleteClient(id);
    }
}
