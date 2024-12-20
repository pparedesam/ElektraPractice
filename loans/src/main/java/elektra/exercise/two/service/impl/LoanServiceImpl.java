package elektra.exercise.two.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import elektra.exercise.two.client.Client;
import elektra.exercise.two.client.ClientFeign;
import elektra.exercise.two.dto.LoanPaymentDTO;
import elektra.exercise.two.model.Loan;
import elektra.exercise.two.repository.LoanRepository;
import elektra.exercise.two.service.ILoanService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository repository;
    private final ClientFeign clientFeign;

    public LoanServiceImpl(LoanRepository repository, ClientFeign clientFeign) {
        this.repository = repository;
        this.clientFeign = clientFeign;
    }

    @Override
    public Loan createLoan(Loan loan) {
        HashMap<String, Object> response = clientFeign.getClientById(loan.getClientId());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object data = response.get("data");
            Client client = mapper.convertValue(data, Client.class);
            if (client == null) {
                throw new IllegalArgumentException("El cliente con ID " + loan.getClientId() + " no existe.");
            }
            return repository.createLoan(loan);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la respuesta del cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Loan> getAllActiveLoans() {
        return repository.findAllActive();
    }

    @Override
    public Optional<Loan> findLoanById(Long id) {
        return repository.findLoanById(id);
    }

    @Override
    public Optional<Loan> updateLoanStatus(Loan loan) {
        return repository.updateStatus(loan);
    }

    @Override
    public boolean deleteLoan(Long id) {
        return repository.deleteLoan(id);
    }

    @Override
    public LoanPaymentDTO calculateTotalPayment(Loan loan) {
        LoanPaymentDTO loanPaymentDTO = new LoanPaymentDTO();
        HashMap<String, Object> response = clientFeign.getClientById(loan.getClientId());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object data = response.get("data");
            Client client = mapper.convertValue(data, Client.class);

            if (client == null) {
                throw new IllegalArgumentException("El cliente con ID " + loan.getClientId() + " no existe.");
            }
            double interestRate = client.clientType().toString().equalsIgnoreCase("VIP") ? 0.05 : 0.10;
            loanPaymentDTO.setAmount(loan.getAmount() + (loan.getAmount() * interestRate));
            loanPaymentDTO.setClientType(client.clientType().toString());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el cliente para calcular el pago total: " + e.getMessage(), e);
        }
        return loanPaymentDTO;
    }

}
