package coza.trojanc.receipt.loader

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Charl Thiem
 */
interface Loader<T> {

    @Throws(IOException::class)
    fun load(inputStream: InputStream): T

    @Throws(IOException::class)
    fun load(jsonString: String): T

    @Throws(IOException::class)
    fun write(instance: T, out: OutputStream)
}
