package org.faker.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.List;

class CountryTest {

    @Test
    void getRandomCountry_ShouldReturnValidCountry() {
        // When
        Map<String, Object> country = Country.getRandomCountry();

        // Then
        assertNotNull(country);
        assertNotNull(country.get("name"));
        assertNotNull(country.get("capital"));
        assertNotNull(country.get("continent"));
    }

    @Test
    void getRandomCapital_ShouldReturnValidCapital() {
        // When
        String capital = Country.getRandomCapital();

        // Then
        assertNotNull(capital);
        assertFalse(capital.isEmpty());
    }

    @Test
    void getCountryByContinent_ShouldReturnValidCountry() {
        // When
        Map<String, Object> country = Country.getCountryByContinent("Europe");

        // Then
        assertNotNull(country);
        assertEquals("Europe", country.get("continent"));
    }

    @Test
    void getCountryByContinent_ShouldThrowExceptionForInvalidContinent() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            Country.getCountryByContinent("InvalidContinent");
        });
    }

    @Test
    void getCountryByRegion_ShouldReturnValidCountry() {
        // When
        Map<String, Object> country = Country.getCountryByRegion("East Africa");

        // Then
        assertNotNull(country);
        assertEquals("East Africa", country.get("region"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void getRandomCountry_ShouldHaveValidGeographicalData() {
        // When
        Map<String, Object> country = Country.getRandomCountry();
        Map<String, Object> geography = (Map<String, Object>) country.get("geography");

        // Then
        assertNotNull(geography);
        assertNotNull(geography.get("area"));
        assertNotNull(geography.get("climate"));
        assertNotNull(geography.get("terrain"));

        Map<String, Object> highestPoint = (Map<String, Object>) geography.get("highest_point");
        assertNotNull(highestPoint);
        assertNotNull(highestPoint.get("name"));
        assertNotNull(highestPoint.get("elevation"));

        List<Map<String, Object>> majorCities = (List<Map<String, Object>>) geography.get("major_cities");
        assertNotNull(majorCities);
        assertFalse(majorCities.isEmpty());

        Map<String, Object> firstCity = majorCities.get(0);
        assertNotNull(firstCity.get("name"));
        assertNotNull(firstCity.get("population"));

        Map<String, Double> coordinates = (Map<String, Double>) firstCity.get("coordinates");
        assertNotNull(coordinates);
        assertNotNull(coordinates.get("latitude"));
        assertNotNull(coordinates.get("longitude"));
    }
}