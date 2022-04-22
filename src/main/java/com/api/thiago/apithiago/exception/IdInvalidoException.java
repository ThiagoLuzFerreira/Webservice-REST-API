package com.api.thiago.apithiago.exception;

//poupar recursos da aplicação - não consulta o BD em caso de ID zero, ou negativo

public class IdInvalidoException extends RuntimeException{

    public IdInvalidoException(String message){
        super(message);
    }
}
