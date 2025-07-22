package com.premierleague.PremierZone.player;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<Player> getPlayers( @RequestParam(required = false) String team,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String position,
                                    @RequestParam(required = false) String nation
                                   ){
        if (team != null && position != null){
            return playerService.getByTeamAndPos(team, position);
        }
        else if (team != null){
            return playerService.getByTeam(team);
        }
        else if (name != null){
            return playerService.getByName(name);
        }
        else if (position != null){
            return playerService.getByPos(position);
        }
        else if (nation != null) {
            return playerService.getByNation(nation);
        }
        else {
            return playerService.getAllPlayers();
        }
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlyer(@RequestBody Player player){
        Player resultPlayer = playerService.updatePlayer(player);
        if (resultPlayer != null){
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player Delete Successfully", HttpStatus.OK);
    }
}
