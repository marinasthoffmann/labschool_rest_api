package tech.devinhouse.labschool_rest_api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SituacaoMatriculaRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @Pattern(regexp = "ATIVO|IRREGULAR|ATENDIMENTO_PEDAGOGICO|INATIVO",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "{campo.invalido}")
    private String situacao;
}
