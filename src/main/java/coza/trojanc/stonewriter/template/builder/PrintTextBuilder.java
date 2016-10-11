package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.template.fields.Text;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTextBuilder {

	private PrintLineBuilder lineBuilder;
	private Text text;

	public PrintTextBuilder(PrintLineBuilder lineBuilder){
		this.lineBuilder = lineBuilder;
		this.text = new Text();
		this.lineBuilder.getLine().getLineItems().add(this.text);
	}

	public PrintTextBuilder text(String text){
		this.text.setText(text);
		return this;
	}

	public PrintTextBuilder align(Align align){
		this.text.setAlignment(align);
		return this;
	}



	public PrintTextBuilder addText(){
		return new PrintTextBuilder(this.lineBuilder);
	}

	public PrintLineBuilder addLine(){
		return this.lineBuilder.addLine();
	}

	public PrintTemplate build(){
		return this.lineBuilder.build();
	}
}
