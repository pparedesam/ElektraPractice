package elektra.exercise.two.service.impl;

import elektra.exercise.two.client.ClientFeign;
import elektra.exercise.two.dto.LoanPaymentDTO;
import elektra.exercise.two.model.Loan;
import elektra.exercise.two.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoanServiceImplTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    private ClientFeign clientFeign;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void calculateTotalPayment_success_vipClient() {
        Long clientId = 1L;
        Loan loan = new Loan(null, 1000.0, clientId, LocalDate.now(), Status.PENDIENTE);

        HashMap<String, Object> clientResponse = new HashMap<>();
        HashMap<String, Object> clientData = new HashMap<>();
        clientData.put("id", clientId);
        clientData.put("name", "Diana Paredes");
        clientData.put("email", "diana@example.com");
        clientData.put("age", 30);
        clientData.put("clientType", "VIP");
        clientResponse.put("data", clientData);

        when(clientFeign.getClientById(clientId)).thenReturn(clientResponse);

        LoanPaymentDTO loanPaymentDTO = loanService.calculateTotalPayment(loan);

        assertEquals(1050.0, loanPaymentDTO.getAmount());
        assertEquals("VIP", loanPaymentDTO.getClientType());

        verify(clientFeign, times(1)).getClientById(clientId);
    }

    @Test
    void calculateTotalPayment_clientNotFound() {
        Long clientId = 1L;
        Loan loan = new Loan(null, 1000.0, clientId, LocalDate.now(), Status.PENDIENTE);

        HashMap<String, Object> clientResponse = new HashMap<>();
        clientResponse.put("data", null);

        when(clientFeign.getClientById(clientId)).thenReturn(clientResponse);

        Exception exception = assertThrows(RuntimeException.class, () -> loanService.calculateTotalPayment(loan));
        assertEquals("Error al procesar el cliente para calcular el pago total: El cliente con ID " + clientId + " no existe.", exception.getMessage());
        verify(clientFeign, times(1)).getClientById(clientId);
    }
}