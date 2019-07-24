package coza.trojanc.receipt.template;

import java.awt.*;

public class BarcodeTemplate {

    private String barcodeDigit;

    private Image barcodeImage;

    public String getBarcodeDigit() {
        return barcodeDigit;
    }

    public void setBarcodeDigit(String barcodeDigit) {
        this.barcodeDigit = barcodeDigit;
    }

    public Image getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(Image barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
