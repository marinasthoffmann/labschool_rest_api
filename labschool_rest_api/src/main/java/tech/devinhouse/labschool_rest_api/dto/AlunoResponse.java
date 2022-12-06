package tech.devinhouse.labschool_rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoResponse {

    @Schema(title = "Código de identificação do aluno")
    private Integer codigo;

    @Schema(title = "Nome do aluno")
    private String nome;

    @Schema(title = "Telefone do aluno")
    private String telefone;

    @Schema(title = "Data de nascimento do aluno", description = "YYYY-mm-dd")
    private LocalDate dataNascimento;

    @Schema(title = "CPF do aluno", description = "Deve ser registro único")
    private Long cpf;

    @Schema(title = "Situação da matrícula do aluno",
            description = "ATIVO | INATIVO | IRREGULAR | ATENDIMENTO_PEDAGOGICO ")
    private String situacao;

    @Schema(title = "Nota do aluno")
    private Float nota;

    @Schema(title = "Número de atendimentos pedagógicos do aluno")
    private Integer atendimentos;
}
