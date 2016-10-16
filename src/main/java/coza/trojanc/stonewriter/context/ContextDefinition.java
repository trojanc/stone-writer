package coza.trojanc.stonewriter.context;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface ContextDefinition {

	Map<String, ContextVariable> getFields();
}
