package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.dto.AlunoResponse;
import tech.devinhouse.labschool_rest_api.exception.RegistroExistenteException;
import tech.devinhouse.labschool_rest_api.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;
import tech.devinhouse.labschool_rest_api.repository.AlunoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository repository;
    private ModelMapper mapper;

    public List<Aluno> consultar(){
        return repository.findAll();
    }

    public List<Aluno> consultar(String situacao) {
        List<Aluno> alunos = new ArrayList<>();
        for (Aluno aluno : consultar()){
            if(aluno.getSituacao().toString().equals(situacao))
                alunos.add(aluno);
        }
        return alunos;
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
        aluno = repository.save(aluno);
        return aluno;
    }
}
