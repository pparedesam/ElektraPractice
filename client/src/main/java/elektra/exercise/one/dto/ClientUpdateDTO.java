package elektra.exercise.one.dto;


import elektra.exercise.one.model.ClientType;
import elektra.exercise.one.util.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientUpdateDTO(
        @NotNull(groups = ValidationGroups.Update.class) Long id,
        @NotBlank(groups = ValidationGroups.Update.class) String name,
        @Email(groups = ValidationGroups.Update.class) String email,
        @Min(value = 18, groups = ValidationGroups.Update.class) int age,
       ClientType clientType
) {
}