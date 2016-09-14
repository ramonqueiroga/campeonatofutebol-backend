package br.com.campeonato.futebol.resource;

import br.com.campeonato.futebol.domain.SoccerTeam;
import br.com.campeonato.futebol.service.SoccerTeamService;
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
public class SoccerTeamResource {

    @Autowired
    private SoccerTeamService soccerTeamService;

    @RequestMapping(value="/api/soccerteam/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<SoccerTeam> findOne(@PathVariable("id") String identifier) {
        Long id = Long.parseLong(identifier);
        SoccerTeam soccerTeam = soccerTeamService.findOne(id);
        if(soccerTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(soccerTeam, HttpStatus.OK);
    }

    @RequestMapping(value="/api/soccerteam/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<SoccerTeam>> findAll() {
        List<SoccerTeam> soccerTeamList = soccerTeamService.findAll();
        if(soccerTeamList == null || soccerTeamList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(soccerTeamList, HttpStatus.OK);
    }

}
