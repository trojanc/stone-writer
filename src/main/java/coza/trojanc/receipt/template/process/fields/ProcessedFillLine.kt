package coza.trojanc.receipt.template.process.fields

/**
 * @author Charl Thiem
 */
class ProcessedFillLine : ProcessedLineItem {

    private val character: Char;

    constructor(): this(' '){
    }

    constructor(character: Char): super() {
        this.character = character
    }

    fun getCharacter(): Char {
        return character
    }
}
