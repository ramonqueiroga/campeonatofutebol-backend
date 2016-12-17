package br.com.campeonato.futebol.service;

import br.com.campeonato.futebol.domain.Users;
import br.com.campeonato.futebol.infrastructure.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@Service
public class UsersService extends AbstractService<Users, Integer, UsersRepository> {

    @Autowired
    private UsersRepository usersRepository;

    public Users findByNicknameAndPassword(String nickname, String password) {
        return this.usersRepository.findByNicknameAndPassword(nickname, password);
    }
}
