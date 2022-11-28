package tech.devinhouse.labschool_rest_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.Estado;
import tech.devinhouse.labschool_rest_api.model.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.labschool_rest_api.model.enums.FormacaoAcademica;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PROFESSORES")
@AllArgsConstructor
public class Professor extends Pessoa{

    @Enumerated(EnumType.STRING)
    private FormacaoAcademica formacaoAcademica;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private ExperienciaDesenvolvimento experienciaDesenvolvimento;

    public Professor(String nome, String telefone, LocalDate dataNascimento, Long cpf, FormacaoAcademica formacaoAcademica, Estado estado, ExperienciaDesenvolvimento experienciaDesenvolvimento) {
        super(nome, telefone, dataNascimento, cpf);
        this.formacaoAcademica = formacaoAcademica;
        this.estado = estado;
        this.experienciaDesenvolvimento = experienciaDesenvolvimento;
    }

    public Professor() {

    }
}
