package org.faker.services;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.*;

public class Country {
    private static final Random random = new Random();

    private static Map<String, List<Map<String, Object>>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Country.class.getClassLoader().getResourceAsStream("country.yml");
        return yaml.load(inputStream);
    }

    public static Map<String, Object> getRandomCountry() {
        Map<String, List<Map<String, Object>>> data = loadYaml();
        List<Map<String, Object>> countries = data.get("countries");
        return countries.get(random.nextInt(countries.size()));
    }

    // Basic country information
    public static String getRandomCountryName() {
        return (String) getRandomCountry().get("name");
    }

    public static String getRandomCapital() {
        return (String) getRandomCountry().get("capital");
    }

    public static String getRandomContinent() {
        return (String) getRandomCountry().get("continent");
    }

    public static String getRandomRegion() {
        return (String) getRandomCountry().get("region");
    }

    public static List<String> getRandomLanguages() {
        @SuppressWarnings("unchecked")
        List<String> languages = (List<String>) getRandomCountry().get("languages");
        return languages;
    }

    public static String getRandomCurrency() {
        return (String) getRandomCountry().get("currency");
    }

    public static String getRandomCountryCode() {
        return (String) getRandomCountry().get("code");
    }

    // Geographical information
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomGeography() {
        return (Map<String, Object>) getRandomCountry().get("geography");
    }

    public static double getRandomArea() {
        return ((Number) getRandomGeography().get("area")).doubleValue();
    }

    public static String getRandomAreaUnit() {
        return (String) getRandomGeography().get("area_unit");
    }

    public static int getRandomCoastlineLength() {
        return ((Number) getRandomGeography().get("coastline_length")).intValue();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomHighestPoint() {
        return (Map<String, Object>) getRandomGeography().get("highest_point");
    }

    public static String getRandomClimate() {
        return (String) getRandomGeography().get("climate");
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRandomTerrain() {
        return (List<String>) getRandomGeography().get("terrain");
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRandomNaturalResources() {
        return (List<String>) getRandomGeography().get("natural_resources");
    }

    // Major cities information
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getRandomMajorCities() {
        return (List<Map<String, Object>>) getRandomGeography().get("major_cities");
    }

    public static Map<String, Object> getRandomMajorCity() {
        List<Map<String, Object>> cities = getRandomMajorCities();
        return cities.get(random.nextInt(cities.size()));
    }

    // Filter methods
    public static Map<String, Object> getCountryByContinent(String continent) {
        Map<String, List<Map<String, Object>>> data = loadYaml();
        List<Map<String, Object>> countries = data.get("countries").stream()
                .filter(country -> continent.equalsIgnoreCase((String) country.get("continent")))
                .toList();

        if (countries.isEmpty()) {
            throw new IllegalArgumentException("No country found for continent: " + continent);
        }
        return countries.get(random.nextInt(countries.size()));
    }

    public static Map<String, Object> getCountryByRegion(String region) {
        Map<String, List<Map<String, Object>>> data = loadYaml();
        List<Map<String, Object>> countries = data.get("countries").stream()
                .filter(country -> region.equalsIgnoreCase((String) country.get("region")))
                .toList();

        if (countries.isEmpty()) {
            throw new IllegalArgumentException("No country found for region: " + region);
        }
        return countries.get(random.nextInt(countries.size()));
    }

    public static Map<String, Object> getCountryByName(String name) {
        Map<String, List<Map<String, Object>>> data = loadYaml();
        return data.get("countries").stream()
                .filter(country -> name.equalsIgnoreCase((String) country.get("name")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + name));
    }

    public static Map<String, Object> getCountryByCode(String code) {
        Map<String, List<Map<String, Object>>> data = loadYaml();
        return data.get("countries").stream()
                .filter(country -> code.equalsIgnoreCase((String) country.get("code")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found for code: " + code));
    }

    // Specific city information
    @SuppressWarnings("unchecked")
    public static Map<String, Double> getRandomCityCoordinates() {
        Map<String, Object> city = getRandomMajorCity();
        return (Map<String, Double>) city.get("coordinates");
    }

    public static String getRandomCityName() {
        Map<String, Object> city = getRandomMajorCity();
        return (String) city.get("name");
    }

    public static int getRandomCityPopulation() {
        Map<String, Object> city = getRandomMajorCity();
        return ((Number) city.get("population")).intValue();
    }

    // Highest point specific information
    public static String getRandomHighestPointName() {
        Map<String, Object> highestPoint = getRandomHighestPoint();
        return (String) highestPoint.get("name");
    }

    public static int getRandomHighestPointElevation() {
        Map<String, Object> highestPoint = getRandomHighestPoint();
        return ((Number) highestPoint.get("elevation")).intValue();
    }

    public static String getRandomHighestPointUnit() {
        Map<String, Object> highestPoint = getRandomHighestPoint();
        return (String) highestPoint.get("unit");
    }
}