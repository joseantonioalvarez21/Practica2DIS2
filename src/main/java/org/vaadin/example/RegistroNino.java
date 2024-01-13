package org.vaadin.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistroNino {
    private String _id;
    private String MsCode;
    private String Year;
    private String EstCode;
    private Float Estimate;
    private Float SE;
    private Float LowerCIB;
    private Float UpperCIB;
    private String Flag;

    public RegistroNino(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMsCode() {
        return MsCode;
    }

    public void setMsCode(String msCode) {
        MsCode = msCode;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getEstCode() {
        return EstCode;
    }

    public void setEstCode(String estCode) {
        EstCode = estCode;
    }

    public Float getEstimate() {
        return Estimate;
    }

    public void setEstimate(Float estimate) {
        Estimate = estimate;
    }

    public Float getSE() {
        return SE;
    }

    public void setSE(Float SE) {
        this.SE = SE;
    }

    public Float getLowerCIB() {
        return LowerCIB;
    }

    public void setLowerCIB(Float lowerCIB) {
        LowerCIB = lowerCIB;
    }

    public Float getUpperCIB() {
        return UpperCIB;
    }

    public void setUpperCIB(Float upperCIB) {
        UpperCIB = upperCIB;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public RegistroNino(@JsonProperty("_id") String _id,
                        @JsonProperty("MsCode") String msCode,
                        @JsonProperty("Year") String year,
                        @JsonProperty("EstCode") String estCode,
                        @JsonProperty("Estimate") Object estimate, // Cambiado a Object para manejar nulos
                        @JsonProperty("SE") Float SE,
                        @JsonProperty("LowerCIB") Object lowerCIB, // Cambiado a Object para manejar nulos
                        @JsonProperty("UpperCIB") Object upperCIB, // Cambiado a Object para manejar nulos
                        @JsonProperty("Flag") Object flag) { // Cambiado a Object para manejar nulos
        this._id = _id;
        this.MsCode = msCode;
        this.Year = year;
        this.EstCode = estCode;
        this.Estimate = (estimate != null) ? Float.parseFloat(estimate.toString()) : null;
        this.SE = SE;
        this.LowerCIB = (lowerCIB != null) ? Float.parseFloat(lowerCIB.toString()) : null;
        this.UpperCIB = (upperCIB != null) ? Float.parseFloat(upperCIB.toString()) : null;
        this.Flag = (flag != null) ? flag.toString() : null;
    }

    private Float parseNullableFloat(String value) {
        return "null".equals(value) ? null : Float.parseFloat(value);
    }
}
