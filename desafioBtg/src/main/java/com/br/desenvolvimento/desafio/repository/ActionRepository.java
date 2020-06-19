package com.br.desenvolvimento.desafio.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.br.desenvolvimento.desafio.entity.Action;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.singleton.ActionSingleton;

@Repository
@NoRepositoryBean
public class ActionRepository {

	public Action findByPlayerName(String name) throws JokenpoException {
		List<Action> lsAction = findAll().stream()
				.filter(action -> (action.getPlayer().getName().compareToIgnoreCase(name) == 0))
				.collect(Collectors.toList());
		Optional<Action> playerFirst = lsAction.stream().findFirst();
		if (playerFirst.isPresent())
			return playerFirst.get();

		throw new JokenpoException("Jogada inválidaJogada inválida");
	}

	public boolean delete(Action action) throws JokenpoException {
		if (verifyActionIsNull())
			throw new JokenpoException("Erro ao remover jogada");

		return ActionSingleton.getInstanceSingleton().remove(action);
	}

	public List<Action> findAll() throws JokenpoException {
		if (verifyActionIsNull())
			throw new JokenpoException("Jogada inválida");

		return ActionSingleton.getInstanceSingleton();
	}

	public Action save(Action action) throws JokenpoException {
		if (!verifyActionIsNull() && ActionSingleton.getInstanceSingleton().add(action))
			return action;
		throw new JokenpoException("Erro ao salvar jogada");
	}

	private boolean verifyActionIsNull() {
		return Objects.isNull(ActionSingleton.getInstanceSingleton());
	}
}
