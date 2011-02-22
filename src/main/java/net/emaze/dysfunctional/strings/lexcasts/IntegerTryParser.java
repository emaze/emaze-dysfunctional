package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class IntegerTryParser implements Delegate<Maybe<Integer>, String> {

    private final int radix;

    public IntegerTryParser(int radix) {
        this.radix = radix;
    }

    @Override
    public Maybe<Integer> perform(String parsee) {
        try {
            return Maybe.just(Integer.parseInt(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
