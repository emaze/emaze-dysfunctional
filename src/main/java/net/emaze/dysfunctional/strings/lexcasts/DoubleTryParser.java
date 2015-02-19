package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;

/**
 *
 * @author rferranti
 */
public class DoubleTryParser implements Function<String, Optional<Double>> {

    @Override
    public Optional<Double> apply(String parsee) {
        if (parsee == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Double.parseDouble(parsee));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
