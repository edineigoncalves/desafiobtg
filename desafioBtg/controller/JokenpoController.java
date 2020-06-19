package br.com.game.jokenpo.controller;

import br.com.game.jokenpo.models.api.ApiResponse;
import br.com.game.jokenpo.exception.JokenpoException;
import br.com.game.jokenpo.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")
public class JokenpoController {

    private CoreServiceImpl service;

    @Autowired
    public JokenpoController(CoreServiceImpl service) {
        this.service = service;
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> reset() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.service.limpar()));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> play() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.service.jogar()));
    }

}
