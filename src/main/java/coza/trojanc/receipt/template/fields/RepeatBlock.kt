package coza.trojanc.receipt.template.fields

import java.util.ArrayList

/**
 * A class representing a repeating section of the receipt.
 *
 *
 * A repeated section can consist of one or more line items, and repeats on a context item array
 * @author Charl Thiem
 */
class RepeatBlock : TemplateLine {

    /**+
     * Context item to repeat this block on
     */
    /**
     * Gets repeat on.
     *
     * @return the repeat on
     */
    /**
     * Sets repeat on.
     *
     * @param repeatOn the repeat on
     */
    var repeatOn: String? = null

    /**
     * Lines to repeat
     */
    /**
     * Gets lines.
     *
     * @return the lines
     */
    /**
     * Sets lines.
     *
     * @param lines the lines
     */
    var lines: List<TemplateLine> = ArrayList()
}
