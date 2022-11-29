package tech.devinhouse.labschool_rest_api.exception;

public class RegistroExistenteException extends RuntimeException{

    public RegistroExistenteException(String entidade, Long cpf) {
        super(entidade + " com cpf " + cpf + " já existente!");
    }

}
