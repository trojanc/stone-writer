package coza.trojanc.stonewriter.context.impl;

import coza.trojanc.stonewriter.context.ContextDefinition;
import coza.trojanc.stonewriter.context.ContextVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class SimpleContextDefinition implements ContextDefinition {

	private final Map<String, ContextVariable> variables = new HashMap<>();

	public SimpleContextDefinition(){

	}

	@Override
	public Map<String, ContextVariable> getFields() {
		return Collections.unmodifiableMap(variables);
	}

	public void addVariable(ContextVariable variable){
		variables.put(variable.getKey(), variable);
	}
}
