package coza.trojanc.receipt.context.impl

import coza.trojanc.receipt.context.ContextMap

/**
 * Default implementation of a [ContextMap]
 * @author Charl Thiem
 */
class DefaultContextMap : ContextMap {

    /**
     * Instantiates a new Default context map.
     */
    constructor(){
        resolvedVariables = hashMapOf();
    }

    private val resolvedVariables: MutableMap<String, String>

    override fun add(key: String, value: String) {
        resolvedVariables.put(key, value)
    }

    override fun get(key: String): String? {
        return resolvedVariables.get(key)
    }

    override fun has(key: String): Boolean {
        return resolvedVariables.containsKey(key)
    }
}
