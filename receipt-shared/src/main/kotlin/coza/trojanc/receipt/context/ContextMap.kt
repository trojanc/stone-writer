package coza.trojanc.receipt.context

/**
 * A class representing a context map of resolved variables.
 * @author Charl Thiem
 */
interface ContextMap {

    /**
     * Add a variable to the context map
     * @param key Key to add the variable to.
     * @param value Value for the variable
     */
    fun add(key: String, value: String)

    /**
     * Get a variable from the context map
     * @param key Key for the variable.
     * @return The variable
     */
    fun get(key: String): String?

    /**
     * Returns true if the key exists in the context.
     * @param key Key to look for.
     * @return True if the item exist.
     */
    fun has(key: String): Boolean
}
