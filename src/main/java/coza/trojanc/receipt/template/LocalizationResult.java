package coza.trojanc.receipt.template;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizationResult {

    @JsonProperty("TerminatePhase")
    private String TerminatePhase;
    @JsonProperty("BarcodeFormat")
    private String BarcodeFormat;
    @JsonProperty("BarcodeFormatString")
    private String BarcodeFormatString;
    @JsonProperty("Angle")
    private Integer Angle;
    @JsonProperty("ResultPoints")
    private String[] ResultPoints;
    @JsonProperty("ModuleSize")
    private Integer ModuleSize;
    @JsonProperty("PageNumber")
    private Integer PageNumber;
    @JsonProperty("RegionName")
    private String RegionName;
    @JsonProperty("DocumentName")
    private String DocumentName;
    @JsonProperty("AccompanyingTextBytes")
    private String AccompanyingTextBytes;

    public String getTerminatePhase() {
        return TerminatePhase;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        this.RegionName = regionName;
    }

    public String getAccompanyingTextBytes() {
        return AccompanyingTextBytes;
    }

    public void setAccompanyingTextBytes(String accompanyingTextBytes) {
        this.AccompanyingTextBytes = accompanyingTextBytes;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        this.DocumentName = documentName;
    }

    public Integer getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.PageNumber = pageNumber;
    }

    public Integer getModuleSize() {
        return ModuleSize;
    }

    public void setModuleSize(Integer moduleSize) {
        this.ModuleSize = moduleSize;
    }

    public String[] getResultPoints() {
        return ResultPoints;
    }

    public void setResultPoints(String[] resultPoints) {
        this.ResultPoints = resultPoints;
    }

    public Integer getAngle() {
        return Angle;
    }

    public void setAngle(Integer angle) {
        this.Angle = angle;
    }

    public String getBarcodeFormatString() {
        return BarcodeFormatString;
    }

    public void setBarcodeFormatString(String barcodeFormatString) {
        this.BarcodeFormatString = barcodeFormatString;
    }

    public String getBarcodeFormat() {
        return BarcodeFormat;
    }

    public void setBarcodeFormat(String barcodeFormat) {
        this.BarcodeFormat = barcodeFormat;
    }

    public void setTerminatePhase(String terminatePhase) {
        this.TerminatePhase = terminatePhase;
    }

    
}