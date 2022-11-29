package tech.devinhouse.labschool_rest_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import tech.devinhouse.labschool_rest_api.model.enums.SituacaoMatricula;

import java.time.LocalDate;

@Data
public class AlunoResponse {

    private Integer codigo;

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private Long cpf;

    private SituacaoMatricula situacao;

    private Float nota;

    private Integer atendimentos;
}
