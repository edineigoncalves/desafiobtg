package com.br.desenvolvimento.desafio.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.enumeration.EnumObject;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.repository.ActionRepository;
import com.br.desenvolvimento.desafio.repository.PlayerRepository;
import com.br.desenvolvimento.desafio.service.ActionService;
import com.br.desenvolvimento.desafio.singleton.ActionSingleton;

@Service
public class ActionServiceImpl implements ActionService {

    
    @Autowired
    private ActionRepository actionRepository;
    
    @Autowired
    private PlayerRepository playerRepository;

   public Action save(Action action) throws JokenpoException {
        if(Objects.isNull(action)
                || StringUtils.isEmpty(action.getPlayer().getName())
                || StringUtils.isEmpty(action.getEnumObject())){

            throw new JokenpoException("Jogada inválida");
        }
        Player player = this.playerRepository.findByName(action.getPlayer().getName());
        this.verifyActions(player);

        EnumObject objectAction = EnumObject.getObjectByName(action.getEnumObject().getName());
        if(Objects.isNull(objectAction)){

            throw new JokenpoException("Jogada inválida");
        }

        return this.actionRepository.save(new Action(player, objectAction));
    }

    public List<Action> findAll() throws JokenpoException {
        return this.actionRepository.findAll();
    }

    public List<Action> deletePlayerByName(String name) throws JokenpoException {
        if(StringUtils.isEmpty(name)){

            throw new JokenpoException("Nome de jogador inválido");
        }

        Action entity = this.actionRepository.findByPlayerName(name);

        if(this.actionRepository.delete(entity)){
            return this.findAll();
        };

        throw new JokenpoException("Erro ao remover jogada");
    }

    public void allClear(){
        ActionSingleton.clearSingleton();
    }

    private void verifyActions(Player player) throws JokenpoException {
        long count = this.actionRepository.findAll()
                .stream()
                .filter(act -> act.getPlayer().getName().compareToIgnoreCase(player.getName())==0)
                .count();
        if(count > 0){
            throw new JokenpoException("Jogada existente");
        }
    }

	}
