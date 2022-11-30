package tech.devinhouse.labschool_rest_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.Estado;
import tech.devinhouse.labschool_rest_api.model.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.labschool_rest_api.model.enums.FormacaoAcademica;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PROFESSORES")
@AllArgsConstructor
public class Professor extends Pessoa{

    @Enumerated(EnumType.STRING)
    private FormacaoAcademica formacao;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private ExperienciaDesenvolvimento experiencia;

    public Professor(String nome, String telefone, LocalDate dataNascimento, Long cpf, FormacaoAcademica formacaoAcademica, Estado estado, ExperienciaDesenvolvimento experienciaDesenvolvimento) {
        super(nome, telefone, dataNascimento, cpf);
        this.formacao = formacaoAcademica;
        this.estado = estado;
        this.experiencia = experienciaDesenvolvimento;
    }

    public Professor() {

    }
}
