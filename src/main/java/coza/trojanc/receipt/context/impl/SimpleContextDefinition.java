package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.ContextVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class SimpleContextDefinition implements ContextDefinition {

	private final Map<String, SimpleContextVariable> fields = new HashMap<>();

	public SimpleContextDefinition(){

	}

	public void addVariable(SimpleContextVariable variable) {
		fields.put(variable.getKey(), variable);
	}

	@Override
	public Map<String, ContextVariable> getFields() {
		return Collections.unmodifiableMap(fields);
	}

	public void setFields(Map<String, SimpleContextVariable> fields) {
		this.fields.clear();
		this.fields.putAll(fields);
	}
}
