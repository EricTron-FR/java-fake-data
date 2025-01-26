package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Mail {
    private static final Random random = new Random();

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
        List<String> mailList = data.get("mails").get("icloud");
        return mailList.get(random.nextInt(mailList.size()));
    }

    public static String getHotmail(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> mailList = data.get("mails").get("hotmail");
        return mailList.get(random.nextInt(mailList.size()));
    }

    public static String getAol(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> mailList = data.get("mails").get("aol");
        return mailList.get(random.nextInt(mailList.size()));
    }

    public static String getYahoo(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> mailList = data.get("mails").get("yahoo");
        return mailList.get(random.nextInt(mailList.size()));
    }

    public static String getProton(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> mailList = data.get("mails").get("proton");
        return mailList.get(random.nextInt(mailList.size()));
    }

    public static String getGmail(){
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> gmailList = data.get("mails").get("gmail");
        return gmailList.get(random.nextInt(gmailList.size()));
    }



}
