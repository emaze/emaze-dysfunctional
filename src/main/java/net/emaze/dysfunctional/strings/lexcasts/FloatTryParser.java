package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;

/**
 *
 * @author rferranti
 */
public class FloatTryParser implements Function<String, Optional<Float>> {

    @Override
    public Optional<Float> apply(String parsee) {
        if (parsee == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Float.parseFloat(parsee));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
