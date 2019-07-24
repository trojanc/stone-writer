package coza.trojanc.receipt.template.fields;

import java.awt.*;

public class BarcodeImage {

    private Image barcodeEAN;

    public BarcodeImage() {
    }

    public BarcodeImage(Image barcodeEAN) {
        this.barcodeEAN = barcodeEAN;
    }

    public Image getBarcodeEAN() {
        return barcodeEAN;
    }

    public void setBarcodeEAN(Image barcodeEAN) {
        this.barcodeEAN = barcodeEAN;
    }
}
