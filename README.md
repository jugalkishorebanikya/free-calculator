# Spring Boot Scientific Calculator API

A comprehensive REST API scientific calculator with **50+ operations** across 9 categories.

## Run

```bash
mvn spring-boot:run
```

Swagger UI → **http://localhost:8080/swagger-ui.html**

---

## API Categories

### 1. Basic Arithmetic
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/v1/sci-calculator/add` | POST | a + b |
| `/api/v1/sci-calculator/subtract` | POST | a - b |
| `/api/v1/sci-calculator/multiply` | POST | a × b |
| `/api/v1/sci-calculator/divide` | POST | a ÷ b |
| `/api/v1/sci-calculator/modulo` | POST | a mod b |

### 2. Power & Roots
| Endpoint | Description |
|----------|-------------|
| `POST /power` | aᵇ |
| `GET  /sqrt?value=` | √x |
| `GET  /cbrt?value=` | ∛x |
| `POST /nth-root` | ⁿ√x (a=value, b=n) |

### 3. Trigonometry (degrees)
`sin`, `cos`, `tan`, `asin`, `acos`, `atan`, `atan2`, `sinh`, `cosh`, `tanh`

### 4. Logarithms & Exponentials
`ln`, `log10`, `log2`, `log-base` (custom base), `exp` (eˣ), `exp10` (10ˣ)

### 5. Combinatorics
`factorial` (n!), `permutation` P(n,r), `combination` C(n,r)

### 6. Number Theory
`gcd`, `lcm`, `is-prime`, `abs`, `ceil`, `floor`, `round`, `signum`

### 7. Statistics (POST with JSON array)
`mean`, `median`, `stddev`, `variance`, `sum`, `product`, `min`, `max`

```bash
curl -X POST http://localhost:8080/api/v1/sci-calculator/mean \
     -H "Content-Type: application/json" \
     -d '[2, 4, 4, 4, 5, 5, 7, 9]'
```

### 8. Unit Conversions
- Temperature: `celsius-to-fahrenheit`, `fahrenheit-to-celsius`, `celsius-to-kelvin`, `kelvin-to-celsius`
- Distance: `km-to-miles`, `miles-to-km`, `meters-to-feet`, `feet-to-meters`
- Weight: `kg-to-lbs`, `lbs-to-kg`
- Volume: `litre-to-gallon`
- Angles: `deg-to-rad`, `rad-to-deg`

### 9. Constants
```bash
GET /api/v1/sci-calculator/constants
```
Returns: π, e, φ (golden ratio), √2, √3, ln2, ln10, log2e, log10e

---

## Example Request & Response

```bash
curl "http://localhost:8080/api/v1/sci-calculator/sin?value=45"
```
```json
{
  "operation": "sin",
  "result": 0.7071067812,
  "expression": "sin(45°) = 0.7071067812",
  "note": "Input in degrees"
}
```

---

## Error Response
```json
{
  "errorCode": "DIVISION_BY_ZERO",
  "message": "Cannot divide by zero.",
  "timestamp": "2025-01-01T12:00:00"
}
```

## Run Tests
```bash
mvn test
```
