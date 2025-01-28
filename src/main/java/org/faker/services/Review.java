package org.faker.services;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Review {
    private static final Random random = new Random();

    private static Map<String, Map<String, List<String>>> loadYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Review.class.getClassLoader().getResourceAsStream("review.yml");
        return yaml.load(inputStream);
    }

    public static int getRandomRating() {
        return random.nextInt(5) + 1;
    }

    public static String getRandomComment() {
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> positiveComments = data.get("reviews").get("positive");
        List<String> neutralComments = data.get("reviews").get("neutral");
        List<String> negativeComments = data.get("reviews").get("negative");

        int totalSize = positiveComments.size() + neutralComments.size() + negativeComments.size();
        int randomIndex = random.nextInt(totalSize);

        if (randomIndex < positiveComments.size()) {
            return positiveComments.get(randomIndex);
        } else if (randomIndex < positiveComments.size() + neutralComments.size()) {
            return neutralComments.get(randomIndex - positiveComments.size());
        } else {
            return negativeComments.get(randomIndex - positiveComments.size() - neutralComments.size());
        }
    }

    public static String getRandomCommentByRating(String rating) {
        Map<String, Map<String, List<String>>> data = loadYaml();
        List<String> comments = data.get("reviews").get(rating.toLowerCase());
        if (comments == null) {
            throw new IllegalArgumentException("Invalid rating. Use 'positive', 'neutral', or 'negative'");
        }
        return comments.get(random.nextInt(comments.size()));
    }
}
