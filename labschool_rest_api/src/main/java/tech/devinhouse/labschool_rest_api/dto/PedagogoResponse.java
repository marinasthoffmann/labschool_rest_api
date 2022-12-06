package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PedagogoResponse {

    @Schema(title = "Código de identificação do pedagogo")
    private Integer codigo;

    @Schema(title = "Nome do pedagogo")
    private String nome;

    @Schema(title = "Telefone do aluno")
    private String telefone;


    @Schema(title = "Data de nascimento do pedagogo", description = "YYYY-mm-dd")
    private LocalDate dataNascimento;

    @Schema(title = "CPF do pedagogo")
    private Long cpf;

    @Schema(title = "Número de atendimentos pedagógicos do pedagogo")
    private Integer atendimentos;
}
