package coza.trojanc.receipt.template.loader.impl

import coza.trojanc.receipt.loader.JsonLoader
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader

/**
 * @author Charl Thiem
 */
class JsonTemplateDefinitionLoader : JsonLoader<PrintTemplate>(), TemplateDefinitionLoader
