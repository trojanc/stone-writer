package coza.trojanc.receipt.context.loader.impl;

import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.ContextVariable;
import coza.trojanc.receipt.context.impl.SimpleContextVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a context definition loaded from a json file
 * @author Charl Thiem
 */
public class LoadedContextDefinition implements ContextDefinition {

	private Map<String, SimpleContextVariable> fields = new HashMap<>();

	@Override
	public Map<String, ? extends ContextVariable> getFields() {
		return fields;
	}

	public void setFields(Map<String, SimpleContextVariable> fields){
		this.fields = fields;
	}
}
