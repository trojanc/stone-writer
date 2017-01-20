package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.ContextResolver;
import coza.trojanc.receipt.context.ContextVariable;
import coza.trojanc.receipt.context.DynamicType;
import org.apache.commons.jexl3.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class DefaultContextResolver implements ContextResolver {

	private ContextDefinition contextDefinition;
	private JexlContext jc;
	private JexlEngine jexl;

	private DefaultContextMap resolvedVariables;

	public DefaultContextResolver(){
	}

	@Override
	public DefaultContextMap resolve(ContextDefinition contextDefinition, Map<String, Object> variables) {
		this.contextDefinition = contextDefinition;
		this.resolvedVariables = new DefaultContextMap();
		this.jc = new MapContext(variables);
		this.jexl = new JexlBuilder().create();
		resolveContext();
		return resolvedVariables;
	}

	private void resolveContext(){
		contextDefinition.getFields().forEach((s, contextVariable) -> {
			String value = resolveContextVariable(contextVariable);
			resolvedVariables.add(contextVariable.getKey(), value);
		});
	}

	private String resolveContextVariable(ContextVariable contextVariable){
		final Object evaluatedObject = evaluateExpression(contextVariable.getExpression());

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
			String formattedDate = sdf.format((Date)evaluatedObject);
			return formattedDate;
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
				String formattedNumber = df.format(evaluatedObject);
				return formattedNumber;
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
				String formattedNumber = String.format(contextVariable.getFormatting(), evaluatedObject).trim();
				return formattedNumber;
			}

		}

		// Could not resolve variable
		return contextVariable.getExpression();
	}

	private Object evaluateExpression(String expression){
		JexlExpression e = jexl.createExpression(expression);
		return e.evaluate(this.jc);
	}
}
