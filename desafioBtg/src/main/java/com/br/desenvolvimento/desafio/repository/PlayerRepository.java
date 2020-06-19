package com.br.desenvolvimento.desafio.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import com.br.desenvolvimento.desafio.entity.Player;
import com.br.desenvolvimento.desafio.exception.JokenpoException;
import com.br.desenvolvimento.desafio.singleton.PlayerSingleton;

@RestController
public class PlayerRepository {

	public Player findByName(String name) throws JokenpoException {
		List<Player> lsPlayers = findAll().stream().filter(player -> (player.getName().compareToIgnoreCase(name) == 0))
				.collect(Collectors.toList());
		Optional<Player> playerFirst = lsPlayers.stream().findFirst();
		if (playerFirst.isPresent())
			return playerFirst.get();

		throw new JokenpoException("Erro na pesquisa de jogador(es)");
	}

	public boolean delete(Player player) throws JokenpoException {
		if (verifyPlayerSingletonIsNull())
			throw new JokenpoException("Erro ao remover jogador");

		return PlayerSingleton.getInstanceSingleton().remove(player);
	}

	public Player save(Player player) throws JokenpoException {
		if (!verifyPlayerSingletonIsNull() && PlayerSingleton.getInstanceSingleton().add(player))
			return player;
		throw new JokenpoException("Erro ao inserir jogador");
	}

	public List<Player> findAll() throws JokenpoException {
		if (verifyPlayerSingletonIsNull())
			throw new JokenpoException("Erro ao pesquisar jogador(es)");

		return PlayerSingleton.getInstanceSingleton();
	}

	private boolean verifyPlayerSingletonIsNull() {
		return Objects.isNull(PlayerSingleton.getInstanceSingleton());
	}

}
