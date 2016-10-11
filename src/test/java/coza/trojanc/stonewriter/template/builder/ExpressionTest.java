package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.template.fields.Text;
import org.apache.commons.jexl3.*;
import org.junit.Test;

/**
 * Created by Charl-PC on 2016-10-11.
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
