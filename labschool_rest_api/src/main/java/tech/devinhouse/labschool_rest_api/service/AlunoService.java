package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.repository.AlunoRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository repository;

    public List<Aluno> consultar(){
        return repository.findAll();
    }

    public Aluno criar(Aluno aluno){
//        boolean alunoExistente = repository.existsAlunoByCpf(aluno.getCpf());
//        if (alunoExistente)
//            throw new RegistroExistenteException("Aluno", aluno.getCpf());
        aluno = repository.save(aluno);
        return aluno;
    }

}
