package elektra.exercise.two.dto;

import elektra.exercise.two.model.Status;
import jakarta.validation.constraints.NotNull;

public record LoanUpdateDTO(
        @NotNull(message = "Loan ID cannot be null")
        Long id,

        @NotNull(message = "Status cannot be null")
        Status status
) {}
