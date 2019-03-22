package by.epam.javaweb.evgeniyyaskevich.finalproject.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private final String resourceFileName;
    private final ResourceBundle resourceBundle;

    public ResourceManager(String resourceFileName) {
        this.resourceFileName = resourceFileName;
        resourceBundle = ResourceBundle.getBundle(resourceFileName, Locale.ENGLISH);
    }

    public ResourceManager(String resourceFileName, Locale locale) {
        this.resourceFileName = resourceFileName;
        resourceBundle = ResourceBundle.getBundle(resourceFileName, locale);
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    public String getResourceFileName() {
        return resourceFileName;
    }
}
