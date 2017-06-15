package coza.trojanc.receipt.template.process.fields;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Charl-PC on 2016-10-11.
 */
 @JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ProcessedFeed.class, name = "feed"),
		@JsonSubTypes.Type(value = ProcessedLine.class, name = "line"),
		@JsonSubTypes.Type(value = ProcessedFillLine.class, name = "fillLine"),
})
public interface ProcessedLineItem {
}
