package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class ByteParser implements Function<String, Byte> {

    private final int radix;

    public ByteParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        this.radix = radix;
    }

    @Override
    public Byte apply(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Byte.parseByte(parsee, radix);
    }
}
