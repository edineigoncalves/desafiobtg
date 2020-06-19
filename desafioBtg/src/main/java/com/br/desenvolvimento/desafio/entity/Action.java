package com.br.desenvolvimento.desafio.entity;

import javax.persistence.Entity;

import com.br.desenvolvimento.desafio.enumeration.EnumObject;

@Entity
public class Action {
	
	private Player player;
	private EnumObject enumObject;
	
	public Action(Player player, EnumObject enumObject) {
		super();
		this.player = player;
		this.enumObject = enumObject;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public EnumObject getEnumObject() {
		return enumObject;
	}
	public void setEnumObject(EnumObject enumObject) {
		this.enumObject = enumObject;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enumObject == null) ? 0 : enumObject.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		if (enumObject != other.enumObject)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Action player=" + player + ", enumObject=" + enumObject;
	}
	
	
}
