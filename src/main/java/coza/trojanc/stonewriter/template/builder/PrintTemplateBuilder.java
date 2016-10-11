package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.template.PrintTemplate;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTemplateBuilder {

	private PrintTemplate template;

	public PrintTemplateBuilder(){
		template = new PrintTemplate();
	}

	public PrintTemplateBuilder name(String name){
		template.setName(name);
		return this;
	}


	public PrintLineBuilder addLine(){
		return new PrintLineBuilder(this);
	}

	PrintTemplate getTemplate() {
		return template;
	}

	public PrintTemplate build(){
		return template;
	}
}
