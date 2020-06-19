package com.br.desenvolvimento.desafio.service;

import java.util.List;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.exception.JokenpoException;

public interface ActionService {

    Action save(Action Action) throws JokenpoException;

    List<Action> findAll() throws JokenpoException;

    List<Action> deletePlayerByName(String playerName) throws JokenpoException;

    void allClear();

}
