package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AlunoRequest {

    @Schema(title = "Nome do aluno")
    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 1, max = 70, message = "{campo.invalido}")
    private String nome;

    @Schema(title = "Telefone do aluno")
    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 8, max = 20, message = "{campo.invalido}")
    private String telefone;

    @Schema(title = "Data de nascimento do aluno", description = "YYYY-mm-dd")
    @NotNull(message = "{campo.obrigatorio}")
    @Past
    private LocalDate dataNascimento;

    @Schema(title = "CPF do aluno", description = "Deve ser registro único")
    @NotNull(message = "{campo.obrigatorio}")
    @Digits(integer = 11, fraction = 0)
    private Long cpf;

    @Schema(title = "Situação da matrícula do aluno",
            description = "ATIVO | INATIVO | IRREGULAR | ATENDIMENTO_PEDAGOGICO ")
    @NotEmpty(message = "{campo.obrigatorio}")
    @Pattern(regexp = "ATIVO|IRREGULAR|ATENDIMENTO_PEDAGOGICO|INATIVO",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "{campo.invalido}")
    private String situacao;

    @Schema(title = "Nota do aluno")
    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 0, message = "{campo.invalido}")
    @Max(value = 10, message = "{campo.invalido}")
    private Float nota;
}
