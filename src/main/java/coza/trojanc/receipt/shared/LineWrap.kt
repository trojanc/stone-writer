package coza.trojanc.receipt.shared

/**
 * Enumuration of line wrapping methods
 * @author Charl Thiem
 */
enum class LineWrap private constructor(internal var value: String) {
    /**
     * Do not wrap the line. Any content that does not fit into the line will be cut
     */
    NO_WRAP("none"),

    /**
     * Wrap lines applying the offset and alignment for each wrapped line
     */
    WRAP("wrap"),

    /**
     * Wrap lines, but don't apply alignment or offsets, instead keep wrapped lines fully left
     * with zero offset applied
     */
    WRAP_LEFT("left")
}
