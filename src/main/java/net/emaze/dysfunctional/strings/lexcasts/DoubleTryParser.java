package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class DoubleTryParser implements Function<String, Maybe<Double>> {

    @Override
    public Maybe<Double> apply(String parsee) {
        if (parsee == null) {
            return Maybe.nothing();
        }
        try {
            return Maybe.just(Double.parseDouble(parsee));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
