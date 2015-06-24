package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.Optional;

/**
 *
 * @author rferranti
 */
public class ByteTryParser implements Function<String, Optional<Byte>> {

    private final int radix;

    public ByteTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Optional<Byte> apply(String parsee) {
        try {
            return Optional.of(Byte.parseByte(parsee, radix));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
