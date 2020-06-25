package utils;

import java.util.List;

import importadasFaia.Rule;

public class Regla extends Rule {
	
	public Regla (List<String> condition, String then, int id, int specifity, int priority, int novelty) {
		super(condition, then, id, specifity, priority, novelty);
	}

	@Override
	public boolean isActive(Object status) {
		// TODO Auto-generated method stub
		return false;
	}

}
