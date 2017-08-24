package coza.trojanc.receipt.context

/**
 * @author Charl Thiem
 */
interface ContextDefinition {

    /**
     * Gets fields.
     *
     * @return the fields
     */
    fun getFields(): Map<String, ContextVariable>
}
