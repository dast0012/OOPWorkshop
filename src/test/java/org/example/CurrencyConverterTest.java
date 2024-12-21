package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CurrencyConverterTest {
    private CurrencyConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CurrencyConverter();
        // Antag, at vi har nogle testvalutakurser
        converter.updateExchangeRates(); // Opdaterer med testdata
    }

    @Test
    public void testConvertDKKToEUR() {
        double result = converter.convert(100, "DKK", "EUR");
        assertEquals(13.4, result, 0.1); // Forventet værdi, juster efter aktuelle kurser
    }

    @Test
    public void testConvertDKKToJPY() {
        double result = converter.convert(100, "DKK", "JPY");
        assertEquals(2187.60, result, 50.0); // Opdateret forventet værdi baseret på aktuelle kurser
    }

    @Test
    public void testInvalidCurrency() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(100, "DKK", "INVALID");
        });
        assertEquals("Ugyldig valuta", exception.getMessage());
    }
} 