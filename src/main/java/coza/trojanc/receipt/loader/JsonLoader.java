package coza.trojanc.receipt.loader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Charl-PC on 2017-06-15.
 */
public class JsonLoader<T> extends AbstractLoader<T> {


	protected static final ObjectMapper MAPPER = new ObjectMapper();

	protected Class<T> clazz;

	public JsonLoader(){
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
