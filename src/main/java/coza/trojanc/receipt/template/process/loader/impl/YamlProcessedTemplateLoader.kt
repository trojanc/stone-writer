package coza.trojanc.receipt.template.process.loader.impl

import coza.trojanc.receipt.loader.YamlLoader
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.loader.ProcessedTemplateLoader

/**
 * @author Charl Thiem
 */
class YamlProcessedTemplateLoader : YamlLoader<ProcessedTemplate>(), ProcessedTemplateLoader
