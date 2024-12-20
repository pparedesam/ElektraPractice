package elektra.exercise.two.controller;

import elektra.exercise.two.dto.LoanCreateDTO;
import elektra.exercise.two.dto.LoanPaymentDTO;
import elektra.exercise.two.dto.LoanUpdateDTO;
import elektra.exercise.two.model.Loan;
import elektra.exercise.two.service.ILoanService;
import elektra.exercise.two.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class LoanController implements LoanApi {

    private final ILoanService loanService;

    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public ResponseEntity<ApiResponse<Loan>> createLoan(LoanCreateDTO loanDTO) {
        Loan loan = new Loan(null, loanDTO.amount(), loanDTO.clientId(), loanDTO.date(), loanDTO.status());
        Loan createdLoan = loanService.createLoan(loan);
        ApiResponse<Loan> response = ApiResponse.success(HttpStatus.CREATED, "Préstamo creado exitosamente", createdLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ApiResponse<Loan>> getLoanById(Long id) {
        Optional<Loan> loan = loanService.findLoanById(id);
        if (loan.isPresent()) {
            ApiResponse<Loan> response = ApiResponse.success(HttpStatus.OK, "Préstamo encontrado", loan.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Préstamo no encontrado", List.of("ID préstamo no válido")));
    }

    @Override
    public ResponseEntity<ApiResponse<Loan>> updateLoanStatus(LoanUpdateDTO loanUpdateDTO) {
        Loan loan = new Loan(loanUpdateDTO.id(), 0, null, null, loanUpdateDTO.status());
        Optional<Loan> updatedLoan = loanService.updateLoanStatus(loan);
        if (updatedLoan.isPresent()) {
            ApiResponse<Loan> response = ApiResponse.success(HttpStatus.OK, "Estado del préstamo actualizado exitosamente", updatedLoan.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Préstamo no encontrado", List.of("ID préstamo no válido")));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> deleteLoan(Long id) {
        boolean deleted = loanService.deleteLoan(id);
        if (deleted) {
            ApiResponse<Void> response = ApiResponse.success(HttpStatus.OK, "Préstamo eliminado exitosamente", null);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Préstamo no encontrado", List.of("ID préstamo no válido")));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Loan>>> getAllActiveLoans() {
        List<Loan> activeLoans = loanService.getAllActiveLoans();
        ApiResponse<List<Loan>> response = ApiResponse.success(HttpStatus.OK, "Préstamos activos recuperados con éxito", activeLoans);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponse<LoanPaymentDTO>> calculateLoanPayment(Long id) {
        Optional<Loan> loan = loanService.findLoanById(id);
        if (loan.isPresent()) {
            LoanPaymentDTO loanPaymentDTO;
            loanPaymentDTO = loanService.calculateTotalPayment(loan.get());
            ApiResponse<LoanPaymentDTO> response = ApiResponse.success(HttpStatus.OK, "Pago total calculado correctamente para el tipo de cliente: " + loanPaymentDTO.getClientType(), loanPaymentDTO);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(HttpStatus.NOT_FOUND, "Préstamo no encontrado", List.of("ID préstamo no válido")));
    }
}
