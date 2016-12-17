package br.com.campeonato.futebol.infrastructure.util.token.jwt;

import br.com.campeonato.futebol.domain.Users;
import br.com.campeonato.futebol.infrastructure.exceptions.AuthenticationException;
import br.com.campeonato.futebol.infrastructure.exceptions.DecodeTokenException;
import br.com.campeonato.futebol.infrastructure.util.token.Token;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@Component
public class JWTToken implements Token<Users> {

    private static final byte[] SHARED_SECRET = generateSecret();
    public static final String ISSUER = "campeonato-futebol";
    public static final List<String> AUDIENCE = Collections.singletonList("campeonato-futebol");
    public static final String SUBJECT = "auth";

    @Override
    public String createToken(Users users, Date expirationToken) {
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET);
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), this.createClaims(users, expirationToken));
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new AuthenticationException("Error creating jwt token", e);
        }
    }

    @Override
    public Users decodeToken(String token) {
        Map<String, Object> claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET);

            if(signedJWT.verify(verifier)) {
                JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
                claims = jwtClaimsSet.getClaims();
            } else {
                throw new DecodeTokenException("Invalid token");
            }

            Users users = new Users();
            users.setEmail((String) claims.get("email"));
            users.setNickname((String) claims.get("nickname"));
            users.setName((String) claims.get("username"));
            users.setSurname((String) claims.get("surname"));
            return users;
        } catch (ParseException | JOSEException e) {
            throw new DecodeTokenException("Error decoding token");
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        Date now = new Date();
        Date expirationTime = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET);

            if(signedJWT.verify(verifier)) {
                JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
                expirationTime = jwtClaimsSet.getExpirationTime();
            }
        } catch (ParseException | JOSEException e) {
            throw new DecodeTokenException("Error decoding token when validating it expiration");
        }

        if(expirationTime != null && now.before(expirationTime)) {
            return true;
        }else {
            return false;
        }
    }

    private JWTClaimsSet createClaims(Users users, Date expirationTime) {
        if(users == null) throw new AuthenticationException("User is null");
        if(StringUtils.isEmpty(users.getName())) throw new AuthenticationException("Need user name to create token");
        if(StringUtils.isEmpty(users.getSurname())) throw new AuthenticationException("Need user surname to create token");
        if(StringUtils.isEmpty(users.getNickname())) throw new AuthenticationException("Need user nickname to create token");
        if(StringUtils.isEmpty(users.getEmail())) throw new AuthenticationException("Need user email to create token");
        return new JWTClaimsSet.Builder()
                .issuer(ISSUER)
                .audience(AUDIENCE)
                .subject(SUBJECT)
                .expirationTime(expirationTime)
                .claim("nickname", users.getNickname())
                .claim("username", users.getName())
                .claim("email", users.getEmail())
                .claim("surname", users.getSurname()).build();
    }

    private static byte[] generateSecret() {
        return new byte[] {(byte)-34, (byte)25, (byte)-40, (byte)92, (byte)-90, (byte)26, (byte)-17, (byte)-94, (byte)78, (byte)106, (byte)4, (byte)-40, (byte)88, (byte)-57, (byte)5, (byte)7, (byte)-10, (byte)-119, (byte)-70, (byte)-23, (byte)-119, (byte)-11, (byte)-122, (byte)41, (byte)29, (byte)-34, (byte)-77, (byte)-12, (byte)4, (byte)3, (byte)35, (byte)-88 };
    }

}
