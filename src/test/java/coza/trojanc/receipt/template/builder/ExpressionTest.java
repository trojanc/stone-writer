package coza.trojanc.receipt.template.builder;

import coza.trojanc.receipt.template.fields.Text;
import org.apache.commons.jexl3.*;
import org.junit.jupiter.api.Test;

/**
 * @author Charl Thiem
 */
public class ExpressionTest {

	@Test
	public void testBasicExpression(){
		JexlEngine jexl = new JexlBuilder().create();
		// Create an expression
		String jexlExp = "item.text";
		JexlExpression e = jexl.createExpression( jexlExp );

		// Create a context and add data
		JexlContext jc = new MapContext();

		Text text = new Text();
		text.setText("Hello world");

		jc.set("item",text);

		// Now evaluate the expression, getting the result
		Object o = e.evaluate(jc);
		System.out.println(o);
	}
}
