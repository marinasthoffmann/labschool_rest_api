package tech.devinhouse.labschool_rest_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "ALUNOS")
@AllArgsConstructor
public class Aluno extends Pessoa{

    @Enumerated(EnumType.STRING)
    private SituacaoMatricula situacao;
    private Float nota;
    private Integer atendimentos;

    public Aluno(String nome, String telefone, LocalDate dataNascimento, Long cpf,
                 SituacaoMatricula situacaoMatricula, Float nota) {
        super(nome, telefone, dataNascimento, cpf);
        this.situacao = situacaoMatricula;
        this.nota = nota;
        this.atendimentos = 0;
    }

    public Aluno() {

    }
}
