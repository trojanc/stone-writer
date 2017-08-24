package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.ContextVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of a {@link ContextDefinition}.
 *
 * @author Charl Thiem
 */
public class SimpleContextDefinition implements ContextDefinition {

	/**
	 * Map of fields to resolve
	 */
	private final Map<String, SimpleContextVariable> fields = new HashMap<>();

	/**
	 * Creates a new instance of a {@link SimpleContextDefinition}.
	 */
	public SimpleContextDefinition(){
	}

	/**
	 * Adds a {@link ContextVariable} to the definition.
	 * @param variable The variable to add to the definition.
	 */
	public void addVariable(SimpleContextVariable variable) {
		fields.put(variable.getKey(), variable);
	}

	/**
	 * Gets the map of fields in this {@link ContextDefinition}.
	 * @return The map of fields in this {@link ContextDefinition}.
	 */
	@Override
	public Map<String, ContextVariable> getFields() {
		return Collections.unmodifiableMap(fields);
	}

	/**
	 * Sets the map of fields in this {@link ContextDefinition}.
	 * @param fields The map of fields to set in this  {@link ContextDefinition}.
	 */
	public void setFields(Map<String, SimpleContextVariable> fields) {
		this.fields.clear();
		this.fields.putAll(fields);
	}
}
