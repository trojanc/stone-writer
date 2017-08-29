package coza.trojanc.receipt.template.loader.impl

import com.fasterxml.jackson.databind.jsontype.NamedType
import coza.trojanc.receipt.loader.JsonLoader
import coza.trojanc.receipt.loader.ReceiptTypeResolverBuilder
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.fields.*
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader

/**
 * @author Charl Thiem
 */
class JsonTemplateDefinitionLoader : JsonLoader<PrintTemplate>(), TemplateDefinitionLoader{

    init {
        MAPPER.setDefaultTyping(ReceiptTypeResolverBuilder())
        MAPPER.registerSubtypes(NamedType(Feed::class.java, "feed"))
        MAPPER.registerSubtypes(NamedType(Line::class.java, "line"))
        MAPPER.registerSubtypes(NamedType(FillLine::class.java, "fillLine"))
        MAPPER.registerSubtypes(NamedType(RepeatBlock::class.java, "repeatBlock"))
        MAPPER.registerSubtypes(NamedType(DynamicText::class.java, "dynamicText"))
        MAPPER.registerSubtypes(NamedType(Text::class.java, "staticText"))
    }
}
