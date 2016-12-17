package br.com.campeonato.futebol.infrastructure.util.token.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
public class TokenExpirationUtil {

    public static Date getExpirationToken() {
        LocalDateTime now = LocalDateTime.now();
        now.plusDays(1);
        now.withHour(4);
        now.withMinute(0);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

}
