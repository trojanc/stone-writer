package coza.trojanc.stonewriter.context.loader.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coza.trojanc.stonewriter.context.ContextDefinition;
import coza.trojanc.stonewriter.context.ContextVariable;
import coza.trojanc.stonewriter.context.impl.SimpleContextVariable;
import coza.trojanc.stonewriter.context.loader.ContextDefinitionLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-11-17.
 */
public class JsonContextDefinitionLoader implements ContextDefinitionLoader{

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public ContextDefinition load(InputStream inputStream) throws IOException {
		try {
			ContextDefinition definition = MAPPER.readValue(inputStream, JsonLoadedContextDefinition.class);
			return definition;
		}catch (JsonParseException | JsonMappingException e){
			throw new IOException("Failed to load json object", e);
		}
	}

	@Override
	public void write(ContextDefinition definition, OutputStream out) throws IOException {
		MAPPER.writeValue(out, definition);
	}

	/**
	 * A class representing a context definition loaded from a json file
	 */
	private static class JsonLoadedContextDefinition implements ContextDefinition{

		private Map<String, SimpleContextVariable> fields = new HashMap<>();

		@Override
		public Map<String, ? extends ContextVariable> getFields() {
			return fields;
		}

		public void setFields(Map<String, SimpleContextVariable> fields){
			this.fields = fields;
		}
	}
}
