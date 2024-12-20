package elektra.exercise.one.controller;

import elektra.exercise.one.dto.ClientCreateDTO;
import elektra.exercise.one.dto.ClientUpdateDTO;
import elektra.exercise.one.model.Client;
import elektra.exercise.one.util.ApiResponse;
import elektra.exercise.one.util.CommonApiResponses;
import elektra.exercise.one.util.ValidationGroups;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/clients")
public interface ClientApi {

    @Operation(
            summary = "Registrar Cliente",
            description = "Realiza la creación de un cliente nuevo",
            tags = {"client-controller"}
    )
    @CommonApiResponses
    @PostMapping(
            value = "",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<ApiResponse<Client>> addClient(
            @Validated(ValidationGroups.Create.class) @RequestBody ClientCreateDTO clientDTO);


    @Operation(
            summary = "Obtener Cliente por ID",
            description = "Obtiene un cliente por su ID",
            tags = {"client-controller"}
    )
    @CommonApiResponses
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<ApiResponse<Client>> getClientById(
            @PathVariable Long id);


    @Operation(
            summary = "Actualizar Cliente",
            description = "Actualiza un cliente existente",
            tags = {"client-controller"}
    )
    @CommonApiResponses
    @PutMapping(
            value = "",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<ApiResponse<Client>> updateClient(
            @Validated(ValidationGroups.Update.class) @RequestBody ClientUpdateDTO clientDTO);


    @Operation(
            summary = "Eliminar Cliente",
            description = "Elimina un cliente por su ID",
            tags = {"client-controller"}
    )
    @CommonApiResponses
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable Long id);


    @Operation(
            summary = "Listar Clientes",
            description = "Obtiene la lista de todos los clientes",
            tags = {"client-controller"}
    )
    @CommonApiResponses
    @GetMapping(
            value = "",
            produces = {"application/json"}
    )
    ResponseEntity<ApiResponse<List<Client>>> getAllClients();
}
