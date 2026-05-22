package com.example.scicalculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Single-operand input")
public class UnaryRequest {

    @NotNull(message = "Value must not be null")
    @Schema(description = "Input value", example = "45.0")
    private Double value;

    public UnaryRequest() {}
    public UnaryRequest(Double value) { this.value = value; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
}
