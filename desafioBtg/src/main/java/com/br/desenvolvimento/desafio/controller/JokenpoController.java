package com.br.desenvolvimento.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.service.impl.ManagerServiceImpl;

@RestController
@RequestMapping("/game")
public class JokenpoController {

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @DeleteMapping(value = "/clear")
    public ResponseEntity<Object> clear() throws JokenpoException {
        return ResponseEntity.ok(managerServiceImpl.clear());
    }

    @GetMapping(value = "/play")
    public ResponseEntity<Object> play() throws JokenpoException {
        return ResponseEntity.ok(managerServiceImpl.play());
    }

}
