package elektra.exercise.one.repository;

import elektra.exercise.one.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClientRepository {
    private final List<Client> clients = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Client addClient(Client client) {
        Long id = idCounter.incrementAndGet();
        Client newClient = new Client(id, client.name(), client.email(), client.age(), client.clientType());
        clients.add(newClient);
        return newClient;
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    public Optional<Client> findById(Long id) {
        return clients.stream()
                .filter(client -> client.id().equals(id))
                .findFirst();
    }

    public Optional<Client>  updateClient(Client client) {
        return findById(client.id()).map(existingClient -> {
            clients.remove(existingClient);
            Client updatedClient = new Client(
                    client.id(),
                    client.name(),
                    client.email(),
                    client.age(),
                    client.clientType()
            );
            clients.add(updatedClient);
            return updatedClient;
        });
    }

    public boolean deleteClient(Long id) {
        return findById(id).map(client -> {
            clients.remove(client);
            return true;
        }).orElse(false);
    }
}
