package coza.trojanc.receipt.context.impl

import coza.trojanc.receipt.context.ContextMap

/**
 * Default implementation of a [ContextMap]
 * @author Charl Thiem
 */
class DefaultContextMap
/**
 * Instantiates a new Default context map.
 *
 * @param initialSize the initial size
 */
@JvmOverloads constructor(initialSize: Int = 0) : ContextMap {

    private val resolvedVariables: MutableMap<String, String>

    init {
        resolvedVariables = hashMapOf();
    }

    override fun add(name: String, value: String) {
        resolvedVariables.put(name, value)
    }

    override fun get(name: String): String? {
        return resolvedVariables.get(name)
    }

    override fun has(key: String): Boolean {
        return resolvedVariables.containsKey(key)
    }
}
/**
 * Instantiates a new Default context map.
 */
