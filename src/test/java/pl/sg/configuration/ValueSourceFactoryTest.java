package pl.sg.configuration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.sg.configuration.model.EnvironmentVariable;
import pl.sg.configuration.model.SecretFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValueSourceFactoryTest {

    @ParameterizedTest
    @MethodSource("createValueSourceOfExpectedTypeParameters")
    void createValueSourceOfExpectedType(Object key, Class<?> expectedType) {
        Supplier<Optional<String>> forKey = ValueSourceFactory.getForKey(key);
        assertThat(forKey).isInstanceOf(expectedType);
    }

    public static Stream<Arguments> createValueSourceOfExpectedTypeParameters() {
        return Stream.of(
                Arguments.arguments(null, NoValueSource.class),
                Arguments.arguments(new EnvironmentVariable("ev"), EnvironmentVariableValueSource.class),
                Arguments.arguments(new SecretFile(Path.of("/")), SecretFileValueSource.class)
        );
    }
}