package coza.trojanc.receipt.loader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charl-PC on 2017-06-15.
 */
public abstract class AbstractDefinitionLoader<T> implements DefinitionLoader<T> {

	public T load(InputStream inputStream, ObjectMapper mapper, Class<T> clazz) throws IOException {
		try {
			return mapper.readValue(inputStream, clazz);
		}catch (JsonParseException | JsonMappingException e){
			throw new IOException("Failed to load json object", e);
		}
	}

	public T load(String jsonString, ObjectMapper mapper, Class<T> clazz) throws IOException {
		try {
			return mapper.readValue(jsonString, clazz);
		}catch (JsonParseException | JsonMappingException e){
			throw new IOException("Failed to load   object", e);
		}
	}

	public void write(T instance, OutputStream out, ObjectMapper mapper) throws IOException {
		mapper.writeValue(out, instance);
	}
}
