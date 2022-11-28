package tech.devinhouse.labschool_rest_api.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.labschool_rest_api.model.Aluno;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

//    boolean existsAlunoByCpf(Long cpf);
}
