package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Names {
    private static final Random random = new Random();
    private static final List<String> firstNames = loadFirstNames();
    private static final List<String> lastNames = loadLastNames();

    private static List<String> loadFirstNames() {
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("names").get("firstNames");
    }

    private static List<String> loadLastNames() {
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("names").get("lastNames");
    }

    private static Map<String, Map<String, List<String>>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Names.class.getClassLoader().getResourceAsStream("names.yml");
        return yaml.load(inputStream);
    }

    public static String getFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public static String getLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }
    public static String getFullName(){
        return getFirstName() + " " + getLastName();
    }
}
