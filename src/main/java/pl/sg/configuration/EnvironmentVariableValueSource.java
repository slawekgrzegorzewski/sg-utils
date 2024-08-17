package pl.sg.configuration;

import org.jetbrains.annotations.Nullable;
import pl.sg.configuration.model.EnvironmentVariable;

import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

public class EnvironmentVariableValueSource implements Supplier<Optional<String>> {

    @Nullable
    private final EnvironmentVariable environmentVariableName;

    public EnvironmentVariableValueSource(@Nullable EnvironmentVariable environmentVariable) {
        this.environmentVariableName = environmentVariable;
    }

    @Override
    public Optional<String> get() {
        return ofNullable(environmentVariableName).map(EnvironmentVariable::name).map(System::getenv);
    }
}
