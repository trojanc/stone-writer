package coza.trojanc.stonewriter.context.loader;

import coza.trojanc.stonewriter.context.ContextDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An interface representing a class that can load a context definition
 */
public interface ContextDefinitionLoader {

	/**
	 * Load a <code>ContextDefinition</code> from a <code>InputStream</code>
	 *
	 * @param inputStream Steam to read from.
	 * @return ContextDefinition loaded from input stream
	 * @throws IOException the io exception
	 */
	ContextDefinition load(InputStream inputStream) throws IOException;

	/**
	 * Write.
	 *
	 * @param out the out
	 * @throws IOException the io exception
	 */
	void write(ContextDefinition definition, OutputStream out) throws IOException;
}
