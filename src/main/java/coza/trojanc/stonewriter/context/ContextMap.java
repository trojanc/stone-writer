package coza.trojanc.stonewriter.context;

/**
 * A class representing a context map of resolved variables
 */
public interface ContextMap {

	/**
	 * Add a variable to the context map
	 * @param key Key to add the variable to.
	 * @param value Value for the variable
	 */
	void add(String key, String value);

	/**
	 * Get a variable from the context map
	 * @param key Key for the variable.
	 * @return The variable
	 */
	String get(String key);
}
