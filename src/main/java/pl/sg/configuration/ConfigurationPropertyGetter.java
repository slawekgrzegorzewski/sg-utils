package pl.sg.configuration;

import org.jetbrains.annotations.Nullable;
import pl.sg.configuration.model.ConfigurationProperty;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.*;

public class ConfigurationPropertyGetter {
    private final Map<String, ConfigurationProperty> properties;

    public ConfigurationPropertyGetter(Map<String, ConfigurationProperty> properties) {
        this.properties = new ConcurrentHashMap<>(properties);
    }

    public Optional<String> get(String propertyName) {
        ConfigurationProperty configurationProperty = ofNullable(properties.get(propertyName))
                .orElseThrow(() -> new RuntimeException("Property " + propertyName + " not found"));
        return getValue(configurationProperty.environmentVariable())
                .or(() -> getValue(configurationProperty.secretFile()));
    }

    public String getOrDefault(String propertyName, String defaultValue) {
        return get(propertyName).orElse(defaultValue);
    }

    private <T> Optional<String> getValue(@Nullable T environmentVariableName) {
        return ValueSourceFactory.getForKey(environmentVariableName).get();
    }
}
