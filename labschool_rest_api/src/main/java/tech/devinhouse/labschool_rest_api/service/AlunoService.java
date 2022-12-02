package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.exception.RegistroExistenteException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.repository.AlunoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository repository;

    public List<Aluno> consultar(){
        return repository.findAll();
    }

    public List<Aluno> consultar(String situacao) {
        List<Aluno> alunos = new ArrayList<>();
        consultar().stream().filter(a -> a.getSituacao().toString().equals(situacao)).forEach(alunos::add);
        return alunos;
    }

    public Aluno consultar(Integer codigo) throws RegistroNaoEncontradoException {
        return repository.findById(codigo).orElseThrow(() -> new RegistroNaoEncontradoException("Aluno", codigo));
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
        Aluno aluno = repository.findById(codigo)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Aluno.class.getSimpleName(), codigo));

        aluno.setSituacao(SituacaoMatricula.valueOf(situacaoMatricula));
        aluno = repository.save(aluno);
        return aluno;
    }

    public void excluir(Integer codigo) throws RegistroNaoEncontradoException {
        Aluno aluno = repository.findById(codigo)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Aluno.class.getSimpleName(), codigo));
        repository.delete(aluno);
    }
}
