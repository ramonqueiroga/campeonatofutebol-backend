package br.com.campeonato.futebol.infrastructure.resource;

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
@RequestMapping(value = "/api/player")
public class PlayerResource {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Player> findOne(@PathVariable("id") String identifier) {
        Long id = Long.parseLong(identifier);
        Player player = playerService.findOne(id);
        if(player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Player>> findAll() {
        List<Player> playerList = playerService.findAll();
        if(playerList == null || playerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Player> saveOne(@RequestBody Player player) {
        if(player != null) {
            Player playerRegistered = this.playerService.save(player);
            if(playerRegistered != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
