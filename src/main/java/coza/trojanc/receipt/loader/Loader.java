package coza.trojanc.receipt.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Charl Thiem
 */
public interface Loader<T> {

	T load(InputStream inputStream) throws IOException;

	T load(String jsonString) throws IOException;

	void write(T instance, OutputStream out) throws IOException;
}
