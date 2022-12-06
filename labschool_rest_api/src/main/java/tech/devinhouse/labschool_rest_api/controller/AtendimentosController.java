package tech.devinhouse.labschool_rest_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labschool_rest_api.dto.*;
import tech.devinhouse.labschool_rest_api.exception.AlunoJaEmAtendimentoException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.Pedagogo;
import tech.devinhouse.labschool_rest_api.service.AtendimentoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/atendimentos")
@AllArgsConstructor
public class AtendimentosController {

    private ModelMapper mapper;

    private AtendimentoService service;

    @Operation(summary = "Serviço de realização de atendimentos pedagógicos",
            description = "Serviço de realização de atendimentos pedagógicos. Requer um aluno e um pedagogo.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atendimento pedagógico realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação do request body",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))}),
                    @ApiResponse(responseCode = "409", description = "Aluno já se encontra em atendimento pedagógico",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Código do aluno ou pedagogo não cadastrado",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))})
            }
    )
    @PutMapping
    public ResponseEntity<AtendimentoResponse> realizarAtendimento(@RequestBody @Valid AtendimentoRequest request)
            throws AlunoJaEmAtendimentoException, RegistroNaoEncontradoException {
        Integer codigoAluno = request.getCodigoAluno();
        Integer codigoPedagogo = request.getCodigoPedagogo();
        service.realizarAtendimento(codigoAluno, codigoPedagogo);

        Aluno aluno = service.consultaAlunoAtendido(codigoAluno);
        Pedagogo pedagogo = service.consultaPedagogoAtendente(codigoPedagogo);
        AlunoResponse alunoResponse = mapper.map(aluno, AlunoResponse.class);
        PedagogoResponse pedagogoResponse = mapper.map(pedagogo, PedagogoResponse.class);

        AtendimentoResponse response = new AtendimentoResponse(alunoResponse, pedagogoResponse);
        return ResponseEntity.ok(response);
    }
}
