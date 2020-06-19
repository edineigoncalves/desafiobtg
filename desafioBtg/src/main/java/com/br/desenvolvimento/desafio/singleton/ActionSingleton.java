package com.br.desenvolvimento.desafio.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.br.desenvolvimento.desafio.entity.Action;

public final class ActionSingleton {

	private static List<Action> INSTANCE;

	private ActionSingleton(){
	    }

	public static List<Action> getInstanceSingleton() {
		if (verifyActionNull(INSTANCE)) 
			INSTANCE = new ArrayList<Action>();
		
		return INSTANCE;
	}

	public static List<Action> clearSingleton() {
		INSTANCE = new ArrayList<Action>();
		return getInstanceSingleton();
	}
	
	private static boolean verifyActionNull(List<Action> lsAction) {
		return Objects.isNull(lsAction) && lsAction.isEmpty();
	}

}
