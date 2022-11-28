package tech.devinhouse.labschool_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labschool_rest_api.model.Pedagogo;
import tech.devinhouse.labschool_rest_api.repository.PedagogoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PedagogoService {
    private PedagogoRepository repository;

    public Pedagogo criar(Pedagogo pedagogo){
        return repository.save(pedagogo);
    }

    public List<Pedagogo> consultar() {
        return repository.findAll();
    }
}
