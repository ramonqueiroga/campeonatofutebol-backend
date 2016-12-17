package br.com.campeonato.futebol.infrastructure.util.token.jwt;


import br.com.campeonato.futebol.domain.Users;
import br.com.campeonato.futebol.infrastructure.exceptions.AuthenticationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class JWTTokenTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYW1wZW9uYXRvLWZ1dGVib2wiLCJzdWIiOiJhdXRoIiwic3VybmFtZSI6InF1ZWlyb2dhIiwiaXNzIjoiY2FtcGVvbmF0by1mdXRlYm9sIiwibmlja25hbWUiOiJyYW1vbnF1ZWlyb2dhIiwiZXhwIjoxNDgxOTk5OTgxLCJlbWFpbCI6ImVtYWlsIiwidXNlcm5hbWUiOiJSYW1vbiJ9.Qxc7PTP0oI7OKWA6mwqM0AffKjpCBozDo-3LK3cIauQ";
    private JWTToken jwtToken = new JWTToken();

    @Test
    public void testTokenCreation() {
        Users users = getUsersMock();

        String token = this.jwtToken.createToken(users);
        assertNotNull(token);
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    public void testDecodeToken() {
        Users users = this.jwtToken.decodeToken(TOKEN);
        assertEquals(users, this.getUsersMock());

    }

    @Test(expected = AuthenticationException.class)
    public void testTokenCreationWithBlankUser() {
        Users users = new Users();
        this.jwtToken.createToken(users);
    }

    @Test(expected = AuthenticationException.class)
    public void testTokenCreationWithNullUser() {
        this.jwtToken.createToken(null);
    }


    private Users getUsersMock() {
        Users users = new Users();
        users.setName("Ramon");
        users.setSurname("queiroga");
        users.setNickname("ramonqueiroga");
        users.setEmail("email");
        return users;
    }

}