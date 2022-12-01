package tech.devinhouse.labschool_rest_api.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AtendimentoRequest {

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1, message = "{campo.invalido}")
    private Integer codigoAluno;

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1, message = "{campo.invalido}")
    private Integer codigoPedagogo;
}
