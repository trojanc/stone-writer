package coza.trojanc.receipt.context


/**
 * An interface defining the basic functionality required for implementing a context resolver.
 * The [ContextResolver] is responsible for taking the [ContextDefinition] and a [Map] of input
 * variables and resolve the [ContextVariable]s to a String value within a [ContextMap].
 * for further use later with a template.
 *
 * @author Charl Thiem
 */
interface ContextResolver {

    /**
     * Resolve a context by using the {@param contextDefinition} and `inputVariables` to create
     * a [ContextMap]
     * @param contextDefinition Context definition to use for resolving variables.
     * @param inputVariables Map of input parameters to use.
     * @return A [ContextMap] of resolved variables.
     */
    fun resolve(contextDefinition: ContextDefinition, inputVariables: Map<String, Any>): ContextMap

    companion object {

        /**
         * Regular expression to match an expression containing an array
         */
        val ARRAY_EXPRESSION = "^([\\w+\\.?]+)\\[\\]([\\.\\w+]+)*$"
        val ARRAY_EXPRESSION_PATTERN = Regex(ARRAY_EXPRESSION)
        val ARRAY_LENGTH_SUFFIX = "[].$\$length"
    }
}
