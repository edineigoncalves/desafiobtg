package com.br.desenvolvimento.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.br.desenvolvimento.desafio.entity.ApiResponse;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.repository.ActionRepository;
import com.br.desenvolvimento.desafio.repository.PlayerRepository;
import com.br.desenvolvimento.desafio.service.impl.ActionServiceImpl;
import com.br.desenvolvimento.desafio.service.impl.PlayerServiceImpl;
import com.google.gson.Gson;

import springfox.documentation.swagger2.mappers.ModelMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerControllerTest {

    private static String HOST = "http://localhost";
    private static String ENDPOINT = "/v1/btg/jokenpo/player";

    @LocalServerPort
    private int randomServerPort;

    private RestTemplate restTemplate;
    private PlayerRepository playerRepository;
    private ActionRepository actionRepository;
    private ActionServiceImpl actionService;
    private PlayerServiceImpl playerService;

    @Before
    public void setup(){
        restTemplate = new RestTemplate();
        playerRepository = new PlayerRepository();
        actionRepository = new ActionRepository();
        actionService = new ActionServiceImpl();
        playerService = new PlayerServiceImpl();
    }

    @Test
    public void getAllPlayersWithoutInserted() throws URISyntaxException {

        this.playerService.allClear();

        ResponseEntity<String> result = restTemplate.getForEntity(getJogadorUri(), String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("data"));

        ApiResponse apiResponse = new Gson().fromJson(result.getBody(), ApiResponse.class);

        Assert.assertNotNull(apiResponse.getMeta().getTimestamp());

        List<Player> listResponse = new ModelMapper().map(apiResponse.getData(), List.class);

        Assert.assertEquals(0, listResponse.size());
    }

    @Test
    public void insertPlayer() throws URISyntaxException {

        this.playerService.allClear();

        HttpEntity<Player> requestForInsert = new HttpEntity<>(
                new Player("Play1"), new HttpHeaders());

        ResponseEntity<String> result = restTemplate.postForEntity(getJogadorUri(), requestForInsert, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("data"));
        Assert.assertEquals(true, result.getBody().contains("jogador"));
        Assert.assertEquals(true, result.getBody().contains("Play1"));

        Assert.assertEquals(1, this.getAllPlayers().size());
    }

    @Test
    public void deletePlayer() throws URISyntaxException, JokenpoException {

        this.playerService.allClear();
        this.playerService.save(new Player("Play1"));

        restTemplate.delete(getJogadorUri() + "/?jogador=Play1");

        Assert.assertEquals(0, this.getAllPlayers().size());
    }

    private List<Player> getAllPlayers() throws URISyntaxException {

        ResponseEntity<String> result = restTemplate.getForEntity(getJogadorUri(), String.class);
        ApiResponse apiResponse = new Gson().fromJson(result.getBody(), ApiResponse.class);

        return new ModelMapper().map(apiResponse.getData(), List.class);
    }

    private URI getJogadorUri() throws URISyntaxException {
        final String baseUrl = HOST + ":" + randomServerPort + ENDPOINT;
        return new URI(baseUrl);
    }

}