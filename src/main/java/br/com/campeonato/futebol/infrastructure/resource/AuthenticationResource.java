package br.com.campeonato.futebol.infrastructure.resource;

import br.com.campeonato.futebol.domain.Users;
import br.com.campeonato.futebol.infrastructure.util.token.TokenCreator;
import br.com.campeonato.futebol.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@RestController
@RequestMapping(value = "/api/authentication")
public class AuthenticationResource {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenCreator<Users> tokenCreator;

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> doAuthentication(@RequestBody Users users) {
        Users user = this.usersService.findByNicknameAndPassword(users.getNickname(), users.getPassword());
        if(user != null) {
            return ResponseEntity.ok(this.tokenCreator.createToken(user));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
