package coza.trojanc.receipt.template.loader.impl

import com.fasterxml.jackson.databind.ObjectMapper
import coza.trojanc.receipt.loader.JsonLoader
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.fields.TemplateLine
import coza.trojanc.receipt.template.fields.TemplateLineMixIn
import coza.trojanc.receipt.template.fields.TemplateTextItem
import coza.trojanc.receipt.template.fields.TemplateTextItemMixIn
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader

/**
 * @author Charl Thiem
 */
class JsonTemplateDefinitionLoader : JsonLoader<PrintTemplate>(), TemplateDefinitionLoader{
    override fun prepareMapper(mapper: ObjectMapper) {
        mapper.addMixIn(TemplateTextItem::class.java, TemplateTextItemMixIn::class.java)
        mapper.addMixIn(TemplateLine::class.java, TemplateLineMixIn::class.java)
    }


}
