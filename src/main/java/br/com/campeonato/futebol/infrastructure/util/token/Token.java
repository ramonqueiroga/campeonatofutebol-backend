package br.com.campeonato.futebol.infrastructure.util.token;

import java.util.Date;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public interface Token<T> {
    String createToken(T t, Date expirationToken);
    T decodeToken(String token);
    boolean isTokenValid(String token);
}
