package com.example.scicalculator.service;

import com.example.scicalculator.exception.CalculatorException;
import com.example.scicalculator.model.CalcResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.scicalculator.model.CalcResponse.fmt;

@Service
public class ScientificCalculatorService {

    // ══════════════════════════════════════════════════════════════
    // 1. BASIC ARITHMETIC
    // ══════════════════════════════════════════════════════════════

    public CalcResponse add(double a, double b) {
        double r = a + b;
        return new CalcResponse("add", r, fmt(a) + " + " + fmt(b) + " = " + fmt(r));
    }

    public CalcResponse subtract(double a, double b) {
        double r = a - b;
        return new CalcResponse("subtract", r, fmt(a) + " - " + fmt(b) + " = " + fmt(r));
    }

    public CalcResponse multiply(double a, double b) {
        double r = a * b;
        return new CalcResponse("multiply", r, fmt(a) + " × " + fmt(b) + " = " + fmt(r));
    }

    public CalcResponse divide(double a, double b) {
        if (b == 0) throw new CalculatorException("DIVISION_BY_ZERO", "Cannot divide by zero.");
        double r = a / b;
        return new CalcResponse("divide", r, fmt(a) + " ÷ " + fmt(b) + " = " + fmt(r));
    }

    public CalcResponse modulo(double a, double b) {
        if (b == 0) throw new CalculatorException("MODULO_BY_ZERO", "Divisor cannot be zero.");
        double r = a % b;
        return new CalcResponse("modulo", r, fmt(a) + " mod " + fmt(b) + " = " + fmt(r));
    }

    // ══════════════════════════════════════════════════════════════
    // 2. POWER & ROOTS
    // ══════════════════════════════════════════════════════════════

    public CalcResponse power(double base, double exponent) {
        double r = Math.pow(base, exponent);
        return new CalcResponse("power", r, fmt(base) + " ^ " + fmt(exponent) + " = " + fmt(r));
    }

    public CalcResponse squareRoot(double value) {
        if (value < 0) throw new CalculatorException("NEGATIVE_SQRT",
                "Cannot compute square root of a negative number.");
        double r = Math.sqrt(value);
        return new CalcResponse("sqrt", r, "√" + fmt(value) + " = " + fmt(r));
    }

    public CalcResponse cubeRoot(double value) {
        double r = Math.cbrt(value);
        return new CalcResponse("cbrt", r, "∛" + fmt(value) + " = " + fmt(r));
    }

    public CalcResponse nthRoot(double value, double n) {
        if (n == 0) throw new CalculatorException("ZERO_ROOT", "Root degree cannot be zero.");
        if (value < 0 && n % 2 == 0)
            throw new CalculatorException("NEGATIVE_EVEN_ROOT",
                    "Cannot compute even root of a negative number.");
        double r = Math.pow(value, 1.0 / n);
        return new CalcResponse("nthRoot", r, fmt(n) + "√" + fmt(value) + " = " + fmt(r));
    }

    // ══════════════════════════════════════════════════════════════
    // 3. TRIGONOMETRY  (degrees)
    // ══════════════════════════════════════════════════════════════

    public CalcResponse sin(double degrees) {
        double r = roundTrig(Math.sin(Math.toRadians(degrees)));
        return new CalcResponse("sin", r, "sin(" + fmt(degrees) + "°) = " + fmt(r))
                .withNote("Input in degrees");
    }

    public CalcResponse cos(double degrees) {
        double r = roundTrig(Math.cos(Math.toRadians(degrees)));
        return new CalcResponse("cos", r, "cos(" + fmt(degrees) + "°) = " + fmt(r))
                .withNote("Input in degrees");
    }

    public CalcResponse tan(double degrees) {
        if (degrees % 180 == 90)
            throw new CalculatorException("UNDEFINED_TAN", "tan(" + degrees + "°) is undefined.");
        double r = roundTrig(Math.tan(Math.toRadians(degrees)));
        return new CalcResponse("tan", r, "tan(" + fmt(degrees) + "°) = " + fmt(r))
                .withNote("Input in degrees");
    }

    public CalcResponse asin(double value) {
        if (value < -1 || value > 1) throw new CalculatorException("DOMAIN_ERROR",
                "asin input must be between -1 and 1.");
        double r = Math.toDegrees(Math.asin(value));
        return new CalcResponse("asin", r, "asin(" + fmt(value) + ") = " + fmt(r) + "°")
                .withNote("Result in degrees");
    }

    public CalcResponse acos(double value) {
        if (value < -1 || value > 1) throw new CalculatorException("DOMAIN_ERROR",
                "acos input must be between -1 and 1.");
        double r = Math.toDegrees(Math.acos(value));
        return new CalcResponse("acos", r, "acos(" + fmt(value) + ") = " + fmt(r) + "°")
                .withNote("Result in degrees");
    }

    public CalcResponse atan(double value) {
        double r = Math.toDegrees(Math.atan(value));
        return new CalcResponse("atan", r, "atan(" + fmt(value) + ") = " + fmt(r) + "°")
                .withNote("Result in degrees");
    }

    public CalcResponse atan2(double y, double x) {
        double r = Math.toDegrees(Math.atan2(y, x));
        return new CalcResponse("atan2", r,
                "atan2(" + fmt(y) + ", " + fmt(x) + ") = " + fmt(r) + "°")
                .withNote("Result in degrees");
    }

    // ── Hyperbolic ─────────────────────────────────────────────────

    public CalcResponse sinh(double value) {
        double r = Math.sinh(value);
        return new CalcResponse("sinh", r, "sinh(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse cosh(double value) {
        double r = Math.cosh(value);
        return new CalcResponse("cosh", r, "cosh(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse tanh(double value) {
        double r = Math.tanh(value);
        return new CalcResponse("tanh", r, "tanh(" + fmt(value) + ") = " + fmt(r));
    }

    // ══════════════════════════════════════════════════════════════
    // 4. LOGARITHMS & EXPONENTIALS
    // ══════════════════════════════════════════════════════════════

    public CalcResponse naturalLog(double value) {
        if (value <= 0) throw new CalculatorException("DOMAIN_ERROR",
                "ln is only defined for positive numbers.");
        double r = Math.log(value);
        return new CalcResponse("ln", r, "ln(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse log10(double value) {
        if (value <= 0) throw new CalculatorException("DOMAIN_ERROR",
                "log₁₀ is only defined for positive numbers.");
        double r = Math.log10(value);
        return new CalcResponse("log10", r, "log₁₀(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse log2(double value) {
        if (value <= 0) throw new CalculatorException("DOMAIN_ERROR",
                "log₂ is only defined for positive numbers.");
        double r = Math.log(value) / Math.log(2);
        return new CalcResponse("log2", r, "log₂(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse logBase(double value, double base) {
        if (value <= 0) throw new CalculatorException("DOMAIN_ERROR",
                "Logarithm is only defined for positive numbers.");
        if (base <= 0 || base == 1) throw new CalculatorException("INVALID_BASE",
                "Base must be positive and not equal to 1.");
        double r = Math.log(value) / Math.log(base);
        return new CalcResponse("logBase", r,
                "log" + fmt(base) + "(" + fmt(value) + ") = " + fmt(r));
    }

    public CalcResponse exp(double value) {
        double r = Math.exp(value);
        return new CalcResponse("exp", r, "e^" + fmt(value) + " = " + fmt(r));
    }

    public CalcResponse exp10(double value) {
        double r = Math.pow(10, value);
        return new CalcResponse("exp10", r, "10^" + fmt(value) + " = " + fmt(r));
    }

    // ══════════════════════════════════════════════════════════════
    // 5. FACTORIAL & COMBINATORICS
    // ══════════════════════════════════════════════════════════════

    public CalcResponse factorial(int n) {
        if (n < 0) throw new CalculatorException("NEGATIVE_FACTORIAL",
                "Factorial is not defined for negative numbers.");
        if (n > 20) throw new CalculatorException("OVERFLOW",
                "Max input is 20 to avoid overflow. Use gamma for larger values.");
        long r = factVal(n);
        return new CalcResponse("factorial", r, n + "! = " + r);
    }

    public CalcResponse permutation(int n, int r) {
        validateNR(n, r);
        long result = factVal(n) / factVal(n - r);
        return new CalcResponse("permutation", result,
                "P(" + n + "," + r + ") = " + result)
                .withNote("Ordered arrangements");
    }

    public CalcResponse combination(int n, int r) {
        validateNR(n, r);
        long result = factVal(n) / (factVal(r) * factVal(n - r));
        return new CalcResponse("combination", result,
                "C(" + n + "," + r + ") = " + result)
                .withNote("n choose r (unordered selections)");
    }

    // ══════════════════════════════════════════════════════════════
    // 6. NUMBER THEORY & ROUNDING
    // ══════════════════════════════════════════════════════════════

    public CalcResponse gcd(long a, long b) {
        long r = gcdVal(Math.abs(a), Math.abs(b));
        return new CalcResponse("gcd", r, "GCD(" + a + ", " + b + ") = " + r)
                .withNote("Greatest Common Divisor");
    }

    public CalcResponse lcm(long a, long b) {
        if (a == 0 || b == 0) return new CalcResponse("lcm", 0,
                "LCM(" + a + ", " + b + ") = 0");
        long r = Math.abs(a * b) / gcdVal(Math.abs(a), Math.abs(b));
        return new CalcResponse("lcm", r, "LCM(" + a + ", " + b + ") = " + r)
                .withNote("Least Common Multiple");
    }

    public CalcResponse isPrime(long n) {
        boolean prime = checkPrime(n);
        return new CalcResponse("isPrime", prime ? 1 : 0,
                n + (prime ? " IS prime" : " is NOT prime"));
    }

    public CalcResponse absoluteValue(double value) {
        double r = Math.abs(value);
        return new CalcResponse("abs", r, "|" + fmt(value) + "| = " + fmt(r));
    }

    public CalcResponse ceiling(double value) {
        double r = Math.ceil(value);
        return new CalcResponse("ceil", r, "⌈" + fmt(value) + "⌉ = " + fmt(r));
    }

    public CalcResponse floor(double value) {
        double r = Math.floor(value);
        return new CalcResponse("floor", r, "⌊" + fmt(value) + "⌋ = " + fmt(r));
    }

    public CalcResponse round(double value) {
        long r = Math.round(value);
        return new CalcResponse("round", r, "round(" + fmt(value) + ") = " + r);
    }

    public CalcResponse signum(double value) {
        double r = Math.signum(value);
        return new CalcResponse("signum", r,
                "signum(" + fmt(value) + ") = " + fmt(r))
                .withNote("Returns -1, 0, or 1");
    }

    // ══════════════════════════════════════════════════════════════
    // 7. STATISTICS
    // ══════════════════════════════════════════════════════════════

    public CalcResponse mean(double[] values) {
        validateArr(values);
        double sum = Arrays.stream(values).sum();
        double r = sum / values.length;
        return new CalcResponse("mean", r, "mean = " + fmt(r))
                .withNote("Arithmetic mean of " + values.length + " values");
    }

    public CalcResponse median(double[] values) {
        validateArr(values);
        double[] s = values.clone();
        Arrays.sort(s);
        int n = s.length;
        double r = (n % 2 == 0) ? (s[n/2-1] + s[n/2]) / 2.0 : s[n/2];
        return new CalcResponse("median", r, "median = " + fmt(r));
    }

    public CalcResponse standardDeviation(double[] values) {
        validateArr(values);
        double mean = Arrays.stream(values).sum() / values.length;
        double variance = Arrays.stream(values).map(v -> Math.pow(v - mean, 2)).sum() / values.length;
        double r = Math.sqrt(variance);
        return new CalcResponse("stddev", r, "σ = " + fmt(r))
                .withNote("Population standard deviation");
    }

    public CalcResponse variance(double[] values) {
        validateArr(values);
        double mean = Arrays.stream(values).sum() / values.length;
        double r = Arrays.stream(values).map(v -> Math.pow(v - mean, 2)).sum() / values.length;
        return new CalcResponse("variance", r, "σ² = " + fmt(r))
                .withNote("Population variance");
    }

    public CalcResponse sum(double[] values) {
        validateArr(values);
        double r = Arrays.stream(values).sum();
        return new CalcResponse("sum", r, "Σ = " + fmt(r));
    }

    public CalcResponse product(double[] values) {
        validateArr(values);
        double r = Arrays.stream(values).reduce(1, (a, b) -> a * b);
        return new CalcResponse("product", r, "∏ = " + fmt(r));
    }

    public CalcResponse min(double[] values) {
        validateArr(values);
        double r = Arrays.stream(values).min().getAsDouble();
        return new CalcResponse("min", r, "min = " + fmt(r));
    }

    public CalcResponse max(double[] values) {
        validateArr(values);
        double r = Arrays.stream(values).max().getAsDouble();
        return new CalcResponse("max", r, "max = " + fmt(r));
    }

    // ══════════════════════════════════════════════════════════════
    // 8. UNIT CONVERSIONS
    // ══════════════════════════════════════════════════════════════

    public CalcResponse degreesToRadians(double deg) {
        double r = Math.toRadians(deg);
        return new CalcResponse("degToRad", r, fmt(deg) + "° = " + fmt(r) + " rad");
    }

    public CalcResponse radiansToDegrees(double rad) {
        double r = Math.toDegrees(rad);
        return new CalcResponse("radToDeg", r, fmt(rad) + " rad = " + fmt(r) + "°");
    }

    public CalcResponse celsiusToFahrenheit(double c) {
        double r = c * 9.0 / 5.0 + 32;
        return new CalcResponse("celsiusToFahrenheit", r, fmt(c) + "°C = " + fmt(r) + "°F");
    }

    public CalcResponse fahrenheitToCelsius(double f) {
        double r = (f - 32) * 5.0 / 9.0;
        return new CalcResponse("fahrenheitToCelsius", r, fmt(f) + "°F = " + fmt(r) + "°C");
    }

    public CalcResponse celsiusToKelvin(double c) {
        double r = c + 273.15;
        return new CalcResponse("celsiusToKelvin", r, fmt(c) + "°C = " + fmt(r) + " K");
    }

    public CalcResponse kelvinToCelsius(double k) {
        if (k < 0) throw new CalculatorException("DOMAIN_ERROR",
                "Kelvin cannot be negative.");
        double r = k - 273.15;
        return new CalcResponse("kelvinToCelsius", r, fmt(k) + " K = " + fmt(r) + "°C");
    }

    public CalcResponse kmToMiles(double km) {
        double r = km * 0.621371;
        return new CalcResponse("kmToMiles", r, fmt(km) + " km = " + fmt(r) + " miles");
    }

    public CalcResponse milesToKm(double miles) {
        double r = miles / 0.621371;
        return new CalcResponse("milesToKm", r, fmt(miles) + " miles = " + fmt(r) + " km");
    }

    public CalcResponse kgToLbs(double kg) {
        double r = kg * 2.20462;
        return new CalcResponse("kgToLbs", r, fmt(kg) + " kg = " + fmt(r) + " lbs");
    }

    public CalcResponse lbsToKg(double lbs) {
        double r = lbs / 2.20462;
        return new CalcResponse("lbsToKg", r, fmt(lbs) + " lbs = " + fmt(r) + " kg");
    }

    public CalcResponse metersToFeet(double m) {
        double r = m * 3.28084;
        return new CalcResponse("metersToFeet", r, fmt(m) + " m = " + fmt(r) + " ft");
    }

    public CalcResponse feetToMeters(double ft) {
        double r = ft / 3.28084;
        return new CalcResponse("feetToMeters", r, fmt(ft) + " ft = " + fmt(r) + " m");
    }

    public CalcResponse litreToGallon(double litre) {
        double r = litre * 0.264172;
        return new CalcResponse("litreToGallon", r, fmt(litre) + " L = " + fmt(r) + " gal");
    }

    // ══════════════════════════════════════════════════════════════
    // 9. CONSTANTS
    // ══════════════════════════════════════════════════════════════

    public Map<String, Object> getConstants() {
        Map<String, Object> c = new LinkedHashMap<>();
        c.put("PI",       Math.PI);
        c.put("E",        Math.E);
        c.put("PHI",      (1 + Math.sqrt(5)) / 2);
        c.put("SQRT2",    Math.sqrt(2));
        c.put("SQRT3",    Math.sqrt(3));
        c.put("LN2",      Math.log(2));
        c.put("LN10",     Math.log(10));
        c.put("LOG2E",    1.0 / Math.log(2));
        c.put("LOG10E",   Math.log10(Math.E));
        c.put("INFINITY", Double.POSITIVE_INFINITY);
        return c;
    }

    // ══════════════════════════════════════════════════════════════
    // PRIVATE HELPERS
    // ══════════════════════════════════════════════════════════════

    private double roundTrig(double v) {
        return Math.abs(v) < 1e-10 ? 0.0 : Math.round(v * 1e10) / 1e10;
    }

    private long factVal(int n) {
        if (n > 20) throw new CalculatorException("OVERFLOW", "Value too large.");
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }

    private long gcdVal(long a, long b) {
        return b == 0 ? a : gcdVal(b, a % b);
    }

    private boolean checkPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(n); i += 2)
            if (n % i == 0) return false;
        return true;
    }

    private void validateArr(double[] values) {
        if (values == null || values.length == 0)
            throw new CalculatorException("EMPTY_INPUT", "Input array must not be empty.");
    }

    private void validateNR(int n, int r) {
        if (n < 0 || r < 0) throw new CalculatorException("DOMAIN_ERROR",
                "n and r must be non-negative.");
        if (r > n) throw new CalculatorException("DOMAIN_ERROR",
                "r cannot be greater than n.");
    }
}
