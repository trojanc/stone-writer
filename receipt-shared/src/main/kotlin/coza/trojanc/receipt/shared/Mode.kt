package coza.trojanc.receipt.shared

/**
 * @author Charl Thiem
 */
enum class Mode private constructor(private val value: String) {
    NORMAL("normal"),
    BOLD("bold")
}
