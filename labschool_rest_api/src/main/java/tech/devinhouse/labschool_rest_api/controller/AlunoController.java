package tech.devinhouse.labschool_rest_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.labschool_rest_api.dto.AlunoRequest;
import tech.devinhouse.labschool_rest_api.dto.AlunoResponse;
import tech.devinhouse.labschool_rest_api.dto.ErroResponse;
import tech.devinhouse.labschool_rest_api.dto.SituacaoMatriculaRequest;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.service.AlunoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService service;
    private ModelMapper mapper;

    @Operation(summary = "Serviço de cadastro de aluno",
            description = "Serviço de cadastro de aluno. Requer os dados do aluno. Não deve conter CPFs já cadastrados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição com dados inválidos",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))}),
                    @ApiResponse(responseCode = "409", description = "CPF já cadastrado",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))})
            }
    )
    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody @Valid AlunoRequest request){
        Aluno aluno = mapper.map(request, Aluno.class);
        aluno = service.criar(aluno);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.created(URI.create(response.getCodigo().toString())).body(response);
    }

    @Operation(summary = "Serviço de atualização da situação da matrícula do aluno",
            description = "Serviço de atualização da situação da matrícula do aluno. Requer o código e situação do aluno.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Situação de matrícula do aluno atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição com dados inválidos",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Código do aluno não localizado",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))})
            }
    )
    @PutMapping("{codigo}")
    public ResponseEntity<AlunoResponse> atualizarSituacao(@PathVariable("codigo") Integer codigo,
                                                           @RequestBody @Valid SituacaoMatriculaRequest request)
            throws RegistroNaoEncontradoException {
        String situacaoMatricula = request.getSituacao();
        Aluno aluno = service.atualizar(codigo, situacaoMatricula);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Serviço de consulta de alunos",
            description = "Serviço de consulta de alunos. Utilização opcional de query param com filtro por situaçãoda matrícula.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Consulta de alunos realizada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> consultar(
            @RequestParam(value = "situacao", required = false) String situacao){

        List<Aluno> alunos;
        alunos = (situacao == null) ? service.consultar() : service.consultar(situacao);

        List<AlunoResponse> response = alunos.stream().map(a-> mapper.map(a, AlunoResponse.class)).
                collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Serviço de consulta de alunos cadastrados através do código",
            description = "Serviço de realização de consultas dos alunos cadastrados. Requer o código do aluno.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Consulta do aluno realizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Código de aluno não localizado",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))})
            }
    )
    @GetMapping("{codigo}")
    public ResponseEntity<AlunoResponse> consultarPorId(@PathVariable("codigo") Integer codigo)
            throws RegistroNaoEncontradoException {
        Aluno aluno = service.consultar(codigo);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Serviço de exclusão de registro do aluno",
            description = "Serviço de exclusão de registro do aluno. Requer o código do aluno.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Registro do aluno excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Código do aluno não localizado",
                            content = {@Content(schema = @Schema(implementation = ErroResponse.class))})
            }
    )
    @DeleteMapping("{codigo}")
    public ResponseEntity excluir(@PathVariable("codigo") Integer codigo) throws RegistroNaoEncontradoException {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
