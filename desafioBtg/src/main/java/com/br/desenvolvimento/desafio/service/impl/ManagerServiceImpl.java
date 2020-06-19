package com.br.desenvolvimento.desafio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.entity.JokenpoResult;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.enumeration.EnumObject;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.service.ManagerService;
import com.br.desenvolvimento.desafio.singleton.ActionSingleton;
import com.br.desenvolvimento.desafio.singleton.PlayerSingleton;

@Service
public class ManagerServiceImpl implements ManagerService {

    private static final String EMPATE = "Empate!";
    private static final String VITORIA = " É O VENCEDOR DESTA PARTIDA!";
    private static final String FIMDEJOGO = "É O VENCEDOR : ";
    private static final String SEPARADOR = " / ";
    
    @Autowired
    private PlayerServiceImpl playerService;
    @Autowired
    private ActionServiceImpl actionService;

    public List<Player> clear() throws JokenpoException {
        ActionSingleton.clearSingleton();
        PlayerSingleton.clearSingleton();
        return this.playerService.findAll();
    }

    public JokenpoResult play() throws JokenpoException {
        this.checkRequirements();
        List<String> winner = new ArrayList<>();
        this.actionService.findAll()
                .forEach(action -> {
                    try {
                        if(checkExistsWinner(action.getEnumObject().getWeakness())){
                            winner.add(action.getPlayer().getName());
                        }
                    } catch (JokenpoException e) {
                        e.getMessage();
                    }
                });

        JokenpoResult jokeponResult = new JokenpoResult(this.msgVitoria(winner),
                this.historicoMovimentos(this.actionService.findAll()));

        ActionSingleton.clearSingleton();

        return jokeponResult;
    }

    private void checkRequirements() throws JokenpoException {
        int amountPlayer=this.playerService.findAll().size();
        int amountAction=this.actionService.findAll().size();
            
            switch (amountPlayer) {
			case 0:
				throw new JokenpoException("Quantidade de jogadores inexistente");

			default:
				if (amountPlayer <= 1){
		            throw new JokenpoException("Quantidade de jogador(es) inválida(s) para iniciar jogo");
		        } else if (amountAction <= 1){
		            throw new JokenpoException("Quantidade de jogada(s) inválida(s)");
		        } else if (amountPlayer != amountAction){
		            throw new JokenpoException("Quantidade de jogares insuficiente para iniciar jogo");
				
			}
        }
    }

    private Boolean checkExistsWinner(List<EnumObject> weakness) throws JokenpoException {
        for (EnumObject enumObject : weakness) {

            for(Action action : this.actionService.findAll()){
                if(action.getEnumObject().getName().compareTo(enumObject.getName()) == 0){
                    return false;
                }
            }
        }

        return true;
    }

    private String msgVitoria(List<String> winners){
        String message = "";
        if(winners.size() == 0){
            message = EMPATE;
        } else if(winners.size() == 1) {
            message = winners.get(0).toUpperCase().trim() + VITORIA;
        } else {
            message = FIMDEJOGO;
            int counter = 0;
            for(String name : winners){
                counter++;
                if(counter == winners.size()){
                    message = message + name;
                } else {
                    message = message + name + SEPARADOR;
                }
            }
        }
        return message;
    }

    private List<String> historicoMovimentos(List<Action> lsAction) {
        List<String> result = new ArrayList<>();
        for(Action action : lsAction){
            String message = action.getPlayer().getName() + " (" + action.getEnumObject().getName() + ")";
            result.add(message);
        }
        return result;
    }

}
