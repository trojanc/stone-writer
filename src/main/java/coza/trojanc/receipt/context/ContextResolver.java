package coza.trojanc.receipt.context;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface ContextResolver {

	/**
	 * Regular expression to match an expression containing an array
	 */
	String ARRAY_EXPRESSION = "^([\\w+\\.?]+)\\[\\](\\.\\w+)*$";
	Pattern ARRAY_EXPRESSION_PATTERN = Pattern.compile(ARRAY_EXPRESSION);
	String ARRAY_LENGTH_SUFFIX = "[].$$length";

	ContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> variables);
}
