package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.printer.impl.PlainTextPrinter;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import org.junit.jupiter.api.Test;

/**
 * @author Charl Thiem
 */
public class PrinterServiceTest {
	@Test
	public void print() throws Exception {
		ProcessedTemplate processedTemplate = TestUtils.getProcessedTemplate();

		PrinterService printerService = new PrinterService();
		Printer plainTextPrinter = new PlainTextPrinter();
		printerService.print(processedTemplate, plainTextPrinter.getLayoutBuilder());
		String printedText = (String)plainTextPrinter.getLayoutBuilder().getFormat();
		System.out.println(">" + printedText + "<");
	}

}