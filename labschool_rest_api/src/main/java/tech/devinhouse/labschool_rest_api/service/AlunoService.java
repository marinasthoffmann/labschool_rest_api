package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.exception.RegistroExistenteException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository repository;

    public List<Aluno> consultar(){
        return repository.findAll();
    }

    public Aluno criar(Aluno aluno){
        boolean alunoExistente = repository.existsAlunoByCpf(aluno.getCpf());
        if (alunoExistente)
            throw new RegistroExistenteException("Aluno", aluno.getCpf());
        aluno.setAtendimentos(0);
        aluno = repository.save(aluno);
        return aluno;
    }

    public Aluno atualizar(Integer codigo, String situacaoMatricula) throws RegistroNaoEncontradoException {
        Optional<Aluno> alunoOptional = repository.findById(codigo);
        if(alunoOptional.isEmpty())
            throw new RegistroNaoEncontradoException("Aluno", codigo);
        Aluno aluno = alunoOptional.get();
        aluno.setSituacao(SituacaoMatricula.valueOf(situacaoMatricula));
        return aluno;
    }
}
