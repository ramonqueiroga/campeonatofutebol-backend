package br.com.campeonato.futebol.repository;

import br.com.campeonato.futebol.domain.SoccerTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ramon on 07-Sep-16.
 */
@Repository
public interface SoccerTeamRepository extends CrudRepository<SoccerTeam, Long> {

}
