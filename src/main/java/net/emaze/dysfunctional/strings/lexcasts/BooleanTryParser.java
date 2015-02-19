package net.emaze.dysfunctional.strings.lexcasts;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.Optional;

public class BooleanTryParser implements Function<String, Optional<Boolean>> {

    private static final List<String> ACCEPTED_VALUES = Arrays.asList("true", "false");

    @Override
    public Optional<Boolean> apply(String parsee) {
        if (parsee == null || !ACCEPTED_VALUES.contains(parsee.toLowerCase())) {
            return Optional.empty();
        }
        return Optional.of(Boolean.parseBoolean(parsee));
    }
}
