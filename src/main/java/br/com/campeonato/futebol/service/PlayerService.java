package br.com.campeonato.futebol.service;

import br.com.campeonato.futebol.domain.Player;
import br.com.campeonato.futebol.infrastructure.repository.PlayerRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Ramon on 07-Sep-16.
 */

@Service
public class PlayerService extends AbstractService<Player, Long, PlayerRepository> {

}
