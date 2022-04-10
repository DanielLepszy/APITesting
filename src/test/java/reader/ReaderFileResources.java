package reader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


public class ReaderFileResources {

    public ClassLoader readTestResource(String fileName)   {

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getClass().getClassLoader();
    }
    public String readSingleProperty(String filePath,String propertyName)  {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        }

        File file = null;
        List<String> lines = null;
        try {
            file = new File(resource.toURI());
           lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        assert lines != null;
        lines.stream().filter(s ->
                    s.equals(propertyName))
                .collect(Collectors.toList());

        return lines.get(0).split("=")[1];
    }

}
