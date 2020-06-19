package com.br.desenvolvimento.desafio.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

public class JokenpoResult {
	
	@NotNull
    private String result;

    @NotNull
    private List<String> historic;

    public JokenpoResult(String result, List<String> historic) {
        this.result = result;
        this.historic = historic;
    }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<String> getHistoric() {
		return historic;
	}

	public void setHistoric(List<String> historic) {
		this.historic = historic;
	}
    
    
}
