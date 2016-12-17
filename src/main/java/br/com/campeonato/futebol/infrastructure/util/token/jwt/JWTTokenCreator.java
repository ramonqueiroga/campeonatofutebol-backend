package br.com.campeonato.futebol.infrastructure.util.token.jwt;

import br.com.campeonato.futebol.domain.Users;
import br.com.campeonato.futebol.infrastructure.exceptions.AuthenticationException;
import br.com.campeonato.futebol.infrastructure.util.token.TokenCreator;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@Component
public class JWTTokenCreator implements TokenCreator<Users> {

    private static final byte[] SHARED_SECRET = generateSecret();

    @Override
    public String createToken(Users users) {
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET);
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), this.createClaims(users));
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new AuthenticationException("Error creating jwt token", e);
        }
    }

    private JWTClaimsSet createClaims(Users users) {
        return new JWTClaimsSet.Builder()
                .issuer("campeonato-futebol")
                .audience(Collections.singletonList("campeonato-futebol"))
                .subject("auth")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .claim("nickname", users.getNickname())
                .claim("username", users.getName())
                .claim("email", users.getEmail())
                .claim("sobrenome", users.getSurname()).build();
    }

    private static byte[] generateSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] sharedSecret = new byte[32];
        secureRandom.nextBytes(sharedSecret);
        return sharedSecret;
    }

}
