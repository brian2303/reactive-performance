package com.example.demo.service;

import com.example.demo.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

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

    public Mono<Map<String, Collection<Player>>> teamWithPlayers(){
        Flux<Player> players = playerRepository.findAll();
        return players
                .filter(player -> player.age >= 35)
                .map(player -> {
                    player.name = player.name.toUpperCase(Locale.ROOT);
                    return player;
                })
                .buffer(100)
                .flatMap(playerA -> players
                        .filter(playerB -> playerA.stream()
                                .anyMatch(a ->  a.club.equals(playerB.club)))
                )
                .distinct()
                .collectMultimap(Player::getClub);
    }
}

