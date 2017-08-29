package coza.trojanc.receipt.loader

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Charl Thiem
 */
abstract class AbstractLoader<T> : Loader<T> {

    @Throws(IOException::class)
    fun load(inputStream: InputStream, mapper: ObjectMapper, clazz: Class<T>): T {
        try {
            return mapper.readValue(inputStream, clazz)
        } catch (e: JsonParseException) {
            throw IOException("Failed to load json object", e)
        } catch (e: JsonMappingException) {
            throw IOException("Failed to load json object", e)
        }

    }

    @Throws(IOException::class)
    fun load(jsonString: String, mapper: ObjectMapper, clazz: Class<T>): T {
        try {
            return mapper.readValue(jsonString, clazz)
        } catch (e: JsonParseException) {
            throw IOException("Failed to load   object", e)
        } catch (e: JsonMappingException) {
            throw IOException("Failed to load   object", e)
        }

    }

    @Throws(IOException::class)
    fun write(instance: T, out: OutputStream, mapper: ObjectMapper) {
        mapper.writeValue(out, instance)
    }
}
