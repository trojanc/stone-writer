package coza.trojanc.receipt.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charl-PC on 2017-06-15.
 */
public interface Loader<T> {

	T load(InputStream inputStream) throws IOException;

	T load(String jsonString) throws IOException;

	void write(T instance, OutputStream out) throws IOException;
}
