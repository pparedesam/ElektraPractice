package elektra.exercise.one.controller;

import elektra.exercise.one.dto.ClientCreateDTO;
import elektra.exercise.one.dto.ClientUpdateDTO;
import elektra.exercise.one.model.Client;
import elektra.exercise.one.service.impl.ClientServiceImpl;
import elektra.exercise.one.util.ApiResponse;
import elektra.exercise.one.util.ValidationGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class ClientController implements ClientApi {

    private final ClientServiceImpl service;
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    public ClientController(ClientServiceImpl service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> addClient(ClientCreateDTO clientDTO) {
        log.info("Received Client DTO: {}", clientDTO);

        Client client = new Client(null, clientDTO.name(), clientDTO.email(), clientDTO.age(), clientDTO.clientType());
        Client newClient = service.addClient(client);

        ApiResponse<Client> response = ApiResponse.success(HttpStatus.OK, "Cliente registrado exitosamente", newClient);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> getClientById(Long id) {
        return service.findClientById(id)
                .map(client -> ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Cliente encontrado", client)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Cliente no encontrado", List.of("ID inválido"))));
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> updateClient(@Validated(ValidationGroups.Update.class) ClientUpdateDTO clientDTO) {

        Client clientToUpdate = new Client(clientDTO.id(), clientDTO.name(), clientDTO.email(), clientDTO.age(), clientDTO.clientType());
        return service.updateClient(clientToUpdate)
                .map(updatedClient -> ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Cliente actualizado exitosamente", updatedClient)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Cliente no encontrado", List.of("ID inválido"))));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> deleteClient(Long id) {
        boolean deleted = service.deleteClient(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Cliente eliminado exitosamente", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Cliente no encontrado", List.of("ID inválido")));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Client>>> getAllClients() {
        List<Client> clients = service.getAllClients();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Lista de Clientes", clients));
    }
}
