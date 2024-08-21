package pl.sg.configuration;

import org.jetbrains.annotations.Nullable;
import pl.sg.configuration.model.EnvironmentVariable;
import pl.sg.configuration.model.SecretFile;

import java.util.Optional;
import java.util.function.Supplier;

public class ValueSourceFactory {

    public static Supplier<Optional<String>> getForKey(@Nullable final Object key) {
        return switch (key) {
            case null -> new NoValueSource();
            case EnvironmentVariable environmentVariable -> new EnvironmentVariableValueSource(environmentVariable);
            case SecretFile secretFile -> new SecretFileValueSource(secretFile);
            default -> throw new IllegalArgumentException("Key's " + key + " type has no dedicated ValueSource.");
        };
    }
}
