package com.example.scicalculator;

import com.example.scicalculator.exception.CalculatorException;
import com.example.scicalculator.model.CalcResponse;
import com.example.scicalculator.service.ScientificCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScientificCalculatorServiceTest {

    private ScientificCalculatorService svc;

    @BeforeEach
    void setUp() {
        svc = new ScientificCalculatorService();
    }

    // ── Arithmetic ────────────────────────────────────────────────
    @Nested
    @DisplayName("Basic Arithmetic")
    class Arithmetic {
        @Test
        void add() {
            assertEquals(13.0, svc.add(8, 5).getResult());
        }

        @Test
        void subtract() {
            assertEquals(3.0, svc.subtract(8, 5).getResult());
        }

        @Test
        void multiply() {
            assertEquals(40.0, svc.multiply(8, 5).getResult());
        }

        @Test
        void divide() {
            assertEquals(2.5, svc.divide(10, 4).getResult());
        }

        @Test
        void modulo() {
            assertEquals(1.0, svc.modulo(10, 3).getResult());
        }

        @Test
        void divByZero() {
            assertThrows(CalculatorException.class, () -> svc.divide(5, 0));
        }
    }

    // ── Power & Roots ─────────────────────────────────────────────
    @Nested
    @DisplayName("Power & Roots")
    class PowerRoots {
        @Test
        void power() {
            assertEquals(1024.0, svc.power(2, 10).getResult());
        }

        @Test
        void sqrt() {
            assertEquals(12.0, svc.squareRoot(144).getResult());
        }

        @Test
        void cbrt() {
            assertEquals(3.0, svc.cubeRoot(27).getResult());
        }

        @Test
        void nthRoot() {
            assertEquals(2.0, svc.nthRoot(16, 4).getResult(), 1e-9);
        }

        @Test
        void sqrtNeg() {
            assertThrows(CalculatorException.class, () -> svc.squareRoot(-1));
        }
    }

    // ── Trigonometry ──────────────────────────────────────────────
    @Nested
    @DisplayName("Trigonometry")
    class Trig {
        @Test
        void sin90() {
            assertEquals(1.0, svc.sin(90).getResult());
        }

        @Test
        void cos0() {
            assertEquals(1.0, svc.cos(0).getResult());
        }

        @Test
        void sin180() {
            assertEquals(0.0, svc.sin(180).getResult());
        }

        @Test
        void tan45() {
            assertEquals(1.0, svc.tan(45).getResult(), 1e-9);
        }

        @Test
        void asin1() {
            assertEquals(90.0, svc.asin(1).getResult(), 1e-9);
        }

        @Test
        void acos1() {
            assertEquals(0.0, svc.acos(1).getResult(), 1e-9);
        }

        @Test
        void sinh0() {
            assertEquals(0.0, svc.sinh(0).getResult());
        }
    }

    // ── Logarithms ────────────────────────────────────────────────
    @Nested
    @DisplayName("Logarithms")
    class Logs {
        @Test
        void lnE() {
            assertEquals(1.0, svc.naturalLog(Math.E).getResult(), 1e-9);
        }

        @Test
        void log10() {
            assertEquals(2.0, svc.log10(100).getResult(), 1e-9);
        }

        @Test
        void log2() {
            assertEquals(3.0, svc.log2(8).getResult(), 1e-9);
        }

        @Test
        void logNeg() {
            assertThrows(CalculatorException.class, () -> svc.naturalLog(-1));
        }

        @Test
        void expZero() {
            assertEquals(1.0, svc.exp(0).getResult());
        }
    }

    // ── Combinatorics ─────────────────────────────────────────────
    @Nested
    @DisplayName("Combinatorics")
    class Combinatorics {
        @Test
        void factorial5() {
            assertEquals(120.0, svc.factorial(5).getResult());
        }

        @Test
        void factorial0() {
            assertEquals(1.0, svc.factorial(0).getResult());
        }

        @Test
        void perm() {
            assertEquals(60.0, svc.permutation(5, 3).getResult());
        }

        @Test
        void comb() {
            assertEquals(10.0, svc.combination(5, 3).getResult());
        }

        @Test
        void factNeg() {
            assertThrows(CalculatorException.class, () -> svc.factorial(-1));
        }
    }

    // ── Number Theory ─────────────────────────────────────────────
    @Nested
    @DisplayName("Number Theory")
    class NumberTheory {
        @Test
        void gcd() {
            assertEquals(6.0, svc.gcd(48, 18).getResult());
        }

        @Test
        void lcm() {
            assertEquals(12.0, svc.lcm(4, 6).getResult());
        }

        @Test
        void prime7() {
            assertEquals(1.0, svc.isPrime(7).getResult());
        }

        @Test
        void prime4() {
            assertEquals(0.0, svc.isPrime(4).getResult());
        }

        @Test
        void abs() {
            assertEquals(5.0, svc.absoluteValue(-5).getResult());
        }

        @Test
        void ceil() {
            assertEquals(4.0, svc.ceiling(3.1).getResult());
        }

        @Test
        void floor() {
            assertEquals(3.0, svc.floor(3.9).getResult());
        }
    }

    // ── Statistics ────────────────────────────────────────────────
    @Nested
    @DisplayName("Statistics")
    class Statistics {
        double[] data = {2, 4, 4, 4, 5, 5, 7, 9};

        @Test
        void mean() {
            assertEquals(5.0, svc.mean(data).getResult());
        }

        @Test
        void median() {
            assertEquals(3.5,
                    svc.median(new double[]{1, 2, 3, 4, 5, 6}).getResult());
        }

        @Test
        void stddev() {
            assertEquals(2.0, svc.standardDeviation(data).getResult(), 1e-9);
        }

        @Test
        void sum() {
            assertEquals(40.0, svc.sum(data).getResult());
        }

        @Test
        void min() {
            assertEquals(2.0, svc.min(data).getResult());
        }

        @Test
        void max() {
            assertEquals(9.0, svc.max(data).getResult());
        }
    }

    // ── Conversions ───────────────────────────────────────────────
    @Nested
    @DisplayName("Unit Conversions")
    class Conversions {
        @Test
        void cToF() {
            assertEquals(212.0, svc.celsiusToFahrenheit(100).getResult(), 1e-9);
        }

        @Test
        void fToC() {
            assertEquals(100.0, svc.fahrenheitToCelsius(212).getResult(), 1e-9);
        }

        @Test
        void cToK() {
            assertEquals(373.15, svc.celsiusToKelvin(100).getResult(), 1e-9);
        }

        @Test
        void kmMiles() {
            assertEquals(0.621371, svc.kmToMiles(1).getResult(), 1e-5);
        }

        @Test
        void kgLbs() {
            assertEquals(2.20462, svc.kgToLbs(1).getResult(), 1e-4);
        }
    }
}
