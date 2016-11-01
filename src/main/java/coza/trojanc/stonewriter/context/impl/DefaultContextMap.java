package coza.trojanc.stonewriter.context.impl;

import coza.trojanc.stonewriter.context.ContextMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-11-01.
 */
public class DefaultContextMap implements ContextMap {

	private final Map<String, String> resolvedVariables;

	public DefaultContextMap(){
		this(0);
	}

	public DefaultContextMap(int initialSize){
		resolvedVariables = new HashMap<>(initialSize);
	}

	@Override
	public void add(String name, String value){
		resolvedVariables.put(name, value);
	}

	@Override
	public String get(String name){
		return resolvedVariables.get(name);
	}
}
