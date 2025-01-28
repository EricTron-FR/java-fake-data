package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Money {
    private static final Random random = new Random();

    private static Map<String, Map<String, Object>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Money.class.getClassLoader().getResourceAsStream("money.yml");
        return yaml.load(inputStream);
    }

    public static String getRandomCurrency() {
        Map<String, Map<String, Object>> data = loadYaml();
        List<String> currencies = new ArrayList<>(data.get("currencies").keySet());
        return currencies.get(random.nextInt(currencies.size()));
    }

    public static double getRandomAmount(double min, double max) {
        return Math.round((min + (max - min) * random.nextDouble()) * 100.0) / 100.0;
    }

    public static String getCurrencySymbol(String currency) {
        Map<String, Map<String, Object>> data = loadYaml();
        @SuppressWarnings("unchecked")
        Map<String, Object> currencyData = (Map<String, Object>) data.get("currencies").get(currency);
        return (String) currencyData.get("symbol");
    }
}
