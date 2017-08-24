package coza.trojanc.receipt.template

import coza.trojanc.receipt.template.fields.TemplateLine

import java.util.ArrayList
import java.util.stream.Collectors

/**
 * A print template defines all details and formulas that must be printed
 * @author Charl Thiem
 */
class PrintTemplate {

    /**
     * Name of this template
     */
    /**
     * Get the name of the template
     *
     * @return name
     */
    /**
     * Set the name of the template
     *
     * @param name the name
     */
    var name: String? = null

    /**
     * List of lines to be printed
     */
    private val lines = ArrayList<TemplateLine>()

    /**
     * Get the lines for this template
     *
     * @return lines
     */
    fun getLines(): List<TemplateLine> {
        return lines
    }

    /**
     * Add a line to the template
     *
     * @param line the line
     */
    fun addLine(line: TemplateLine) {
        this.lines.add(line)
    }

    /**
     * Return a  string representing the template.
     * @return
     */
    override fun toString(): String {
//        val sb = StringBuilder()
//        sb.append("PrintTemplate[")
//                .append("name=").append(this.name).append(",")
//                .append("lines=[")
//        val linesString = lines.stream()
//                .map<String>(Function<TemplateLine, String> { it.toString() })
//                .collect<String, *>(Collectors.joining(","))
//        sb.append(linesString)
//        sb.append("]")
//                .append("]")
//        return sb.toString()
        return ""
    }
}
