const expressionDiv = document.getElementById('expression');
const resultDiv = document.getElementById('result');

let expression = '';

/* =========================
   DISPLAY FUNCTIONS
========================= */

function updateDisplay() {

    expressionDiv.innerText = expression;
}

function appendValue(value) {

    expression += value;

    updateDisplay();
}

function clearDisplay() {

    expression = '';

    resultDiv.innerText = '0';

    updateDisplay();
}

function deleteLast() {

    expression = expression.slice(0, -1);

    updateDisplay();
}

/* =========================
   SCIENTIFIC OPERATIONS
========================= */

function unaryOperation(operation) {

    const map = {

        /* DEGREE-BASED TRIG */

        sin: 'sinDeg(',
        cos: 'cosDeg(',
        tan: 'tanDeg(',

        sinh: 'Math.sinh(',
        cosh: 'Math.cosh(',
        tanh: 'Math.tanh(',

        asin: 'radToDeg(Math.asin(',
        acos: 'radToDeg(Math.acos(',
        atan: 'radToDeg(Math.atan(',

        /* ROOTS */

        sqrt: 'Math.sqrt(',
        cbrt: 'Math.cbrt(',

        /* LOGS */

        log10: 'Math.log10(',
        log2: 'Math.log2(',
        ln: 'Math.log(',

        exp: 'Math.exp(',
        exp10: '10**(',

        /* GENERAL */

        abs: 'Math.abs(',
        ceil: 'Math.ceil(',
        floor: 'Math.floor(',
        round: 'Math.round(',
        signum: 'Math.sign(',

        /* UNIT CONVERSIONS */

        'deg-to-rad': 'degToRad(',
        'rad-to-deg': 'radToDeg(',

        'km-to-miles': 'kmToMiles(',
        'miles-to-km': 'milesToKm(',

        'kg-to-lbs': 'kgToLbs(',
        'lbs-to-kg': 'lbsToKg(',

        'meters-to-feet': 'metersToFeet(',
        'feet-to-meters': 'feetToMeters(',

        'celsius-to-fahrenheit': 'celsiusToFahrenheit(',
        'fahrenheit-to-celsius': 'fahrenheitToCelsius('
    };

    if (map[operation]) {

        expression += map[operation];

        updateDisplay();
    }
}

/* =========================
   BINARY OPERATIONS
========================= */

function binaryOperation(operation) {

    const map = {

        power: '**',

        modulo: '%',

        atan2: 'Math.atan2(',

        'nth-root': 'root(',

        'log-base': 'logBase('
    };

    if (map[operation]) {

        expression += map[operation];

        updateDisplay();
    }
}

/* =========================
   FACTORIAL
========================= */

function factorialOperation() {

    expression += '!';

    updateDisplay();
}

/* =========================
   COMBINATORICS
========================= */

function combinatoricsOperation(type) {

    if (type === 'permutation') {

        expression += 'nPr(';
    }

    if (type === 'combination') {

        expression += 'nCr(';
    }

    updateDisplay();
}

/* =========================
   GCD / LCM
========================= */

function gcdLcmOperation(type) {

    if (type === 'gcd') {

        expression += 'gcd(';
    }

    if (type === 'lcm') {

        expression += 'lcm(';
    }

    updateDisplay();
}

/* =========================
   PRIME CHECK
========================= */

function primeOperation() {

    expression += 'isPrime(';

    updateDisplay();
}

/* =========================
   HELPER FUNCTIONS
========================= */

function factorial(n) {

    if (n < 0) return NaN;

    if (n === 0 || n === 1) return 1;

    let result = 1;

    for (let i = 2; i <= n; i++) {

        result *= i;
    }

    return result;
}

function gcd(a, b) {

    while (b !== 0) {

        [a, b] = [b, a % b];
    }

    return a;
}

function lcm(a, b) {

    return (a * b) / gcd(a, b);
}

function isPrime(n) {

    if (n < 2) return false;

    for (let i = 2; i <= Math.sqrt(n); i++) {

        if (n % i === 0) {

            return false;
        }
    }

    return true;
}

function nPr(n, r) {

    return factorial(n) / factorial(n - r);
}

function nCr(n, r) {

    return factorial(n) /
        (factorial(r) * factorial(n - r));
}

function root(x, n) {

    return Math.pow(x, 1 / n);
}

function logBase(base, value) {

    return Math.log(value) / Math.log(base);
}

/* =========================
   DEGREE/RADIAN FUNCTIONS
========================= */

function degToRad(x) {

    return x * (Math.PI / 180);
}

function radToDeg(x) {

    return x * (180 / Math.PI);
}

function sinDeg(x) {

    return Math.sin(degToRad(x));
}

function cosDeg(x) {

    return Math.cos(degToRad(x));
}

function tanDeg(x) {

    return Math.tan(degToRad(x));
}

/* =========================
   UNIT CONVERSIONS
========================= */

function kmToMiles(x) {
    return x * 0.621371;
}

function milesToKm(x) {
    return x * 1.60934;
}

function kgToLbs(x) {
    return x * 2.20462;
}

function lbsToKg(x) {
    return x * 0.453592;
}

function metersToFeet(x) {
    return x * 3.28084;
}

function feetToMeters(x) {
    return x * 0.3048;
}

function celsiusToFahrenheit(x) {
    return (x * 9 / 5) + 32;
}

function fahrenheitToCelsius(x) {
    return (x - 32) * 5 / 9;
}

/* =========================
   MAIN CALCULATOR ENGINE
========================= */

function calculateExpression() {

    try {

        let exp = expression;

        /* CONSTANTS */

        exp = exp.replace(/π/g, Math.PI);

        exp = exp.replace(/\be\b/g, Math.E);

        /* FACTORIAL */

        exp = exp.replace(
            /(\d+)!/g,
            (_, n) => factorial(Number(n))
        );

        const result = eval(exp);

        if (
            result === Infinity ||
            isNaN(result)
        ) {

            resultDiv.innerText = 'Math Error';

            return;
        }

        resultDiv.innerText =
            Number(result.toFixed(10));

    } catch (error) {

        resultDiv.innerText = 'Syntax Error';
    }
}

/* =========================
   KEYBOARD SUPPORT
========================= */

document.addEventListener('keydown', (event) => {

    const key = event.key;

    /* BLOCK LETTER INJECTION */

    if (/^[a-zA-Z]$/.test(key)) {

        event.preventDefault();

        return;
    }

    /* ALLOWED KEYS */

    if (
        '0123456789+-*/().,%!'.includes(key)
    ) {

        appendValue(key);
    }

    /* ENTER */

    if (key === 'Enter') {

        calculateExpression();
    }

    /* BACKSPACE */

    if (key === 'Backspace') {

        deleteLast();
    }

    /* ESCAPE */

    if (key === 'Escape') {

        clearDisplay();
    }
});