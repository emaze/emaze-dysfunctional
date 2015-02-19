package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class FloatTryParser implements Function<String, Maybe<Float>> {

    @Override
    public Maybe<Float> apply(String parsee) {
        if (parsee == null) {
            return Maybe.nothing();
        }
        try {
            return Maybe.just(Float.parseFloat(parsee));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
