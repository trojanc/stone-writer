package coza.trojanc.receipt.shared

/**
 * @author Charl Thiem
 */
enum class CutMode private constructor(private val value: String) {
    FULL("full"),
    PARTIAL("partial")
}
