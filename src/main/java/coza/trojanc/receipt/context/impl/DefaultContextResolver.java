package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.*;
import org.apache.commons.jexl3.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class DefaultContextResolver implements ContextResolver {

	/**
	 * The definition of the context to resolve
	 */
	private ContextDefinition contextDefinition;

	/**
	 * A JEXL context which will be used to resolve expressions within
	 */
	private JexlContext jc;

	/**
	 * JEXL Engine being used
	 */
	private JexlEngine jexl;

	/**
	 * Map of resolved context variables
	 */
	private DefaultContextMap resolvedVariables;


	/**
	 * Creates a new instance of the <code>DefaultContextResolver</code>
	 */
	public DefaultContextResolver(){
	}

	@Override
	public ContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> variables) {
		this.contextDefinition = contextDefinition;
		this.resolvedVariables = new DefaultContextMap();
		this.jc = new MapContext(variables);
		this.jexl = new JexlBuilder().create();
		resolveContext();
		return resolvedVariables;
	}

	private void resolveContext(){
		contextDefinition.getFields().forEach((s, contextVariable) -> resolveContextVariable(contextVariable));
	}

	private String processEvaluatedObject(ContextVariable contextVariable, Object evaluatedObject){

		// Plain String
		if(contextVariable.getType() == DynamicType.String){
			return evaluatedObject.toString();
		}

		// Date
		else if(contextVariable.getType() == DynamicType.Date){
			if(!Date.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.util.Date instead found: " + evaluatedObject.getClass().toString());
			}
			SimpleDateFormat sdf = new SimpleDateFormat(contextVariable.getFormatting());
			return sdf.format((Date)evaluatedObject);
		}

		// Decimal
		else if(contextVariable.getType() == DynamicType.Decimal){
			if(!Number.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.getClass().toString());
			}
			if(contextVariable.getFormatting() == null){
				return evaluatedObject.toString();
			}
			else{
				DecimalFormat df = new DecimalFormat(contextVariable.getFormatting());
				return df.format(evaluatedObject);
			}
		}

		// Number
		else if(contextVariable.getType() == DynamicType.Number){
			if(!Number.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.getClass().toString());
			}
			if(contextVariable.getFormatting() == null){
				return evaluatedObject.toString();
			}
			else{
				return String.format(contextVariable.getFormatting(), evaluatedObject).trim();
			}

		}

		// Could not resolve variable
		return contextVariable.getExpression();
	}

	private void resolveList(ContextVariable contextVariable, int size){
		final String expressionPrefix = getArrayExpressionPrefix(contextVariable.getExpression());
		final String expressionSuffix = getArrayExpressionSuffix(contextVariable.getExpression());
		final String contextKeyPrefix = getArrayExpressionPrefix(contextVariable.getKey());
		final String contextKeySuffix = getArrayExpressionSuffix(contextVariable.getKey());
		resolvedVariables.add(contextKeyPrefix+ARRAY_LENGTH_SUFFIX, Integer.toString(size));
		for(int idx = 0 ; idx < size; idx++){
			final String lookupExpression = expressionPrefix + "[" + idx + "]" + expressionSuffix;
			final String contextKey = contextKeyPrefix + "[" + idx + "]" + contextKeySuffix;
			final Object resolvedObject = evaluateExpression(lookupExpression);
			final String processedValue = processEvaluatedObject(contextVariable, resolvedObject);
			resolvedVariables.add(contextKey, processedValue);
		}

	}

	/**
	 * Resolve a context variable.
	 * @param contextVariable The variable definition to resolve.
	 */
	private void resolveContextVariable(ContextVariable contextVariable){
		final String expression = contextVariable.getExpression();

		if(isArrayExpression(expression)){
			final String prefix = getArrayExpressionPrefix(expression);
			Object evaluatedArray = evaluateExpression(prefix);
			int size = ((List)evaluatedArray).size();
			// It is a list
			if(List.class.isAssignableFrom(evaluatedArray.getClass())){
				size = ((List)evaluatedArray).size();
			}
			// It is an array
			else if(evaluatedArray.getClass().isArray()){
				size = Array.getLength(evaluatedArray);
			}
			if(size > 0){
				resolveList(contextVariable, size);
			}
		}
		else{
			final Object evaluatedObject = evaluateExpression(expression);
			String value =  processEvaluatedObject(contextVariable, evaluatedObject);
			resolvedVariables.add(contextVariable.getKey(), value);
		}
	}

	/**
	 * Returns true of the expression is of an array
	 * @param expression
	 * @return
	 */
	static boolean isArrayExpression(String expression){
		return expression.matches(ARRAY_EXPRESSION);
	}

	/**
	 * Get the prefix for the array expression
	 * @param expression
	 * @return
	 */
	static String getArrayExpressionPrefix(String expression){
		Matcher m = ARRAY_EXPRESSION_PATTERN.matcher(expression);
		if(m.find()){
			return m.group(1);
		}
		else{
			throw new RuntimeException("Expression is not an array pattern");
		}
	}

	/**
	 * Get the suffix for the array expression
	 * @param expression
	 * @return
	 */
	static String getArrayExpressionSuffix(String expression){
		Matcher m = ARRAY_EXPRESSION_PATTERN.matcher(expression);
		if(m.find()){
			return m.group(2) == null ? "" : m.group(2);
		}
		else{
			throw new RuntimeException("Expression is not an array pattern");
		}
	}

	/**
	 * Evaluate the expression within the JEXL context.
	 * @param expression
	 * @return
	 */
	private Object evaluateExpression(String expression){
		JexlExpression e = jexl.createExpression(expression);
		return e.evaluate(this.jc);
	}
}
