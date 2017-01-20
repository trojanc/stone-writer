package coza.trojanc.receipt.context;

import coza.trojanc.receipt.context.impl.DefaultContextMap;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface ContextResolver {

	DefaultContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> variables);
}
