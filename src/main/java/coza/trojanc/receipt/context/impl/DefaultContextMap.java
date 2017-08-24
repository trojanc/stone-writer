package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.ContextMap;

import java.util.HashMap;
import java.util.Map;


/**
 * Default implementation of a {@link ContextMap}
 * @author Charl Thiem
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
