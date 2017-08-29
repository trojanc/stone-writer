package coza.trojanc.receipt.context.loader.impl

import coza.trojanc.receipt.context.ContextDefinition
import coza.trojanc.receipt.context.ContextVariable
import coza.trojanc.receipt.context.impl.SimpleContextVariable


/**
 * A class representing a context definition loaded from a json file
 * @author Charl Thiem
 */
class LoadedContextDefinition : ContextDefinition {

    private var fields: Map<String, SimpleContextVariable> = hashMapOf()

    override fun getFields(): Map<String, ContextVariable> {
        return fields
    }

    fun setFields(fields: Map<String, SimpleContextVariable>) {
        this.fields = fields
    }
}
