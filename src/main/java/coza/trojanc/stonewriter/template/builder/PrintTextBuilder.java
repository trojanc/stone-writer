package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.fields.AbstractTextItem;
import coza.trojanc.stonewriter.template.fields.DynamicText;
import coza.trojanc.stonewriter.template.fields.Text;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTextBuilder {

	private final PrintLineBuilder lineBuilder;
	private final AbstractTextItem text;
	private final boolean isDynamic;

	public PrintTextBuilder(PrintLineBuilder lineBuilder, boolean dymanic){
		this.lineBuilder = lineBuilder;
		this.isDynamic = dymanic;
		this.text = dymanic ? new DynamicText() :  new Text();
		this.lineBuilder.getLine().getLineItems().add(this.text);
	}

	public PrintTextBuilder text(String text){
		if(!isDynamic){
			((Text)this.text).setText(text);
		}
		return this;
	}

	public PrintTextBuilder key(String key){
		if(isDynamic){
			((DynamicText)this.text).setContextKey(key);
		}
		return this;
	}


	public PrintTextBuilder align(Align align){
		this.text.setAlignment(align);
		return this;
	}



	public PrintTextBuilder addText(){
		return new PrintTextBuilder(this.lineBuilder, false);
	}

	public PrintTextBuilder addDynamicText(){
		return new PrintTextBuilder(this.lineBuilder, true);
	}

	public PrintLineBuilder addLine(){
		return this.lineBuilder.addLine();
	}

	public PrintTemplate build(){
		return this.lineBuilder.build();
	}
}
