package tech.devinhouse.labschool_rest_api.controller;

import lombok.AllArgsConstructor;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.labschool_rest_api.dto.AlunoRequest;
import tech.devinhouse.labschool_rest_api.dto.AlunoResponse;
import tech.devinhouse.labschool_rest_api.dto.SituacaoMatriculaRequest;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.service.AlunoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService service;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody @Valid AlunoRequest request){
        Aluno aluno = mapper.map(request, Aluno.class);
        aluno = service.criar(aluno);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.created(URI.create(response.getCodigo().toString())).body(response);
    }

    @PutMapping("{codigo}")
    public ResponseEntity<AlunoResponse> atualizarSituacao(@PathVariable("codigo") @Valid Integer codigo,
                                                           @RequestBody @Valid SituacaoMatriculaRequest request)
            throws RegistroNaoEncontradoException {
        String situacaoMatricula = request.getSituacao();
        Aluno aluno = service.atualizar(codigo, situacaoMatricula);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> consultar(
            @RequestParam(value = "situacao", required = false) String situacao){

        List<Aluno> alunos;
        if (situacao == null)
            alunos = service.consultar();
        else
            alunos = service.consultar(situacao);

        List<AlunoResponse> response = alunos.stream().map(a-> mapper.map(a, AlunoResponse.class)).
                collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<AlunoResponse> consultarPorId(@PathVariable("codigo") @Valid Integer codigo)
            throws RegistroNaoEncontradoException {
        Aluno aluno = service.consultar(codigo);
        AlunoResponse response = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity excluir(@PathVariable("codigo") @Valid Integer codigo) throws RegistroNaoEncontradoException {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
