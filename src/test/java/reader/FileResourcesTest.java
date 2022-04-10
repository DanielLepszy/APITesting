package reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.URL;


public class FileResourcesTest {

    @DisplayName("Test loading a properties file")
    @Test
    void loadPropertiesTest()   {

        String fileName = "APITestingConfiguration/api.properties";

        ReaderFileResources reader = new ReaderFileResources();
        ClassLoader loader = reader.readTestResource(fileName);
        Assertions.assertNotNull(loader);

    }
    @DisplayName("Test loading a properties file")
    @Test
    void loadSinglePropertyTest()   {
        String filePath = "APITestingConfiguration/api.properties";
        String propertyName = "baseUrl";
        ReaderFileResources reader = new ReaderFileResources();
       String propertyValue =  reader.readSingleProperty(filePath,propertyName);

       Assertions.assertEquals(propertyValue,"http://localhost:80/");


    }
}
