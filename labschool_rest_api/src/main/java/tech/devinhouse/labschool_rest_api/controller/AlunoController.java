package tech.devinhouse.labschool_rest_api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.labschool_rest_api.dto.AlunoRequest;
import tech.devinhouse.labschool_rest_api.dto.AlunoResponse;
import tech.devinhouse.labschool_rest_api.dto.SituacaoMatriculaRequest;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.service.AlunoService;

import java.net.URI;

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

}
