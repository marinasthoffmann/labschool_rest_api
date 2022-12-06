package tech.devinhouse.labschool_rest_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labschool_rest_api.dto.PedagogoResponse;
import tech.devinhouse.labschool_rest_api.model.Pedagogo;
import tech.devinhouse.labschool_rest_api.service.PedagogoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedagogos")
@AllArgsConstructor
public class PedagogoController {

    private PedagogoService service;

    private ModelMapper mapper;

    @Operation(summary = "Serviço de consulta de pedagogos", description = "Serviço de consulta de pedagogos.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Consulta de pedagogos realizada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<PedagogoResponse>> consultar(){
        List<Pedagogo> pedagogos = service.consultar();
        List<PedagogoResponse> response = pedagogos.stream().map(p-> mapper.map(p, PedagogoResponse.class)).
                collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
