package coza.trojanc.receipt.template.fields

/**
 * A line that is filled with the specified character.
 * @author Charl Thiem
 */
class FillLine : TemplateLine {

    var character = ' '

    constructor() {}
    constructor(character: Char) {
        this.character = character
    }
}
