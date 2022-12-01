package tech.devinhouse.labschool_rest_api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labschool_rest_api.dto.AlunoResponse;
import tech.devinhouse.labschool_rest_api.dto.AtendimentoRequest;
import tech.devinhouse.labschool_rest_api.dto.AtendimentoResponse;
import tech.devinhouse.labschool_rest_api.dto.PedagogoResponse;
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
