package com.br.desenvolvimento.desafio.service;

import java.util.List;

import com.br.desenvolvimento.desafio.entity.JokenpoResult;
import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;

public interface ManagerService {

    List<Player> clear() throws JokenpoException;

    JokenpoResult play() throws JokenpoException;

}
