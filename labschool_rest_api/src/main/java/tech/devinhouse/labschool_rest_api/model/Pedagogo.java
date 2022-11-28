package tech.devinhouse.labschool_rest_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PEDAGOGOS")
@AllArgsConstructor
public class Pedagogo extends Pessoa {

    private Integer atendimentos;

    public Pedagogo(String nome, String telefone, LocalDate dataNascimento, Long cpf) {
        super(nome, telefone, dataNascimento, cpf);
        this.atendimentos = 0;
    }

    public Pedagogo() {

    }
}
