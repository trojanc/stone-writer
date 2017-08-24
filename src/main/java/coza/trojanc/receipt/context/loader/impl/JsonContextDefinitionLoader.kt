package coza.trojanc.receipt.context.loader.impl

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import coza.trojanc.receipt.context.ContextDefinition
import coza.trojanc.receipt.context.impl.SimpleContextDefinition
import coza.trojanc.receipt.context.loader.ContextDefinitionLoader
import coza.trojanc.receipt.loader.JsonLoader

import java.io.IOException
import java.io.InputStream

/**
 * @author Charl Thiem
 */
class JsonContextDefinitionLoader : JsonLoader<ContextDefinition>(), ContextDefinitionLoader {

    @Throws(IOException::class)
    override fun load(inputStream: InputStream): ContextDefinition {
        try {
            return JsonLoader.MAPPER.readValue(inputStream, SimpleContextDefinition::class.java)
        } catch (e: JsonParseException) {
            throw IOException("Failed to load json object", e)
        } catch (e: JsonMappingException) {
            throw IOException("Failed to load json object", e)
        }

    }

    @Throws(IOException::class)
    override fun load(jsonString: String): ContextDefinition {
        try {
            return JsonLoader.MAPPER.readValue(jsonString, SimpleContextDefinition::class.java)
        } catch (e: JsonParseException) {
            throw IOException("Failed to load json object", e)
        } catch (e: JsonMappingException) {
            throw IOException("Failed to load json object", e)
        }

    }
}
