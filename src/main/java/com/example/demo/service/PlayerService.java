package com.example.demo.service;

import com.example.demo.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;

    public void save(Mono<Player> player){
        playerRepository.save(player.block());
    }

    public Flux<Player> findAll(){
        return playerRepository.findAll();
    }
}

