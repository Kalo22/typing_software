package typing_game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWordSelector {

    public String getRandomWords(String path, int numberOfWords) {
        List<String> randomWords = new ArrayList<>();
        try (InputStream inputStream = RandomWordSelector.class.getClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream != null) {
                // Read lines from the file
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                if (numberOfWords > lines.size()) {
                    throw new IllegalArgumentException("The number of selected words is too large");
                }

                Random random = new Random();
                for (int i = 0; i < numberOfWords; i++) {
                    int randomIndex = random.nextInt(lines.size());
                    randomWords.add(lines.get(randomIndex));
                    lines.remove(randomIndex);
                }
            } else {
                System.out.println("Resource is not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String displayStr = String.join(" ", randomWords);
        return displayStr;
    }

    public void checkIfExists(String path) {
        String filePath = path;

        try {
            ClassLoader classLoader = RandomWordSelector.class.getClassLoader();
            URL resourceURL = classLoader.getResource(filePath);

            if(resourceURL != null) {
                Path fileP = Paths.get(resourceURL.toURI());
                // Read lines from the file
                List<String> lines = Files.readAllLines(fileP);

                // Process each line
                for (String line : lines) {
                    System.out.println(line);
                }
            } else {
                System.out.println("Resource si not found: " + filePath);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
