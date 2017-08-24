package coza.trojanc.receipt.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

/**
 * @author Charl Thiem
 */
public class XmlLoader<T> extends AbstractLoader<T> {

	protected static final ObjectMapper MAPPER = new XmlMapper();

	protected Class<T> clazz;

	public XmlLoader(){
		this.clazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T load(InputStream inputStream) throws IOException {
		return load(inputStream, MAPPER, clazz);
	}

	@Override
	public T load(String jsonString) throws IOException {
		return load(jsonString, MAPPER, clazz);
	}

	@Override
	public void write(T instance, OutputStream out) throws IOException {
		write(instance, out, MAPPER);
	}
}
