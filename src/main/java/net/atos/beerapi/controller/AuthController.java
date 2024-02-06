package net.atos.beerapi.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.beerapi.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Auth API", description = "API para autenticação")
@OpenAPIDefinition(
        info = @Info(
                title = "API de Autenticação",
                description = "API para geração de tokens de acesso",
                version = "1.0.0",
                contact = @Contact(name = "Angelo Mesquita", url = "localhost:8080", email = "angeloamesquita@gmail.com")
        )
)
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/token")
    @Operation(
            summary = "Gerar Token de Acesso",
            description = "Gera um token de acesso com base nas credenciais fornecidas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token gerado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public String gerarToken() {

            String token = tokenProvider.generateToken();
            return token;

    }
}
