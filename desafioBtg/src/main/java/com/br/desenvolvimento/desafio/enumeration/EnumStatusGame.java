package com.br.desenvolvimento.desafio.enumeration;

public enum EnumStatusGame {
	
	 EMPATE ("EMPATE"),
	 VITORIA  ("O VENCEDOR DESTA PARTIDA É"),
	 FIM ("O VENCEDOR É ");
	
	private String status;
	
	private EnumStatusGame(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
