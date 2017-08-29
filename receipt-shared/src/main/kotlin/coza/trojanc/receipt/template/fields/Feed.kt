package coza.trojanc.receipt.template.fields


/**
 * Text - Defines a printer feed element.
 * @author Charl Thiem
 */
class Feed : TemplateLine {

    //number of lines to feed
    var feedAmount: Int = 0

    /**
     * Feed constructor.
     */
    constructor() {
        this.feedAmount = 3
    }

    constructor(amount: Int) {
        this.feedAmount = amount
    }

}
