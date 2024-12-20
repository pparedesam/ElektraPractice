package elektra.exercise.two.controller;

import elektra.exercise.two.dto.LoanCreateDTO;
import elektra.exercise.two.dto.LoanPaymentDTO;
import elektra.exercise.two.dto.LoanUpdateDTO;
import elektra.exercise.two.model.Loan;
import elektra.exercise.two.util.ApiResponse;
import elektra.exercise.two.util.CommonApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/loans")
public interface LoanApi {

    @Operation(
            summary = "Registrar Préstamo",
            description = "Realiza la Creación de un nuevo Préstamo",
            tags = {"loan-controller"})
    @CommonApiResponses
    @PostMapping(
            value = "",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<ApiResponse<Loan>> createLoan(@RequestBody LoanCreateDTO loanDTO);

    @Operation(
            summary = "Obtener Préstamo por ID",
            description = "Realiza la obtención de un Préstamo por ID",
            tags = {"loan-controller"})
    @CommonApiResponses
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"})
    ResponseEntity<ApiResponse<Loan>> getLoanById(@PathVariable Long id);

    @Operation(
            summary = "Actualizar Préstamo",
            description = "Actualiza el Estado del Préstamo",
            tags = {"loan-controller"})
    @CommonApiResponses
    @PatchMapping(
            value = "/status",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<ApiResponse<Loan>> updateLoanStatus(
            @RequestBody LoanUpdateDTO loanUpdateDTO);

    @Operation(
            summary = "Eliminar Préstamo",
            description = "Realiza eliminación del Préstamo por ID",
            tags = {"loan-controller"})
    @CommonApiResponses
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"})
    ResponseEntity<ApiResponse<Void>> deleteLoan(@PathVariable Long id);

    @Operation(
            summary = "Listado de Préstamos Activos",
            description = "Obtiene los Préstamos Activos, en estado PENDIENTE",
            tags = {"loan-controller"})
    @CommonApiResponses
    @GetMapping(
            value = "/active",
            produces = {"application/json"})
    ResponseEntity<ApiResponse<List<Loan>>> getAllActiveLoans();

    @Operation(
            summary = "Calcular Pago del Préstamo",
            description = "Calcula el pago del Préstamo incluido por los intereses segun el tipo de cliente",
            tags = {"loan-controller"})
    @CommonApiResponses
    @GetMapping(
            value = "/{id}/payment",
            produces = {"application/json"})
    ResponseEntity<ApiResponse<LoanPaymentDTO>> calculateLoanPayment(
            @PathVariable("id") Long id);
}
