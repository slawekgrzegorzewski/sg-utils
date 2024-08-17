package pl.sg.configuration.model;

import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

import static java.util.Optional.ofNullable;

public record ConfigurationProperty(
        @Nullable EnvironmentVariable environmentVariable,
        @Nullable SecretFile secretFile) {

    public ConfigurationProperty(@Nullable String environmentVariable, @Nullable Path secretFile) {
        this(
                ofNullable(environmentVariable).map(EnvironmentVariable::new).orElse(null),
                ofNullable(secretFile).map(SecretFile::new).orElse(null));
    }
}
