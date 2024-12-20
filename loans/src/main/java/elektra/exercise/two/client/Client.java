package elektra.exercise.two.client;


public record Client(
        Long id,
        String name,
        String email,
        Integer age,
        ClientType clientType
) {}

