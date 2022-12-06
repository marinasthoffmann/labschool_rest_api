package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.Estado;
import tech.devinhouse.labschool_rest_api.model.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.labschool_rest_api.model.enums.FormacaoAcademica;
import java.time.LocalDate;

@Data
public class ProfessorResponse {

    @Schema(title = "Código de identificação do professor")
    private Integer codigo;

    @Schema(title = "Nome do professor")
    private String nome;

    @Schema(title = "Telefone do professor")
    private String telefone;

    @Schema(title = "Data de nascimento do professor", description = "YYYY-mm-dd")
    private LocalDate dataNascimento;

    @Schema(title = "CPF do professor")
    private Long cpf;

    @Schema(title = "Formação acadêmica do professor",
            description = "GRADUACAO_INCOMPLETA | GRADUACAO_COMPLETA | MESTRADO | DOUTORADO ")
    private FormacaoAcademica formacao;

    @Schema(title = "Status atual do professor",
            description = "ATIVO | INATIVO")
    private Estado estado;

    @Schema(title = "Área de experiência em desenvolvimento do professor",
            description = "FRONT_END | BACK_END | FULL_STACK")
    private ExperienciaDesenvolvimento experiencia;
}
