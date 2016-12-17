package br.com.campeonato.futebol.infrastructure.util.token;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public interface TokenCreator<T> {
    String createToken(T t);
}
