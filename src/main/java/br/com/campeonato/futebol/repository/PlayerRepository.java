package br.com.campeonato.futebol.repository;

import br.com.campeonato.futebol.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ramon on 07-Sep-16.
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
