package elektra.exercise.one.util;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Solicitud/ Operación exitosa",
                content =
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = elektra.exercise.one.util.ApiResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Solicitud con sintaxis incorrecta o parámetros faltantes.",
                content =
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = elektra.exercise.one.util.ApiResponse.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Recurso no encontrado. Servicio no disponible.",
                content =
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = elektra.exercise.one.util.ApiResponse.class))),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor.",
                content =
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = elektra.exercise.one.util.ApiResponse.class)))
})
public @interface CommonApiResponses {}
