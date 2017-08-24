package coza.trojanc.receipt.context.impl

import coza.trojanc.receipt.context.ContextDefinition
import coza.trojanc.receipt.context.ContextVariable

/**
 * A simple implementation of a [ContextDefinition].
 *
 * @author Charl Thiem
 */
/**
 * Creates a new instance of a [SimpleContextDefinition].
 */
class SimpleContextDefinition : ContextDefinition {

    /**
     * Map of fields to resolve
     */
    val fields = hashMapOf<String, ContextVariable>()

    /**
     * Adds a [ContextVariable] to the definition.
     * @param variable The variable to add to the definition.
     */
    fun addVariable(variable: SimpleContextVariable) {
        fields.put(variable.getKey(), variable)
    }

    /**
     * Gets the map of fields in this [ContextDefinition].
     * @return The map of fields in this [ContextDefinition].
     */
    override fun getFields(): Map<String, ContextVariable> {
        return this.fields; // TODO make a copy
    }

    /**
     * Sets the map of fields in this [ContextDefinition].
     * @param fields The map of fields to set in this  [ContextDefinition].
     */
    fun setFields(fields: Map<String, SimpleContextVariable>) {
        this.fields.clear()
        this.fields.putAll(fields)
    }
}
