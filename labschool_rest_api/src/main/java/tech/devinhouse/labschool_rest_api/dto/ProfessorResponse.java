package tech.devinhouse.labschool_rest_api.dto;

import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.Estado;
import tech.devinhouse.labschool_rest_api.model.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.labschool_rest_api.model.enums.FormacaoAcademica;
import java.time.LocalDate;

@Data
public class ProfessorResponse {

    private Integer codigo;

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private Long cpf;

    private FormacaoAcademica formacao;

    private Estado estado;

    private ExperienciaDesenvolvimento experiencia;
}
