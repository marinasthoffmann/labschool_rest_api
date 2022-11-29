package tech.devinhouse.labschool_rest_api.exception;

public class RegistroNaoEncontradoException extends Throwable {

    public RegistroNaoEncontradoException(String entidade, Integer codigo) {
        super(entidade + " com código " + codigo + " não encontrado!");
    }
}
