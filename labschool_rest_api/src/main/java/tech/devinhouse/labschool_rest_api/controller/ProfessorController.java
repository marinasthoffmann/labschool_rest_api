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
import tech.devinhouse.labschool_rest_api.dto.ProfessorResponse;
import tech.devinhouse.labschool_rest_api.model.Professor;
import tech.devinhouse.labschool_rest_api.service.ProfessorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/professores")
@AllArgsConstructor
public class ProfessorController {

    private ProfessorService service;
    private ModelMapper mapper;

    @Operation(summary = "Serviço de consulta de professores", description = "Serviço de consulta de professores.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Consulta de professores realizada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> consultar(){
        List<Professor> professores = service.consultar();
        List<ProfessorResponse> response = professores.stream().map(p-> mapper.map(p, ProfessorResponse.class)).
                collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
