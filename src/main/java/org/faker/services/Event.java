package org.faker.services;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.*;

public class Event {
    private static final Random random = new Random();

    private static Map<String, Map<String, List<Map<String, Object>>>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Event.class.getClassLoader().getResourceAsStream("events.yml");
        return yaml.load(inputStream);
    }

    // Basic Event methods
    public static Map<String, Object> getRandomEvent() {
        Map<String, Map<String, List<Map<String, Object>>>> data = loadYaml();
        Map<String, List<Map<String, Object>>> events = data.get("events");
        List<Map<String, Object>> allEvents = getAllEvents(events);
        return allEvents.get(random.nextInt(allEvents.size()));
    }

    private static List<Map<String, Object>> getAllEvents(Map<String, List<Map<String, Object>>> events) {
        List<Map<String, Object>> allEvents = new ArrayList<>();
        for (List<Map<String, Object>> categoryEvents : events.values()) {
            allEvents.addAll(categoryEvents);
        }
        return allEvents;
    }

    public static String getRandomEventName() {
        return (String) getRandomEvent().get("name");
    }

    public static String getRandomVenue() {
        return (String) getRandomEvent().get("venue");
    }

    public static int getRandomCapacity() {
        return ((Number) getRandomEvent().get("capacity")).intValue();
    }

    public static String getRandomEventType() {
        return (String) getRandomEvent().get("type");
    }

    public static String getRandomCategory() {
        Map<String, Map<String, List<Map<String, Object>>>> data = loadYaml();
        Map<String, List<Map<String, Object>>> events = data.get("events");
        List<String> categories = new ArrayList<>(events.keySet());
        return categories.get(random.nextInt(categories.size()));
    }

    // Time and Date methods
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomDateTime() {
        return (Map<String, Object>) getRandomEvent().get("datetime");
    }

    public static String getRandomDate() {
        return (String) getRandomDateTime().get("date");
    }

    public static String getRandomTime() {
        return (String) getRandomDateTime().get("time");
    }

    public static String getRandomDuration() {
        return (String) getRandomDateTime().get("duration");
    }

    // Location methods
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomLocation() {
        return (Map<String, Object>) getRandomEvent().get("location");
    }

    public static String getRandomCity() {
        return (String) getRandomLocation().get("city");
    }

    public static String getRandomCountry() {
        return (String) getRandomLocation().get("country");
    }

    public static Map<String, Double> getRandomCoordinates() {
        @SuppressWarnings("unchecked")
        Map<String, Double> coordinates = (Map<String, Double>) getRandomLocation().get("coordinates");
        return coordinates;
    }

    // Price and Ticket methods
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomTicketInfo() {
        return (Map<String, Object>) getRandomEvent().get("tickets");
    }

    public static double getRandomTicketPrice() {
        return ((Number) getRandomTicketInfo().get("price")).doubleValue();
    }

    public static String getRandomTicketCurrency() {
        return (String) getRandomTicketInfo().get("currency");
    }

    public static int getRandomAvailableTickets() {
        return ((Number) getRandomTicketInfo().get("available")).intValue();
    }

    // Filter methods
    public static Map<String, Object> getEventByCategory(String category) {
        Map<String, Map<String, List<Map<String, Object>>>> data = loadYaml();
        List<Map<String, Object>> events = data.get("events").get(category.toLowerCase());

        if (events == null || events.isEmpty()) {
            throw new IllegalArgumentException("No events found for category: " + category);
        }
        return events.get(random.nextInt(events.size()));
    }

    public static Map<String, Object> getEventByName(String name) {
        Map<String, Map<String, List<Map<String, Object>>>> data = loadYaml();
        List<Map<String, Object>> allEvents = getAllEvents(data.get("events"));
        return allEvents.stream()
                .filter(event -> name.equalsIgnoreCase((String) event.get("name")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + name));
    }

    public static List<Map<String, Object>> getEventsByType(String type) {
        Map<String, Map<String, List<Map<String, Object>>>> data = loadYaml();
        List<Map<String, Object>> allEvents = getAllEvents(data.get("events"));
        return allEvents.stream()
                .filter(event -> type.equalsIgnoreCase((String) event.get("type")))
                .toList();
    }

    // Organizer methods
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getRandomOrganizer() {
        return (Map<String, Object>) getRandomEvent().get("organizer");
    }

    public static String getRandomOrganizerName() {
        return (String) getRandomOrganizer().get("name");
    }

    public static String getRandomOrganizerContact() {
        return (String) getRandomOrganizer().get("contact");
    }
}