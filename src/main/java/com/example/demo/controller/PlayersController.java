package com.example.demo.controller;

import com.example.demo.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;

@RestController
public class PlayersController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/players",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Map<String, Collection<Player>>> teamWithPlayers(){
        return playerService.teamWithPlayers();
    }

}
