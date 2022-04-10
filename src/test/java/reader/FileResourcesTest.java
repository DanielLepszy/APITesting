package reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.MissingResourceException;


public class FileResourcesTest {

    @DisplayName("Test loading an single property")
    @Test
    public void referenceToProperty() {
        String fileName = "api";
        String propertyName = "HOST_URL";
        ReaderFileResources reader = new ReaderFileResources();
        String value = reader.readProperty(fileName, propertyName);
        Assertions.assertEquals(value, "http://localhost:80/api_testing");

    }
    @DisplayName("Test loading an single property from non-existed property file")
    @Test
    public void referenceToNonExistedPropertyFile() {
        String fileName = "Non-existed";
        String propertyName = "HOST_URL";
        String expectedExceptionMessage = String.format("Can't find bundle for base name API_CONFIG.%s, locale en_US", fileName);
        ReaderFileResources reader = new ReaderFileResources();
        MissingResourceException thrown = Assertions.assertThrows(MissingResourceException.class,() ->{
            reader.readProperty(fileName, propertyName);
        });
        Assertions.assertEquals(expectedExceptionMessage,thrown.getMessage());

    }
    @DisplayName("Test loading an single property from non-existed property key")
    @Test
    public void referenceToNonExistedPropertyKey() {
        String fileName = "api";
        String propertyName = "Non-existed";
        String expectedExceptionMessage = String.format("Can't find resource for bundle java.util.PropertyResourceBundle, key %s", propertyName);
        ReaderFileResources reader = new ReaderFileResources();
        MissingResourceException thrown = Assertions.assertThrows(MissingResourceException.class,() ->{
            reader.readProperty(fileName, propertyName);
        });
        Assertions.assertEquals(expectedExceptionMessage,thrown.getMessage());

    }
    @DisplayName("Test loading an single property reference to another file path")
    @Test
    public void referenceToAnotherConfigFile() {
        String fileName = "jdbc";
        String propertyName = "USER";
        String filePath = "DATABASE";
        ReaderFileResources reader = new ReaderFileResources();
        String value =  reader.readProperty(fileName, propertyName,filePath);
        Assertions.assertEquals(value, "root");

    }
}
