package coza.trojanc.stonewriter.context.impl;

import coza.trojanc.stonewriter.context.ContextMap;

import java.util.HashMap;
import java.util.Map;


/**
 * The type Default context map.
 */
public class DefaultContextMap implements ContextMap {

	private final Map<String, String> resolvedVariables;

	/**
	 * Instantiates a new Default context map.
	 */
	public DefaultContextMap(){
		this(0);
	}

	/**
	 * Instantiates a new Default context map.
	 *
	 * @param initialSize the initial size
	 */
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

	@Override
	public boolean has(String key) {
		return resolvedVariables.containsKey(key);
	}
}
