package coza.trojanc.receipt.context.impl

import coza.trojanc.receipt.context.*
import org.apache.commons.jexl3.*

import java.lang.reflect.Array
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Default implementation of a [ContextResolver].
 * @author Charl Thiem
 */
/**
 * Creates a new instance of the `DefaultContextResolver`
 */
class DefaultContextResolver : ContextResolver {

    /**
     * The definition of the context to resolve
     */
    private var contextDefinition: ContextDefinition? = null

    /**
     * A JEXL context which will be used to resolve expressions within
     */
    private var jc: JexlContext? = null

    /**
     * JEXL Engine being used
     */
    private var jexl: JexlEngine? = null

    /**
     * Map of resolved context variables
     */
    private val resolvedVariables: DefaultContextMap = DefaultContextMap()

    override fun resolve(contextDefinition: ContextDefinition, inputVariables: Map<String, Any>): ContextMap {
        this.contextDefinition = contextDefinition
        this.jc = MapContext(inputVariables)
        this.jexl = JexlBuilder().create()
        resolveContext()
        return resolvedVariables
    }

    private fun resolveContext() {
        contextDefinition!!.getFields().forEach { s, contextVariable -> resolveContextVariable(contextVariable) }
    }

    private fun processEvaluatedObject(contextVariable: ContextVariable, evaluatedObject: Any): String {

        // Plain String
        if (contextVariable.getType() == DynamicType.String) {
            return evaluatedObject.toString()
        } else if (contextVariable.getType() == DynamicType.Date) {
            if (!Date::class.java.isAssignableFrom(evaluatedObject.javaClass)) {
                throw IllegalArgumentException("Expected evaluated object to be of type java.util.Date instead found: " + evaluatedObject.javaClass.toString())
            }
            val sdf = SimpleDateFormat(contextVariable.getFormatting())
            return sdf.format(evaluatedObject as Date)
        } else if (contextVariable.getType() == DynamicType.Decimal) {
            if (!Number::class.java.isAssignableFrom(evaluatedObject.javaClass)) {
                throw IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.javaClass.toString())
            }
            if (contextVariable.getFormatting() == null) {
                return evaluatedObject.toString()
            } else {
                val df = DecimalFormat(contextVariable.getFormatting())
                return df.format(evaluatedObject)
            }
        } else if (contextVariable.getType() == DynamicType.Number) {
            if (!Number::class.java.isAssignableFrom(evaluatedObject.javaClass)) {
                throw IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.javaClass.toString())
            }
            return if (contextVariable.getFormatting() == null) {
                evaluatedObject.toString()
            } else {
                String.format(contextVariable.getFormatting()!!, evaluatedObject).trim { it <= ' ' }
            }

        }// Number
        // Decimal
        // Date

        // Could not resolve variable
        return contextVariable.getExpression()
    }

    private fun resolveList(contextVariable: ContextVariable, size: Int) {
        val expressionPrefix = getArrayExpressionPrefix(contextVariable.getExpression())
        val expressionSuffix = getArrayExpressionSuffix(contextVariable.getExpression())
        val contextKeyPrefix = getArrayExpressionPrefix(contextVariable.getKey())
        val contextKeySuffix = getArrayExpressionSuffix(contextVariable.getKey())
        resolvedVariables.add(contextKeyPrefix + ContextResolver.ARRAY_LENGTH_SUFFIX, Integer.toString(size))
        for (idx in 0..size - 1) {
            val lookupExpression = "$expressionPrefix[$idx]$expressionSuffix"
            val contextKey = "$contextKeyPrefix[$idx]$contextKeySuffix"
            val resolvedObject = evaluateExpression(lookupExpression)
            val processedValue = processEvaluatedObject(contextVariable, resolvedObject)
            resolvedVariables.add(contextKey, processedValue)
        }

    }

    /**
     * Resolve a context variable.
     * @param contextVariable The variable definition to resolve.
     */
    private fun resolveContextVariable(contextVariable: ContextVariable) {
        val expression = contextVariable.getExpression()

        if (isArrayExpression(expression)) {
            val prefix = getArrayExpressionPrefix(expression)
            val evaluatedArray = evaluateExpression(prefix)
            var size = (evaluatedArray as List<*>).size
            // It is a list
            if (evaluatedArray is List<*>) {
                size = evaluatedArray.size
            } else if (evaluatedArray.javaClass.isArray) {
                size = Array.getLength(evaluatedArray)
            }// It is an array
            if (size > 0) {
                resolveList(contextVariable, size)
            }
        } else {
            val evaluatedObject = evaluateExpression(expression)
            val value = processEvaluatedObject(contextVariable, evaluatedObject)
            resolvedVariables!!.add(contextVariable.getKey(), value)
        }
    }

    /**
     * Evaluate the expression within the JEXL context.
     * @param expression
     * @return
     */
    private fun evaluateExpression(expression: String): Any {
        val e = jexl!!.createExpression(expression)
        return e.evaluate(this.jc)
    }

    companion object {

        /**
         * Returns true of the expression is of an array
         * @param expression
         * @return
         */
        @JvmStatic
        fun isArrayExpression(expression: String): Boolean {
            return expression.matches(ContextResolver.ARRAY_EXPRESSION.toRegex())
        }

        /**
         * Get the prefix for the array expression
         * @param expression
         * @return
         */
        fun getArrayExpressionPrefix(expression: String): String {
            val m = ContextResolver.ARRAY_EXPRESSION_PATTERN.matcher(expression)
            return if (m.find()) {
                m.group(1)
            } else {
                throw RuntimeException("Expression is not an array pattern")
            }
        }

        /**
         * Get the suffix for the array expression
         * @param expression
         * @return
         */
        fun getArrayExpressionSuffix(expression: String): String {
            val m = ContextResolver.ARRAY_EXPRESSION_PATTERN.matcher(expression)
            return if (m.find()) {
                if (m.group(2) == null) "" else m.group(2)
            } else {
                throw RuntimeException("Expression is not an array pattern")
            }
        }
    }
}
