package coza.trojanc.receipt.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Charl-PC on 2017-06-15.
 */
public class YamlLoader<T> extends AbstractLoader<T> {

	protected static final ObjectMapper MAPPER = new YAMLMapper().disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID);

	protected Class<T> clazz;

	public YamlLoader(){
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
