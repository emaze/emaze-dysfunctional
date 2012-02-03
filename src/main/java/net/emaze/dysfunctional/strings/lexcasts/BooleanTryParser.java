package net.emaze.dysfunctional.strings.lexcasts;

import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

public class BooleanTryParser implements Delegate<Maybe<Boolean>, String> {

    private static final List<String> ACCEPTED_VALUES = Arrays.asList("true", "false");

    @Override
    public Maybe<Boolean> perform(String parsee) {
        if (parsee == null || !ACCEPTED_VALUES.contains(parsee.toLowerCase())) {
            return Maybe.nothing();
        }
        return Maybe.just(Boolean.parseBoolean(parsee));
    }
}
