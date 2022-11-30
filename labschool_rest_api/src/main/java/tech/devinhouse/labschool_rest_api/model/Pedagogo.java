package tech.devinhouse.labschool_rest_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
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
