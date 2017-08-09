package coza.trojanc.receipt;

import coza.trojanc.receipt.context.*;
import coza.trojanc.receipt.context.impl.DefaultContextMap;
import coza.trojanc.receipt.context.impl.SimpleContextDefinition;
import coza.trojanc.receipt.context.impl.SimpleContextVariable;
import coza.trojanc.receipt.context.test.SoldItem;
import coza.trojanc.receipt.context.test.TestTransaction;
import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.builder.PrintTemplateBuilder;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.TemplateProcessor;
import coza.trojanc.receipt.template.process.impl.DefaultTemplateProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class TestUtils {

	public static final String KEY_TRADER_NAME = "traderName";
	public static final String KEY_NUM_ITEMS = "numItems";
	public static final String KEY_TRANSCACTION_DATE = "transactionDate";
	public static final String KEY_TRANSCACTION_SOLD_NAME = "soldItemName";
	public static final String KEY_TRANSCACTION_SOLD_VALUE = "soldItemValue";
	public static final String DATE_FORMAT = "YYYY-MM-dd";

	private static SimpleContextDefinition contextDefinition;
	private static Map<String, Object> contextVariables;
	private static ContextMap resolvedVariables;

	public static ContextDefinition createContextDefinitions(){
		if(contextDefinition == null) {
			contextDefinition = new SimpleContextDefinition();

			SimpleContextVariable cd;

			// Name
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.String);
			cd.setExpression("transaction.traderName");
			cd.setKey(KEY_TRADER_NAME);
			contextDefinition.addVariable(cd);

			// Age
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.Number);
			cd.setExpression("transaction.numItems");
			cd.setKey(KEY_NUM_ITEMS);
			contextDefinition.addVariable(cd);

			// Birth
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.Date);
			cd.setFormatting(DATE_FORMAT);
			cd.setExpression("transaction.transactionDate");
			cd.setKey(KEY_TRANSCACTION_DATE);
			contextDefinition.addVariable(cd);

			// TODO these must become a list of items
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.String);
			cd.setExpression("transaction.soldItem.name");
			cd.setKey(KEY_TRANSCACTION_SOLD_NAME);
			contextDefinition.addVariable(cd);

			// TODO these must become a list of items
			cd = new SimpleContextVariable();
			cd.setType(DynamicType.String);
			cd.setExpression("transaction.soldItem.value");
			cd.setKey(KEY_TRANSCACTION_SOLD_VALUE);
			contextDefinition.addVariable(cd);
		}

		return contextDefinition;
	}



	public static Map<String, Object> createContextVariables(){
		if(contextVariables == null) {
			Map<String, Object> variables = new HashMap<>();
			variables.put("transaction", new TestTransaction());
			contextVariables = Collections.unmodifiableMap(variables);
		}
		return contextVariables;
	}

	public static ContextMap createResolvedVariables(){
		if(resolvedVariables == null) {
			resolvedVariables = new DefaultContextMap();
			resolvedVariables.add(KEY_TRADER_NAME, TestTransaction.VALUE_TRADERNAME);
			resolvedVariables.add(KEY_NUM_ITEMS, TestTransaction.VALUE_NUM_ITEMS_STRING);
			resolvedVariables.add(KEY_TRANSCACTION_DATE, new SimpleDateFormat(DATE_FORMAT).format(TestTransaction.VALUE_TRANSACTION_DATE));
			resolvedVariables.add(KEY_TRANSCACTION_DATE, new SimpleDateFormat(DATE_FORMAT).format(TestTransaction.VALUE_TRANSACTION_DATE));
			resolvedVariables.add(KEY_TRANSCACTION_SOLD_VALUE, Double.toString(SoldItem.SOLD_ITEM_VALUE));
			resolvedVariables.add(KEY_TRANSCACTION_SOLD_NAME, SoldItem.SOLD_ITEM_NAME);
		}
		return resolvedVariables;
	}

	public static ProcessedTemplate getProcessedTemplate(){
		TemplateProcessor processor = new DefaultTemplateProcessor();
		return processor.process(TestUtils.createTemplate(), TestUtils.createResolvedVariables());
	}

	public static PrintTemplate createTemplate(){
		return new PrintTemplateBuilder().name("Test Template")
				.line()
				.dynamicText(KEY_TRADER_NAME).align(Align.CENTER)
				.fillLine('-')
				.line()
				.text("Date ").align(Align.LEFT)
				.dynamicText(KEY_TRANSCACTION_DATE).align(Align.LEFT).offset(5)
				.line()
				.text("SHIFT").align(Align.LEFT)
				.text("12").align(Align.RIGHT).offset(10)
				.text("TX").align(Align.RIGHT).offset(-7)
				.text("123").align(Align.RIGHT).offset(-2)
				.fillLine('-')
				.line()
				.text("Items:")
				.dynamicText(KEY_NUM_ITEMS).align(Align.RIGHT)
				.line()
				.dynamicText(KEY_TRANSCACTION_SOLD_NAME).align(Align.LEFT)
				.dynamicText(KEY_TRANSCACTION_SOLD_VALUE).align(Align.RIGHT)
				.fillLine('-')
				.feed()
				.build();
	}

	public static OutputStream TEST_OUT = new OutputStream() {
		@Override
		public void write(int b) throws IOException {
			System.out.write(b);
		}
	};

}
