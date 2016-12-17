package br.com.campeonato.futebol.infrastructure.exceptions;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message, Throwable t) {
        super(message, t);
    }

}
