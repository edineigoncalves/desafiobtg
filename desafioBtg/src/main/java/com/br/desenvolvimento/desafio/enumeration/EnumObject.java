package com.br.desenvolvimento.desafio.enumeration;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

import com.br.desenvolvimento.desafio.exception.JokenpoException;

public enum EnumObject {
	
	SPOCK("SPOCK"),
    TESOURA("TESOURA"),
    PAPEL("PAPEL"),
    PEDRA("PEDRA"),
    LAGARTO("LAGARTO");

    private String name;
    private List<EnumObject> weakness;

    static {
        SPOCK.setWeakness(asList(LAGARTO, PAPEL));
        TESOURA.setWeakness(asList(SPOCK, PEDRA));
        PAPEL.setWeakness(asList(TESOURA, LAGARTO));
        PEDRA.setWeakness(asList(SPOCK, PAPEL));
        LAGARTO.setWeakness(asList(TESOURA, PEDRA));
    }

    EnumObject(String nome){
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public List<EnumObject> getWeakness() {
        return weakness;
    }

    public void setWeakness(List<EnumObject> weakness) {
        this.weakness = weakness;
    }

    public static EnumObject getObjectByName(String objectName) throws JokenpoException {
        for (EnumObject value : Arrays.asList(EnumObject.values())) {
            if (objectName.equals(value.getName())) {
                return value;
            }
        }
        throw new JokenpoException("Jogada não habilita");
    }
}
