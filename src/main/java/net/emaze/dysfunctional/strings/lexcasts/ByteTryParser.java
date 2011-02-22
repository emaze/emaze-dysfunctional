package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class ByteTryParser implements Delegate<Maybe<Byte>, String> {

    private final int radix;

    public ByteTryParser(int radix) {
        this.radix = radix;
    }

    @Override
    public Maybe<Byte> perform(String parsee) {
        try {
            return Maybe.just(Byte.parseByte(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
