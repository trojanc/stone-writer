package coza.trojanc.stonewriter.context;

import coza.trojanc.stonewriter.context.impl.DefaultContextMap;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface ContextResolver {

	DefaultContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> variables);
}
