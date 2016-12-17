package br.com.campeonato.futebol.infrastructure.util.token;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public interface Token<T> {
    String createToken(T t);
    T decodeToken(String token);
}
