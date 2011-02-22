package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class ShortTryParser implements Delegate<Maybe<Short>, String> {

    private final int radix;

    public ShortTryParser(int radix) {
        this.radix = radix;
    }

    @Override
    public Maybe<Short> perform(String parsee) {
        try {
            return Maybe.just(Short.parseShort(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
