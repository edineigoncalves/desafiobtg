package com.br.desenvolvimento.desafio.service;

import java.util.List;

import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;

public interface PlayerService {

    Player save(Player player) throws JokenpoException;

    List<Player> findAll() throws JokenpoException;

    Player findPlayerByName(String name) throws JokenpoException;

    List<Player> deletePlayerByName(String name) throws JokenpoException;

    void allClear();

}
