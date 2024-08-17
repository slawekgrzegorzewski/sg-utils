package pl.sg.configuration;

import pl.sg.configuration.model.EnvironmentVariable;
import pl.sg.configuration.model.SecretFile;

import java.util.Optional;
import java.util.function.Supplier;

public class ValueSourceFactory {

    public static Supplier<Optional<String>> getForKey(final Object key) {
        if (key instanceof EnvironmentVariable) {
            return new EnvironmentVariableValueSource((EnvironmentVariable) key);
        } else if (key instanceof SecretFile) {
            return new SecretFileValueSource((SecretFile) key);
        }
        throw new IllegalArgumentException("Key's " + key + " type has no dedicated ValueSource.");
    }
}
