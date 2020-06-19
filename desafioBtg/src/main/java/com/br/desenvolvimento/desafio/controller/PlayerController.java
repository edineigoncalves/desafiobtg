package com.br.desenvolvimento.desafio.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.service.impl.ActionServiceImpl;
import com.br.desenvolvimento.desafio.service.impl.PlayerServiceImpl;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
    private PlayerServiceImpl playerService;
	@Autowired
	private ActionServiceImpl actionService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> savePlayer(@RequestBody Player player)
            throws JokenpoException {
    	return ResponseEntity.ok(this.playerService.save(player));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deletePlayerByName(@PathParam("name") String name) throws JokenpoException {
        return ResponseEntity.ok(playerService.deletePlayerByName(name));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Object> findAllPlayers() throws JokenpoException {
        return ResponseEntity.ok(playerService.findAll());
    }

    @PostMapping(value = "/action/save")
    public ResponseEntity<Object> saveAction(@RequestBody Action action) throws JokenpoException {            
        return ResponseEntity.ok(this.actionService.save(action));
    }

    @DeleteMapping(value = "/action/delete")
    public ResponseEntity<Object> deleteActionByName(@PathParam("player") String player) throws JokenpoException {
        return ResponseEntity.ok(actionService.deletePlayerByName(player));
    }

    @GetMapping(value = "/action/all")
    public ResponseEntity<Object> findAllActions() throws JokenpoException {
        return ResponseEntity.ok(actionService.findAll());
    }

}
