package coza.trojanc.receipt.shared

/**
 * @author Charl Thiem
 */
enum class Align private constructor(internal var value: String) {
    LEFT("left"),
    RIGHT("right"),
    CENTER("center")
}
