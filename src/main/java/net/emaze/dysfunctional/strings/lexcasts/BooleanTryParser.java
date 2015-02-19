package net.emaze.dysfunctional.strings.lexcasts;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;

public class BooleanTryParser implements Function<String, Maybe<Boolean>> {

    private static final List<String> ACCEPTED_VALUES = Arrays.asList("true", "false");

    @Override
    public Maybe<Boolean> apply(String parsee) {
        if (parsee == null || !ACCEPTED_VALUES.contains(parsee.toLowerCase())) {
            return Maybe.nothing();
        }
        return Maybe.just(Boolean.parseBoolean(parsee));
    }
}
