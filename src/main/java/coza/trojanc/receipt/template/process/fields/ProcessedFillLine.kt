package coza.trojanc.receipt.template.process.fields

/**
 * @author Charl Thiem
 */
class ProcessedFillLine : ProcessedLineItem {

    val character = ' '

    constructor() {}

    constructor(character: Char) {
        this.character = character
    }
}
