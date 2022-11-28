package tech.devinhouse.labschool_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.labschool_rest_api.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
