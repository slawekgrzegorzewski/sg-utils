package pl.sg.configuration;

import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.empty;

public class NoValueSource implements Supplier<Optional<String>> {

    @Override
    public Optional<String> get() {
        return empty();
    }
}
