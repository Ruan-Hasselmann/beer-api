package net.atos.beerapi.controller;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.beerapi.model.Beer;
import net.atos.beerapi.provider.JwtTokenProvider;
import net.atos.beerapi.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
@Tag(name= "Beer API", description = "API para gerenciamento das cervejas que eu gosto!!!")
@OpenAPIDefinition(
    info = @Info(
        title = "Documentação da API de cervejas",
        description = "API utilizada em treinamento do Swagger",
        version = "1.0.0",
        contact = @Contact(name = "Angelo Mesquita", url = "localhost:8080", email = "angeloamesquita@gmail.com")
    )
)
public class BeerController {

    private final BeerService beerService;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public BeerController(BeerService beerService, JwtTokenProvider tokenProvider) {
        this.beerService = beerService;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping(produces = "application/json")
    @Operation(
        summary = "Lista todas as cerveja",
        description = "Lista todas as cervejas cadastradas no sistema!"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "operação com sucesso | []")
    })
    public ResponseEntity<List<Beer>> getAllBeer(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwtToken = token;
        Claims claims = tokenProvider.validateToken(jwtToken);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Beer> beers = beerService.getAllBeers();
        return ResponseEntity.ok(beers);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
        summary = "Busca 1 registro de cerveja",
        description = "Busca 1 registro de cerveja pelo seu ID informado na URI"
    )
    public Beer getBeerById(@PathVariable Long id) {
        return beerService.getBeerById(id);
    }

    @PostMapping
    public Beer saveBeer(@RequestBody Beer beer) {
        return beerService.saveBeer(beer);
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
    }
}
