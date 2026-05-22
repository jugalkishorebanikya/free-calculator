package com.example.scicalculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

// ── Binary (two operands) ──────────────────────────────────────────────────

@Schema(description = "Two-operand input")
public class BinaryRequest {

    @NotNull(message = "Operand 'a' must not be null")
    @Schema(description = "First operand", example = "9.0")
    private Double a;

    @NotNull(message = "Operand 'b' must not be null")
    @Schema(description = "Second operand", example = "3.0")
    private Double b;

    public BinaryRequest() {}
    public BinaryRequest(Double a, Double b) { this.a = a; this.b = b; }

    public Double getA() { return a; }
    public void setA(Double a) { this.a = a; }
    public Double getB() { return b; }
    public void setB(Double b) { this.b = b; }
}
