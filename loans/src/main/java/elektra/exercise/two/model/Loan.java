package elektra.exercise.two.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    private Long id;
    private double amount;
    private Long clientId;
    private LocalDate date;
    private Status status;
}
