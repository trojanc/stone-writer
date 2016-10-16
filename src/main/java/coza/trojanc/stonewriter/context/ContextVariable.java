package coza.trojanc.stonewriter.context;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface ContextVariable {

	DynamicType getType();

	String getKey();

	String getFormatting();

	String getExpression();
}
