package coza.trojanc.receipt.template.loader.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charl-PC on 2016-11-17.
 */
public class JsonTemplateDefinitionLoader implements TemplateDefinitionLoader {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public PrintTemplate load(InputStream inputStream) throws IOException {
		try {
			PrintTemplate definition = MAPPER.readValue(inputStream, PrintTemplate.class);
			return definition;
		}catch (JsonParseException | JsonMappingException e){
			throw new IOException("Failed to load json object", e);
		}
	}

	@Override
	public void write(PrintTemplate definition, OutputStream out) throws IOException {
		MAPPER.writeValue(out, definition);
	}
}
