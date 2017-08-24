package coza.trojanc.receipt.template.loader.impl

import java.io.IOException
import java.io.OutputStream

/**
 * @author Charl Thiem
 */
class EscapedJsonOutputStream(private val out: OutputStream) : OutputStream() {
    @Throws(IOException::class)
    override fun write(b: Int) {
        if (b == '\''.toInt()) {
            out.write('\\'.toInt())
            out.write('\''.toInt())
        } else if (b == '\"'.toInt()) {
            out.write('\\'.toInt())
            out.write('\"'.toInt())
        } else {
            out.write(b)
        }
    }

    @Throws(IOException::class)
    override fun close() {

    }
}
