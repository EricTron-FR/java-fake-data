package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

public class Address {
    private static final Random random = new Random();
    private static final Map<String, Object> addressData = loadAddressData();

    private static Map<String, Object> loadAddressData() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = Address.class.getClassLoader().getResourceAsStream("addresses.yml");

            if (inputStream == null) {
                System.err.println("Failed to load addresses.yml - InputStream is null");
                return Collections.emptyMap();
            }

            Map<String, Object> data = yaml.load(inputStream);
            System.out.println("Loaded data: " + data);
            return data;
        } catch (Exception e) {
            System.err.println("Error loading addresses.yml: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @SuppressWarnings("unchecked")
    public static String getStreet() {
        Map<String, Object> addresses = (Map<String, Object>) addressData.get("addresses");
        Map<String, List<String>> streets = (Map<String, List<String>>) addresses.get("streets");
        List<String> types = streets.get("types");
        List<String> names = streets.get("names");
        return types.get(random.nextInt(types.size())) + " " + names.get(random.nextInt(names.size()));
    }

    @SuppressWarnings("unchecked")
    public static String getCity() {
        Map<String, Object> addresses = (Map<String, Object>) addressData.get("addresses");
        List<Map<String, String>> cities = (List<Map<String, String>>) addresses.get("cities");
        return cities.get(random.nextInt(cities.size())).get("name");
    }

    @SuppressWarnings("unchecked")
    public static String getBuilding() {
        Map<String, Object> addresses = (Map<String, Object>) addressData.get("addresses");
        Map<String, List<?>> buildings = (Map<String, List<?>>) addresses.get("buildings");
        List<Integer> numbers = (List<Integer>) buildings.get("numbers");
        List<String> suffixes = (List<String>) buildings.get("suffixes");
        return numbers.get(random.nextInt(numbers.size())) + suffixes.get(random.nextInt(suffixes.size()));
    }

    @SuppressWarnings("unchecked")
    public static String getPostcode() {
        Map<String, Object> addresses = (Map<String, Object>) addressData.get("addresses");
        List<Map<String, String>> cities = (List<Map<String, String>>) addresses.get("cities");
        return cities.get(random.nextInt(cities.size())).get("postcode");
    }

    public static String getFullAddress() {
        return String.format("%s %s\n%s %s",
                getBuilding(), getStreet(),
                getPostcode(), getCity());
    }
}