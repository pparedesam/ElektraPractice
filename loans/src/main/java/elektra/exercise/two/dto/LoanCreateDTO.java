package elektra.exercise.two.dto;

import elektra.exercise.two.model.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LoanCreateDTO(
        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 0")
        double amount,

        @NotNull(message = "Client ID cannot be null")
        Long clientId,

        @NotNull(message = "Date cannot be null")
        @PastOrPresent(message = "Date cannot be in the future")
        LocalDate date,

        @NotNull(message = "Status cannot be null")
        Status status
) {}
