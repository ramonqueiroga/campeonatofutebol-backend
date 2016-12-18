package br.com.campeonato.futebol.domain;

import java.io.Serializable;

/**
 * Created by Ramon Queiroga on 18/12/2016.
 */
public class TokenWrapper implements Serializable {

    private String token;

    public TokenWrapper(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenWrapper tokenWrapper1 = (TokenWrapper) o;

        return token != null ? token.equals(tokenWrapper1.token) : tokenWrapper1.token == null;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }
}

