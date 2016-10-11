package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.fields.Line;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintLineBuilder {
	private Line line;
	private PrintTemplateBuilder templateBuilder;

	public PrintLineBuilder(PrintTemplateBuilder templateBuilder){
		this.templateBuilder = templateBuilder;
		this.line = new Line();
		templateBuilder.getTemplate().getLines().add(this.line);
	}

	public PrintTextBuilder addText(){
		return new PrintTextBuilder(this);
	}

	/**
	 * We are done with this line
	 * @return
	 */
	public PrintLineBuilder addLine(){
		return new PrintLineBuilder(this.templateBuilder);
	}

	Line getLine() {
		return line;
	}

	public PrintTemplate build(){
		return this.templateBuilder.build();
	}
}
