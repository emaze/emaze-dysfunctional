package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class LongTryParser implements Delegate<Maybe<Long>, String> {

    private final int radix;

    public LongTryParser(int radix) {
        this.radix = radix;
    }

    @Override
    public Maybe<Long> perform(String parsee) {
        try {
            return Maybe.just(Long.parseLong(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
