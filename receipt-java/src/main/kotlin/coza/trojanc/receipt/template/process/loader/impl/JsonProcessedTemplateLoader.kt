package coza.trojanc.receipt.template.process.loader.impl

import com.fasterxml.jackson.databind.ObjectMapper
import coza.trojanc.receipt.loader.JsonLoader
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.fields.ProcessedLineItem
import coza.trojanc.receipt.template.process.fields.ProcessedLineItemMixin
import coza.trojanc.receipt.template.process.loader.ProcessedTemplateLoader

/**
 * @author Charl Thiem
 */
class JsonProcessedTemplateLoader : JsonLoader<ProcessedTemplate>(), ProcessedTemplateLoader{
    override fun prepareMapper(mapper: ObjectMapper) {
        mapper.addMixIn(ProcessedLineItem::class.java, ProcessedLineItemMixin::class.java)
    }

}
