package elektra.exercise.two.service;

import elektra.exercise.two.dto.LoanPaymentDTO;
import elektra.exercise.two.model.Loan;

import java.util.List;
import java.util.Optional;

public interface ILoanService {

    Loan createLoan(Loan loan);

    List<Loan> getAllActiveLoans();

    Optional<Loan> findLoanById(Long id);

    Optional<Loan> updateLoanStatus(Loan loan);

    boolean deleteLoan(Long id);

    LoanPaymentDTO calculateTotalPayment(Loan loan);
}
