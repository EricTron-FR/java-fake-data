package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Mail {
    private static final Random random = new Random();
git
    private static Map<String, Map<String, List<String>>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Names.class.getClassLoader().getResourceAsStream("mails.yml");
        return yaml.load(inputStream);
    }

    private static List<String> loadAllMails() {
        Map<String, Map<String, List<String>>> data = loadYaml();
        Map<String, List<String>> mails = data.get("mails");

        List<String> allMails = new ArrayList<>();
        for (List<String> domainMails : mails.values()) {
            allMails.addAll(domainMails);
        }

        return allMails;
    }

    public static String getRandomMail() {
        List<String> allMails = loadAllMails();
        Random random = new Random();
        return allMails.get(random.nextInt(allMails.size()));
    }

    public static String getIcloud(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("icloud").get(random.nextInt(data.size()));
    }

    public static String getHotmail(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("hotmail").get(random.nextInt(data.size()));
    }

    public static String getAol(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("aol").get(random.nextInt(data.size()));
    }

    public static String getYahoo(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("yahoo").get(random.nextInt(data.size()));
    }

    public static String getProton(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("proton").get(random.nextInt(data.size()));
    }

    public static String getGmail(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        return data.get("mails").get("gmail").get(random.nextInt(data.size()));
    }



}
