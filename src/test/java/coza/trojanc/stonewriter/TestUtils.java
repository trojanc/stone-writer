package coza.trojanc.stonewriter;

import coza.trojanc.stonewriter.context.*;
import coza.trojanc.stonewriter.context.impl.SimpleContextDefinition;
import coza.trojanc.stonewriter.context.impl.SimpleContextVariable;
import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.builder.PrintTemplateBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class TestUtils {

	public static final String KEY_PLAYER_NAME = "playerName";
	public static final String KEY_PLAYER_AGE = "playerAge";
	public static final String KEY_PLAYER_BIRTH = "playerBirth";

	private static SimpleContextDefinition contextDefinition;
	private static Map<String, Object> contextVariables;
	private static Map<String, String> resolvedVariables;

	public static ContextDefinition createContextDefinitions(){
		if(contextDefinition == null) {
			contextDefinition = new SimpleContextDefinition();
			Map<String, ContextDefinition> contextMap = new HashMap<>();

			SimpleContextVariable cd;

			// Name
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.String);
			cd.setExpression("player.name");
			cd.setKey(KEY_PLAYER_NAME);
			contextDefinition.addVariable(cd);

			// Age
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.Number);
			cd.setExpression("player.age");
			cd.setKey(KEY_PLAYER_AGE);
			contextDefinition.addVariable(cd);

			// Birth
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.Date);
			cd.setFormatting("YYYY-MM-dd");
			cd.setExpression("player.birth");
			cd.setKey(KEY_PLAYER_BIRTH);
			contextDefinition.addVariable(cd);
		}

		return contextDefinition;
	}



	public static Map<String, Object> createContextVariables(){
		if(contextVariables == null) {
			Map<String, Object> variables = new HashMap<>();
			variables.put("player", new ContextDummyObject());
			contextVariables = Collections.unmodifiableMap(variables);
		}
		return contextVariables;
	}

	public static Map<String, String> createResolvedVariables(){
		if(resolvedVariables == null) {
			Map<String, String> variables = new HashMap<>();
			variables.put(KEY_PLAYER_NAME, ContextDummyObject.NAME);
			variables.put(KEY_PLAYER_AGE, ContextDummyObject.AGE_STRING);
			variables.put(KEY_PLAYER_BIRTH, "2016-10-01");
			resolvedVariables = Collections.unmodifiableMap(variables);
		}
		return resolvedVariables;
	}

	public static PrintTemplate createTemplate(){
		return new PrintTemplateBuilder().name("Test Template")
			.line()
			.text("CHARL TAKEWAYS").align(Align.CENTER)
			.line()
			.text("SHIFT").align(Align.LEFT)
			.text("12").align(Align.RIGHT).offset(10)
			.text("TX").align(Align.RIGHT).offset(-7)
			.text("123").align(Align.RIGHT).offset(-2)
			.line()
			.text("Heres Johnny!").align(Align.RIGHT)
			.text("Heres Johnny!").align(Align.LEFT)
			.line()
			.dynamicText(TestUtils.KEY_PLAYER_AGE).align(Align.RIGHT)
			.dynamicText(TestUtils.KEY_PLAYER_NAME)
			.dynamicText(TestUtils.KEY_PLAYER_BIRTH)
			.build();
	}


}
