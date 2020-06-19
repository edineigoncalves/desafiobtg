package br.com.game.jokenpo.controller;

import br.com.game.jokenpo.models.JogadorReq;
import br.com.game.jokenpo.models.MovModel;
import br.com.game.jokenpo.models.MoveReq;
import br.com.game.jokenpo.models.api.ApiResponse;
import br.com.game.jokenpo.exception.JokenpoException;
import br.com.game.jokenpo.service.impl.JogadorServiceImpl;
import br.com.game.jokenpo.service.impl.MovimentoServiceeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/jogador")
@CrossOrigin(origins = "*")
public class JogadorController {

    private JogadorServiceImpl service;
    private MovimentoServiceeImpl moveService;

    @Autowired
    public JogadorController(JogadorServiceImpl service, MovimentoServiceeImpl moveService) {
        this.service = service;
        this.moveService = moveService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody JogadorReq jogadorReq)
            throws JokenpoException {
        return ResponseEntity.ok(
                new ApiResponse<>(this.service.inserir(jogadorReq)));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Object> excluir(@PathParam("playerName") String playerName) throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.service.excluirByName(playerName)));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> buscarTodos() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.service.buscarTodos()));
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody MovModel model) throws JokenpoException {
            MoveReq moveReq = new MoveReq(model.getJogador(),model.getMovimento());
        return ResponseEntity.ok(
                new ApiResponse<>(this.moveService.inserir(moveReq)));
    }

    @RequestMapping(value = "/move", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathParam("jogador") String jogador) throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.moveService.excluirbyJogadorName(jogador)));
    }

    @RequestMapping(value = "/move", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.moveService.buscarTodos()));
    }

}
