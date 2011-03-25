package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class FloatTryParser implements Delegate<Maybe<Float>, String> {

    @Override
    public Maybe<Float> perform(String parsee) {
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
