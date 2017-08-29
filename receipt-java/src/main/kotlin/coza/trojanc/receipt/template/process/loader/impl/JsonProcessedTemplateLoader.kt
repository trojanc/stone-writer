package coza.trojanc.receipt.template.process.loader.impl

import com.fasterxml.jackson.databind.jsontype.NamedType
import coza.trojanc.receipt.loader.JsonLoader
import coza.trojanc.receipt.loader.ReceiptTypeResolverBuilder
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.fields.ProcessedFeed
import coza.trojanc.receipt.template.process.fields.ProcessedFillLine
import coza.trojanc.receipt.template.process.fields.ProcessedLine
import coza.trojanc.receipt.template.process.loader.ProcessedTemplateLoader

/**
 * @author Charl Thiem
 */
class JsonProcessedTemplateLoader : JsonLoader<ProcessedTemplate>(), ProcessedTemplateLoader{
    init {
        MAPPER.setDefaultTyping(ReceiptTypeResolverBuilder())
        MAPPER.registerSubtypes(NamedType(ProcessedTemplate::class.java, "ProcessedTemplate"))
        MAPPER.registerSubtypes(NamedType(ProcessedFeed::class.java, "feed"))
        MAPPER.registerSubtypes(NamedType(ProcessedLine::class.java, "line"))
        MAPPER.registerSubtypes(NamedType(ProcessedFillLine::class.java, "fillLine"))
    }
}
