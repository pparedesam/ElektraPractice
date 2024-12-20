package elektra.exercise.one.service;

import elektra.exercise.one.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {


    Client addClient(Client client);

    List<Client> getAllClients();

    Optional<Client> findClientById(Long id);

    Optional<Client>  updateClient(Client client);

    boolean deleteClient(Long id);
}
