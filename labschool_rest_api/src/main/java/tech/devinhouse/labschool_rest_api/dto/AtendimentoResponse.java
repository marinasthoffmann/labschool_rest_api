package tech.devinhouse.labschool_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtendimentoResponse {

    private AlunoResponse aluno;

    private PedagogoResponse pedagogo;
}
