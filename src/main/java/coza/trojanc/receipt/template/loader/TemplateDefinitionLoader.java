package coza.trojanc.receipt.template.loader;

import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.template.PrintTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An interface representing a class that can load a context definition
 */
public interface TemplateDefinitionLoader {

	/**
	 * Load a <code>PrintTemplate</code> from a <code>InputStream</code>
	 *
	 * @param inputStream Steam to read from.
	 * @return ContextDefinition loaded from input stream
	 * @throws IOException the io exception
	 */
	PrintTemplate load(InputStream inputStream) throws IOException;

	/**
	 * Create a template from a json formatted string
	 * @param jsonString
	 * @return
	 * @throws IOException
	 */
	PrintTemplate load(String jsonString) throws IOException;

	/**
	 * Write.
	 *
	 * @param out the out
	 * @throws IOException the io exception
	 */
	void write(PrintTemplate definition, OutputStream out) throws IOException;
}
