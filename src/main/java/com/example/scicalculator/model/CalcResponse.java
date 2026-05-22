package com.example.scicalculator.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Result of a scientific calculator operation")
public class CalcResponse {

    @Schema(description = "Operation performed", example = "sin")
    private String operation;

    @Schema(description = "Result of the operation", example = "0.7071")
    private double result;

    @Schema(description = "Human-readable expression", example = "sin(45°) = 0.7071")
    private String expression;

    @Schema(description = "Additional notes or context", example = "Angle in degrees")
    private String note;

    public CalcResponse() {}

    public CalcResponse(String operation, double result, String expression) {
        this.operation = operation;
        this.result = result;
        this.expression = expression;
    }

    public CalcResponse withNote(String note) {
        this.note = note;
        return this;
    }

    // Getters & Setters
    public String getOperation()  { return operation; }
    public double getResult()     { return result; }
    public String getExpression() { return expression; }
    public String getNote()       { return note; }

    public void setOperation(String operation)   { this.operation = operation; }
    public void setResult(double result)         { this.result = result; }
    public void setExpression(String expression) { this.expression = expression; }
    public void setNote(String note)             { this.note = note; }

    // ── Helper: clean number formatting ──────────────────────────
    public static String fmt(double v) {
        if (Double.isNaN(v))      return "NaN";
        if (Double.isInfinite(v)) return v > 0 ? "∞" : "-∞";
        if (v == Math.floor(v) && Math.abs(v) < 1e15)
            return String.valueOf((long) v);
        // Round to 10 significant decimal places to avoid floating noise
        double rounded = Math.round(v * 1e10) / 1e10;
        return String.valueOf(rounded);
    }
}
