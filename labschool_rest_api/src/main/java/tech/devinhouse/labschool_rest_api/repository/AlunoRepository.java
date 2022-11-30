package tech.devinhouse.labschool_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.labschool_rest_api.model.Aluno;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    boolean existsAlunoByCpf(Long cpf);
    Aluno findAlunoBySituacao(SituacaoMatricula situacaoMatricula);
}
