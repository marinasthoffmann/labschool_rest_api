package tech.devinhouse.labschool_rest_api.exception;

public class AlunoJaEmAtendimentoException extends Throwable {

    public AlunoJaEmAtendimentoException() {
        super("Aluno já se encontra em atendimento pedagogico!");
    }
}
