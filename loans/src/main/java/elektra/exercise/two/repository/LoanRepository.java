package elektra.exercise.two.repository;

import elektra.exercise.two.model.Loan;
import elektra.exercise.two.model.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LoanRepository {

    private final List<Loan> loans = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Loan createLoan(Loan loan) {
        Long id = idCounter.incrementAndGet();
        Loan newloan = new Loan(id, loan.getAmount(), loan.getClientId(), loan.getDate(), loan.getStatus());
        loans.add(newloan);
        return newloan;
    }

    public List<Loan> findAllActive() {
        return loans.stream()
                .filter(loan -> loan.getStatus() == Status.PENDIENTE)
                .toList();
    }

    public Optional<Loan> findLoanById(Long id) {
        return loans.stream()
                .filter(loan -> loan.getId().equals(id))
                .findFirst();
    }

    public Optional<Loan> updateStatus(Loan loan) {
        return loans.stream()
                .filter(t -> t.getId().equals(loan.getId()))
                .peek(t -> t.setStatus(loan.getStatus()))
                .findFirst();
    }

    public boolean deleteLoan(Long id) {
        return loans.removeIf(loan -> loan.getId().equals(id));
    }
}
