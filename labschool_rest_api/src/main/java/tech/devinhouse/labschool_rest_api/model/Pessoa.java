package tech.devinhouse.labschool_rest_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class Pessoa {

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private Long cpf;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    public Pessoa(String nome, String telefone, LocalDate dataNascimento, Long cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
}
