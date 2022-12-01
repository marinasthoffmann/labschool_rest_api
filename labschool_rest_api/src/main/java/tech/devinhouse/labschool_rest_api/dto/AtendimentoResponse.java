package tech.devinhouse.labschool_rest_api.dto;

import lombok.Data;

@Data
public class AtendimentoResponse {

    private AlunoResponse aluno;

    private PedagogoResponse pedagogo;

}
