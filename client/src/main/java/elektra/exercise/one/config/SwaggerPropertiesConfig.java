package elektra.exercise.one.config;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "swagger")
@Setter
@Getter
public class SwaggerPropertiesConfig {

    private OpenAPI apiInfo;
}

