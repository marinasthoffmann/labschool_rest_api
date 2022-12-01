package tech.devinhouse.labschool_rest_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labschool_rest_api.dto.AtendimentoRequest;
import tech.devinhouse.labschool_rest_api.dto.AtendimentoResponse;
import tech.devinhouse.labschool_rest_api.exception.AlunoJaEmAtendimentoException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Pessoa;
import tech.devinhouse.labschool_rest_api.service.AtendimentoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@AllArgsConstructor
public class AtendimentosController {

    private AtendimentoService service;

    @PutMapping
    public void realizarAtendimento(@RequestBody @Valid AtendimentoRequest request)
            throws AlunoJaEmAtendimentoException, RegistroNaoEncontradoException {
        Integer codigoAluno = request.getCodigoAluno();
        Integer codigoPedagogo = request.getCodigoPedagogo();
        List<Pessoa> pessoas = service.realizarAtendimento(codigoAluno, codigoPedagogo);
        String blah = "";
    }
}
