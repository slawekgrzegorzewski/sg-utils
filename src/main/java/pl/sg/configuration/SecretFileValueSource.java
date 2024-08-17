package pl.sg.configuration;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sg.configuration.model.SecretFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

public class SecretFileValueSource implements Supplier<Optional<String>> {

    private static final Logger LOG = LoggerFactory.getLogger(SecretFileValueSource.class);
    @Nullable
    private final SecretFile secretFile;

    public SecretFileValueSource(@Nullable SecretFile secretFile) {
        this.secretFile = secretFile;
    }

    @Override
    public Optional<String> get() {
        return ofNullable(secretFile)
                .map(SecretFile::filePath)
                .filter(Files::exists)
                .map(secretFilePath -> {
                    try {
                        List<String> lines = Files.readAllLines(secretFilePath, StandardCharsets.UTF_8);
                        if (lines.isEmpty()) {
                            return null;
                        }
                        return lines.getFirst();
                    } catch (IOException e) {
                        LOG.warn("Problem during reading secret file " + secretFile, e);
                        return null;
                    }
                });
    }
}
