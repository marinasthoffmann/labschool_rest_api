package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.model.Professor;
import tech.devinhouse.labschool_rest_api.repository.ProfessorRepository;

@Service
@AllArgsConstructor
public class ProfessorService {

    private ProfessorRepository repository;

    public Professor criar(Professor professor){
        return repository.save(professor);
    }
}
