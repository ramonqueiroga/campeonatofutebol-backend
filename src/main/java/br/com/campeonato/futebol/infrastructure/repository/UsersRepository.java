package br.com.campeonato.futebol.infrastructure.repository;

import br.com.campeonato.futebol.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ramon Queiroga on 17/12/2016.
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    public Users findByNicknameAndPassword(String nickname, String password);
}
