package elektra.exercise.one.model;

public record Client(

        Long id,
        String name,
        String email,
        Integer age,
        ClientType clientType
) {}