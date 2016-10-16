package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.template.process.fields.ProcessedLine;
import coza.trojanc.stonewriter.shared.Mode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class HtmlPrinter extends AbstractTextPrinter {

	private Document htmlDocument;
	private Element mainRootElement;

	public HtmlPrinter(){

	}


	@Override
	public void feed() {
		mainRootElement.appendChild(htmlDocument.createElement("br"));
	}

	@Override
	public void init(){
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			htmlDocument = icBuilder.newDocument();
			mainRootElement = htmlDocument.createElement("div");
			htmlDocument.appendChild(mainRootElement);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	protected void printLine(ProcessedLine processedLine){
		Element line = htmlDocument.createElement("div");
		processedLine.getLineItems().forEach(processedText ->{
			Element text = htmlDocument.createElement("span");
			text.setAttribute("align", processedText.getAlignment().name().toLowerCase());
			if(processedText.getMode() == Mode.BOLD){
				text.setAttribute("mode", "bold");
			}
			text.setTextContent(processedText.getText());
			line.appendChild(text);
		});
		mainRootElement.appendChild(line);
	}

	public String getHtml(){
		try {
		// output DOM XML to console
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(htmlDocument);
		StreamResult console = new StreamResult(System.out);

			transformer.transform(source, console);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return "";
	}
}
