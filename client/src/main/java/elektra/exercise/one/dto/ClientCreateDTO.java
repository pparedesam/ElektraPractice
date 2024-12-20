package elektra.exercise.one.dto;

import elektra.exercise.one.model.ClientType;
import elektra.exercise.one.util.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClientCreateDTO(
        @NotBlank(groups = ValidationGroups.Create.class) String name,
        @Email(groups = ValidationGroups.Create.class) String email,
        @Min(value = 18, groups = ValidationGroups.Create.class) int age,
      ClientType clientType
) {
}
