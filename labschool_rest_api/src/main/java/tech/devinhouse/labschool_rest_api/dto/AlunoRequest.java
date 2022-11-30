package tech.devinhouse.labschool_rest_api.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AlunoRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 1, max = 70, message = "{campo.invalido}")
    private String nome;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 8, max = 20, message = "{campo.invalido}")
    private String telefone;

    @NotNull(message = "{campo.obrigatorio}")
    @Past
    private LocalDate dataNascimento;

    @NotNull(message = "{campo.obrigatorio}")
    @Digits(integer = 11, fraction = 0)
    private Long cpf;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Pattern(regexp = "ATIVO|IRREGULAR|ATENDIMENTO_PEDAGOGICO|INATIVO",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "{campo.invalido}")
    private String situacao;

    @NotNull(message = "{campo.obrigatorio}")
    @Min(0)
    @Max(10)
    private Float nota;
}
