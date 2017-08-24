package coza.trojanc.receipt.template.loader.impl

import coza.trojanc.receipt.loader.YamlLoader
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader

/**
 * @author Charl Thiem
 */
class YamlTemplateDefinitionLoader : YamlLoader<PrintTemplate>(), TemplateDefinitionLoader
