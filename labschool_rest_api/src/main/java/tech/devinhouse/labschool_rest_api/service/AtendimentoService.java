package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.exception.AlunoJaEmAtendimentoException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.Pedagogo;
import tech.devinhouse.labschool_rest_api.model.Pessoa;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.repository.AlunoRepository;
import tech.devinhouse.labschool_rest_api.repository.PedagogoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtendimentoService {

    private AlunoRepository alunoRepository;
    private PedagogoRepository pedagogoRepository;

    public List<Pessoa> realizarAtendimento(Integer codigoAluno, Integer codigoPedagogo)
            throws RegistroNaoEncontradoException, AlunoJaEmAtendimentoException {

        Optional<Aluno> alunoOptional = alunoRepository.findById(codigoAluno);
        Optional<Pedagogo> pedagogoOptional = pedagogoRepository.findById(codigoPedagogo);
        validaCodigos(alunoOptional, pedagogoOptional, codigoAluno, codigoPedagogo);

        List<Pessoa> atendimento = new ArrayList<>();
        Aluno aluno = alunoOptional.get();
        atualizaAluno(aluno);
        atendimento.add(aluno);

        Pedagogo pedagogo = pedagogoOptional.get();
        atualizaPedagogo(pedagogo);
        atendimento.add(pedagogo);

        return atendimento;
    }

    public void atualizaAluno(Aluno aluno) throws AlunoJaEmAtendimentoException {
        if (aluno.getSituacao().toString().equals("ATENDIMENTO_PEDAGOGICO"))
            throw new AlunoJaEmAtendimentoException();
        aluno.setSituacao(SituacaoMatricula.ATENDIMENTO_PEDAGOGICO);
        aluno.setAtendimentos(aluno.getAtendimentos() + 1);
        alunoRepository.save(aluno);
    }

    public void atualizaPedagogo(Pedagogo pedagogo) {
        pedagogo.setAtendimentos(pedagogo.getAtendimentos() + 1);
        pedagogoRepository.save(pedagogo);
    }

    public void validaCodigos(Optional alunoOptional, Optional pedagogoOptional,
                              Integer codigoAluno, Integer codigoPedagogo) throws RegistroNaoEncontradoException {
        if (alunoOptional.isEmpty())
            throw new RegistroNaoEncontradoException("Aluno", codigoAluno);
        else if (pedagogoOptional.isEmpty())
            throw new RegistroNaoEncontradoException("Pedagogo", codigoPedagogo);
    }
}
