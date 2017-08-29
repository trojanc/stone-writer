package coza.trojanc.receipt.template.fields

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
    private var repeatOn: String? = null

    /**
     * Lines to repeat
     */
    private var lines: MutableList<TemplateLine> = ArrayList()

    /**
     * Gets repeat on.
     *
     * @return the repeat on
     */
    fun getRepeatOn(): String? {
        return repeatOn
    }

    /**
     * Sets repeat on.
     *
     * @param repeatOn the repeat on
     */
    fun setRepeatOn(repeatOn: String) {
        this.repeatOn = repeatOn
    }

    /**
     * Gets lines.
     *
     * @return the lines
     */
    fun getLines(): MutableList<TemplateLine> {
        return lines
    }

    /**
     * Sets lines.
     *
     * @param lines the lines
     */
    fun setLines(lines: List<TemplateLine>) {
        this.lines.clear();
        this.lines.addAll(lines);
    }
}
