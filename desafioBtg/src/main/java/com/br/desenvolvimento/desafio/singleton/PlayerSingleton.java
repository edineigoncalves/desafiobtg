package com.br.desenvolvimento.desafio.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.br.desenvolvimento.desafio.entity.Player;

public final class PlayerSingleton {
	
	private static List<Player> INSTANCE;

    private PlayerSingleton(){
    }

    public static List<Player> getInstanceSingleton() {
        if(verifyPlayerNull(INSTANCE)) 
            INSTANCE = new ArrayList<Player>();
        
        return INSTANCE;
    }

    public static List<Player> clearSingleton(){
        INSTANCE = new ArrayList<Player>();
        return getInstanceSingleton();
    }

    private static boolean verifyPlayerNull(List<Player> lsAction) {
		return Objects.isNull(lsAction) && lsAction.isEmpty();
	}
}
