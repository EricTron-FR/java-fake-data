package org.faker.services;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {


    @Test
    void getRandomCurrency_ShouldReturnValidCurrency() {
        // When
        String currency = Money.getRandomCurrency();

        // Then
        assertNotNull(currency);
        assertTrue(List.of("EUR", "USD", "GBP", "JPY", "CHF").contains(currency));
    }

    @Test
    void getRandomAmount_ShouldReturnValueInRange() {
        // Given
        double min = 10.0;
        double max = 100.0;

        // When
        double amount = Money.getRandomAmount(min, max);

        // Then
        assertTrue(amount >= min);
        assertTrue(amount <= max);

        // Verify decimal places (should have max 2 decimal places)
        String amountStr = String.valueOf(amount);
        int decimalPlaces = amountStr.length() - amountStr.indexOf('.') - 1;
        assertTrue(decimalPlaces <= 2);
    }

    @Test
    void getCurrencySymbol_ShouldReturnCorrectSymbol() {
        // Given
        String[][] currencySymbols = {
                {"EUR", "€"},
                {"USD", "$"},
                {"GBP", "£"},
                {"JPY", "¥"},
                {"CHF", "CHF"}
        };

        // Then
        for (String[] pair : currencySymbols) {
            String currency = pair[0];
            String expectedSymbol = pair[1];
            assertEquals(expectedSymbol, Money.getCurrencySymbol(currency));
        }
    }

    @Test
    void getCurrencySymbol_ShouldThrowExceptionForInvalidCurrency() {
        // Given
        String invalidCurrency = "INVALID";

        // Then
        assertThrows(NullPointerException.class, () -> {
            Money.getCurrencySymbol(invalidCurrency);
        });
    }

    @Test
    void getRandomAmount_ShouldHandleZeroRange() {
        // Given
        double value = 10.0;

        // When
        double amount = Money.getRandomAmount(value, value);

        // Then
        assertEquals(value, amount);
    }

    @Test
    void getRandomAmount_ShouldHandleNegativeNumbers() {
        // Given
        double min = -100.0;
        double max = -10.0;

        // When
        double amount = Money.getRandomAmount(min, max);

        // Then
        assertTrue(amount >= min);
        assertTrue(amount <= max);
    }
}
