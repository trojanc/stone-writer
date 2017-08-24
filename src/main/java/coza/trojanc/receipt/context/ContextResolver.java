package coza.trojanc.receipt.context;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * An interface defining the basic functionality required for implementing a context resolver.
 * The {@link ContextResolver} is responsible for taking the {@link ContextDefinition} and a {@link Map} of input
 * variables and resolve the {@link ContextVariable}s to a String value within a {@link ContextMap}.
 * for further use later with a template.
 *
 * @author Charl Thiem
 */
public interface ContextResolver {

	/**
	 * Regular expression to match an expression containing an array
	 */
	String ARRAY_EXPRESSION = "^([\\w+\\.?]+)\\[\\]([\\.\\w+]+)*$";
	Pattern ARRAY_EXPRESSION_PATTERN = Pattern.compile(ARRAY_EXPRESSION);
	String ARRAY_LENGTH_SUFFIX = "[].$$length";

	/**
	 * Resolve a context by using the {@param contextDefinition} and <code>inputVariables</code> to create
	 * a {@link ContextMap}
	 * @param contextDefinition Context definition to use for resolving variables.
	 * @param inputVariables Map of input parameters to use.
	 * @return A {@link ContextMap} of resolved variables.
	 */
	ContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> inputVariables);
}
