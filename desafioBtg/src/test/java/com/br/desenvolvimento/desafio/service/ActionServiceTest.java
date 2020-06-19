package com.br.desenvolvimento.desafio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.enumeration.EnumObject;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.service.impl.ActionServiceImpl;
import com.br.desenvolvimento.desafio.service.impl.PlayerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ActionServiceTest {

    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private ActionServiceImpl actionService;

    @Test
    public void insertManyPlayersForTestWithSucess() throws JokenpoException {
        
        this.playerService.allClear();
        this.actionService.allClear();
        
        List<String> playerNames = new ArrayList<>(Arrays.asList("Player1", "Player2", "Player3", "Player4", "Player5", "Player6"));
        List<Player> jogadorResp = this.insertManyDifferentPlayers(playerNames);
        Assert.assertEquals(playerNames.size(), jogadorResp.size());
    }

    @Test
    public void playerIfNotActionsTest() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        int playersCounter = this.playerService.findAll().size();
        int movementsCounter = this.actionService.findAll().size();
        Assert.assertEquals(0, movementsCounter);
        Assert.assertNotEquals(0, playersCounter);
    }

    @Test
    public void insertActionTest() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        int expected = 1;
        Player player=new  Player("Player1");
        Action response = this.actionService.save(new Action(player,EnumObject.LAGARTO));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getEnumObject());
        Assert.assertNotNull(response.getPlayer());
        Assert.assertEquals(expected, this.actionService.findAll().size());
    }

    @Test(expected = JokenpoException.class)
    public void insertActionDiferensAndWithPlayersDuplicateTest() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        Player player=new  Player("Player1");
        this.actionService.save(new Action(player, EnumObject.PEDRA));
        this.actionService.save(new Action(player, EnumObject.LAGARTO));
    }

    private List<Player> insertManyDifferentPlayers(List<String> namePlayer) throws JokenpoException {
        List<Player> lsPlayer = new ArrayList<>();
        for(String name : namePlayer){
            Player player = this.playerService.save(new Player(name));
            lsPlayer.add(player);
        }
        return lsPlayer;
    }

}
