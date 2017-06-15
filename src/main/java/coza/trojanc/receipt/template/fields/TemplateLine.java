package coza.trojanc.receipt.template.fields;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Charl-PC on 2016-10-16.
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Feed.class, name = "feed"),
		@JsonSubTypes.Type(value = Line.class, name = "line"),
		@JsonSubTypes.Type(value = FillLine.class, name = "fillLine"),
})
public interface TemplateLine {
}
