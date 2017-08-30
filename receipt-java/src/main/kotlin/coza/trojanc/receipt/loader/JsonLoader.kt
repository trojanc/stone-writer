package coza.trojanc.receipt.loader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import coza.trojanc.receipt.template.process.fields.ProcessedFeed

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.ParameterizedType

/**
 * @author Charl Thiem
 */
abstract class JsonLoader<T> : AbstractLoader<T>() {

    protected var clazz: Class<T>
    private val objectMapper: ObjectMapper;

    init {
        this.clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        objectMapper = jacksonObjectMapper();
        prepareMapper(objectMapper);
    }

    @Throws(IOException::class)
    override fun load(inputStream: InputStream): T {
        return load(inputStream, getMapper(), clazz)
    }

    @Throws(IOException::class)
    override fun load(jsonString: String): T {
        return load(jsonString, getMapper(), clazz)
    }

    @Throws(IOException::class)
    override fun write(instance: T, out: OutputStream) {
        write(instance, out, getMapper())
    }

    fun getMapper(): ObjectMapper{
        return objectMapper;
    }

    abstract fun prepareMapper(mapper: ObjectMapper);

}
