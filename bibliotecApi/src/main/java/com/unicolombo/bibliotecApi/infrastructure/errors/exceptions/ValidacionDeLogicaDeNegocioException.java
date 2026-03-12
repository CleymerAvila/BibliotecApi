package com.unicolombo.bibliotecApi.infrastructure.errors.exceptions;

public class ValidacionDeLogicaDeNegocioException  extends RuntimeException{
    public ValidacionDeLogicaDeNegocioException(String mensaje){
        super(mensaje);
    }
}
