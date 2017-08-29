package coza.trojanc.receipt.template.process

import coza.trojanc.receipt.context.ContextMap
import coza.trojanc.receipt.template.PrintTemplate

/**
 * @author Charl Thiem
 */
interface TemplateProcessor {

    /**
     * Process a template which does not require any variables from a context
     * @param template Template to process
     * @return The processed template.
     */
    fun process(template: PrintTemplate): ProcessedTemplate

    /**
     * Process a template which has variables that needs to be resolved from a context
     * @param template Template to process
     * @param context Context to use for variables.
     * @return The processed template.
     */
    fun process(template: PrintTemplate, context: ContextMap): ProcessedTemplate
}
