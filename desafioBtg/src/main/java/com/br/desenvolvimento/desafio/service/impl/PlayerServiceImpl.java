package com.br.desenvolvimento.desafio.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.repository.PlayerRepository;
import com.br.desenvolvimento.desafio.service.PlayerService;
import com.br.desenvolvimento.desafio.singleton.PlayerSingleton;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
    private PlayerRepository playerRepository;

	@Autowired
	private ActionServiceImpl actionService;

    public Player save(Player player) throws JokenpoException {
        if(this.verifyPlayerExistsByName(player.getName()))
        	throw new JokenpoException("Jogador já cadastrado");
    
        return playerRepository.save(player);
    }

    public List<Player> findAll() throws JokenpoException {
        return playerRepository.findAll();
    }

    public Player findPlayerByName(String name) throws JokenpoException {
        return playerRepository.findByName(name);
    }

    public List<Player> deletePlayerByName(String name) throws JokenpoException {
        if(StringUtils.isEmpty(name)){
            throw new JokenpoException("Jogador inválido");
        }
        try {
            this.actionService.deletePlayerByName(name);
        } catch (JokenpoException ex){
        }

        Player player = this.playerRepository.findByName(name);
        if(this.playerRepository.delete(player)){
            return this.findAll();
        }
        throw new JokenpoException("Erro ao remover jogador");
    }

    public void allClear(){
        PlayerSingleton.clearSingleton();
    }

    private Boolean verifyPlayerExistsByName(String name) {
        try {
            if (!Objects.isNull(this.playerRepository.findByName(name))) {
                return true;
            }
        } catch (JokenpoException e) {
            return false;
        }
        return false;
    }

}
