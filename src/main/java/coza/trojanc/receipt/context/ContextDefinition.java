package coza.trojanc.receipt.context;

import java.util.Map;

/**
 * @author Charl Thiem
 */
public interface ContextDefinition {

	/**
	 * Gets fields.
	 *
	 * @return the fields
	 */
	Map<String, ? extends ContextVariable> getFields();
}
