package br.com.campeonato.futebol.resource;

import br.com.campeonato.futebol.domain.Player;
import br.com.campeonato.futebol.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ramon on 07-Sep-16.
 */
@RestController
public class PlayerResource {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Player> findOne(@PathVariable("id") String identifier) {
        Long id = Long.parseLong(identifier);
        Player player = playerService.findOne(id);
        if(player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/player/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<Player>> findAll() {
        List<Player> playerList = playerService.findAll();
        if(playerList == null || playerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

}
