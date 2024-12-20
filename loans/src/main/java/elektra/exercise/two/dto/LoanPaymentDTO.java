package elektra.exercise.two.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPaymentDTO {
    double amount;
    String clientType;
}
