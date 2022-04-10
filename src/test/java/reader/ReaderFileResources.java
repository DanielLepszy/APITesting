package reader;

import java.util.Optional;
import java.util.ResourceBundle;


public class ReaderFileResources {

    public String readProperty(String fileName, String  propertyName, String... optionalPath)  {

        String path = "API_CONFIG";
        if(optionalPath!=null && !optionalPath.equals(path) && optionalPath.length!=0){
            StringBuilder newPath = new StringBuilder();
            for (String letter:optionalPath
                 ) {
                newPath.append(letter);
            }
            path = newPath.toString();
        }
        Optional<String> file = Optional.of(fileName);
        Optional<String> property = Optional.of(propertyName);

        String fullFilePath = String.format("%1$s.%2$s",path, file.get());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fullFilePath);
        return resourceBundle.getString(property.get());
    }


}
