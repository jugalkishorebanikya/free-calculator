package com.example.scicalculator.controller;

import com.example.scicalculator.model.BinaryRequest;
import com.example.scicalculator.model.CalcResponse;
import com.example.scicalculator.model.UnaryRequest;
import com.example.scicalculator.service.ScientificCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/sci-calculator")
@Validated
public class ScientificCalculatorController {

    private final ScientificCalculatorService svc;

    public ScientificCalculatorController(ScientificCalculatorService svc) {
        this.svc = svc;
    }

    // ══════════════════════════════════════════════════════════════
    // 1. BASIC ARITHMETIC
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "1. Basic Arithmetic")
    @PostMapping("/add")
    @Operation(summary = "Add two numbers")
    public ResponseEntity<CalcResponse> add(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.add(req.getA(), req.getB()));
    }

    @Tag(name = "1. Basic Arithmetic")
    @PostMapping("/subtract")
    @Operation(summary = "Subtract b from a")
    public ResponseEntity<CalcResponse> subtract(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.subtract(req.getA(), req.getB()));
    }

    @Tag(name = "1. Basic Arithmetic")
    @PostMapping("/multiply")
    @Operation(summary = "Multiply two numbers")
    public ResponseEntity<CalcResponse> multiply(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.multiply(req.getA(), req.getB()));
    }

    @Tag(name = "1. Basic Arithmetic")
    @PostMapping("/divide")
    @Operation(summary = "Divide a by b")
    public ResponseEntity<CalcResponse> divide(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.divide(req.getA(), req.getB()));
    }

    @Tag(name = "1. Basic Arithmetic")
    @PostMapping("/modulo")
    @Operation(summary = "Remainder of a divided by b")
    public ResponseEntity<CalcResponse> modulo(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.modulo(req.getA(), req.getB()));
    }

    // ══════════════════════════════════════════════════════════════
    // 2. POWER & ROOTS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "2. Power & Roots")
    @PostMapping("/power")
    @Operation(summary = "Raise a to the power of b")
    public ResponseEntity<CalcResponse> power(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.power(req.getA(), req.getB()));
    }

    @Tag(name = "2. Power & Roots")
    @GetMapping("/sqrt")
    @Operation(summary = "Square root of a number")
    public ResponseEntity<CalcResponse> sqrt(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.squareRoot(value));
    }

    @Tag(name = "2. Power & Roots")
    @GetMapping("/cbrt")
    @Operation(summary = "Cube root of a number")
    public ResponseEntity<CalcResponse> cbrt(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.cubeRoot(value));
    }

    @Tag(name = "2. Power & Roots")
    @PostMapping("/nth-root")
    @Operation(summary = "Nth root: a=value, b=n (root degree)")
    public ResponseEntity<CalcResponse> nthRoot(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.nthRoot(req.getA(), req.getB()));
    }

    // ══════════════════════════════════════════════════════════════
    // 3. TRIGONOMETRY
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "3. Trigonometry")
    @GetMapping("/sin")
    @Operation(summary = "Sine of an angle (degrees)")
    public ResponseEntity<CalcResponse> sin(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.sin(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/cos")
    @Operation(summary = "Cosine of an angle (degrees)")
    public ResponseEntity<CalcResponse> cos(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.cos(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/tan")
    @Operation(summary = "Tangent of an angle (degrees)")
    public ResponseEntity<CalcResponse> tan(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.tan(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/asin")
    @Operation(summary = "Arcsine — returns degrees, input must be [-1, 1]")
    public ResponseEntity<CalcResponse> asin(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.asin(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/acos")
    @Operation(summary = "Arccosine — returns degrees, input must be [-1, 1]")
    public ResponseEntity<CalcResponse> acos(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.acos(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/atan")
    @Operation(summary = "Arctangent — returns degrees")
    public ResponseEntity<CalcResponse> atan(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.atan(value));
    }

    @Tag(name = "3. Trigonometry")
    @PostMapping("/atan2")
    @Operation(summary = "atan2(y, x) — a=y, b=x, returns degrees")
    public ResponseEntity<CalcResponse> atan2(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.atan2(req.getA(), req.getB()));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/sinh")
    @Operation(summary = "Hyperbolic sine")
    public ResponseEntity<CalcResponse> sinh(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.sinh(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/cosh")
    @Operation(summary = "Hyperbolic cosine")
    public ResponseEntity<CalcResponse> cosh(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.cosh(value));
    }

    @Tag(name = "3. Trigonometry")
    @GetMapping("/tanh")
    @Operation(summary = "Hyperbolic tangent")
    public ResponseEntity<CalcResponse> tanh(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.tanh(value));
    }

    // ══════════════════════════════════════════════════════════════
    // 4. LOGARITHMS & EXPONENTIALS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "4. Logarithms & Exponentials")
    @GetMapping("/ln")
    @Operation(summary = "Natural logarithm (base e)")
    public ResponseEntity<CalcResponse> ln(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.naturalLog(value));
    }

    @Tag(name = "4. Logarithms & Exponentials")
    @GetMapping("/log10")
    @Operation(summary = "Logarithm base 10")
    public ResponseEntity<CalcResponse> log10(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.log10(value));
    }

    @Tag(name = "4. Logarithms & Exponentials")
    @GetMapping("/log2")
    @Operation(summary = "Logarithm base 2")
    public ResponseEntity<CalcResponse> log2(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.log2(value));
    }

    @Tag(name = "4. Logarithms & Exponentials")
    @PostMapping("/log-base")
    @Operation(summary = "Logarithm with custom base: a=value, b=base")
    public ResponseEntity<CalcResponse> logBase(@Valid @RequestBody BinaryRequest req) {
        return ResponseEntity.ok(svc.logBase(req.getA(), req.getB()));
    }

    @Tag(name = "4. Logarithms & Exponentials")
    @GetMapping("/exp")
    @Operation(summary = "e raised to the power of value (eˣ)")
    public ResponseEntity<CalcResponse> exp(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.exp(value));
    }

    @Tag(name = "4. Logarithms & Exponentials")
    @GetMapping("/exp10")
    @Operation(summary = "10 raised to the power of value (10ˣ)")
    public ResponseEntity<CalcResponse> exp10(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.exp10(value));
    }

    // ══════════════════════════════════════════════════════════════
    // 5. FACTORIAL & COMBINATORICS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "5. Combinatorics")
    @GetMapping("/factorial")
    @Operation(summary = "Factorial of n (n!), max n=20")
    public ResponseEntity<CalcResponse> factorial(@RequestParam @NotNull Integer n) {
        return ResponseEntity.ok(svc.factorial(n));
    }

    @Tag(name = "5. Combinatorics")
    @GetMapping("/permutation")
    @Operation(summary = "Permutation P(n, r) — ordered arrangements")
    public ResponseEntity<CalcResponse> permutation(
            @RequestParam @NotNull Integer n,
            @RequestParam @NotNull Integer r) {
        return ResponseEntity.ok(svc.permutation(n, r));
    }

    @Tag(name = "5. Combinatorics")
    @GetMapping("/combination")
    @Operation(summary = "Combination C(n, r) — n choose r")
    public ResponseEntity<CalcResponse> combination(
            @RequestParam @NotNull Integer n,
            @RequestParam @NotNull Integer r) {
        return ResponseEntity.ok(svc.combination(n, r));
    }

    // ══════════════════════════════════════════════════════════════
    // 6. NUMBER THEORY & ROUNDING
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "6. Number Theory")
    @GetMapping("/gcd")
    @Operation(summary = "Greatest Common Divisor of a and b")
    public ResponseEntity<CalcResponse> gcd(
            @RequestParam @NotNull Long a,
            @RequestParam @NotNull Long b) {
        return ResponseEntity.ok(svc.gcd(a, b));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/lcm")
    @Operation(summary = "Least Common Multiple of a and b")
    public ResponseEntity<CalcResponse> lcm(
            @RequestParam @NotNull Long a,
            @RequestParam @NotNull Long b) {
        return ResponseEntity.ok(svc.lcm(a, b));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/is-prime")
    @Operation(summary = "Check if a number is prime")
    public ResponseEntity<CalcResponse> isPrime(@RequestParam @NotNull Long n) {
        return ResponseEntity.ok(svc.isPrime(n));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/abs")
    @Operation(summary = "Absolute value")
    public ResponseEntity<CalcResponse> abs(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.absoluteValue(value));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/ceil")
    @Operation(summary = "Ceiling — smallest integer ≥ value")
    public ResponseEntity<CalcResponse> ceil(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.ceiling(value));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/floor")
    @Operation(summary = "Floor — largest integer ≤ value")
    public ResponseEntity<CalcResponse> floor(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.floor(value));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/round")
    @Operation(summary = "Round to nearest integer")
    public ResponseEntity<CalcResponse> round(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.round(value));
    }

    @Tag(name = "6. Number Theory")
    @GetMapping("/signum")
    @Operation(summary = "Sign of a number: -1, 0, or 1")
    public ResponseEntity<CalcResponse> signum(@RequestParam @NotNull Double value) {
        return ResponseEntity.ok(svc.signum(value));
    }

    // ══════════════════════════════════════════════════════════════
    // 7. STATISTICS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "7. Statistics")
    @PostMapping("/mean")
    @Operation(summary = "Arithmetic mean of an array of numbers")
    public ResponseEntity<CalcResponse> mean(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.mean(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/median")
    @Operation(summary = "Median of an array of numbers")
    public ResponseEntity<CalcResponse> median(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.median(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/stddev")
    @Operation(summary = "Population standard deviation")
    public ResponseEntity<CalcResponse> stddev(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.standardDeviation(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/variance")
    @Operation(summary = "Population variance")
    public ResponseEntity<CalcResponse> variance(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.variance(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/sum")
    @Operation(summary = "Sum of an array of numbers")
    public ResponseEntity<CalcResponse> sum(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.sum(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/product")
    @Operation(summary = "Product of an array of numbers")
    public ResponseEntity<CalcResponse> product(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.product(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/min")
    @Operation(summary = "Minimum value in an array")
    public ResponseEntity<CalcResponse> min(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.min(values));
    }

    @Tag(name = "7. Statistics")
    @PostMapping("/max")
    @Operation(summary = "Maximum value in an array")
    public ResponseEntity<CalcResponse> max(@RequestBody double[] values) {
        return ResponseEntity.ok(svc.max(values));
    }

    // ══════════════════════════════════════════════════════════════
    // 8. UNIT CONVERSIONS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "8. Unit Conversions")
    @GetMapping("/deg-to-rad") public ResponseEntity<CalcResponse> degToRad(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.degreesToRadians(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/rad-to-deg") public ResponseEntity<CalcResponse> radToDeg(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.radiansToDegrees(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/celsius-to-fahrenheit") public ResponseEntity<CalcResponse> cToF(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.celsiusToFahrenheit(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/fahrenheit-to-celsius") public ResponseEntity<CalcResponse> fToC(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.fahrenheitToCelsius(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/celsius-to-kelvin")     public ResponseEntity<CalcResponse> cToK(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.celsiusToKelvin(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/kelvin-to-celsius")     public ResponseEntity<CalcResponse> kToC(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.kelvinToCelsius(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/km-to-miles")           public ResponseEntity<CalcResponse> kmToMiles(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.kmToMiles(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/miles-to-km")           public ResponseEntity<CalcResponse> milesToKm(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.milesToKm(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/kg-to-lbs")             public ResponseEntity<CalcResponse> kgToLbs(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.kgToLbs(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/lbs-to-kg")             public ResponseEntity<CalcResponse> lbsToKg(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.lbsToKg(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/meters-to-feet")        public ResponseEntity<CalcResponse> mToFt(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.metersToFeet(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/feet-to-meters")        public ResponseEntity<CalcResponse> ftToM(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.feetToMeters(value)); }
    @Tag(name = "8. Unit Conversions")
    @GetMapping("/litre-to-gallon")       public ResponseEntity<CalcResponse> lToGal(@RequestParam @NotNull Double value) { return ResponseEntity.ok(svc.litreToGallon(value)); }

    // ══════════════════════════════════════════════════════════════
    // 9. CONSTANTS
    // ══════════════════════════════════════════════════════════════

    @Tag(name = "9. Constants")
    @GetMapping("/constants")
    @Operation(summary = "Get all mathematical constants (π, e, φ, √2, etc.)")
    public ResponseEntity<Map<String, Object>> constants() {
        return ResponseEntity.ok(svc.getConstants());
    }
}
