package br.com.campeonato.futebol.infrastructure.exceptions;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public class DecodeTokenException extends RuntimeException {

    public DecodeTokenException(String message) {
        super(message);
    }

}
