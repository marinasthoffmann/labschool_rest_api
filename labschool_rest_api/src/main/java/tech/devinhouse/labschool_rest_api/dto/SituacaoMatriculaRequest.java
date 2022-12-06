package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SituacaoMatriculaRequest {

    @Schema(title = "Situação matrícula que deve constar no registro do aluno após atualização")
    @NotEmpty(message = "{campo.obrigatorio}")
    @Pattern(regexp = "ATIVO|IRREGULAR|ATENDIMENTO_PEDAGOGICO|INATIVO",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "{campo.invalido}")
    private String situacao;
}
