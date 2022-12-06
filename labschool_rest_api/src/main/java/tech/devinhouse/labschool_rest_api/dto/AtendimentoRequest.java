package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AtendimentoRequest {

    @Schema(title = "Código de identificação do aluno, que não deve estar com situação ATENDIMENTO_PEDAGOGICO")
    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1, message = "{campo.invalido}")
    private Integer codigoAluno;

    @Schema(title = "Código de identificação do pedagogo")
    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1, message = "{campo.invalido}")
    private Integer codigoPedagogo;
}
